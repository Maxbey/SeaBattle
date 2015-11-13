package web;

import game.Field.Point;

import java.io.*;
import java.net.Socket;

public class ConnectionToClient implements Runnable {
    private Socket socket;
    private Server server;

    private Thread thread;

    private ObjectOutputStream socketOutput;
    private ObjectInputStream socketInput;

    public ConnectionToClient(Socket socket, Server server) throws IOException {
        this.socket = socket;
        this.server = server;

        thread = new Thread(this);
        thread.start();

        socketOutput = new ObjectOutputStream(this.socket.getOutputStream());
        socketInput = new ObjectInputStream(this.socket.getInputStream());
    }

    String line = null;

    public void run() {

        Object object = null;
        while (true) {
            try {
                object = socketInput.readObject();
                ConnectionToClient anotherClient = server.getAnotherClient(this);

                send(anotherClient.write(object));

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public Object write(Object obj) throws Exception {
        socketOutput.writeObject(obj);
        socketOutput.flush();

        return socketInput.readObject();
    }

    public void send(Object obj) throws Exception {
        socketOutput.writeObject(obj);
        socketOutput.flush();
    }

}
