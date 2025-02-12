package Tender.Usecase;

import java.util.Scanner;

import Tender.Exception.AdminException;
import Tender.Model.Vender;
import Tender.dao.adminDao;
import Tender.daoImpl.adminDaoImpl;

public class registerVenderByAdmin {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);

		System.out.println("Enter vender Id ");

		int VId = sc.nextInt();
		System.out.println("Enter vender name ");
		String VName = sc.next();
		System.out.println("Enter vender username");
		String VUsername = sc.next();
		System.out.println("Enter vender password ");
		String VPassword = sc.next();

		Vender vender = new Vender(VId, VName, VUsername, VPassword);
//		vender.setVid(VId);
//		vender.setVname(VName);
//		vender.setUsername(VUsername);
//		vender.setPassword(VPassword);
		// adminDao dao = new adminDaoImpl();
		
		adminDao dao = new Tender.daoImpl.adminDaoImpl();

		try {
			String result = dao.registerVender(vender);
			System.out.println(result);
		} catch (AdminException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			System.out.println(e.getMessage());
		}

	}

}
