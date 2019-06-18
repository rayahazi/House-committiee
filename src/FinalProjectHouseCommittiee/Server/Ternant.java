package FinalProjectHouseCommittiee.Server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Ternant extends User {
	
	//Attributes:
    String apartmentNum;
	
	// Constructor:
	Ternant(ObjectInputStream inObject, BufferedReader inFromClient, DataOutputStream outToClient, Socket incoming, String username) {
        super(inObject, inFromClient, outToClient, incoming, username);

    //extract apartmentNum:
    PreparedStatement statement;
    try {
        statement = sql.connect().prepareStatement("SELECT apartmentNum FROM ternant WHERE username = ?");
        statement.setString(1, username);

        ResultSet result = statement.executeQuery();

        result.next();
        this.apartmentNum = result.getString("apartmentNum");
    } 
    catch (SQLException e) {
        e.printStackTrace();
    	}
	}

	
	/* Menu for Ternant:
	 *  1 for ternant's payments
	 *  0 for exit
	 * */	 
    @Override
    public void menu() throws SQLException{
        while(true) try {
            int choice = inFromClient.read();
            switch (choice) {
                case 1:
                    TernantPaidMonths();
                    break;               
                case 0:
                    return;
                default:
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
    // Function to show ternant his payed months already: 
    private void TernantPaidMonths() throws SQLException, IOException {
        PreparedStatement statement;
		statement = sql.connect().prepareStatement("SELECT * from paidmonth WHERE apartmentNum = ?");
        statement.setString(1, apartmentNum);
        ResultSet result = statement.executeQuery();

        String str = "";
        if(result.next()){
            for (int i = 2; i <= 13; ++i)
                if(result.getString(i).equals("1"))
                    str += i + " ";
        }
        if (str.equals("")) str = "none";
        outToClient.writeBytes(str + "\n");
        outToClient.writeBytes("end" + "\n");
    }
}