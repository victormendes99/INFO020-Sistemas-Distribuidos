/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consumidor;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MultiThreadChatServer {

    // Declaration section:
    // declare a server socket and a client socket for the server
    // declare an input and an output stream
    static Socket clientSocket = null;
    static ServerSocket serverSocket = null;

    // This chat server can accept up to 10 clients' connections
    static clientThread t[] = new clientThread[10];

    public static void main(String args[]) {

        // The default port
        int port_number = 2222;

        if (args.length < 1) {
            System.out.println("Usage: java MultiThreadChatServer \n"
                    + "Now using port number=" + port_number);
        } else {
            port_number = Integer.valueOf(args[0]);
        }

        // Initialization section:
        // Try to open a server socket on port port_number (default 2222)
        // Note that we can't choose a port less than 1023 if we are not
        // privileged users (root)
        try {
            serverSocket = new ServerSocket(port_number);
        } catch (IOException e) {
            System.out.println(e);
        }

        // Create a socket object from the ServerSocket to listen and accept 
        // connections.
        // Open input and output streams for this socket will be created in 
        // client's thread since every client is served by the server in
        // an individual thread
        while (true) {
            try {
                clientSocket = serverSocket.accept();
                for (int i = 0; i <= 9; i++) {
                    if (t[i] == null) {
                        (t[i] = new clientThread(clientSocket)).start();
                        break;
                    }
                }
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }
}

// This client thread opens the input and the output streams for a particular client,
// ask the client's name, informs all the clients currently connected to the 
// server about the fact that a new client has joined the chat room, 
// and as long as it receive data, echos that data back to all other clients.
// When the client leaves the chat room this thread informs also all the
// clients about that and terminates. 
class clientThread extends Thread {

    DataInputStream is = null;
    PrintStream os = null;
    Socket clientSocket = null;

    public clientThread(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        Programa t = new Programa();
        t.run();

        try {
            Thread.sleep(1);
            clientSocket.close();
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(clientThread.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
