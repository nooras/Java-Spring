package infy.ems.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import infy.ems.entity.Employee;

// Employee repository extending CrudRepository for different opeartions like save, findById etc
public interface EmployeeRepository extends CrudRepository<Employee, Long> {

	public List<Employee> findByRole(String role); //Query Craetion using method name
}
