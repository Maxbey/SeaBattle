package web;

import controllers.SeaBattleClient;
import game.Field.Point;

import java.io.*;
import java.net.Socket;

public class ConnectionToServer implements Runnable {
    private Socket socket;
    private SeaBattleClient client;

    private ObjectInputStream socketInput;
    private ObjectOutputStream socketOutput;
    private Thread thread;

    ConnectionToServer(Socket socket, SeaBattleClient client) throws IOException {
        this.socket = socket;
        this.client = client;

        thread = new Thread(this);
        thread.start();

        socketInput = new ObjectInputStream(this.socket.getInputStream());
        socketOutput = new ObjectOutputStream(this.socket.getOutputStream());
    }

    public Object write(Object obj) throws Exception {
        socketOutput.writeObject(obj);
        socketOutput.flush();

        return socketInput.readObject();
    }

    public void send(Object object) throws Exception {
        socketOutput.writeObject(object);
        socketOutput.flush();
    }

    public void run(){
        Object object = null;

        while(true){
            try {
                object = socketInput.readObject();

                send(client.getCellOnField(object));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}
