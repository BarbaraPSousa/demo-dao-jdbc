package model.dao;

import java.util.List;

import model.entities.Department;

public interface DepartmentDao {

	void insert(Department obj);// função inseri um obj no banco de dados

	void update(Department obj);// função de atuualização de dados

	void deleteBayId(Integer id); // função de deleta dados

	Department findById(Integer id);// pega o id e consultas etem no banco de dados, retorna fal ou null

	List<Department> findAll();// lista de todos os departamentos
}
