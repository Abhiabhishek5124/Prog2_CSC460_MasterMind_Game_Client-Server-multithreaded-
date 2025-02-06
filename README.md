# Prog2_CSC460_MasterMind_Game_Client-Server-multithreaded-

## Overview
This project is a Java-based implementation of the classic Mastermind game, where a client connects to a server to guess a randomly generated 4-color code. The server generates the code, and the client has up to 20 attempts to guess the correct code. The game provides feedback on each guess, indicating correct colors and positions. This project demonstrates **socket programming**, **multi-threading**, and **client-server communication** in Java, offering hands-on learning in network programming, input validation, and exception handling.

## Features
- **Server-Side Game Logic**: The server generates a random 4-color code using the letters R, Y, G, B, W, O.
- **Client-Side Interaction**: The client interacts with the server to submit guesses and receive feedback.
- **Feedback Mechanism**: The server provides feedback on each guess, indicating:
  - `P` for correct color and position.
  - `C` for correct color but wrong position.
- **Input Validation**: The client validates user input to ensure guesses are 4 characters long and only contain valid colors.
- **Multi-Threaded Server**: The server can handle multiple client connections simultaneously using threads.

## How to Run
1. **Compile the Code**:
   - Compile the Java files using the following command:
     ```bash
     javac gameDaemon.java gameThread.java mastermind.java
     ```

2. **Start the Server**:
   - Run the server using the following command:
     ```bash
     java gameDaemon
     ```
   - The server will start listening on port `42456` and display:
     ```
     Waiting for connection
     ```

3. **Start the Client**:
   - Run the client using the following command:
     ```bash
     java mastermind
     ```
   - The client will connect to the server, and the game will begin. The server will display:
     ```
     Received request
     Game string to win is: [Random 4-color code]
     GO message has been sent
     ```

4. **Play the Game**:
   - The client will prompt you to enter a 4-color guess using the letters R, Y, G, B, W, O:
     ```
     Welcome to Mastermind. You will try to guess a 4 color code using
     only the letters ( R, Y, G, B, W, O). You will lose the game if you
     are unable to guess the code correctly in 20 guesses. Good Luck!

     Please enter your guess for the secret code or “QUIT” : 
     ```
   - Enter your guess (e.g., `RGBY`). The server will process the guess and send feedback (e.g., `PPCC`).  
   - The client will display the result:
     ```
     RGBY PPCC
     ```
   - If you guess the code correctly, the client will display:
     ```
     Congratulations!!! You guessed the code correctly in X moves
     ```
   - If you type `QUIT`, the game will end:
     ```
     GOODBYE but please play again!
     ```

## Example Execution

### Server Output:
