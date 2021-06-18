package infy.ems.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import infy.ems.entity.Employee;

// Employee repository extending CrudRepository for different opeartions like save, findById etc
public interface EmployeeRepository extends CrudRepository<Employee, Long> {

	public List<Employee> findByRole(String role); //Query Creation using method name

	@Query("Select e from Employee e where e.salary>=:salary and e.isPermanent=:isPermanent") //Query Creation using Query annotation
	public List<Employee> findByIsPermanetAndSalary(@Param("salary") BigDecimal salary, @Param("isPermanent") Boolean isPermanent); //passing query parameters

	//	@Query("Select e from Employee e where e.salary>=:salary and e.isPermanent=:'true'")
	//	public List<Employee> findByIsPermanetAndSalary(@Param("salary") BigDecimal salary);
}
