package bean;

public class TenderBean {
	
	private int tid;
	private String tname;
	private String ttype;
	private String tdescription;
	private String tdeadline;
	private String tlocation;
	
	
	public TenderBean() {
		// TODO Auto-generated constructor stub
	}


	public TenderBean(int tid, String tname, String ttype, String tdescription, String tdeadline, String tlocation) {
		super();
		this.tid = tid;
		this.tname = tname;
		this.ttype = ttype;
		this.tdescription = tdescription;
		this.tdeadline = tdeadline;
		this.tlocation = tlocation;
	}


	public int getTid() {
		return tid;
	}


	public void setTid(int tid) {
		this.tid = tid;
	}


	public String getTname() {
		return tname;
	}


	public void setTname(String tname) {
		this.tname = tname;
	}


	public String getTtype() {
		return ttype;
	}


	public void setTtype(String ttype) {
		this.ttype = ttype;
	}


	public String getTdescription() {
		return tdescription;
	}


	public void setTdescription(String tdescription) {
		this.tdescription = tdescription;
	}


	public String getTdeadline() {
		return tdeadline;
	}


	public void setTdeadline(String tdeadline) {
		this.tdeadline = tdeadline;
	}


	public String getTlocation() {
		return tlocation;
	}


	public void setTlocation(String tlocation) {
		this.tlocation = tlocation;
	}


	@Override
	public String toString() {
		return "TenderBean [tid=" + tid + ", tname=" + tname + ", ttype=" + ttype + ", tdescription=" + tdescription
				+ ", tdeadline=" + tdeadline + ", tlocation=" + tlocation + "]";
	}
}
