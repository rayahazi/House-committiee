package FinalProjectHouseCommittiee.Users;

/*
  Person class:
  will bee the higher class (Ternant and Manger will inherit it).
  
 */

import java.io.Serializable;

public abstract class Person implements Serializable {

	private int id;
	private String firstName;
	private String lastName;
	private String username;
	private String password;
	
	// firstName
	public String getfirstName() {
		return firstName;
	}

	public void setfirstName(String firstName) {
		this.firstName = firstName;
	}
	
	// lastName
	public String getlastName() {
		return lastName;
	}

	public void setlastName(String lastName) {
		this.lastName = lastName;
	}

	// username
	public String getusername() {
		return username;
	}

	public void setusername(String username) {
		this.username = username;
	}
	
	// password
	public String getpassword() {
		return password;
	}

	public void setpassword(String password) {
		this.password = password;
	}
	
	public int getId() {
		return id;
	}
	
	// Constructor: 
	public Person(int id,String firstName,String lastName, String username,String password) {		
		this.id = id;
		this.username = username;
		this.password = password;
	}

	@Override 
	public String toString(){
		
		return getusername() +" " + getId()+"\n";
	}
	
}
