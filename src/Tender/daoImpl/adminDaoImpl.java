package Tender.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.xdevapi.Result;

import Tender.Exception.AdminException;
import Tender.Exception.VenderException;
import Tender.Model.Admin;
import Tender.Model.Bid;
import Tender.Model.Tender;
import Tender.Model.Vender;
import Tender.dao.adminDao;
import Tender.utility.Dao;

public class adminDaoImpl implements adminDao {

	@Override
	public String registerVender(Vender vender) throws AdminException {
		// if user is adimn then it will allow

		String result = "vender not registered";

		try (Connection conn = Dao.provideConnection()) {
			PreparedStatement ps = conn.prepareStatement("insert into vender values (?,?,?,?)");

			ps.setInt(1, vender.getVid());
			ps.setString(2, vender.getVname());
			ps.setString(3, vender.getUsername());
			ps.setString(4, vender.getPassword());

			int x = ps.executeUpdate();
			if (x > 0) {
				result = "vender register Succesfully ";
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new AdminException(e.getMessage());
		}

		return result;
	}

	@Override
	public List<Vender> getAllVender() throws VenderException {
		List<Vender> venders = new ArrayList<>();
		try (Connection conn = Dao.provideConnection()) {

			PreparedStatement ps = conn.prepareStatement("select * from vender");

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int Vid = rs.getInt("VId");
				String Vname = rs.getString("VName");
				String VUsername = rs.getString("username");
				String VPassword = rs.getString("username");

				Vender vender = new Vender();
				vender.setVid(Vid);
				vender.setVname(Vname);
				vender.setUsername(VUsername);
				vender.setPassword(VPassword);

				venders.add(vender);

			}

//				
//				for (Vender vender : venders) {
//					System.out.println(vender);
//				}
//				

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return venders;
	}

	@Override
	public String CreateNewTender(int TenderId, String TenderName) {

		String message = "Not Inserted ...";
		
		try (Connection conn = Dao.provideConnection()) {
			PreparedStatement ps = conn.prepareStatement("insert into tender values(?,?)");
			ps.setInt(1, TenderId);
			ps.setString(2, TenderName);
			int x = ps.executeUpdate();
			if (x > 0) {
				message = "Tender Inserted  Successfully";
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return message;
	}

	@Override
	public List<Tender> getAllTender() {
		List<Tender> Tenders = new ArrayList<>();
		try (Connection conn = Dao.provideConnection()) {

			PreparedStatement ps = conn.prepareStatement("select * from tender");

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int Tid = rs.getInt("TenderId");
				String Tname = rs.getString("TenderName");

				Tender Tend = new Tender();
				Tend.setTenderId(Tid);
				Tend.setTenderName(Tname);

				Tenders.add(Tend);

			}

//				
//				for (Vender vender : venders) {
//					System.out.println(vender);
//				}
//				

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

		return Tenders;

	}

	@Override
	public List<Bid> getAllBidOfAVender(int VenderId) {
		List<Bid> bids = new ArrayList<>();

		try (Connection conn = Dao.provideConnection()) {

			PreparedStatement ps = conn.prepareStatement("select * from bid where VenderId = ?");
			ps.setInt(1, VenderId);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int B = rs.getInt("BidId");

				int v = rs.getInt("VenderId");
				int f = rs.getInt("FixedPrice");
				Boolean s = rs.getBoolean("status");

				Bid bid = new Bid();
				bid.setBidID(B);
				bid.setVenderId(v);
				bid.setFixedPrice(f);
				bid.setStatus(s);

				bids.add(bid);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return bids;
	}

	@Override
	public String AssignTenderToAVendor(int TenderId, int VID) {
		// TODO Auto-generated method stub
		String message = "not assigned ";

		try (Connection conn = Dao.provideConnection()) {

			PreparedStatement ps = conn.prepareStatement("insert into tendertovender values (? ,?)");
			ps.setInt(1, TenderId);
			ps.setInt(2, VID);

			int x = ps.executeUpdate();
			if (x > 0) {
				message = "tender assign to vender successfully ";
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return message;
	}

	@Override
	public Admin loginIntoAdmin(String AUsername, String APassword) throws AdminException {
		Admin ass = null;

		try (Connection conn = Dao.provideConnection()) {

			PreparedStatement ps = conn.prepareStatement("select * from admin where AUsername = ? AND APassword = ?");

			ps.setString(1, AUsername);
			ps.setString(2, APassword);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				int r = rs.getInt("AID");
				String n = rs.getString("AName");
				String a = rs.getString("AUsername");
				String p = rs.getString("APassword");

				ass = new Admin(r, n, a, p);
			}

		} catch (SQLException e) {
			// printStackTrace(e);
			throw new AdminException("invalid username and password");
		}

		return ass;
	}
}
