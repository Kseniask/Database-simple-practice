import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class CandidateDBdemo {

	public static void main(String[] args) throws SQLException, IOException {
		
		Candidate[] candidates = new Candidate[10];
		Candidate[] fromDB = new Candidate[10];

		int counter =0;
		
		counter = readFile("candidates.csv", candidates,counter);
		
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		//launch driver
		try {
			Class.forName("net.ucanaccess,jdbc.UcanaccessDriver");
		}
		catch(ClassNotFoundException ex) {
			ex.getMessage();
		}
		
		String msAccess = "src/Candidates.accdb";
		String dbURL = "jdbc:ucanaccess://"+msAccess;
		
		connection = DriverManager.getConnection(dbURL);
		statement = connection.createStatement();
		String insertSQL;
//		
		String truncateSQL = "drop table candidate";
		statement.executeUpdate(truncateSQL);
		String createTable = "CREATE TABLE candidate("
				+ "id AUTOINCREMENT primary key,"
				+ "full_name char(255),"
				+ "department char(55),"
				+ "email char(150),"
				+ "match integer);";
		statement.execute(createTable);
	
		for(Candidate cand : candidates) {
			
			insertSQL = "INSERT INTO Candidate ( full_name,department,email,match ) VALUES ( \""+cand.getFull_name()+"\",'"+cand.getDepartment()+"','"+cand.getEmail()+"','"+cand.getMatch()+"');";
		   
			statement.executeUpdate(insertSQL);
			
		}
		
		resultSet = statement.executeQuery("SELECT * FROM candidate ORDER BY full_name");
		fromDB = serialize(resultSet,fromDB);

		
		
		
		for(Candidate c : fromDB) {
			System.out.println(c.getId()+" | "+c.getFull_name()+" | "+c.getDepartment()+" | "+c.getEmail()+" | "+c.getMatch()+"%\n");
		}
		changeCandidate(fromDB, statement, resultSet);
		resultSet = statement.executeQuery("SELECT * FROM candidate ORDER BY full_name");
		fromDB = serialize(resultSet,fromDB);
		
		for(Candidate c : fromDB) {
			System.out.println(c.getId()+" | "+c.getFull_name()+" | "+c.getDepartment()+" | "+c.getEmail()+" | "+c.getMatch()+"%\n");
		}

		try {
			if(connection != null) {
				resultSet.close();
				statement.close();
				connection.close();
				
			}
		}
		catch(SQLException ex) {
			ex.printStackTrace();
		}
//		String result = findName(fromDB);
//		System.out.println(result);
		
	}
		
	public static Candidate[] serialize(ResultSet resultSet,Candidate[] fromDB) throws SQLException {
		int counter =0;
		int id;
		String name, dep,email;
		int match;
		while(resultSet.next() && counter<10) {
			
			id = resultSet.getInt("id");
			name= resultSet.getString(2);
			dep= resultSet.getString(3);
			email= resultSet.getString(4);
			match= resultSet.getInt(5);
			Candidate newbie = new Candidate(name,dep,email,match);
			newbie.setId(id);
			fromDB[counter]=newbie;
			counter++;
			
		}
		return fromDB;
	
	}
	
	public static int readFile(String filename, Candidate[] candidates, int counter) throws IOException {
		 File myFile = new File(filename);
		 Scanner read = new Scanner(myFile);
		 read.nextLine();
		 while(read.hasNext() && counter<10) {
			 String[] data = (read.nextLine()).split(",");
			 Candidate newc = new Candidate(data[0],data[1],data[2],Integer.parseInt(data[3]));
			 candidates[counter] = newc;
			 counter++;
		 }
		 read.close();
		 return counter;
		 
	}
	public static String findName(Candidate[] fromDB) {
		String result="Result: ";
		String[] fullname;
		String fname;
		Scanner keyb = new Scanner(System.in);
		System.out.println("Enter the name you want to find");
		String name = keyb.nextLine();
		for(int i=0; i<fromDB.length; i++) {

			fullname = fromDB[i].getFull_name().split(" ");
			fname = fullname[0];
			if(fname.compareToIgnoreCase(name) == 0) {
				
				result =fromDB[i].getId()+" | "+fromDB[i].getFull_name()+" | "+fromDB[i].getDepartment()+" | "+fromDB[i].getEmail()+" | "+fromDB[i].getMatch()+"%";
				i=fromDB.length;
			}
			else {
				result = "Nothing found";
			}
		}
		return result;
	}
	
	public static void changeCandidate(Candidate[] fromDB, Statement statement, ResultSet resultSet) throws SQLException {
		Scanner keyb = new Scanner(System.in);
		System.out.println("\nDo you want to change any candidate? Y/N");
		String answer = keyb.nextLine();
		String text ="";
		if(answer.compareToIgnoreCase("y") == 0) {
			
			String result = findName(fromDB);
			System.out.print(result);
			if(result !="Nothing found") {
			String[] sp = result.split(" ");
			int id = Integer.parseInt(sp[0]);
			System.out.println("What do you want to change?\nN - name,\nD - department,\nE - email, M - match");
			String choice = keyb.nextLine();
			boolean wrong = false;
			
			if(choice.compareToIgnoreCase("N")!=0 && choice.compareToIgnoreCase("D")!=0 &&
			   choice.compareToIgnoreCase("E")!=0 && choice.compareToIgnoreCase("M")!=0) {
				wrong = true;
			}
			while(wrong) {
				System.out.println("Unknown command. Enter N - name,\nD - department,\nE - email, M - match");
				choice = keyb.nextLine();
			}
			if(choice.compareToIgnoreCase("N")==0) {
				System.out.println("Enter the new name");
				String name = keyb.nextLine();
				text = "full_name = '"+name+"'";
				updateSql(text, statement, id, resultSet);
			}
			else if(choice.compareToIgnoreCase("D")==0) {
				System.out.println("Enter the new department");
				String name = keyb.nextLine();
				text = "department = '"+name+"'";
				updateSql(text, statement, id, resultSet);
			}
			else if(choice.compareToIgnoreCase("E")==0) {
				System.out.println("Enter the new email");
				String email = keyb.nextLine();
				text = "email = '"+email+"'";
				updateSql(text, statement, id, resultSet);
			}
			else if(choice.compareToIgnoreCase("M")==0) {
				System.out.println("Enter the new match percentage");
				String match = keyb.nextLine();
				text = "match = '"+match+"'";
				updateSql(text, statement, id, resultSet);
			}
			
		}
			else {
				changeCandidate(fromDB, statement, resultSet);
			}
		}//irf yes ends
		else {
			System.out.println("Good bye!");
		}
		keyb.close();
		
	}
	
	public static void updateSql(String text, Statement statement, int id, ResultSet resultSet) throws SQLException {
		
		String update = "UPDATE candidate SET "+text+" WHERE id = " + id;
		statement.executeUpdate(update);
		String show = "SELECT * FROM candidate WHERE id = " + id;
		resultSet = statement.executeQuery(show);
		resultSet.next();
//		System.out.println(resultSet.getString(2));
	}

}
