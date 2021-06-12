package application;

import java.util.Date;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		
		Department obj = new Department(2, "Books");//inst. class
		
		Seller seller = new Seller(21, "Bob", "bob@gmail.com", new Date(), 3000.0, obj);//inst Sellerclass seler com valores
		
		SellerDao sellerDao = DaoFactory.createSellerDao();//instanciando um Sellerdao, n precissa do new.
		
		System.out.println(seller);// teste
	}
}
