package model.dao;

import java.util.List;

import model.entities.Department;

public interface DepartmentDao {

	void insert(Department obj);// fun��o inseri um obj no banco de dados

	void update(Department obj);// fun��o de atuualiza��o de dados

	void deleteBayId(Integer id); // fun��o de deleta dados

	Department findById(Integer id);// pega o id e consultas etem no banco de dados, retorna fal ou null

	List<Department> findAll();// lista de todos os departamentos
}
