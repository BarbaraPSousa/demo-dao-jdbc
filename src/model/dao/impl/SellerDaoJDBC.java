package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class SellerDaoJDBC implements SellerDao{

	private Connection conn;//conectando ao banco de dados
	
	public SellerDaoJDBC(Connection conn) {//contrutor da conec.
		this.conn = conn;
	}
	
	@Override
	public void insert(Seller obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"INSERT INTO seller "
					+ "(Name, Email, BirthDate, BaseSalary, DepartmentId)"
					+ "VALUES "
					+ "(?, ?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			
			st.setString(1, obj.getName());
			st.setString(2, obj.getEmail());
			st.setDate(3, new java.sql.Date(obj.getBirthDate().getTime()));
			st.setDouble(4,obj.getBaseSalary());
			st.setInt(5, obj.getDepartment().getId());
			
			int rowsAffected = st.executeUpdate();//executando
			
			if(rowsAffected > 0) {//verificando se inseriu dados
				ResultSet rs = st.getGeneratedKeys();
				if(rs.next()) {//se tiver inserido
					int id = rs.getInt(1);//id gerado
					obj.setId(id);
				}
				DB.closeResultSet(rs);//fechando 
			}
			else {
				throw new DbException("Unexpected error! No rows affected");//se não inserir lanca exceção
			}
		}
		catch (SQLException e) {//tratando possivel exceção
			throw new DbException(e.getMessage());
		}
		finally {//fechando
			DB.closeStatement(st);
		}
		
	}

	@Override
	public void update(Seller obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"UPDATE seller "
					+ "SET Name = ?, Email = ?, BirthDate = ?, BaseSalary = ?, DepartmentId = ? "
					+ "WHERE Id = ?");
			
			st.setString(1, obj.getName());
			st.setString(2, obj.getEmail());
			st.setDate(3, new java.sql.Date(obj.getBirthDate().getTime()));
			st.setDouble(4,obj.getBaseSalary());
			st.setInt(5, obj.getDepartment().getId());
			st.setInt(6, obj.getId());
			
			st.executeUpdate();//executando			
		}
		catch (SQLException e) {//tratando possivel exceção
			throw new DbException(e.getMessage());
		}
		finally {//fechando
			DB.closeStatement(st);
		}
		
	}

	@Override
	public void deleteBayId(Integer id) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("DELETE FROM seller WHERE Id = ?");
			
			st.setInt(1, id);
			
			int rows = st.executeUpdate();
			
			if(rows == 0) {
				throw new DbException("Id doesn't exist");
			}
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public Seller findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT seller.*,department.Name as DepName "
					+ "FROM seller INNER JOIN department "
					+ "ON seller.DepartmentId = department.Id "
					+ "WHERE seller.Id = ?");
			
			st.setInt(1, id);//recebe o parametro da entrada da fun.
			rs = st.executeQuery();
			if(rs.next()) {//consultando banco de dados se não for vazio
				Department dep = instantiateDepartment(rs);
				Seller obj = instantiateSeller(rs, dep);
				return obj;
				}
			return null; // se nao tiver informacao
		}
		catch (SQLException e) {//tratando exceção
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);			
		}
	}

	private Seller instantiateSeller(ResultSet rs, Department dep) throws SQLException {//transf os dados do funcionarios em OBJ
		Seller obj = new Seller();
		obj.setId(rs.getInt("Id"));
		obj.setName(rs.getString("Name"));
		obj.setEmail(rs.getString("Email"));
		obj.setBaseSalary(rs.getDouble("BaseSalary"));
		obj.setBirthDate(rs.getDate("BirthDate"));
		obj.setDepartment(dep);
		return obj;
	}

	private Department instantiateDepartment(ResultSet rs) throws SQLException {//Transformando os dados do DP em OBJ
		Department dep =  new Department();
		dep.setId(rs.getInt("DepartmentId"));
		dep.setName(rs.getString("DepName"));
		return dep;
	}

	@Override
	public List<Seller> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT seller.*,department.Name as DepName " 
					+ "FROM seller INNER JOIN department "							
					+ "ON seller.DepartmentId = department.Id " 
					+ "ORDER BY Name");

			rs = st.executeQuery();

			List<Seller> list = new ArrayList<>();

			Map<Integer, Department> map = new HashMap<>();

			while (rs.next()) {// percorrendo enquanto tiver um proximo

				Department dep = map.get(rs.getInt("DepartmentId"));// guardando qualquer departmente

				if (dep == null) {// verificando se ja existe dep para não repetir
					dep = instantiateDepartment(rs);// instanciando o dep.
					map.put(rs.getInt("DepartmentId"), dep);// salvando informaçao
				}

				Seller obj = instantiateSeller(rs, dep);// instanciando vendedor apontando para dep.
				list.add(obj);
			}
			return list;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public List<Seller> findByDepartment(Department department) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT seller.*,department.Name as DepName " 
					+ "FROM seller INNER JOIN department "
					+ "ON seller.DepartmentId = department.Id "
					+ "WHERE DepartmentId = ? " + "ORDER BY Name");

			st.setInt(1, department.getId());// recebe o parametro da entrada da fun.

			rs = st.executeQuery();

			List<Seller> list = new ArrayList<>();

			Map<Integer, Department> map = new HashMap<>();

			while (rs.next()) {// percorrendo enquanto tiver um proximo

				Department dep = map.get(rs.getInt("DepartmentId"));// guardando qualquer departmente

				if (dep == null) {// verificando se ja existe dep para não repetir
					dep = instantiateDepartment(rs);// instanciando o dep.
					map.put(rs.getInt("DepartmentId"), dep);// salvando informaçao
				}

				Seller obj = instantiateSeller(rs, dep);// instanciando vendedor apontando para dep.
				list.add(obj);
			}
			return list;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}
}
