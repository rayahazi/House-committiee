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
                    +"\tResident menu" + "\n"
                    +"-------------------" + "\n"
                    + "1. Check what you've paid" + "\n"
                    + "2. send eMail to committee" + "\n"
                    + "3. Quit");
            String choice;
            try { choice = scan.nextLine();
                switch (choice){
                    case "1":
                        check_what_paid();
                        break;
                    case "2":
                        sendMail();
                        break;
                    case "3":
                        ServerConnector.OutToServer().writeBytes("quit" + "\n");
                        return;
                    default: break;
                }
            } catch(NoSuchElementException e){e.getStackTrace();}
        }
    }

    // Function that check what paid
    private void check_what_paid() throws IOException {
        String str;
        ServerConnector.OutToServer().writeBytes("get_Paid_Months" + "\n");
        str = ServerConnector.InFromServer().readLine();
        System.out.println("You've paid for months: " + str);
        press_enter();
    }

    // function that send mail
    private void sendMail() throws IOException {
        Scanner scan = new Scanner(System.in);

        System.out.println("Please enter your message: ");
        String str = scan.nextLine();

        ServerConnector.OutToServer().writeBytes("sendMail" + "\n");
        ServerConnector.OutToServer().writeBytes(str + "\n");

        System.out.println("Message sent!");
        press_enter();
    }
}