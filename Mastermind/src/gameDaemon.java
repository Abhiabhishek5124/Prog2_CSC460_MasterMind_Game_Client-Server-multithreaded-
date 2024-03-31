import java.net.*;
import java.io.*;

public class gameDaemon {
    private static int port = 42456, maxConnections= 0;


    public static void main(String[] args) {
        int i =0;
        DataInputStream in;
        DataOutputStream out;
        try{
            ServerSocket server= new ServerSocket(port);
            Socket socket;
            while ((i++<maxConnections) || maxConnections==0) {

                System.out.println("Waiting for connection");
                socket = server.accept();
                gameThread conn_c= new gameThread(socket);
                conn_c.start();
//                System.out.println("Received request");
            }

//            in=new DataInputStream(toclientsocket.getInputStream());
//            out= new DataOutputStream(toclientsocket.getOutputStream());
//          PrintWriter out = new PrintWriter(outstream,true);
//          BufferedReader in = new BufferedReader(new InputStreamReader(instream));

//            double D= in.readDouble();
//            D=D*D;
//            out.writeDouble(D);

        }
        catch (IOException e){
            System.out.println("IOException on socket listen: " + e);
            e.printStackTrace();

        }

    }
}