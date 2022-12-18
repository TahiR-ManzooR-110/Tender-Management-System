package dao;

import java.util.List;
import bean.VendorBean;
import exception.VendorException;

public interface VendorDao {

	public String registerNewVendor(VendorBean vendor) throws VendorException;
	
	public List<VendorBean> viewAllVendors()throws VendorException;
	
}