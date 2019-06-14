package FinalProjectHouseCommittiee.Server;
/*
package FinalProjectHouseCommittiee;


import java.io.*; 
import java.net.*; 
import java.util.ArrayList;

class TCPHandler { 
    
	private static ArrayList<Message> arr_array = new ArrayList<Message>();  // the key for the vault
	static int team = 0 ; 
	static String nickName;
	
  public static void main(String argv[]) throws Exception 
    { 
	 
	  ServerSocket s = null;
	 
		try {
		    s = new ServerSocket(1000);
		
		} catch(IOException e) {
		    System.out.println(e);
		    System.exit(1);
		}

		while (true) {
		    Socket incoming = null;
		    
		    try {
			incoming = s.accept();
			
		    } catch(IOException e) {
			System.out.println(e);
			continue;
		    }

		    System.out.println(incoming);
		    
		    
		 		   		    
		    new SocketHandler(incoming , arr_array  ).start();// the first thread for receive from clients
		    new SocketHandler(incoming ,arr_array ).start();// the second thread for sending to 
	        											//the clients
		   team++; 
		}
    } 
} 
*/