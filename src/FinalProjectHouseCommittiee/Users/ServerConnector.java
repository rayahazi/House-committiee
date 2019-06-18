package FinalProjectHouseCommittiee.Users;


import java.io.*;
import java.net.Socket;

public class ServerConnector {

    /* vars for create connection */
    private static Socket clientSocket;
    private static DataOutputStream outToServer;
    private static BufferedReader inFromServer;
    private static ObjectOutputStream outToServerObject;

    //This function as create connection
    public static void create_connection() throws IOException {
        clientSocket = new Socket("localhost", 10001); //This new object contain a call server
        try {
            outToServer = new DataOutputStream(clientSocket.getOutputStream());
            inFromServer = new BufferedReader(new
                    InputStreamReader(clientSocket.getInputStream()));
            outToServerObject= new ObjectOutputStream(clientSocket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static DataOutputStream OutToServer() {
        return outToServer;
    }

    public static BufferedReader InFromServer() {
        return inFromServer;
    }

    public static ObjectOutputStream OutToServerObject(){
        return outToServerObject;
    }

    public static void closeSocket() throws IOException {clientSocket.close();}
}