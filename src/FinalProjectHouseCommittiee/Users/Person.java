package FinalProjectHouseCommittiee.Users;

import java.io.IOException;

/*
  Person class:
  will bee the higher class (Ternant and Manger will inherit it).
  
 */

import java.io.Serializable;

public abstract class Person {
	
	/* Function that will pop a message "PRESS ENTER TO CONTINUE" */
	static void press_enter() throws IOException {
	    System.out.println("Press enter to continue");
	    System.in.read();
	}
	
	public void menu() throws IOException {}
	
	protected static ServerConnector s;
}

