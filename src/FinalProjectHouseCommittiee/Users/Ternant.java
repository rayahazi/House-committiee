package FinalProjectHouseCommittiee.Users;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Scanner;

import FinalProjectHouseCommittiee.Server.User;

public class Ternant extends Person {

	// Menu: 
	public void menu() throws IOException {
        Scanner scan = new Scanner(System.in); //use class Scanner to get input from user

        while (true){
            System.out.println("-------------------" + "\n"
                    +"\tTernant menu" + "\n"
                    +"-------------------" + "\n"
                    + "[1] See your paayments" + "\n"                  
                    + "3. Quit");
            int choice;
            try { choice = scan.nextInt();
                switch (choice){
                    case 1:
                        Payments();
                        break;                    
                    case 0:
                        ServerConnector.OutToServer().writeBytes("quit\n");
                        return;
                    default: break;
                }
            } catch(NoSuchElementException e){
		    e.getStackTrace();
	    }
        }
    }

    // Function to see ternant's payments
    private void Payments() throws IOException {
        String str;
        ServerConnector.OutToServer().writeBytes("get_Paid_Months" + "\n");
        str = ServerConnector.InFromServer().readLine();
        System.out.println("payments you paid: " + str);
    }
}
