package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.VendorBean;
import exception.VendorException;
import utility.DBUtil;

public class VendorDaoImpl implements VendorDao{
	@Override
	public String registerNewVendor(VendorBean vendor) throws VendorException {
		String message="Registration Failed";
		
		try(Connection conn =DBUtil.provideConnection()) {
			PreparedStatement ps1 =conn.prepareStatement("SELECT * FROM vendor WHERE vemail=?");
			ps1.setString(1, vendor.getVemail());
			ResultSet rs=ps1.executeQuery();
			
			if(rs.next()) {
				message = "Email ID already exists";
			}else {
				try {
					PreparedStatement ps2=conn.prepareStatement("INSERT INTO vendor values(?,?,?,?,?,?)");
					
					ps2.setInt(1, vendor.getVid());
					ps2.setString(2, vendor.getVname());
					ps2.setString(3, vendor.getVemail());
					ps2.setString(4, vendor.getVpassword());
					ps2.setString(5, vendor.getcompany());
					ps2.setString(6, vendor.getaddress());
					
					int x = ps2.executeUpdate();
					
					if(x>0) {
						message = x+" Vendor registered sucessfully";
					}
				}catch(SQLException e) {
					e.printStackTrace();
					e.getMessage();
				}
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			e.getMessage();
		}
		
		return message;
	}

	@Override
	public List<VendorBean> viewAllVendors() throws VendorException {
		List<VendorBean> vendor = new ArrayList<>();
		
		try (Connection conn =DBUtil.provideConnection()){
			
			PreparedStatement ps =conn.prepareStatement("SELECT * FROM vendor");
			
			ResultSet rs =ps.executeQuery();
			
			while(rs.next()) {
				VendorBean v = new VendorBean(rs.getInt("vid"), rs.getString("vname"), rs.getString("vemail"), rs.getString("vpassword"),  rs.getString("company"), rs.getString("address"));
				vendor.add(v);
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
			throw new VendorException(e.getMessage());
		}
		
		if(vendor.size()==0) {
			throw new VendorException("vendor does not exist");
		}
		
		return vendor;
	}
}
