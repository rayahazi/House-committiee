package FinalProjectHouseCommittiee.CODE;

import java.util.Scanner;

public class Main {
	
		// Main function:
		public static void main(String[] args) {			
			/*
			 *  1 - Connect java code to MySql
			 *  2 - Go to Login() function - if user exists correctly - will move forward to menu (per ternant / manager) 
			 */
			Sql sql = new Sql();
			sql.ConectingToSQL();			
		    sql.Login();
			//select_query(level);
		    //selectAll_query(2);	
		    //sql.selectAll_query(1);
		}
}
