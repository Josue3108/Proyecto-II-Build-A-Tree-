package Server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import java.util.StringTokenizer;
import java.util.Vector;


public class Server {

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

        boolean running = true;

        while (running){
            String input = reader.readLine();

            StringTokenizer tokens = new StringTokenizer(input, ":");

            String command = tokens.nextToken();
            String attributes = tokens.nextToken();

            switch (command){
                case "startChallenge":
                    StartChallengeMessage sCMessg = new StartChallengeMessage(attributes);
                    break;
                case "sendToken":
                    StringTokenizer tAtts = new StringTokenizer(attributes);
                    String tArbol = tAtts.nextToken();
                    String tValor = tAtts.nextToken();
                    SendToken sToken = new SendToken(tArbol,tValor);
                    break;
                default:
                    System.out.println("Command not valid. Try: \n startChallenge:<<type of tree>>\n sendToken:<<Type of tree>>,<<value>>");
            }

        }

    }

}
