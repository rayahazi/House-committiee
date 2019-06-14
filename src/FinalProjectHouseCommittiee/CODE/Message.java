package FinalProjectHouseCommittiee.CODE;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Message {

	private ResultSet result;
	Sql sql = new Sql();	
	
	// Constructor
	public Message(ResultSet result) {
		this.result = result;
	}
	
	// Function to run when user is type ternant
	public void TernantFunctionality() throws SQLException {
		System.out.println("\nTernant:\n-------------------");
		System.out.println("User: "+result.getString(1) +' '+ result.getString(2) +' '+ result.getString(3)+' '+ result.getString(4)+' '+ result.getString(5)+' '+ result.getString(6)+' '+ result.getString(7));							
		System.out.println("--------Menu: --------");
		System.out.println("Your payments");
		String idString = result.getString(1);
		String queryString = "select monthPay from ternant where Id="+idString ;			
		String answerString = sql.select_query(1, queryString);
		System.out.println("Months:"+answerString);
	}
	
	
	// Function to run when user is type manager
	public void ManagerFunctionality() throws SQLException {
		
		// Output to client: see options in menu
		System.out.println("\nManager:\n-------------------");
		System.out.println("User: "+result.getString(1) +' '+ result.getString(2) +' '+ result.getString(3)+' '+ result.getString(4)+' '+ result.getString(5)+' '+ result.getString(6));							
		System.out.println("--------Menu: --------");
		System.out.println("[1] Ternant payment plan(per months)");
		System.out.println("[2] Payment of all ternants in our building");
		System.out.println("[3] Update payment details for ternant");
		System.out.println("[4] Monthly income");
		
		// values 
		Scanner scanner = new Scanner(System.in);
		int choice = scanner.nextInt(); 
			
		String queryString,answerString=null;
		String[] arr=null;
		Scanner s = new Scanner(System.in);
		// Switch-case for the manager choice:
		switch (choice) {
		// case 1 - go to DB and show ternant's monthPay 
		case 1:
			System.out.println("Enter required apartment number: ");		
			String apartmentNum = scanner.next();
			queryString = "select FirstName,LastName,monthPay from ternant where ApartmentId="+apartmentNum;			
			answerString = sql.select_query(1, queryString);			
			arr=answerString.split("\\s+");
			String pString="";
			for (int i = 2; i < arr.length; i++) {
				pString += arr[i]+", ";
			}
			System.out.println("Name: "+arr[0]+" "+arr[1]+"\nPayed months: "+pString);
			
			break;
		case 2:
			// Get all ternants's payments
			System.out.println("Full list of ternants payments: ");
			queryString = "select ApartmentId,monthPay from ternant";			
			answerString = sql.select_query(1, queryString);				
			System.out.println(answerString);			
			break;
		case 3:
			// Update ternant's monthly payments
			System.out.println("---Update ternant's monthly payments---");			
			System.out.println("Enter ternant's ID: ");
			int Id = scanner.nextInt();
			System.out.println("Enter new payment: ");
			String NewPayment = scanner.next().toString();
			System.out.println("payment: "+NewPayment+ " Id: "+Id);			
			queryString = "UPDATE `Ternant` SET `MonthPay` = "+NewPayment+" WHERE `Id` ="+Id;					
			sql.update_query(queryString);				
			//System.out.println(answerString);	
			break;
		case 4:
			String line=null;
			// String for months and sum of payments per each month:
			String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
		    int[] paymentPerMonth = {0,0,0,0,0,0,0,0,0,0,0,0};
		    int pay;
		    
			System.out.println("Building income per months:\n ");
			queryString = "select MonthPay,Payment from Ternant;";			
			answerString = sql.select_query(1, queryString);				
			BufferedReader bufReader = new BufferedReader(new StringReader(answerString));
			
			try {
				String[] x;
				while( (line=bufReader.readLine()) != null )
				{
					x=line.split("\\s");
					pay =Integer.parseInt(x[x.length-1]);
					for (int i = 0; i < x.length; i++) 
					{
						// If value is month type:
						int val=Integer.parseInt(x[i]);
						if(val>0 && val<=12) {
							paymentPerMonth[val-1]+=pay;
						}
					}				
				}
				for (int i = 0; i < 12; i++) {
					System.out.println(months[i]+": "+paymentPerMonth[i]);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			break;
		default:
			break;
		}
		
	}
}
