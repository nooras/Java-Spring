package infy.ems.service;

import java.math.BigDecimal;
import java.util.List;

import infy.ems.dto.EmployeeDTO;
import infy.ems.exception.EMSException;

// interface for employeeService
public interface EmployeeService {

	public List<EmployeeDTO> getAllEmployee() throws EMSException;
	public EmployeeDTO getEmployeeDetails(Long employeeID) throws EMSException;
	public List<EmployeeDTO> getEmployeeDetailsWithRole(String role) throws EMSException;
	public Long addEmployee(EmployeeDTO employeeDTO) throws EMSException ;
	public void updateEmployee(long id) throws EMSException;
	public List<EmployeeDTO> getAllPermanetEmployeeWithSalaryAbove(BigDecimal salary, Boolean permanent) throws EMSException;
	public void deleteCustomer(Long customerId) throws EMSException;
	public void deleteCustomers() throws EMSException;
}
