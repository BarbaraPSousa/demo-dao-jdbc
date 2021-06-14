package application;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		
		SellerDao sellerDao = DaoFactory.createSellerDao();//instanciando um Sellerdao, n precissa do new.		
		
		System.out.println(" *** TEST 1: seller findBayId *** ");
		Seller seller = sellerDao.findById(3); //test
		System.out.println(seller);
		
		System.out.println(" \n*** TEST 2: seller findBayId *** ");
		Department department = new Department(2, null);
		List<Seller> list = sellerDao.findByDepartment(department);
		for(Seller obj : list) {
			System.out.println(obj);
		}
	}	
}