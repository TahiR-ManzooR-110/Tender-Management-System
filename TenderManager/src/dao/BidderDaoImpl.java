package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.BidderBean;
import exception.BidderException;
import exception.TenderException;
import utility.DBUtil;

public class BidderDaoImpl implements BidderDao {

	@Override
	public String acceptBid(int bid, int tid, int vid) throws BidderException, TenderException {
		String message = "Bid has not been accepted";
		
		try(Connection conn =DBUtil.provideConnection()) {
			
			PreparedStatement ps =conn.prepareStatement("SELECT * FROM tenderstatus WHERE tid = ?");
			ps.setInt(1, tid);
			
			ResultSet rs =ps.executeQuery();
			
			if(rs.next()) {
				message = "Tender is already assigned";
			}else {
				PreparedStatement ps2 =conn.prepareStatement("UPDATE Bidder SET status=? WHERE bid = ? AND status = ?");
				
				ps2.setString(1, "Assigned");
				ps2.setInt(2, bid);
				ps2.setString(3, "Pending");

				int x =ps2.executeUpdate();
				
				if(x>0) {
					message= "Bid has been accepted successfully";
					TenderDao dao = new TenderDaoImpl();
					message = message + dao.assignTender(vid, tid, bid);
				}
			}
			
		} catch (SQLException e) {
			throw new BidderException(e.getMessage());
		}
		return message;
	}

	@Override
	public String rejectBid(int bid) throws BidderException {
		String message = "Bid has not been rejected";
		
		try(Connection conn = DBUtil.provideConnection()) {
			
			PreparedStatement ps =conn.prepareStatement("UPDATE bidder SET status = ? WHERE bid = ? AND status=?");
			ps.setString(1,"Rejected");
			ps.setInt(2,bid);
			ps.setString(3,"Pending");
			
			int x =ps.executeUpdate();
			
			if(x>0) {
				message = "Bid has been rejected succesfully";	
			}
		} catch (SQLException e) {
			// TODO: handle exception
			throw new BidderException(e.getMessage());
		}
		return message ;
	}

	@Override
	public String bidTendor(int bid, int vid, int tid, double bidamount) throws BidderException {
		String message = "Bidding has been failed";
		
		try(Connection conn =DBUtil.provideConnection()) {
			
			String status = "Pending";
			
			PreparedStatement ps =conn.prepareStatement("INSERT INTO bidder values(?,?,?,?,?)");
			
			ps.setInt(1, bid);
			ps.setInt(2, vid);
			ps.setInt(3, tid);
			ps.setDouble(4, bidamount);
			ps.setString(5, status);
			
			int x =ps.executeUpdate();
			if(x>0) {
				message = "Bidding has been done succesfully for the tender";
			}	
		} catch (SQLException e) {
			throw new BidderException(e.getMessage());
		}
		return message;
	}

	@Override
	public List<BidderBean> getAllBidsOnTendor(int tid) throws BidderException {
		List<BidderBean> bids = new ArrayList<>();
		
		try(Connection conn =DBUtil.provideConnection()) {
			
			PreparedStatement ps =conn.prepareStatement("SELECT * FROM bidder WHERE tid=?");
			ps.setInt(1, tid);
			
			ResultSet rs =ps.executeQuery();
			
			while(rs.next()) {
				BidderBean bb = new BidderBean(rs.getInt("bid"), rs.getInt("vid"),rs.getInt("tid"), rs.getDouble("bidamount"), rs.getString("status"));
				bids.add(bb);
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
			throw new BidderException(e.getMessage());
		}
		return bids;
	}

	@Override
	public List<BidderBean> getAllBidsOfVendor(int vid) throws BidderException {
		List<BidderBean> bids = new ArrayList<BidderBean>();
		
		try(Connection conn =DBUtil.provideConnection()) {
			
			PreparedStatement ps =conn.prepareStatement("SELECT * FROM bidder WHERE vid=?");
			ps.setInt(1, vid);
			
			ResultSet rs =ps.executeQuery();
			
			while(rs.next()) {
				BidderBean bb = new BidderBean(rs.getInt("bid"), rs.getInt("vid"), rs.getInt("tid"), rs.getDouble("bidamount"), rs.getString("status"));
				bids.add(bb);
			}
			
		} catch (SQLException e) {
			throw new BidderException(e.getMessage());
		}
		return bids;
	}

}
