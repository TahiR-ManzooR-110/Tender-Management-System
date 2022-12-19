package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import bean.TenderBean;
import bean.TenderStatusBean;
import exception.TenderException;
import utility.DBUtil;

public class TenderDaoImpl implements TenderDao{

	@Override
	public String CreateNewTendor(TenderBean tender) {
		String message = "Failed to create a Tender";
		
		try (Connection conn =DBUtil.provideConnection()){
			
			PreparedStatement ps =conn.prepareStatement("INSERT INTO tender values(?,?,?,?,?,?)");

			ps.setInt(1, tender.getTid());
			ps.setString(2, tender.getTname());
			ps.setString(3, tender.getTtype());
			ps.setString(4, tender.getTdescription());
			ps.setString(5, tender.getTdeadline());
			ps.setString(6, tender.getTlocation());
			
			int x =ps.executeUpdate();
			
			if(x>0){
				message = "Tender has been created succesfully with Tender id: " + tender.getTid();
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			e.getMessage();
		}
		return message;
	}

	@Override
	public List<TenderBean> getTenderDetailsById(int tid) throws TenderException {
		List<TenderBean> tender = new ArrayList<>();
		
		try(Connection conn =DBUtil.provideConnection()) {
			
			PreparedStatement ps =conn.prepareStatement("SELECT * FROM tender WHERE tid = ?");
			
			ps.setInt(1, tid);
			
			ResultSet rs =ps.executeQuery();
			
			if(rs.next()) {
				
				int tenderid=rs.getInt("tid");
				String tname=rs.getString("tname");
				String ttype=rs.getString("ttype");
				String tdescription=rs.getString("tdescription");
				String tdeadline=rs.getString("tdeadline");
				String tlocation=rs.getString("tlocation");
				
				TenderBean tb = new TenderBean(tenderid,tname,ttype,tdescription,tdeadline,tlocation);
				tender.add(tb);
			}
			
		}catch (SQLException e) {
			throw new TenderException(e.getMessage());
			
		}
		return tender;
	}

	@Override
	public List<TenderBean> getAllTenders() throws TenderException {
		List<TenderBean> tenders  = new ArrayList<TenderBean>();
		
		try (Connection conn =DBUtil.provideConnection()){
			PreparedStatement ps =conn.prepareStatement("SELECT * FROM tender");
			ResultSet rs =ps.executeQuery();
			while(rs.next()){
				TenderBean tender=new TenderBean();
				
				tender.setTid(rs.getInt("tid"));
				tender.setTname(rs.getString("tname"));
				tender.setTtype(rs.getString("ttype"));
				tender.setTdescription(rs.getString("tdescription"));
				tender.setTdeadline(rs.getString("tdeadline"));
				tender.setTlocation(rs.getString("tlocation"));
	
				tenders.add(tender);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new TenderException(e.getMessage());
		}
		return tenders;
	}

	@Override
	public String assignTender(int vid, int tid, int bid) throws TenderException {
		String status = "Tender has not been assigned";
		try (Connection con = DBUtil.provideConnection()){
			PreparedStatement  ps1 = con.prepareStatement("SELECT * FROM tenderstatus WHERE tid=?");
			ps1.setInt(1, tid);
			
			ResultSet rs = ps1.executeQuery();
			
			if(rs.next()){
				
				status = "Tender is already assigned to vendor "+rs.getInt("vid");
			}else{
				PreparedStatement ps = con.prepareStatement("insert into tenderstatus values(?,?,?,?)");
				
				ps.setInt(1, bid);
				ps.setInt(2, vid);
				ps.setInt(3, tid);
				ps.setString(4, "Assigned");

				int x = ps.executeUpdate();
				if(x>0){
					status = "Tender with id "+tid+" has been assigned to vendor with id "+vid;
				}
			}
		} catch (SQLException e) {
			throw new TenderException(e.getMessage());
		}
		return status;
	}

	@Override
	public List<TenderStatusBean> getAllAssignedTenders() throws TenderException {
		List<TenderStatusBean> status = new ArrayList<TenderStatusBean>();
		
		try(Connection conn = DBUtil.provideConnection()) {
		
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM tenderstatus");
		
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				
				TenderStatusBean statuss = new TenderStatusBean(rs.getInt("bid"),rs.getInt("vid"), rs.getInt("tid"),rs.getString("status"));
				
				status.add(statuss);
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
			throw new TenderException(e.getMessage());
		}
		return status;
	}

	@Override
	public boolean removeTendor(int tid) throws TenderException {
		boolean flag = false ;
		try (Connection conn =DBUtil.provideConnection()){
			
			PreparedStatement ps =conn.prepareStatement("delete FROM tender WHERE tid = ?");
			ps.setInt(1, tid);
			
			int x =ps.executeUpdate();
			
			if(x>0) {
				flag = true;
			}
		} catch (SQLException e) {
			throw new TenderException(e.getMessage());
		}
		
		return flag;
	}

	@Override
	public String updateTender(TenderBean tender) throws TenderException {
		String status = "Tender has not been updated";
		
		try(Connection con = DBUtil.provideConnection()) {
			PreparedStatement ps = con.prepareStatement("UPDATE tender SET tname=?,ttype=?,tdescription=?,tdeadline=?,tlocation=? WHERE tid=?");
			
			ps.setString(1, tender.getTname());
			ps.setString(2, tender.getTtype());
			ps.setString(3, tender.getTdescription());
			ps.setString(4,tender.getTdeadline());
			ps.setString(5, tender.getTlocation());
			ps.setInt(6, tender.getTid());
			int x=ps.executeUpdate();
			if(x>0)
				status="Tender has been updated successfully";
			
		} catch (SQLException e) {
			e.printStackTrace();
			e.getMessage();
		}
		return status;
	}

}
