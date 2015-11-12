package web;

import java.io.*;
import java.net.Socket;

public class ConnectionToClient implements Runnable {
    private Socket socket;
    private Server server;

    private Thread thread;

    private DataOutputStream socketOutput;
    private DataInputStream socketInput;

    public ConnectionToClient(Socket socket, Server server) throws IOException {
        this.socket = socket;
        this.server = server;

        thread = new Thread(this);
        thread.start();

        socketOutput = new DataOutputStream(this.socket.getOutputStream());
        socketInput = new DataInputStream(this.socket.getInputStream());
    }

    String line = null;

    public void run(){
        System.out.println("HERE");
        while(true){
            try {
                line = socketInput.readUTF();
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println("From client: " + line);
            System.out.println("And then am sending it back to all clients...");

            try {
                server.sendToAll(line);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void write(String string) throws IOException {
        socketOutput.writeUTF(string);
        socketOutput.flush();
    }
}
