## Details of Employee Management System Rest:

1. Infy.ems-EmsRestApplication.java => Main

2. Infy.ems.entity Employee.java => Employee Entity Class

3. Infy.ems.dto- EmployeeDTO.java => Employee DTO(Data transfer Object) Class and validation

4. Infy.emis.reposirtory-EmployeeRepository.java=> Repository interface which is extending CrudRepository interface. With the help of CrudRepository we can use save, findById, findALL etc methods.

5. Infy.ems.service

a. EmployeeService-interface(getAllEmployee, getEmployeeDetails, getEmployeeDetailsWithRole, addEmployee, updateEmployee)

b. EmployeeServiceimpl-Implementation of EmployeeService interface.

- getAllEmployee => Return the list of all employees

- getEmployeeDetails=> Return employee details for given employee Id

- getEmployeeDetailsWithRole=> Return the list of employees with the given role

- addEmployee=> Add employee to database

- updateEmployee=> Update employee details for the given id

- getAllPermanetEmployeeWithSalaryAbove => Get all permanet employee with salary above range

- deleteCustomer => Delete customer with Id

- deleteCustomers => Delete all customers

6. Infy.ems.api- EmployeeApi.java => Rest Api class for mapping get and post method. 
7. Infy.ems.utility
    - Errorinfo java => For handling error
    - ExceptionControllAdvice => For handliding validation and exception
    - LoggingAspect => For log in file

8. Infy.ems.exception => For handling Exception

9. application.properties => Info related to db and some exception message

10. ValidationMessage.properties => For showing validation message

11. Log4j2.propertied => For log in file propeties

12. test.txt => All testcases