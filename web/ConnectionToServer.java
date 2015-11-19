package web;

import controllers.SeaBattleClient;
import game.Field.Cell;
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

    public void write(Object obj) throws Exception {
        socketOutput.writeObject(obj);
        socketOutput.flush();
    }

    public void run() {
        Object object = null;

        while (true) {
            try {
                object = socketInput.readObject();
                Request request = (Request) object;

                if(request.getType().equals(RequestConfig.CELL_REQUEST)){

                    Cell cell = (Cell) request.getData();
                    client.updateEnemyCell(cell);
                }

                else if(request.getType().equals(RequestConfig.POINT_REQUEST)){
                    Point point = (Point) request.getData();

                    Object result = client.takeAttack(point);
                    Request response = new Request(RequestConfig.CELL_REQUEST, result);

                    write(response);
                }

                else if(request.getType().equals(RequestConfig.WIN_REQUEST)){
                    client.setWin();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}
