package infy.ems;

import java.sql.Connection;
import java.sql.DriverManager;

// DB Connection with postgress sql
public class ConnectDB {
	Connection connect() {
		Connection c = null;
		try {
			Class.forName("org.postgresql.Driver"); // Before connecting to db dynamically call postgres driver class file into memoy
			c = DriverManager
					.getConnection("jdbc:postgresql://localhost:5432/EMS",
							"postgres", "root");  //connection to postgres
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getClass().getName()+": "+e.getMessage());
			System.exit(0);
		}
		System.out.println("Opened database successfully...");
		return c;
	}
}
