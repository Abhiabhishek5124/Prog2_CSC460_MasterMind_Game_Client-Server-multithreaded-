import java.io.*;
import java.net.Socket;
import java.util.Random;

public class gameThread extends Thread {
    private final Socket client;

    public gameThread(Socket clientSocket) {
        this.client = clientSocket;
    }

    // Method to process the user's guess and generate the response
    private static String processGuess(String guess, String code) {
        StringBuilder response = new StringBuilder();
        StringBuilder remainingGuess = new StringBuilder(guess);
        StringBuilder remainingCode = new StringBuilder(code);

        // First pass: Check for correct color and position
        for (int i = 0; i < 4; i++) {
            char guessedChar = guess.charAt(i);
            char codeChar = code.charAt(i);

            if (guessedChar == codeChar) {
                response.append('P');  // Correct color and position
                remainingGuess.setCharAt(i, ' '); // Mark the character in guess as already used
                remainingCode.setCharAt(i, ' ');
            }
        }

        // Second pass: Check for correct color but wrong position
        if (response.length() < 4) { // Not all positions filled with 'P'
            for (int i = 0; i < 4; i++) {
                char codeChar = remainingCode.charAt(i);
                if (codeChar != ' ') {
                    int index = remainingGuess.indexOf(String.valueOf(codeChar));

                    if (index != -1) {
                        response.append('C');  // Correct color but wrong position
                        remainingGuess.setCharAt(index, ' '); // Mark the character in guess as already used
                    }
                }
            }
        }

        return response.reverse().toString(); // Reverse the response for correct output order
    }

    // Override the run method of Thread class for thread execution
    @Override
    public void run() {
        try (DataInputStream in = new DataInputStream(client.getInputStream());
             DataOutputStream out = new DataOutputStream(client.getOutputStream())) {

            // Generate a random code for the game
            Random random = new Random();
            StringBuilder codeBuilder = new StringBuilder();
            String letters = "RYGBWO";
            for (int i = 0; i < 4; i++) {
                codeBuilder.append(letters.charAt(random.nextInt(letters.length())));
            }
            System.out.println("Game string to win is: " + codeBuilder);

            int count = 0;
            while (count <= 20) { // Limit the number of guesses to 20
                if (count == 0) {
                    out.writeUTF("GO"); // Send the start message to the client
                    System.out.println("GO message has been sent");
                }
                count++;

                String gameString = in.readUTF(); // Read the user's guess from the client
                System.out.println("The guess is: " + gameString);
                if (gameString.equalsIgnoreCase("quit")) {
                    break; // Exit the loop if the user wants to quit
                }
                gameString = processGuess(gameString, codeBuilder.toString()); // Process the guess
                System.out.println("The gameString is: " + gameString);
                out.writeUTF(gameString); // Send the response to the client
            }

            client.close(); // Close the socket when done
        } catch (IOException e) {
            System.out.println("IOException on socket listen: " + e);
            e.printStackTrace();
        }
    }
}
