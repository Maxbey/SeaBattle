package web;

import java.io.*;
import java.net.Socket;

public class ConnectionToServer implements Runnable {
    private Socket socket;

    private DataInputStream socketInput;
    private DataOutputStream socketOutput;
    private Thread thread;

    ConnectionToServer(Socket socket) throws IOException {
        this.socket = socket;

        thread = new Thread(this);
        thread.start();

        socketInput = new DataInputStream(this.socket.getInputStream());
        socketOutput = new DataOutputStream(this.socket.getOutputStream());

        String line = null;
    }

    public void write(String str) throws IOException {
        socketOutput.writeUTF(str);
        socketOutput.flush();
    }

    public void run(){
        String line = null;

        while(true){
            try {
                line = socketInput.readUTF();
                System.out.println("Recive from server: " + line);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
