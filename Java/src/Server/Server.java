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

        while (true){
            try {
                server = new ServerSocket(PORT);
                break;
            } catch (IOException e){
                PORT +=1;
                System.out.println("Port busy, selecting next port...");
            }
        }

        System.out.println("Server started listening on port: "+PORT);

        System.out.println("Waiting for client...");

        final Socket[] socket = new Socket[1];


        ServerSocket finalServer = server;
        Thread startSocket = new Thread() {
            public void run() {
                try {
                    socket[0] = finalServer.accept();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };

        startSocket.start();
    }

}
