package FinalProjectHouseCommittiee.CODE;

/*
  Sql class:
  will connect java code to MySQL (via jdbc driver)  
  functions for query are:
  * ConectingToSQL() 
  * delete_statement()
  * update_statement() - will update username's password
  * select_query() - will show all table's elements 
  * Login() - all the menu for login option
  
   Note: For each query function - if user is Ternat - he will be redirected to his own table, 
   and so with Manager user. (By using int tbl)
 */

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Scanner;
import java.sql.Connection;

public class Sql {

	private static Connection connect; 
	
	// Connection to MySQL function:
		public static void ConectingToSQL ()
		{	
			try {
				Class.forName("com.mysql.jdbc.Driver");// connect to the driver jar file mysql connector 
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			
			String host = "jdbc:mysql://localhost:3306/Committiee";
			String username = "root";//user name
			String password = "";// password is empty now..
			
			try {
				 connect = (Connection) DriverManager.getConnection(host, username, password);
			} catch (SQLException e) {
				e.printStackTrace();
			}	
		}
		
		
	    // Login function:
		public static void Login(){	
			String sqlLogin="";
			int tbl;
			String username,password;
			boolean flag=true; // To loop while username & password aren't correct
			Scanner scanner = new Scanner(System.in);
			// Instructions for user:
			while(flag) {
				System.out.println("\nWelcome to House Committiee");
				System.out.println("Enter your level please: \n[1] Ternant\n[2] Manager");
				tbl = scanner.nextInt();
				System.out.println("Enter your username: ");
				username = scanner.next();
				
				System.out.println("--------Menu--------");
				System.out.println("[1] Enter your password and login ");
				System.out.println("[2] Change your password ");
				
				int ans=scanner.nextInt();
				switch (ans) {
				case 1:
					System.out.println("Enter your password: ");
					password = scanner.next();
					// If tbl = 1 use ternant, if tbl = 2 use manager		
					sqlLogin="";						
					if (tbl==1) 			
						sqlLogin= "select * from Ternant where username = ? and password = ?";	
					else if (tbl==2) 
						sqlLogin= "select * from Manager where username = ? and password = ?";			
					try {
						PreparedStatement st = connect.prepareStatement(sqlLogin);  // statement gets the right sql query
						st.setString(1,username); // attribute 1 - username
						st.setString(2,password); // attribute 2 - password
						
						ResultSet result = st.executeQuery();// execute the statement	
						if(!result.next())  // case there is no username + password match in DB
							System.out.println("Wrong parameters! try again");
						else    // case login details are correct
						{
							System.out.println("Login successfully");							
							// Go to Message functionality:	
							Message message = new Message(result);
							if (tbl==1) 			
								message.TernantFunctionality();
							else if (tbl==2) 
								message.ManagerFunctionality();
							flag=false;
						} 
					}
					catch (SQLException e) {
						e.printStackTrace();
						System.out.println("Login went wrong");
					}							
					
					break;
				case 2: // Update password case
					System.out.println("Enter your password: ");
					password = scanner.next();
					System.out.println("Enter your Id: ");
					String Id = scanner.next();
					
					// If tbl = 1 use ternant, if tbl = 2 use manager		
					sqlLogin="";
					
					// Change password in chosen table
					if (tbl==1) 			
						sqlLogin= "UPDATE `Ternant` SET `password` = "+password+" WHERE `Id` ="+Id;	
					else if (tbl==2) 
						sqlLogin= "UPDATE `Manager` SET `password` = "+password+" WHERE `Id` ="+Id;			
					try {
						    update_query(sqlLogin);
							System.out.println("Changed password successfully");							
							// Go to Message functionality:		
					}
					catch (Exception e) {
						e.printStackTrace();
						System.out.println("Login went wrong");
					}							
					break;

				default:
					break;
				}
				
				
			}
		}
	
	// Delete function:
	public static void delete_statement(int tbl,String username,String password){	
		
		// If tbl = 1 use ternant, if tbl = 2 use manager		
		String sqldelete="";
		if (tbl==1) 					
			sqldelete= "delete from Ternant where username = ? and password = ?";	
		else if (tbl==2) 
			sqldelete= "delete from Manager where username = ? and password = ?";	
		
		try {
			PreparedStatement st = connect.prepareStatement(sqldelete);  // statement gets the right sql query
			st.setString(1,username); // attribute 1 - username
			st.setString(2,password); // attribute 2 - password
			st.executeUpdate(); 			
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}			
	}
	

	// Update function - gets string query and executes it. 
	public static void update_query(String sqlupdate){			
		try {
			PreparedStatement pst = connect.prepareStatement(sqlupdate);			
			pst.executeUpdate();
			System.out.println("Updated successfully");  // case update succeed
						
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	// Abstract select query function - will execute any type of select:
	public static String select_query(int tbl,String queryString)
	{	
		String answer="";
		try 
		{			
			PreparedStatement statement = connect.prepareStatement(queryString);				
			ResultSet result = statement.executeQuery();// execute the statement
			ResultSetMetaData rsmd = result.getMetaData();

			int columnsNumber = rsmd.getColumnCount();  // Get column number to loop on:
			
			while(result.next())// take the rows in the result set
			{
				//getString(i) - take the col number i }
				 for (int j = 1; j <= columnsNumber; j++) {
					answer += result.getString(j) +' ';
				}
				 answer+="\n";
			}
			//System.out.println(answer);
		} 
		catch (SQLException e) {				
			e.printStackTrace();
		}
		return answer;				
	}
	
	
	// Select all function:
	public static void selectAll_query(int tbl)
	{	
		String query="";
		// If ternant:
		if(tbl==1)
		{
			query="select * from ternant";	
			tbl=1;
		}	
			
		else if(tbl==2)
		{
			query="select * from manager";	
			tbl=2;
		}		
		String answerString=select_query(tbl,query);
		System.out.println(answerString);
	}
		
	
	// insertManager() - function to add new manager member: (6 parameters)
	public static void insertManager(String s1 ,String s2,String s3,String s4,String s5,String s6 ){
		
		String sqlInsert = "insert into Committiee.Manager (Id,Username,Password,FirstName,LastName,Years) values (?,?,?,?,?,?)";
		
		try {
			PreparedStatement pst = connect.prepareStatement(sqlInsert);
			pst.setString(1, s1);
			pst.setString(2, s2);
			pst.setString(3, s3);
			pst.setString(4, s4);
			pst.setString(5, s5);
			pst.setString(6, s6);		
		
			pst.execute();		
			
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}	
	}	
	
	// insertTernant() - function to add new Ternant member:(8 parameters)
	public static void insertTernant(String s1 ,String s2,String s3,String s4,String s5,String s6,String s7,String s8 ){
		
		String sqlInsert = "insert into Committiee.Ternant (Id,Username,Password,FirstName,LastName,ApartmentId,Payment,MonthPay) values (?,?,?,?,?,?,?,?)";
		
		try {
			PreparedStatement pst = connect.prepareStatement(sqlInsert);
			pst.setString(1, s1);
			pst.setString(2, s2);
			pst.setString(3, s3);
			pst.setString(4, s4);
			pst.setString(5, s5);
			pst.setString(6, s6);
			pst.setString(7, s7);
			pst.setString(8, s8);
		
			pst.execute();		
			
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}	
	}	

	
	
}