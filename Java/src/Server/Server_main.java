package Server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import java.util.StringTokenizer;

public class Server_main {

    //PORT TO LISTEN TO
    static int PORT = 8000;
    static String[] trees = {"AVL","BST","BT", "SPL"};
    static PlayerManager[] players;
    static boolean[] onChallenge = {false};

    public static void  sendMessage(String msg, OutputStream os) throws IOException {
        byte [] toSendBytes = msg.getBytes();
        int toSendLen = toSendBytes.length;
        byte[] toSendLenBytes = new byte[4];
        toSendLenBytes[0] = (byte)(toSendLen & 0xff);
        toSendLenBytes[1] = (byte)((toSendLen >> 8) & 0xff);
        toSendLenBytes[2] = (byte)((toSendLen >> 16) & 0xff);
        toSendLenBytes[3] = (byte)((toSendLen >> 24) & 0xff);
        os.write(toSendLenBytes);
        os.write(toSendBytes);
    }

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
        final InputStream[] is = {null};
        final OutputStream[] os = {null};


        Thread startReading = new Thread() {
            public void run() {
                while (true){
                    try {
                        byte [] lenBytes = new byte[4];
                        is[0].read(lenBytes,0,4);

                        int len = (((lenBytes[3] & 0xff) << 24) | ((lenBytes[2] & 0xff) << 16) |
                                ((lenBytes[1] & 0xff) << 8) | (lenBytes[0] & 0xff));
                        byte[] receivedBytes = new byte[len];

                        is[0].read(receivedBytes,0,len);

                        String msg = new String(receivedBytes,0,len);

                        System.out.println(msg);

                        StringTokenizer tokens = new StringTokenizer(msg, ":");

                        String msg_type = tokens.nextToken();

                        switch (msg_type){
                            case "token":
                                if (onChallenge[0]){
                                    int playerNumber = Integer.parseInt(tokens.nextToken());
                                    //if tree type equals token type
                                    //token:TYPE:VALUE
                                    players[playerNumber].addPuntos(4);
                                    sendMessage("Add:"+playerNumber+":4",os[0]);

                                    int tokenValue = Integer.parseInt(tokens.nextToken());
                                    sendMessage(players[playerNumber].addToTree(tokenValue),os[0]);
                                }


                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        };

        ServerSocket finalServer = server;

        Thread startSocket = new Thread() {
            public void run() {
                try {
                    socket[0] = finalServer.accept();

                    is[0] = socket[0].getInputStream();
                    os[0] = socket[0].getOutputStream();
                    System.out.println("Client connected successfully");
                    startReading.start();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };

        startSocket.start();


        Thread startChallenge = new Thread() {
            public void run() {
                //sendMessage("Start Challenge:"+"");

            }
        };

        boolean running = true;


        while (running){
            String input = reader.readLine();
            sendMessage(input, os[0]);

        }
    }

}
