package FinalProjectHouseCommittiee.Server;

import java.sql.*;

public class sql {

    private static Connection connect;

    // Constructor:
    sql(){
        connection();
        ConnectingToSQL();
    }
    
    public static synchronized Connection connect() {
        return connect;
    }

    public static void connection()
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {          
            e.printStackTrace();
        }
    }

    public static void ConnectingToSQL()
    {
        connection();
        String host = "jdbc:mysql://localhost:3306/Committiee";
        String username = "raya";
        String password = "";


        try {
            connect = (Connection) DriverManager.getConnection(host, username, password);
            System.out.println("Connected to DB");
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
    
   
}