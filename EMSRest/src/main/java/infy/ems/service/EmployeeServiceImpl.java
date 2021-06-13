package infy.ems.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import infy.ems.dto.EmployeeDTO;
import infy.ems.entity.Employee;
import infy.ems.exception.EMSException;
import infy.ems.repository.EmployeeRepository;

@Service(value = "employeeService")
@Transactional
public class EmployeeServiceImpl implements EmployeeService  {

	@Autowired
	private EmployeeRepository employeeRepository;


	//Get list of all employee
	@Override
	public List<EmployeeDTO> getAllEmployee() throws EMSException {
		Iterable<Employee> employees = employeeRepository.findAll();
		List<EmployeeDTO> emplDTOList = new ArrayList<>();
		employees.forEach(employee -> {
			EmployeeDTO employeeDTO = new EmployeeDTO();
			employeeDTO.setEmployeeId(employee.getEmployeeId());
			employeeDTO.setName(employee.getName());
			employeeDTO.setRole(employee.getRole());
			employeeDTO.setIsPermanent(employee.getIsPermanent());
			employeeDTO.setSalary(employee.getSalary());
			emplDTOList.add(employeeDTO);
		});
		if(emplDTOList.isEmpty()) {
			throw new EMSException("Service.NO_EMPLOYEES");
		}
		return emplDTOList;
	}

	//Get details of geiven employee id
	@Override
	public EmployeeDTO getEmployeeDetails(Long employeeID) throws EMSException {
		Optional<Employee> optional = employeeRepository.findById(employeeID);
		Employee employee = optional.orElseThrow(()-> new EMSException("Service.NO_EMPLOYEE_DATA"));
		EmployeeDTO employeeDTO = new EmployeeDTO();
		employeeDTO.setEmployeeId(employee.getEmployeeId());
		employeeDTO.setName(employee.getName());
		employeeDTO.setRole(employee.getRole());
		employeeDTO.setIsPermanent(employee.getIsPermanent());
		employeeDTO.setSalary(employee.getSalary());
		return employeeDTO;
	}


	//Get list of all emplyee with the given role
	@Override
	public List<EmployeeDTO> getEmployeeDetailsWithRole(String role) throws EMSException {
		List<Employee> empList = employeeRepository.findByRole(role);
		List<EmployeeDTO> emplDTOList = new ArrayList<>();
		empList.forEach(employee -> {
			EmployeeDTO employeeDTO = new EmployeeDTO();
			employeeDTO.setEmployeeId(employee.getEmployeeId());
			employeeDTO.setName(employee.getName());
			employeeDTO.setRole(employee.getRole());
			employeeDTO.setIsPermanent(employee.getIsPermanent());
			employeeDTO.setSalary(employee.getSalary());
			emplDTOList.add(employeeDTO);
		});
		if(emplDTOList.isEmpty()) {
			throw new EMSException("Service.NO_EMPLOYEE_WITH_ROLE");
		}
		return emplDTOList;
	}

	// add employee in db
	@Override
	public Long addEmployee(EmployeeDTO employeeDTO) throws EMSException {
		Employee employee = new Employee();
		employee.setName(employeeDTO.getName());
		employee.setRole(employeeDTO.getRole());
		employee.setIsPermanent(employeeDTO.getIsPermanent());
		employee.setSalary(employeeDTO.getSalary());
		Employee employee2 = employeeRepository.save(employee);
		return employee2.getEmployeeId();
	}


	// update employee with given id - updating salary with 50000
	@Override
	public void updateEmployee(long id) throws EMSException {
		Optional<Employee> optional = employeeRepository.findById(id);
		Employee employee = optional.orElseThrow(()-> new EMSException("Service.NO_EMPLOYEE_DATA"));
		employee.setSalary(BigDecimal.valueOf(50000.00));

	}

}
