package infy.ems;

// Employee   management System
public class EMS {
	public static void main(String args[]) {
		CRUD c = new CRUD();

		//Call Create
		System.out.println("------------------");
		c.createTable();

		//Call insert
		System.out.println("------------------");
		c.insertData(); //extra

		//Call FetchEmployee
		System.out.println("------------------");
		c.fetchEmployee("1");

		//Call update
		System.out.println("------------------");
		c.UpdateSalary("35000");
		System.out.println("------------------");
	}
}
