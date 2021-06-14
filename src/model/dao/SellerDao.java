package model.dao;

import java.util.List;

import model.entities.Department;
import model.entities.Seller;

public interface SellerDao {

	void insert(Seller obj);// fun��o q insere um obj no banco de dados

	void update(Seller obj);

	void deleteBayId(Integer id);

	Seller findById(Integer id);// fun��o q pega o id e consultas se tem no banco de dados, retorna fal ou null

	List<Seller> findAll();// fun��o lista de todos os departamentos
	
	List<Seller> findByDepartment(Department department);//func de busca por department
	

}
