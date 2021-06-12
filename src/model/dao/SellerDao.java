package model.dao;

import java.util.List;

import model.entities.Seller;

public interface SellerDao {

	void insert(Seller obj);// inseri um obj no banco de dados

	void update(Seller obj);

	void deleteBayId(Integer id);

	Seller findById(Integer id);// pega o id e consultas etem no banco de dados, retorna fal ou null

	List<Seller> findAll();// lista de todos os departamentos

}
