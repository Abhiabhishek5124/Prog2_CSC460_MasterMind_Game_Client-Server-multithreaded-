import java.net.*;
import java.io.*;

public class gameDaemon {
    private static int port = 42456, maxConnections= 0;

    public static void main(String[] args) {
        try{
            // Create a ServerSocket object to listen on the specified port

            ServerSocket server = new ServerSocket(port, 0, InetAddress.getByName("0.0.0.0"));
            Socket socket;
            System.out.println("Waiting for connection");

            // Server loop to continuously accept client connections
            while (true) {
                // Accept incoming client connection
                socket = server.accept();
                gameThread thread= new gameThread(socket);
                // Start the thread to handle the client's request
                thread.start();
                System.out.println("Received request");
            }
        }
        catch (IOException e) {
            // Catch and handle any IOException that might occur
            System.out.println("IOException occurred: " + e.getMessage());
            e.printStackTrace();
        }

    }
}

