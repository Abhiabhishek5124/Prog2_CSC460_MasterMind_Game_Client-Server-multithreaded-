import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Random;

public class gameThread extends Thread{
    private Socket client;
    gameThread(Socket c){
        this.client=c;
    }

    public String processGuess(String guess, StringBuilder correct){
        String P ="";
        String C = "";
        for (int i = 0; i < correct.length(); i++) {

            for (int j = 0; j < guess.length(); j++) {
                if (guess.charAt(j)==correct.charAt(i)){
                    if (j==i){
                        P.concat("P");
                    }else {
                        C.concat("C");
                    }
                }
            }

        }
        return C.concat(P);
    }

    public void run(){
        try{
            DataInputStream in= new DataInputStream(client.getInputStream());
//          DataOutputStream out= new DataOutputStream(client.getOutputStream());
            PrintStream out =new PrintStream(client.getOutputStream());
            Random random = new Random();
            StringBuilder codeBuilder = new StringBuilder();

            String letters= "RYGBWO";
            for (int i = 0; i < 4; i++) {
                codeBuilder.append(letters.charAt(random.nextInt(letters.length())));
            }
            System.out.println("Game string to win is : "+ codeBuilder);


            String gameString = "    ";
            int count=0;


            while ((gameString.equals("PPPP"))|| (count<=20)) {
                if (count==0){
                    out.println("GO");
                    System.out.println("GO message has been send");
                }
                count++;
                gameString= in.readUTF();  //read the string from the client
                if (gameString.toLowerCase().equals("quit")){
                    break;
                }
                gameString= processGuess(gameString, codeBuilder);

            }



            client.close();    // if done close socket


        }
        catch (IOException e){
            System.out.println("IOException on socket listen: " + e);
            e.printStackTrace();

        }


    }

}
