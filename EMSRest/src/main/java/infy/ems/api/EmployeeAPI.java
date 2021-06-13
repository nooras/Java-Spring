package infy.ems.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

	@GetMapping(value = "/employees")
	public ResponseEntity<List<EmployeeDTO>> getAllEmployeesDetails() throws EMSException{
		List<EmployeeDTO> empList = employeeService.getAllEmployee();
		return new ResponseEntity<>(empList, HttpStatus.OK);
	}

	@GetMapping(value = "/employee/{id}")
	public ResponseEntity<EmployeeDTO> getEmployeeData(@PathVariable Long id) throws EMSException{
		EmployeeDTO empDTO = employeeService.getEmployeeDetails(id);
		return new ResponseEntity<>(empDTO, HttpStatus.OK);
	}

	@GetMapping(value = "/employee")
	public ResponseEntity<List<EmployeeDTO>> getEmployeeDetailsWithRole(@RequestParam(value="role") String role) throws EMSException{
		List<EmployeeDTO> empList = employeeService.getEmployeeDetailsWithRole(role);
		return new ResponseEntity<>(empList, HttpStatus.OK);
	}

	@PostMapping(value = "/employee")
	public ResponseEntity<String> addEmployee(@RequestBody EmployeeDTO employee) throws EMSException{
		Long empid = employeeService.addEmployee(employee);
		String successMessage = "Employee added successfully : " + empid;
		return new ResponseEntity<>(successMessage, HttpStatus.CREATED);
	}

	@PostMapping(value = "/employee/{id}")
	public ResponseEntity<String> updateEmployee(@PathVariable Long id) throws EMSException{
		employeeService.updateEmployee(id);
		String successMessage = "Employee updated successfully : " + id;
		return new ResponseEntity<>(successMessage, HttpStatus.OK);
	}


}
