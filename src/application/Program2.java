package application;

import java.util.List;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

public class Program2 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		DepartmentDao departmentDao = DaoFactory.createDeparment();
		
		System.out.println(" *** TEST 1: Department findBayId *** ");
		Department dep = departmentDao.findById(1);
		System.out.println(dep);
		
		System.out.println("\n *** TEST 2: Department findAll *** ");
		List<Department> list = departmentDao.findAll();
		for(Department obj: list) {
			System.out.println(obj);
		}
		
		System.out.println("\n *** TEST 3: Department insert *** ");
		Department newDep = new Department(null, "Car"); 
		departmentDao.insert(newDep);
		System.out.println("Inserted! new id = " + newDep.getId());
		
		
		System.out.println("\n *** TEST 4: Department update *** ");
		dep = departmentDao.findById(1);
		dep.setName("Food");
		departmentDao.update(dep);
		System.out.println("Updata Completed");
		
		System.out.println("\n *** TEST 5: Department delete *** ");
		System.out.print("Enter id for delete test: ");
		int id = sc.nextInt();
		departmentDao.deleteBayId(id);
		System.out.println("Delete Completed!");

		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		sc.close();

	}

}
