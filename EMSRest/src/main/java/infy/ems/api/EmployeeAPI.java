package infy.ems.api;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import infy.ems.dto.EmployeeDTO;
import infy.ems.exception.EMSException;
import infy.ems.service.EmployeeService;

// Rest api for managing employee of company

@RestController
@RequestMapping(value="/api")
@Validated
public class EmployeeAPI {
	@Autowired
	private EmployeeService employeeService;

	//Get list of all employee
	@GetMapping(value = "/employees")
	public ResponseEntity<List<EmployeeDTO>> getAllEmployeesDetails() throws EMSException{
		List<EmployeeDTO> empList = employeeService.getAllEmployee();
		return new ResponseEntity<>(empList, HttpStatus.OK);
	}

	//Get details of given employee id
	@GetMapping(value = "/employee/{id}")
	public ResponseEntity<EmployeeDTO> getEmployeeData(@PathVariable Long id) throws EMSException{
		EmployeeDTO empDTO = employeeService.getEmployeeDetails(id);
		return new ResponseEntity<>(empDTO, HttpStatus.OK);
	}

	//Get list of all emplyee with the given role
	@GetMapping(value = "/employee")
	public ResponseEntity<List<EmployeeDTO>> getEmployeeDetailsWithRole(@RequestParam(value="role") String role) throws EMSException{
		List<EmployeeDTO> empList = employeeService.getEmployeeDetailsWithRole(role);
		return new ResponseEntity<>(empList, HttpStatus.OK);
	}

	// add employee in db
	@PostMapping(value = "/employee")
	public ResponseEntity<String> addEmployee(@Valid @RequestBody EmployeeDTO employee) throws EMSException{
		Long empid = employeeService.addEmployee(employee);
		String successMessage = "Employee added successfully : " + empid;
		return new ResponseEntity<>(successMessage, HttpStatus.CREATED);
	}

	// update employee with given id - updating salary
	@PutMapping(value = "/employee/{id}")
	public ResponseEntity<String> updateEmployee(@PathVariable Long id) throws EMSException{
		employeeService.updateEmployee(id);
		String successMessage = "Employee updated successfully : " + id;
		return new ResponseEntity<>(successMessage, HttpStatus.OK);
	}

	//Assignement 2

	//get all permanent employee with salary above range using query parameters
	// http://localhost:8080/api/employee/permanentstaff?salary=20000&permanent=true
	@GetMapping(value = "/employee/permanentstaff")
	public ResponseEntity<List<EmployeeDTO>> getAllPermanetEmployeeWithSalaryAbove(
			@RequestParam(value="salary") BigDecimal salary, @RequestParam(value="permanent") Boolean permanent
			) throws EMSException{
		List<EmployeeDTO> empList = employeeService.getAllPermanetEmployeeWithSalaryAbove(salary, permanent);
		return new ResponseEntity<>(empList, HttpStatus.OK);
	}

	// Delete customer with employee id
	// http://localhost:8080/api/employee/1
	@DeleteMapping(value = "/employee/{id}")
	public ResponseEntity<String> deleteCustomer(@PathVariable Long id) throws EMSException{
		employeeService.deleteCustomer(id);
		String successMessage = "Employee Deleted successfully : " + id;
		return new ResponseEntity<>(successMessage, HttpStatus.OK);
	}

	//Delete all employee
	// http://localhost:8080/api/employee/deleteall
	@DeleteMapping(value = "/employee/deleteall")
	public ResponseEntity<String> deleteCustomers() throws EMSException{
		employeeService.deleteCustomers();
		String successMessage = "All Employee Deleted successfully. ";
		return new ResponseEntity<>(successMessage, HttpStatus.OK);
	}
}
