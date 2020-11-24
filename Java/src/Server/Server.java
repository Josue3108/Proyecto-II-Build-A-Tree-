package Server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import java.util.Vector;


public class Server {
    static Vector<ClientHandler> clientList = new Vector<>();
    static int i = 0;

    //PORT TO LISTEN TO

    static int PORT;

    public static void main(String[] args) throws IOException{
        final boolean[] flag1 = {true};

        //READER FROM THE CONSOLE

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));


        //Ask to type the port

        System.out.println("Select a port to start the server on");

        String inputPort = reader.readLine();

        while (true){
            try{
                PORT = Integer.parseInt(inputPort);
                break;
            } catch (NumberFormatException e){

                System.out.println("Input a valid port to listen to...");
                System.out.println("Select a port to start the server on...");

                inputPort = reader.readLine();
            }
        }

        ServerSocket server;


    }

}
