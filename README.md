# Prog2_CSC460_MasterMind_Game_Client-Server-multithreaded-


## Overview
This project is a Java-based implementation of the classic Mastermind game, where a client connects to a server to guess a randomly generated 4-color code. The server generates the code, and the client has up to 20 attempts to guess the correct code. The game provides feedback on each guess, indicating correct colors and positions.

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
   - The server will start listening on port `42456`.

3. **Start the Client**:
   - Run the client using the following command:
     ```bash
     java mastermind
     ```
   - The client will connect to the server, and the game will begin.

4. **Play the Game**:
   - The client will prompt you to enter
