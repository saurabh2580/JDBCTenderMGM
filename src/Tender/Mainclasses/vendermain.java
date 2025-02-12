package Tender.Mainclasses;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

import Tender.Exception.AdminException;
import Tender.Exception.VenderException;
import Tender.Model.Admin;
import Tender.Model.Bid;
import Tender.Model.Tender;
import Tender.Model.Vender;
import Tender.dao.adminDao;
import Tender.dao.venderDao;
import Tender.daoImpl.VenderDaoimpl;
import Tender.daoImpl.adminDaoImpl;

public class vendermain {

	public static venderDao vdao = new VenderDaoimpl();
	public static Scanner sc = new Scanner(System.in);

	public static void Vendertask() {

		System.out.println(" Login into your account.");

		System.out.println("Vender enter your user name ");
		String username = sc.next();

		System.out.println("Enter Your Password");
		String password = sc.next();

		Vender ans;
		try {
			ans = vdao.LoginVender(username, password);

			System.out.println(ans);

			if (ans != null) {
				System.out.println("Welcome " + username);

				while (true) {
					System.out.println("Enter Your Choice");
					System.out.println("-----------------------------");
					System.out.println("  2 View all the current Tenders.");
					System.out.println("  3 Place a Bid against a Tender.");
					System.out.println("  4 View status of a Bid(Whether Selected or Not)");
					System.out.println("  5 View his own Bid History.");

					System.out.println("  6 to exit.");

					int VednerChoice = sc.nextInt();
					switch (VednerChoice) {
					case 2:

						try {
							List<Tender> tenders = vdao.getAllTender();

							for (Tender tender : tenders) {
								System.out.println(tender);
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
						break;

					case 3:
						System.out.println("enter id of tender  for which you want place Bid");
						int tid = sc.nextInt();
						System.out.println("enter your Bid  id");
						int bid = sc.nextInt();
						String res = vdao.bid_tender(tid, bid);
						System.out.println(res);
						break;

					case 4:
						System.out.println("Status  of  your  aLL bid ");

						int venderId = ans.getVid();

						// adminDao Dao = new adminDaoImpl() ;
						Map<Integer, Boolean> hm = vdao.bidStatus(venderId);
						for (Map.Entry<Integer, Boolean> me : hm.entrySet()) {

							// Printing keys
							System.out.print(me.getKey() + ":");
							System.out.println(me.getValue());
						}
//						
						break;

					case 5:
						try {

							int vid = ans.getVid();
							System.out.println("your all bid ");
							List<Bid> bids = vdao.hisOwnBidHistory(vid);

							for (Bid b : bids) {
								System.out.println(b);
							}
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						break;
					case 6:

						System.out.println("you successfully  logout ");
						return;

					}
				}
			} else {
				System.out.println("wrong user name and password ");
			}
		} catch (VenderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
