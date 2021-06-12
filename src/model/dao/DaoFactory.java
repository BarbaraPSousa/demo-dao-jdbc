package model.dao;

import db.DB;
import model.dao.impl.SellerDaoJDBC;

public class DaoFactory {
	
	public static SellerDao createSellerDao() {//metodo que retorna a interface, internamente instancia a implementa��o
		return new SellerDaoJDBC(DB.getConnection());
	}

}
