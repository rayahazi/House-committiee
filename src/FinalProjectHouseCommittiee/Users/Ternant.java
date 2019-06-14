package FinalProjectHouseCommittiee.Users;

import java.io.Serializable;

public class Ternant extends Person implements Serializable{

	// Private variables:
	private String firstName;
	private String lastName;
	private int apartmentNumber;
	private int payment;
	
	// Get & Set for each variable:
	

	// apartmentNumber
	public int getapartmentNumber() {
		return apartmentNumber;
	}

	public void setapartmentNumber(int apartmentNumber) {
		this.apartmentNumber = apartmentNumber;
	}
	
	// payment
		public int getpayment() {
			return payment;
		}

		public void setpayment(int payment) {
			this.payment = payment;
		}
	
	// Constructor:
	public Ternant(int id, String firstName, String lastName, String username, String password, int apartmentNumber,int payment) {		
		super(id,firstName,lastName,username,password);	
		this.apartmentNumber = apartmentNumber;
		this.payment = payment;
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
		if ( obj instanceof Ternant)
		{
			Ternant l = (Ternant) obj;
			return l.getId() == getId();
		}
		return false; 
	}
}
