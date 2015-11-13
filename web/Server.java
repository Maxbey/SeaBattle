package web;

import java.net.*;
import java.io.*;
import java.util.ArrayList;

public class Server {

    private ArrayList<ConnectionToClient> clientList;
    private ServerSocket serverSocket;

    public Server(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        clientList = new ArrayList<ConnectionToClient>();
    }

    public void start() throws Exception {
        Socket socket = null;

        while(clientList.size() < 2){
            System.out.println("Waiting for a client");
            socket = serverSocket.accept();

            System.out.println("Got a client");
            clientList.add(new ConnectionToClient(socket, this));
        }

        System.out.println(clientList.size());
    }

    public ConnectionToClient getAnotherClient(ConnectionToClient client) throws Exception {
        for(ConnectionToClient connection : clientList){
            if(connection != client){
                return connection;
            }
        }

        throw new Exception("Cannot get another client");
    }
}
