package infy.ems;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.Statement;

public class CRUD {

	/*
	Create a employee table with details of employee as columns such as employeeid, firstName, lastName, DOJ, mobileNumber,
	salary and working
	 */
	void createTable() {
		try {
			//Creating object of ConnectDB class to access DB
			ConnectDB db = new ConnectDB();
			Connection c = db.connect();

			// Statement for executing sql query
			Statement stmt = null;
			stmt = c.createStatement();

			DatabaseMetaData dbm = c.getMetaData();  // Getting all metadata from DB EMS
			ResultSet rs = dbm.getTables(null, null, "employee", null);

			//if employee table not present in DB then create table for employee
			if (!rs.next()) {

				//SQL query for craete table employee
				String sql = "CREATE TABLE EMPLOYEE " +
						"(employeeID INT PRIMARY KEY     NOT NULL," +
						" firstName       TEXT    NOT NULL, " +
						" lastName        TEXT    NOT NULL, " +
						" DOJ             DATE    NOT NULL, " +
						" mobileNumber    VARCHAR(10)     NOT NULL, " +
						" salary          REAL," +
						" working         bool)" ;

				//Execute query
				stmt.executeUpdate(sql);
				System.out.println("Table created successfully...");
			}else{
				System.out.println("Table already exists");
			}
			stmt.close();
			c.close();

		}catch(Exception e) {

			System.err.println( e.getClass().getName()+": "+ e.getMessage() );
			System.exit(0);

		}
	}

	// Inserting employee data in DB
	void insertData() {
		try {
			//Creating object of ConnectDB class and access DB
			ConnectDB db = new ConnectDB();
			Connection c = db.connect();

			// Statement for executing sql query
			Statement stmt = null;
			stmt = c.createStatement();

			// SQL query for inserting data in employee table
			String sql1 = "INSERT INTO "
					+ "employee(employeeid, firstname, lastname, doj, mobilenumber, salary, working) "
					+ "VALUES('1', 'Nooras', 'Fatime', '01-03-2021', '1234569512', '12000', 'False')";
			stmt.executeUpdate(sql1);

			String sql2 = "INSERT INTO "
					+ "employee(employeeid, firstname, lastname, doj, mobilenumber, salary, working) "
					+ "VALUES('2', 'Manish', 'Kumar', '01-03-2021', '1234569512', '12000', 'True')";
			stmt.executeUpdate(sql2);

			String sql3 = "INSERT INTO "
					+ "employee(employeeid, firstname, lastname, doj, mobilenumber, salary, working) "
					+ "VALUES('3', 'Shivam', 'Choubey', '01-03-2021', '1234569512', '12000', 'True')";
			stmt.executeUpdate(sql3);

			String sql4 = "INSERT INTO "
					+ "employee(employeeid, firstname, lastname, doj, mobilenumber, salary, working) "
					+ "VALUES('4', 'Naman', 'Agrawal', '01-03-2021', '1234569512', '12000', 'False')";
			stmt.executeUpdate(sql4);

			stmt.close();
			c.close();
			System.out.println("Data inserted successfully...");
		}catch(Exception e) {

			System.err.println( e.getClass().getName()+": "+ e.getMessage() );
			System.exit(0);

		}
	}

	//Fetch deatils of employee based on empid
	void fetchEmployee(String empid) {
		try {
			//Creating object of ConnectDB class and access DB
			ConnectDB db = new ConnectDB();
			Connection c = db.connect();

			// Statement for executing sql query
			Statement stmt = null;
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery( "SELECT * FROM employee where employeeid="+ empid); //fetch all table data in rs Resultset

			//If data is present show all emp data
			if(rs.next() ) {
				System.out.println("Employee Details Present of id : " + empid);
				System.out.println("EmployeeId : " + rs.getString("employeeid"));  //get data at a specific columns
				System.out.println("FirstName : " + rs.getString("firstname"));
				System.out.println("LastName : " + rs.getString("lastname"));
				System.out.println("DOJ : " + rs.getString("doj"));
				System.out.println("Mobile No. : " + rs.getString("mobilenumber"));
				System.out.println("Salary : " + rs.getString("salary"));
				System.out.println("Working : " + rs.getString("working"));

			}else {
				System.out.println("Employee data not present of id : "+ empid);
			}
			stmt.close();
			c.close();
		}catch(Exception e) {

			System.err.println( e.getClass().getName()+": "+ e.getMessage() );
			System.exit(0);
		}
	}

	// update salary of employee with newSalary whose salary is less than 30000 and currently working
	void UpdateSalary(String salaryNew) {
		try {
			//Creating object of ConnectDB class to access DB
			ConnectDB db = new ConnectDB();
			Connection c = db.connect();

			// Statement for executing sql query
			Statement stmt = null;
			stmt = c.createStatement();

			// SQL query for updating data in employee table
			String sql = "UPDATE employee set salary = "+salaryNew +" where salary<'30000' and working='t';";
			stmt.executeUpdate(sql);
			System.out.println("Data update Successfully...");

			//retrieving all data whose salary is greater than 30000 and working is true i.e updated data
			ResultSet rs = stmt.executeQuery( "SELECT * FROM employee where salary>'30000' and working='t'" );
			while ( rs.next() ) {
				System.out.println("Employee Details : ");
				System.out.println("EmployeeId : " + rs.getString("employeeid"));
				System.out.println("FirstName : " + rs.getString("firstname"));
				System.out.println("LastName : " + rs.getString("lastname"));
				System.out.println("DOJ : " + rs.getString("doj"));
				System.out.println("Mobile No. : " + rs.getString("mobilenumber"));
				System.out.println("Salary : " + rs.getString("salary"));
				System.out.println("Working : " + rs.getString("working"));
			}
			stmt.close();
			c.close();
		}catch(Exception e) {

			System.err.println( e.getClass().getName()+": "+ e.getMessage() );
			System.exit(0);
		}
	}
}
