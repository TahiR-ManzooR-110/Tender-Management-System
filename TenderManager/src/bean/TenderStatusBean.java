package bean;

public class TenderStatusBean {
	
	private int bid;
	private int vid;
	private int tid;
	private String status;
	
	public TenderStatusBean() {
		// TODO Auto-generated constructor stub
	}

	public TenderStatusBean(int bid, int vid, int tid, String status) {
		super();
		this.bid = bid;
		this.vid = vid;
		this.tid = tid;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "TenderStatus [bid=" + bid + ", vid=" + vid + ", tid=" + tid + ", status=" + status + "]";
	}
	
	

}
