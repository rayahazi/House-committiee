package FinalProjectHouseCommittiee.Server;

import java.io.*;
import java.net.Socket;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import FinalProjectHouseCommittiee.Users.user;

public class SocketHandler extends Thread {
	
	// Attributes:
	String type;
    ObjectInputStream inObject;
    BufferedReader inFromClient;
    DataOutputStream outToClient;
    Socket incoming;
    User userObj;
	
	// Constructor:
    SocketHandler(Socket incoming) {
        this.incoming = incoming;
    }

    // run() function
    public void run() {
        try {
            inFromClient= new BufferedReader(new InputStreamReader(incoming.getInputStream()));
            outToClient = new DataOutputStream (incoming.getOutputStream() );
            inObject =    new ObjectInputStream(incoming.getInputStream());
        }
        catch (IOException e){
            e.printStackTrace();
        }

        try {
            typeOfUser();
            //changing or connecting
            String str = inFromClient.readLine();
            user User = (user) inObject.readObject();
            if (str.toLowerCase().equals("connect")){ //connecting execution
                if (checkPassword(User)){
                    outToClient.writeBytes("ok\n");}
                else outToClient.writeBytes("wrong\n");
            }
            else if (str.toLowerCase().equals("change")) //changing password execution
                if (changePassword(User))
                    outToClient.writeBytes("ok\n");
                else {
                    outToClient.writeBytes("wrong\n");
                 return;
                }

                if (type.equals("committee"))
                    userObj = new Manager(inObject, inFromClient, outToClient, incoming, User.get_username());
                else if (type.equals("ternant"))
                    userObj = new Ternant(inObject, inFromClient, outToClient, incoming, User.get_username());

                userObj.menu(); 
        } catch (IOException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    }

    /* This function handle with Change password*/
    private boolean changePassword(user user) throws SQLException, IOException {
        PreparedStatement statement;

                if (type.equals("committee"))
                    statement = sql.connect().prepareStatement("SELECT username FROM manager WHERE username = ?");
                else
                    statement = sql.connect().prepareStatement("SELECT username FROM ternant WHERE username = ?");
        statement.setString(1,user.get_username());

        ResultSet result = statement.executeQuery();
        if (result.next()) {
            if (type.equals("committee"))
                statement = sql.connect().prepareStatement("UPDATE manager SET password = ? WHERE username = ?");
            else 
            	statement = sql.connect().prepareStatement("UPDATE ternant SET password = ? WHERE username = ?");

            statement.setString(1,user.get_password());
            statement.setString(2,user.get_username());
            statement.executeUpdate();
            return true;
        }
        outToClient.writeBytes("wrong\n");
        return false;
    }

    // Check password function:
    private boolean checkPassword(user user) throws SQLException {
        PreparedStatement statement;
        if (type.equals("manager"))
        statement = sql.connect().prepareStatement(
                "SELECT username FROM manager WHERE username = ? AND password = ?");
        else statement = sql.connect().prepareStatement(
                "SELECT username FROM ternant WHERE username = ? AND password = ?");

        statement.setString(1, user.get_username());
        statement.setString(2, user.get_password());
        ResultSet result = statement.executeQuery();

        if (result.next())
            return true;
        return false;

    }

    private void typeOfUser() throws IOException {
       // type of user:
        type = inFromClient.readLine();
    }

  
}