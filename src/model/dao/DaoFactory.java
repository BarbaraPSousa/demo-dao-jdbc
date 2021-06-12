package model.dao;

import model.dao.impl.SellerDaoJDBC;

public class DaoFactory {
	
	public static SellerDao createSellerDao() {//metodo que retorna a interface, internamente instancia a implementação
		return new SellerDaoJDBC();
	}

}
