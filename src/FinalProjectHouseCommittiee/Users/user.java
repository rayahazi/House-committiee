package FinalProjectHouseCommittiee.Users;

import java.io.IOException;
import java.io.Serializable;

public class user {
	
	 //Attributes:
    private String username;
    private String password;
    
    // Constructor:
    public user(String username, String password){
        this.username = username;
        this.password = password;
    }

    // definitions of functions:
    public String get_password(){return this.password;}
    public String get_username(){ return this.username;}
    public static void menu() throws IOException {}
}