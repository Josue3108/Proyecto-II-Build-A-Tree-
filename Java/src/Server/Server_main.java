package Server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import java.util.StringTokenizer;



public class Server_main {

    //PORT TO LISTEN TO

    static int PORT = 8000;

    public static void main(String[] args) throws IOException{
        final boolean[] flag1 = {true};

        //READER FROM THE CONSOLE

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));


        //Ask to type the port


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
                    System.out.println("Client connected successfully");

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };

        startSocket.start();


        boolean running = true;

        while (running){
            String input = reader.readLine();
            if (!input.equals("")) {
                StringTokenizer tokens = new StringTokenizer(input, ":");

                String command = tokens.nextToken();
                String attributes = tokens.nextToken();

                switch (command) {
                    case "startChallenge":
                        StartChallengeMessage sCMessg = new StartChallengeMessage(attributes);
                        break;
                    case "sendToken":
                        StringTokenizer tAtts = new StringTokenizer(attributes);
                        String tArbol = tAtts.nextToken();
                        String tValor = tAtts.nextToken();
                        SendToken sToken = new SendToken(tArbol, tValor);
                        break;
                    default:
                        System.out.println("Command not valid. Try:");
                        System.out.println("* startChallenge:<<type of tree>>");
                        System.out.println("* sendToken:<<Type of tree>>,<<value>>");
                }
            } else {
                System.out.println("Command not valid. Try:");
                System.out.println("* startChallenge:<<type of tree>>");
                System.out.println("* sendToken:<<Type of tree>>,<<value>>");
            }
        }

    }

}
