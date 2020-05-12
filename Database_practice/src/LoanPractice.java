import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.Scanner;

public class LoanPractice {

	public static void main(String[] args) throws SQLException {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		
		
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
		}
		catch(ClassNotFoundException ex) {
			System.out.println("Problem loading driver" + ex.getMessage());
		}
		
		String msAccess = "src/loans.accdb";
		String dbURL = "jdbc:ucanaccess://" + msAccess;
		Loan[] loans = new Loan[40];
		int counter=0;
		connection = DriverManager.getConnection(dbURL);
		statement = connection.createStatement();
		
		resultSet = statement.executeQuery("SELECT * FROM loan");
		
		while(resultSet.next() ) {
			if(resultSet.getString(3).startsWith("Stud")) {
				StudentLoan newLoan = new StudentLoan(counter+1, resultSet.getString(1), resultSet.getString(2), resultSet.getInt(4),resultSet.getString(3));
				newLoan.setStud_id(0);
				loans[counter] = newLoan;
			}
			else if(resultSet.getString(3).startsWith("Mor")) {
				Mortgage newLoan = new Mortgage(counter+1, resultSet.getString(1), resultSet.getString(2), resultSet.getInt(4),resultSet.getString(3));
				newLoan.setAddress("");
				loans[counter] = newLoan;
			}
			else if(resultSet.getString(3).startsWith("Car")) {
				CarLoan newLoan = new CarLoan(counter+1, resultSet.getString(1), resultSet.getString(2), resultSet.getInt(4),resultSet.getString(3));
				newLoan.setCar_plate("");
				loans[counter] = newLoan;
			}
			counter++;
		}
		
		for(int i=0;i<counter;i++){
			System.out.println(loans[i]);
		}
		
		System.out.println("Enter the id to find record");
		Scanner input = new Scanner(System.in);
		int id = input.nextInt();
		input.nextLine();
		ResultSet record = statement.executeQuery("SELECT * FROM loan WHERE loan_num = "+ id);
		if(record != null) {
		System.out.println("Found");
		}
		else {
			System.out.println("NO RESULT");
		}
	}
	
	public static void runQuery(String text) {
		
	}

}
