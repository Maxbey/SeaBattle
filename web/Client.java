package web;

import controllers.SeaBattleClient;

import java.io.*;
import java.net.Socket;

public class Client {
    private ConnectionToServer server;
    Socket socket;

    public Client(String ip, int port) throws IOException {
        socket = new Socket(ip, port);

    }

    public void connect(SeaBattleClient client) throws Exception {
        server = new ConnectionToServer(socket, client);
    }

    public ConnectionToServer getConnection(){
        return server;
    }
}
