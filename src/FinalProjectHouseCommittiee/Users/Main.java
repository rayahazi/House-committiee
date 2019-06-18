package FinalProjectHouseCommittiee.Users;

import java.io.*;
import java.util.Scanner;

public class Main {

	 /* vars */
	 private static user User;
	 private static Person live_user;
	 
	 // main function:
	 public static void main(String args[]) {
	     ServerConnector s = new ServerConnector(); 
	     try {
	         s.create_connection();
	         if (connection_menu())
	             user.menu();
	         else s.closeSocket();
	     } catch (IOException e) {
	         e.printStackTrace();
	     }
	     try {
	         s.closeSocket();
	     } catch (IOException e) {
	         e.printStackTrace();
	     }
	 }

		 
	 public static Boolean connection_menu() throws IOException {
		 
		// Attributes:
	     Scanner scan = new Scanner(System.in);
	     String choice = null;
	     String ConnectOrChangePass = null;
	     Boolean bool = false;
	
	     // Type of user:
	     System.out.println("I am a\n\t 1. Ternant\n\t 2. Manager");
	     while (!bool) {
	         bool = true;
	         choice = scan.nextLine();
	         switch (choice) {
	             case "1":
	                 choice = "ternant";
	                 break;
	             case "2":
	                 choice = "manager";
	                 break;
	             default:
	                 System.out.println("wrong! ");
	                 bool = false;
	                 break;
	         }
	     }
	
	     ServerConnector.OutToServer().writeBytes(choice + "\n");
	     bool = false;
	
	     //user picking if connecting or change username/password
	     while (!bool) {
	         bool = true;
	         System.out.println("Menu:");
	         System.out.println("[1] Connect as user");
	         System.out.println("[2] Change password");
	         ConnectOrChangePass = scan.nextLine();
	         switch (ConnectOrChangePass) {
	             case "1":
	                 ConnectOrChangePass = "connect";
	                 break;
	             case "2":
	                 ConnectOrChangePass = "change";
	                 break;
	             default:
	                 System.out.println("WRONG CREDENTIALS, TRY AGAIN!");
	                 bool = false;
	                 break;
	         }
	     }
	
	     ServerConnector.OutToServer().writeBytes(ConnectOrChangePass + "\n");
	
	     // New user: ternant or manager:
	     String username, password;
	     System.out.print("Username: ");
	     username = scan.nextLine();
	     System.out.print("Password: ");
	     password = scan.nextLine();
	
	     User = new user(username, password); // create new object user that contain username and password
	     System.out.println(User.get_username() + " " + User.get_password());
	     ServerConnector.OutToServerObject().writeObject(User); // send this obj to the server
	
	     switch (choice){
	         case "Ternant":
	             live_user = new Ternant();
	             break;
	         case "Manager":
	             live_user = new Manager();
	             break;
	     }
	
	     String answer = ServerConnector.InFromServer().readLine();
	     if (answer.matches("ok")){
	         System.out.println("Connection succeed!");
	         //live_user.enter_connection();
	         live_user.menu();
	         return true;
	     }
	     else{
	         System.out.println("Connection failed and aborted!");
	         return false;
	     }
	 }

}