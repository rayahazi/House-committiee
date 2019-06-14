package FinalProjectHouseCommittiee.Users;

import java.io.Serializable;

public class Manager extends Person implements Serializable{

	// Private variables:
	private int years;
	
	// Get & Set for each variable:
	// years
	public int getyears() {
		return years;
	}

	public void setyears(int years) {
		this.years = years;
	}
	
	// Constructor:
	public Manager(int id, String firstName, String lastName, String username, String password,int years) {		
		super(id,firstName,lastName,username,password);	
		this.years = years;
	}
/*
	@Override
	public String toString()
	{
		return super.toString() +" "+ getAge();
	}*/
	@Override
	public boolean equals(Object obj)
	{
		if ( obj instanceof Manager)
		{
			Manager l = (Manager) obj;
			return l.getId() == getId();
		}
		return false; 
	}
}
