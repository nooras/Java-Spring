package infy.ems.dto;

import java.math.BigDecimal;

//Employee DTO Class
public class EmployeeDTO {

	private Long employeeId;
	private String name;
	private String role;
	private Boolean isPermanent;
	private BigDecimal salary;
	public Long getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public Boolean getIsPermanent() {
		return isPermanent;
	}
	public void setIsPermanent(Boolean isPermanent) {
		this.isPermanent = isPermanent;
	}
	public BigDecimal getSalary() {
		return salary;
	}
	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}

	public EmployeeDTO() {
		super();
	}
	public EmployeeDTO(Long employeeId, String name, String role, Boolean isPermanent, BigDecimal salary) {
		super();
		this.employeeId = employeeId;
		this.name = name;
		this.role = role;
		this.isPermanent = isPermanent;
		this.salary = salary;
	}


}
