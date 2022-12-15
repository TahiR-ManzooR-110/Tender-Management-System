package bean;

public class VendorBean {
	
	private int vid;
	private String vname;
	private String vemail;	
	private String vpassword;
	private String vcompany;
	private String vaddress;
	
	public VendorBean() {
		// TODO Auto-generated constructor stub
	}

	public VendorBean(int vid, String vname, String vemail, String vpassword, String vcompany, String vaddress) {
		super();
		this.vid = vid;
		this.vname = vname;
		this.vemail = vemail;
		this.vpassword = vpassword;
		this.vcompany = vcompany;
		this.vaddress = vaddress;
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

	public String getVcompany() {
		return vcompany;
	}

	public void setVcompany(String vcompany) {
		this.vcompany = vcompany;
	}

	public String getVaddress() {
		return vaddress;
	}

	public void setVaddress(String vaddress) {
		this.vaddress = vaddress;
	}

	@Override
	public String toString() {
		return "VendorBean [vid=" + vid + ", vname=" + vname + ", vemail=" + vemail + ", vpassword=" + vpassword
				+ ", vcompany=" + vcompany + ", vaddress=" + vaddress + "]";
	}
}
