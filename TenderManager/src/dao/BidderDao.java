package dao;

import java.util.List;
import bean.BidderBean;
import exception.BidderException;

public interface BidderDao {

	public String acceptBid(int bid, int tid, int vid)throws BidderException;
	
	public String rejectBid(int bid)throws BidderException;
	
	public String bidOnTendor(int bid, int vid , int tid , double bidamount)throws BidderException;
	
	public List<BidderBean> getAllBidsOnTenders(int tid)throws BidderException;
	
	public List<BidderBean> getAllBidsOfVendors(int vid)throws BidderException;
	
}

