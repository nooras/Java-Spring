package infy.ems;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EmsRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmsRestApplication.class, args);
		System.out.println("Employee Mangement Started. Check log/ErrorLog.log for error.");
	}

}
