package application;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		
		SellerDao sellerDao = DaoFactory.createSellerDao();//instanciando um Sellerdao, n precissa do new.
		
		System.out.println(" ** TEST 1: seller findBayId *** ");
		Seller seller = sellerDao.findById(3); //test
		System.out.println(seller);// teste
	}
}
