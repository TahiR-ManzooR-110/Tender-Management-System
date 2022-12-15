package bean;

public class BidderBean {
	
	private int bid;
	private int vid;
	private int tid;
	private double bidAmount;
	private String status;
	
	public BidderBean() {
		// TODO Auto-generated constructor stub
	}
	
	public BidderBean(int bid, int vid, int tid, double bidAmount, String status) {
		super();
		this.bid = bid;
		this.vid = vid;
		this.tid = tid;
		this.bidAmount = bidAmount;
		this.status = status;
	}

	public int getBid() {
		return bid;
	}

	public void setBid(int bid) {
		this.bid = bid;
	}

	public int getVid() {
		return vid;
	}

	public void setVid(int vid) {
		this.vid = vid;
	}

	public int getTid() {
		return tid;
	}

	public void setTid(int tid) {
		this.tid = tid;
	}

	public double getBidAmount() {
		return bidAmount;
	}

	public void setBidAmount(double bidAmount) {
		this.bidAmount = bidAmount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "BidderBean [bid=" + bid + ", vid=" + vid + ", tid=" + tid + ", bidAmount=" + bidAmount + ", status="
				+ status + "]";
	}
}
