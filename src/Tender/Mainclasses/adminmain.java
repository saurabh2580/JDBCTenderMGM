package Tender.Mainclasses;

import java.util.List;
import java.util.Scanner;

import Tender.Exception.AdminException;
import Tender.Exception.VenderException;
import Tender.Model.Admin;
import Tender.Model.Tender;
import Tender.Model.Vender;
import Tender.dao.adminDao;
import Tender.daoImpl.adminDaoImpl;

public class adminmain {
	
//	The Role of Administrator is: 
//		1. Login with his account.
//		2. Register new Vendor. (assign a new username and password to him)
//		3. View all the vendors.
//		4. Create new tenders.
//		5. View All the Tenders.
//		6. View All the Bids of a tender.
//		7. Assign tender to a vendor.
	
	
	public static adminDao dao = new adminDaoImpl();
	public static Scanner sc =  new Scanner (System.in);

	public static void Adminstuff() {

		while(true){
			System.out.println("Enter Your Choice");
			System.out.println("---->  2  for  Register new Vendor. (assign a new username and password to him)");
			System.out.println("---->  3 View all the vendors");
			System.out.println("---->  4 Create new tenders.");
			System.out.println("---->  5 View All the Tenders.");
			System.out.println("---->  6 Assign tender to a vendor .");
			
			System.out.println("---->  7 to exit.");	
		
		int AdminChoice = sc.nextInt();
		switch(AdminChoice) {
		case 2:
		System.out.println("Enter vender Id");
		int id  = sc .nextInt();
		System.out.println("Enter vender name");
		String name = sc.next();
		System.out.println("Assign vender username ");
		String Vusername = sc.next();
		System.out.println("Assign vender password");
		String Vpassword = sc.next();
		
		Vender vender = new Vender();
		vender.setVid(id);
		vender.setVname(name);
		vender.setUsername(Vusername);
		vender.setPassword(Vpassword);
		
			String message;
			try {
				message = dao.registerVender(vender);
				System.out.println(message);
			} catch (AdminException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		break;
		
	
		case 3:
			List<Vender> venders;
			try {
				venders = dao.getAllVender();
				for (Vender vend : venders) {
					System.out.println(vend);
				}
			} catch (VenderException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			
			break;
		case 4:
			System.out.println("Enter tender ID");

		    int TenderId = sc.nextInt();
		    
		    System.out.println("Enter tender Name");
		    String tenderName = sc.next();
		    
		 //   adminDao Dao = new adminDaoImpl() ;
		    String result = dao.CreateNewTender(TenderId, tenderName);
		    System.out.println(result);
			
			break;
			
		case 5:
			try {
				List <Tender> tenders =	dao.getAllTender();
				
				for (Tender tender : tenders) {
					System.out.println(tender);
				}
			} catch (Exception e){
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
				
			break;
		case 6:
			System.out.println("Enter valid Tender id ");
			 int Tid = sc.nextInt();

			 System.out.println("Enter valid vender id ");
			 int vid = sc.nextInt();
			  
			 String re = dao.AssignTenderToAVendor(Tid, vid);
			 
			 System.out.println(re);
			 
			 break;
		case 7:
			
			return;
			
	}
}
	}
	
	public static void Admintask() {
		//System.out.println("SMART TENDER MANAGER ");
		
		
	System.out.println(" Login into your account.");
	System.out.println("--------------------------------------");	
	
	System.out.println("Admin enter your user name ");
	 String username = sc.next();
	 
	 System.out.println("Enter Your Password");
	 String password = sc.next();
	 
	Admin ans ;
	
	try {
		ans = dao.loginIntoAdmin( username , password);
	
		//Adminstuff();
		
	  			
		if(ans != null){
					System.out.println("Welcome "+ username);
					// System.out.println(ans);
					while(true){
						System.out.println("Enter Your Choice");
						System.out.println("---->  2 for Register new Vendor. (assign a new username and password to him)");
						System.out.println("---->  3 View all the vendors");
						System.out.println("---->  4 Create new tenders.");
						System.out.println("---->  5 View All the Tenders.");
						System.out.println("---->  6 Assign tender to a vendor .");
						
						System.err.println("---->  7 to exit.");	
					
					int AdminChoice = sc.nextInt();
					switch(AdminChoice) {
					case 2:
					System.out.println("Enter vender Id");
					int id  = sc .nextInt();
					System.out.println("Enter vender name");
					String name = sc.next();
					System.out.println("Assign vender username ");
					String Vusername = sc.next();
					System.out.println("Assign vender password");
					String Vpassword = sc.next();
					
					Vender vender = new Vender();
					vender.setVid(id);
					vender.setVname(name);
					vender.setUsername(Vusername);
					vender.setPassword(Vpassword);
					
						String message;
						try {
							message = dao.registerVender(vender);
							System.out.println(message);
						} catch (AdminException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					break;
					
				
					case 3:
						List<Vender> venders;
						try {
							venders = dao.getAllVender();
							for (Vender vend : venders) {
								System.out.println(vend);
							}
						} catch (VenderException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						
						break;
						
						

					case 4:
						System.out.println("Enter tender ID");

					    int TenderId = sc.nextInt();
					    
					    System.out.println("Enter tender Name");
					    String tenderName = sc.next();
					    
					 //   adminDao Dao = new adminDaoImpl() ;
					    String result = dao.CreateNewTender(TenderId, tenderName);
					    System.out.println(result);
						
						break;
						
					case 5:
						try {
							List <Tender> tenders =	dao.getAllTender();
							
							for (Tender tender : tenders) {
								System.out.println(tender);
							}
						} catch (Exception e){
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
							
						break;
					case 6:
						System.out.println("Enter valid Tender id ");
						 int Tid = sc.nextInt();

						 System.out.println("Enter valid vender id ");
						 int vid = sc.nextInt();
						  
						 String re = dao.AssignTenderToAVendor(Tid, vid);
						 
						 System.out.println(re);
						 
						 break;
					case 7:
						System.out.println(" logout Successfully ");
						
						return;
						
				}
			}
					   
	}
		else{
		System.out.println("Wrong user name and password ");
	}
	} catch (AdminException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	

	}

}
