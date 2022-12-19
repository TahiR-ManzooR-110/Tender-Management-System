package bean;

public class VendorBean {
	
	private int vid;
	private String vname;
	private String vemail;	
	private String vpassword;
	private String company;
	private String address;
	
	public VendorBean() {
		// TODO Auto-generated constructor stub
	}

	public VendorBean(int vid, String vname, String vemail, String vpassword, String company, String address) {
		super();
		this.vid = vid;
		this.vname = vname;
		this.vemail = vemail;
		this.vpassword = vpassword;
		this.company = company;
		this.address = address;
	}

	public int getVid() {
		return vid;
	}

	public void setVid(int vid) {
		this.vid = vid;
	}

	public String getVname() {
		return vname;
	}

	public void setVname(String vname) {
		this.vname = vname;
	}

	public String getVemail() {
		return vemail;
	}

	public void setVemail(String vemail) {
		this.vemail = vemail;
	}

	public String getVpassword() {
		return vpassword;
	}

	public void setVpassword(String vpassword) {
		this.vpassword = vpassword;
	}

	public String getcompany() {
		return company;
	}

	public void setcompany(String company) {
		this.company = company;
	}

	public String getaddress() {
		return address;
	}

	public void setaddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "VendorBean [vid=" + vid + ", vname=" + vname + ", vemail=" + vemail + ", vpassword=" + vpassword
				+ ", company=" + company + ", address=" + address + "]";
	}
}
