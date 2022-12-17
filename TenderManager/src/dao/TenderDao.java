package dao;

import java.util.List;
import bean.TenderBean;
import bean.TenderStatusBean;
import exception.TenderException;

public interface TenderDao {
	
	public String CreateNewTendor(TenderBean tender);
	
	public List<TenderBean> getTenderDetailsById(int tid)throws TenderException;

	public List<TenderBean> getAllTenders()throws TenderException;
	
	public String assignTender(int vid, int tid,int bid)throws TenderException;

	public List<TenderStatusBean> getAllAssignedTenders() throws TenderException;
	
	public boolean removeTendor(int tid)throws TenderException;
	
	public String updateTender(TenderBean tender)throws TenderException;
	
}