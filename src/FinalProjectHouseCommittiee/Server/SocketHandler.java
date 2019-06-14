package FinalProjectHouseCommittiee.Server;
/*
package FinalProjectHouseCommittiee;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.ArrayList;

public class SocketHandler extends Thread {
	
	// Attributes:
	Socket incoming;
	ArrayList<Message>  arr_array;
	private String clientSentence;
	int team;
	String NickNAME;
	static int num = 15;
	int point=0;
	static boolean flag = false;
	
	// Constructor:
	SocketHandler(Socket _in , ArrayList<Message> arr_array)
	{
		this.incoming=_in;
		this.arr_array = arr_array;		
	}
	
	// Run function:
	public void run()
	{	
		try
		{    
           BufferedReader inFromClient = new BufferedReader(new InputStreamReader(incoming.getInputStream())); 
          
          NickNAME = (inFromClient.readLine());
          team = Integer.parseInt((inFromClient.readLine()));
          in("1A2B3C" , incoming , arr_array , team , NickNAME);// save the client Sentence
              
          while(true) {		
	           clientSentence = inFromClient.readLine(); // Receive from client	          
	           if(clientSentence.toLowerCase().equals("bye"))// if the client send bye 
	           {										// we close the connection	        	   
	        	   System.out.println(incoming + " is now disconnect");	        	   
	        	   incoming.close();	        	  
	        	   break;
	           }	           
	       	           
	          in(clientSentence , incoming , arr_array , team , NickNAME);// save the client Sentence
	           
	          if(flag == true)
	          {
	        	  point=0;
	        	  sleep(5000);
	        	  flag = false;	        	  
	          }
	           clientSentence="";	        	          
	        }		
		}
		catch(IOException e)
		{
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static synchronized  void in(String clientSentence , Socket incoming , ArrayList<Message>  arr_array , int team , String nickName ){
		
			arr_array.add(new Message(clientSentence , incoming , team , nickName));	
  
	}
	
}
*/
