import java.net.*;
import java.io.*;

public class gameDaemon {
    private static int port = 42456, maxConnections= 0;

    public static void main(String[] args) {
        int i =0;
        try{
            ServerSocket server= new ServerSocket(port);
            Socket socket;
            while ((i++<maxConnections) || maxConnections==0) {

                System.out.println("Waiting for connection");
                socket = server.accept();
                gameThread conn= new gameThread(socket);
                conn.start();
//                System.out.println("Received request");
            }

        }
        catch (IOException e) {
            System.out.println("IOException occurred: " + e.getMessage());
            e.printStackTrace();
        }

    }
}

