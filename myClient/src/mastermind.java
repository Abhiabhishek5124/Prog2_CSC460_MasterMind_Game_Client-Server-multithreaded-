import java.io.*;
import java.net.*;
import java.util.Scanner;

public class mastermind {

    // Method to verify the input guess
    public static Boolean verifyInput(String guess){
        // Check if the input guess is "QUIT" to end the game
        if (guess.toUpperCase().equals("QUIT")){
            return true;
        }
        // Check if the input guess is not 4 characters long
        if (guess.length()!=4){
            System.out.println("Invalid guess !! The guess should be 4 characters long");
            return false;
        } else {
            // Check if the input guess contains only valid colors (R, Y, G, B, W, O)
            for (int i = 0; i < guess.length(); i++) {
                char ch = guess.charAt(i);
                if (ch != 'R' && ch != 'Y' && ch != 'G' && ch != 'B' && ch != 'W' && ch != 'O') {
                    System.out.println("Invalid guess !! . You have to guess a 4 color code using only the letters ( R, Y, G, B, W, O).");
                    return false;
                }
            }
        }
        return true;
    }



    public static void main(String[] args) {

//        System.out.println("Client is attempting connection");
        try{
            // Establish connection with the server
            Socket toserversocket=new Socket("localhost",42456);
            DataInputStream instream=new DataInputStream(toserversocket.getInputStream());
            DataOutputStream outstream= new DataOutputStream(toserversocket.getOutputStream());

//            System.out.println("Connection has been made. Welcome to masterMind");
            // Initialize variables
            int counter = 0;
            Scanner input = new Scanner(System.in);
            String guessString="";
            String result;
            System.out.println(guessString);
            // Game loop
            while ((counter<20) && (!guessString.toUpperCase().equals("QUIT"))){
                if (counter==0) {
                    result = instream.readUTF();
//                    System.out.println(result);
                    if (result.equals("GO")) {
                        // Welcome message and game rules
                        System.out.println("Welcome to Mastermind. You will try to guess a 4 color code using\n" +
                                "only the letters ( R, Y, G, B, W, O).  You will lose the game if you\n" +
                                "are unable to guess the code correctly in 20 guesses.  Good Luck!\n");
                    }
                }
                // Prompt user for guess
                do{
                    System.out.print("Please enter your guess for the secret code or “QUIT” : ");
                    guessString=input.nextLine();
                    // Check if user wants to quit the game
                    if (guessString.toUpperCase().equals("QUIT")){
                        System.out.println("GOODBYE but please play again!");
                        outstream.writeUTF(guessString);
                        return;
                    }
                }while(!verifyInput(guessString));

                // Send the guess to the server and receive result
                outstream.writeUTF(guessString);
                counter++;
                result= instream.readUTF();
                System.out.println(guessString + " " +result);

                // Check if user guessed correctly
                if (result.equals("PPPP")){
                    System.out.println();
                    System.out.println("Congratulations!!! You guessed the code correctly in "+ counter+ " moves");
                    return;
                }
            }
            System.out.println();
            System.out.println("GOODBYE but please play again!");


        }
        catch (ConnectException e) {
            System.err.println("Error: Unable to establish connection. Please check the server availability and port.");
        } catch (IOException e) {
            System.err.println("Error: Unable to read/write data. Please check your network connection and try again.");
        }
    }
}