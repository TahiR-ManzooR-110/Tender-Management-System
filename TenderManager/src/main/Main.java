package main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Scanner;

import bean.BidderBean;
import bean.TenderBean;
import bean.VendorBean;
import dao.BidderDao;
import dao.BidderDaoImpl;
import dao.TenderDao;
import dao.TenderDaoImpl;
import dao.VendorDao;
import dao.VendorDaoImpl;
import exception.AdminException;
import utility.DBUtil;

public class Main {
	public static void admin() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter your Admin Username");
		String username = sc.next();
		System.out.println("Please enter your Admin Password");
		String password = sc.next();
		try (Connection conn = DBUtil.provideConnection()) {
			PreparedStatement ps = conn.prepareStatement("SELECT password FROM admin WHERE username = ?");
			ps.setString(1, username);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				String pass = rs.getString("password");

				if (pass.equals(password)) {

					System.out.println("Welcome " + username);

					while (true) {

						System.out.println("Select any one of the following: ");

						System.out.println("1. Register new Vendor.");
						System.out.println("2. View all the vendors.");
						System.out.println("3. Create new tenders.");
						System.out.println("4. View All the Tenders.");
						System.out.println("5. View All the Bids of a tender.");
						System.out.println("6. Assign tender to a vendor.");
						System.out.println("7. Remove tender from list");
						System.out.println("8. Update tender");
						System.out.println("9. Return back to admin menu");
						System.out.println("10.Return back to main menu");

						int k = sc.nextInt();

						switch (k) {
							case 1: {

								System.out.println("WELCOME TO REGISTER VENDOR");

								System.out.println("Enter Vendor id: ");
								int vid = sc.nextInt();

								System.out.println("Enter Vendor name: ");
								String vname = sc.next();

								System.out.println("Enter Vendor email: ");
								String vemail = sc.next();

								System.out.println("Enter Vendor password: ");
								String vpassword = sc.next();

								System.out.println("Enter Vendor Company name: ");
								String company = sc.next();

								System.out.println("Enter Vendor address: ");
								String address = sc.next();

								VendorBean vb = new VendorBean(vid, vname, vemail, vpassword, company, address);

								VendorDao dao = new VendorDaoImpl();

								String status = dao.registerNewVendor(vb);

								System.out.println(status);

								break;
							}
							case 2: {

								System.out.println("LIST OF VENDORS:");

								VendorDao dao = new VendorDaoImpl();
								List<VendorBean> list = dao.viewAllVendors();

								for (VendorBean v : list) {
									System.out.println("==============================================");
									System.out.println("Vendor id: "+v.getVid());
									System.out.println("Vendor name: "+v.getVname());
									System.out.println("Vendor email: "+v.getVemail());
									System.out.println("Vendor password: "+v.getVpassword());
									System.out.println("Company: "+v.getcompany());
									System.out.println("Address: "+v.getaddress());
									
									System.out.println("================================================");
								}
								break;
							}
							case 3: {

								System.out.println("Enter tender id: ");
								int tid = sc.nextInt();

								System.out.println("Enter tender name: ");
								String tname = sc.next();

								System.out.println("Enter tender type");
								String ttype = sc.next();

								System.out.println("Enter description of the tender");
								String tdesc = sc.next();

								System.out.println("Enter tender deadline date");
								String tdeadline = sc.next();

								System.out.println("Enter tender location");
								String tlocation = sc.next();

								TenderBean vb = new TenderBean(tid, tname, ttype, tdesc, tdeadline, tlocation);

								TenderDao dao = new TenderDaoImpl();

								String status = dao.CreateNewTendor(vb);

								System.out.println(status);
								break;
							}
							case 4: {
								System.out.println("LIST OF TENDERS:");

								TenderDao dao = new TenderDaoImpl();
								List<TenderBean> list = dao.getAllTenders();

								for (TenderBean t : list) {
									System.out.println("==============================================");
									System.out.println("Tender id: "+t.getTid());
									System.out.println("Tender name: "+t.getTname());
									System.out.println("Tender type: "+t.getTtype());
									System.out.println("Tender description: "+t.getTdescription());
									System.out.println("Tender deadline: "+t.getTdeadline());
									System.out.println("Tender location: "+t.getTlocation());
									System.out.println("==============================================");
								}
								break;
							}
							case 5: {

								System.out.println("Enter Tender id: ");

								int tid = sc.nextInt();

								BidderDao dao = new BidderDaoImpl();

								List<BidderBean> list = dao.getAllBidsOnTendor(tid);

								if (list.size() == 0) {
									System.out.println("Tendor is not currently up for bid.");
								} else {

									for (BidderBean t : list) {
										System.out.println(t);
									}
								}
								break;
							}

							case 6: {

								System.out.println("ASSIGN THE TENDER TO A VENDOR: ");

								System.out.println("ENTER VENDOR ID");
								int vid = sc.nextInt();

								System.out.println("ENTER TENDOR ID");
								int tid = sc.nextInt();

								System.out.println("ENTER BIDDER ID");
								int bid = sc.nextInt();

								TenderDao dao = new TenderDaoImpl();
								String message = dao.assignTender(tid, vid, bid);

								System.out.println(message);
								break;
							}
							case 7: {

								System.out.println("Enter Tender id : ");

								int tid = sc.nextInt();

								TenderDao dao = new TenderDaoImpl();
								if (dao.removeTendor(tid)) {

									System.out.println("Tender removed Sucessfully");
								} else {
									System.out.println("Failed: Tender does not removed");

								}
								break;
							}

							case 8: {

								System.out.println("Enter tender id");
								int tid = sc.nextInt();

								System.out.println("Enter tender name");
								String tname = sc.next();

								System.out.println("Enter tender type");
								String ttype = sc.next();

								System.out.println("Enter description of the tender");
								String tdescription = sc.next();

								System.out.println("Enter tender deadline date");
								String tdeadline = sc.next();

								System.out.println("Enter tender location");
								String tlocation = sc.next();

								TenderBean vb = new TenderBean(tid, tname, ttype, tdescription, tdeadline, tlocation);

								TenderDao dao = new TenderDaoImpl();

								String message = dao.updateTender(vb);

								System.out.println(message);

								break;
							}

							case 9: {
								admin();
								break;

							}
							case 10: {

								main(null);
								break;

							}

							default:
								System.out.println("Enter valid choice");
						}
					}
				} else {
					throw new AdminException("Invalid password or username");
				}
			} else {
				throw new AdminException("Admin does not exist with these credentials..");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void vendor() {

		Scanner sc = new Scanner(System.in);

		System.out.println("Please enter your username");

		String uname = sc.next();

		System.out.println("Please enter your Password");

		String upass = sc.next();

		try (Connection conn = DBUtil.provideConnection()) {

			PreparedStatement ps = conn.prepareStatement("SELECT * FROM vendor WHERE vname = ? and vpassword = ?");
			ps.setString(1, uname);
			ps.setString(2, upass);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				
				String name = rs.getString("vname");
				String pass = rs.getString("vpassword");
				
				if ((pass.equals(upass)) && (name.equals(uname))) {

					System.out.println("Welcome "+ name);

					while (true) {

						System.out.println("Select any one of the following: ");
						System.out.println("1. View all the current Tenders");
						System.out.println("2. Place a Bid against a Tender.");
						System.out.println("3. View status of a Bid(Whether Selected or Not).");
						System.out.println("4. View your own Bid History");
						System.out.println("5. Return to back vendor menu");
						System.out.println("6. Return to back main menu");

						int k = sc.nextInt();

						switch (k) {
							case 1: {

								System.out.println("VIEW ALL THE CURRENT TENDERS");

								TenderDao dao = new TenderDaoImpl();
								List<TenderBean> list = dao.getAllTenders();

								for (TenderBean t : list) {
									System.out.println("==============================================");
									System.out.println("Tender id: "+t.getTid());
									System.out.println("Tender name: "+t.getTname());
									System.out.println("Tender type: "+t.getTtype());
									System.out.println("Tender description: "+t.getTdescription());
									System.out.println("Tender deadline: "+t.getTdeadline());
									System.out.println("Tender location: "+t.getTlocation());
									System.out.println("==============================================");
								}
								break;

							}

							case 2: {

								System.out.println("PLACING BID AGAINST TENDER");

								System.out.println("ENTER BIDDER ID: ");
								int bid = sc.nextInt();

								System.out.println("ENTER YOUR VENDOR ID: ");
								int vid = sc.nextInt();

								System.out.println("ENTER TENDER ID YOU WANT TO BID ON: ");
								int tid = sc.nextInt();

								System.out.println("ENTER YOUR BIDDER AMOUNT: ");
								double bidamount = sc.nextDouble();

								BidderDao dao = new BidderDaoImpl();

								String message = dao.bidTendor(bid, vid, tid, bidamount);

								System.out.println(message);

								break;

							}

							case 3: {

								System.out.println("VIEW STATUS OF A BID(WHETHER SELECTED OR NOT).");

								System.out.println("ENTER BIDDER ID: ");
								int bid = sc.nextInt();

								System.out.println("ENTER YOUR TENDER ID: ");
								int tid = sc.nextInt();

								System.out.println("ENTER YOUR VENDOR ID: ");
								int vid = sc.nextInt();

								System.out.println("BID ACCEPTANCE STATUS");
								BidderDao dao = new BidderDaoImpl();
								String message = dao.acceptBid(bid, tid, vid);
								System.out.println(message);
								System.out.println();
								System.out.println("BID REJECTION STATUS");
								String status = dao.rejectBid(bid);
								System.out.println(status);
								System.out.println();
								break;

							}

							case 4: {

								System.out.println("VIEW YOUR OWN BID HISTORY");

								System.out.println("ENTER YOUR VENDOR ID: ");
								int vid = sc.nextInt();

								BidderDao dao = new BidderDaoImpl();

								List<BidderBean> list = dao.getAllBidsOfVendor(vid);

								for (BidderBean t : list) {
									System.out.println(t);
								}
								break;
							}

							case 5: {

								vendor();
								break;

							}
							case 6: {

								main(null);
								break;

							}

							default:

								System.out.println("Enter valid choice");
								vendor();
						}
					}
				} else {
					throw new AdminException("Invalid password or username");
				}
			} else {
				throw new AdminException("Vendor does not exist with these credentials...");
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		System.out.println("WELCOME TO TENDER MANAGEMENT SYSTEM");
		System.out.println("1. Admin");
		System.out.println("2. Vendor");
		System.out.println("Enter 1 to select Admin or Enter 2 to select Vendor");
		int n = sc.nextInt();
		if (n == 1) {
			admin();
			main(null);
		} else if (n == 2) {
			 vendor();
			main(null);
		} else {
			System.out.println("Something went wrong!");
		}
	}
}
