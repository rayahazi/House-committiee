package FinalProjectHouseCommittiee.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/*
 * Main class:
 * 1. Create ServerSocket with port 5000 (foor example)
 * 
 */


public class Main {
 
    public static void main(String[] args) {
        
    	// Attributes:
        ServerSocket s = null;
        sql data;

        // try connection:
        try {
            s = new ServerSocket(5000);
            data = new sql();
        } 
        catch (IOException e) {
            System.out.println(e);
            System.exit(1);
        }

        // accept incoming data
        while (true) {
            Socket incoming = null;
            try {
                incoming = s.accept();
            } 
            catch (IOException e) {
                System.out.println(e);
                continue;
            }

            System.out.println(incoming);
            new SocketHandler(incoming).start();
        }
    }
    
}