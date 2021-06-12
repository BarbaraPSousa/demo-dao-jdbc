package model.dao;

import java.util.List;

import model.entities.Department;

public interface DepartmentDao {

	void insert(Department obj);// inseri um obj no banco de dados

	void update(Department obj);

	void deleteBayId(Integer id);

	Department findById(Integer id);// pega o id e consultas etem no banco de dados, retorna fal ou null

	List<Department> findAll();// lista de todos os departamentos
}
