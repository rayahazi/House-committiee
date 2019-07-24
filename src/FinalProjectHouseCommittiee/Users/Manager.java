package FinalProjectHouseCommittiee.Users;


import java.io.BufferedReader;
import java.io.Console;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Vector;

import FinalProjectHouseCommittiee.Server.User;

public class Manager extends User {

	// Menu function to show to user
	public void menu() throws IOException {
        Scanner scan = new Scanner(System.in);
        while (true) {
        	System.out.println("\nManager:\n-------------------");        	
    		System.out.println("--------Menu: --------");
    		System.out.println("[1] Ternant payment plan(per months)");
    		System.out.println("[2] Payment of all ternants in our building");
    		System.out.println("[3] Update payment details for ternant");
    		System.out.println("[4] Monthly income");
    		System.out.println("[0] exit");

            int choice = scan.nextInt();
            switch (choice) {
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
            	ServerConnector.OutToServer().writeBytes("quit" + "\n");
                return;
            }             
          }
        
    }

    private void AllPayments() throws IOException {
        /* This function show paying status of all residents*/
        ServerConnector.OutToServer().writeBytes("paying status all" + "\n");
        System.out.print("All paying status: ");

        String server_line = ServerConnector.InFromServer().readLine();
        while(!server_line.equals("end")){
            System.out.println(server_line);
            server_line = ServerConnector.InFromServer().readLine();
        }
        
    }

    private void TernantPayment() throws IOException {
        /* This function show paying status of a resident*/
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter the apartment number");
        String aptNum = scan.nextLine();
        ServerConnector.OutToServer().writeBytes("paying status resident" + "\n");
        ServerConnector.OutToServer().writeBytes(aptNum + "\n");

        String server_line = ServerConnector.InFromServer().readLine();
        while (!server_line.equals("end")) {
            System.out.println(server_line);
            server_line = ServerConnector.InFromServer().readLine();
        }
        
    }


    private void UpdatePay() throws IOException {
        /* This function update paying status */
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter apartment number");
        String aptnum = scan.nextLine();
        System.out.println("How much money have paid?");
        String money = scan.nextLine();
        Boolean bool = true;
        String month = null;
        while (bool) {
            System.out.println("for which month? 1-12");
            month = String.valueOf(Integer.parseInt(scan.nextLine()));
            if (Integer.parseInt(month) > 0 && Integer.parseInt(month) < 13)
                bool = false;
            else System.out.println("wrong input");
        }
        String str = aptnum + " " + money + " " + month;

        ServerConnector.OutToServer().writeBytes("update payment" + "\n");
        ServerConnector.OutToServer().writeBytes(str + "\n");

        String serverMsg = ServerConnector.InFromServer().readLine();
        while (!serverMsg.equals("end")) {
            System.out.println(serverMsg);
            serverMsg = ServerConnector.InFromServer().readLine();
        }
        
    }

    private void DeletePay() throws IOException {
        /*This function delete this month payment */
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter apartment number to delete payment:");
        String apt_num = scan.nextLine();
        ServerConnector.OutToServer().writeBytes("delete payment" + "\n");
        ServerConnector.OutToServer().writeBytes(apt_num + "\n");
        if (ServerConnector.InFromServer().readLine().toLowerCase().equals("ok"))
            System.out.println("Payment for resident #" + apt_num + " has been deleted");
        else System.out.println("No such resident or no payment , going to menu");
        
    }


}

