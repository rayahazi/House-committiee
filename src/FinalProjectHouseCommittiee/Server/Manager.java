package FinalProjectHouseCommittiee.Server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Vector;

/*
 * Class Manager - 
 * will handle user of type - manager 
 * 
 */


public class Manager extends User {


	// Constructor:
    public Manager(ObjectInputStream inObject, BufferedReader inFromClient, DataOutputStream outToClient,
			Socket incoming, String username) {
		super(inObject, inFromClient, outToClient, incoming, username);	}

	/* Menu for Manager:
	 * [1] Ternant payment plan
	 * [2] Payment of all ternants in our building
	 * [3] Update payment details for ternant
	 * [4] Monthly income 
	 *  0 for exit
	 * */
	@Override
    public void menu() throws IOException {
        while (true){
            try {
            	// Output to client: see options in menu
        		System.out.println("\nManager:\n-------------------");        	
        		System.out.println("--------Menu: --------");
        		System.out.println("[1] Ternant payment plan(per months)");
        		System.out.println("[2] Payment of all ternants in our building");
        		System.out.println("[3] Update payment details for ternant");
        		System.out.println("[4] Monthly income");
        		System.out.println("[0] exit");
        		
                int res = inFromClient.read();
                switch (res) {
                    case 1:
                    	TernantPayment();
                        break;
                    case 2:
                    	AllPayments();
                        break;
                    case 3:
                    	DeletePay();
                        break;
                    case 4:
                        UpdatePay();
                        break;                  
                    case 0:
                        return;

                }
            }catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

	// case 1: specific ternant's payment:
    private void TernantPayment() throws IOException, SQLException {
        String apartmentNum = inFromClient.readLine();

        
        PreparedStatement check_if_exist = sql.connect().prepareStatement("select FirstName,LastName,monthPay from ternant where ApartmentId= ?");
        check_if_exist.setString(1, apartmentNum);
        ResultSet check_result = check_if_exist.executeQuery();

        // If apartment's num isn't exist in DB:
        if (!check_result.next()) {
            outToClient.writeBytes("Wrong parameter!" + "\n");
            outToClient.writeBytes("end" + "\n");
            return;
        }

        // If exists:
        PreparedStatement statement = sql.connect().prepareStatement("select FirstName,LastName,monthPay from ternant where ApartmentId= ?");
        statement.setString(1, apartmentNum);
        ResultSet result = statement.executeQuery();

        if (result.next()) {
            StringBuilder answers = new StringBuilder("Ternant: " + apartmentNum + " paid for:  "
                    + "\t");
            for (int i = 2; i <= 13; ++i) {
                if (Integer.parseInt(result.getString("m" + (i - 1))) > 0)
                    answers.append(i - 1);
            }
            outToClient.writeBytes(answers.toString() + "\n");
        } else outToClient.writeBytes("No payments for this ternant" + "\n");

        outToClient.writeBytes("end" + "\n");
    }

	
    // case 2: 
    private void AllPayments() throws SQLException, IOException {
    	String queryString = "select ApartmentId,monthPay from ternant";			
		String answerString = sql.select_query(1, queryString);
        outToClient.writeBytes(answerString.toString() + "\n");
        outToClient.writeBytes("end" + "\n");
    }

    
    private void display_income() throws SQLException, IOException {
        /* This function display income from db*/
        for (Integer i = 2; i < 14; i++) {
            String str = "SELECT SUM(m" + String.valueOf(i-1) + ") FROM paidmonth";
            PreparedStatement statement = sql.connect().prepareStatement(str);
            ResultSet result = statement.executeQuery();
            result.next();
            String sum = result.getString(1);
            /* Send back to the client tha total income payments for month */
            outToClient.writeBytes("Total income for month " +  String.valueOf(i-1) + ": " + sum + "\n");
        }
        outToClient.writeBytes("ok" + "\n");
    }

    
    
    // Delete function: 
    private void DeletePay() throws SQLException, IOException {
        /* This function delete payment from db*/
        String apartmentNum = inFromClient.readLine();
        PreparedStatement statement = sql.connect().prepareStatement("SELECT apartmentNum FROM resident WHERE apartmentNum = ?");
        statement.setString(1, apartmentNum);
        ResultSet result = statement.executeQuery();
        if (!result.next()) {
            outToClient.writeBytes("nosuch" + "\n");
            return;
        }
        String month = "m";
        Calendar cal = Calendar.getInstance();
        month += String.valueOf(cal.get(Calendar.MONTH) + 1);
        String str = "UPDATE paidmonth SET ";
        str += month;
        PreparedStatement updatestatement =
                sql.connect().prepareStatement(str + " = 0 WHERE apartmentNum = ?");
        updatestatement.setString(1, apartmentNum);
        updatestatement.executeUpdate();
        outToClient.writeBytes("ok\n");
    }
    
    
    
    private void UpdatePay() throws IOException, SQLException, ClassNotFoundException {
        //This function update payment in db
        String str[] = inFromClient.readLine().split(" "); //apartmentNum, payment, month

        System.out.println("apartmentNum " + str[0] + "payment " + str[1] + "month " + str[2]);
        PreparedStatement statement = sql.connect().prepareStatement(
                "select * from ternant where apartmentNum = ?");
        statement.setString(1, str[0]);
        ResultSet result = statement.executeQuery();

        if (!result.next()) {
            outToClient.writeBytes("wrong parameter\n");
            outToClient.writeBytes("end\n");
            return;
        }

        str[2] = 'm' + str[2];
        String Qstr = "update paidmonth set " + str[2] + "= ";
        PreparedStatement updateStatement = sql.connect().prepareStatement(Qstr + "? where apartmentNum = ?");
        updateStatement.setString(1, str[1]);
        updateStatement.setString(2, str[0]);
        updateStatement.executeUpdate();

        /* Send back to the client that payment as update */
        outToClient.writeBytes("Payment updated for ternant\n");
        outToClient.writeBytes("end\n");
    }


}
