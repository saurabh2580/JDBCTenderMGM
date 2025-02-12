package Tender.dao;

import java.util.List;

import Tender.Exception.AdminException;
import Tender.Exception.VenderException;
import Tender.Model.Admin;
import Tender.Model.Bid;
import Tender.Model.Tender;
import Tender.Model.Vender;

public interface adminDao {

	public Admin loginIntoAdmin(String AUsername, String APassword) throws AdminException;

	public String registerVender(Vender vernder) throws AdminException;

	public List<Vender> getAllVender() throws VenderException;

	public String CreateNewTender(int TenderId, String TenderName);

	public List<Tender> getAllTender();

	public List<Bid> getAllBidOfAVender(int VenderId);

	public String AssignTenderToAVendor(int TenderId, int VID);
}
