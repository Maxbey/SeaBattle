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

    public Object sendRequest(Object obj) throws Exception {
        return server.write(obj);
    }

    public void connect(SeaBattleClient client) throws Exception {
        server = new ConnectionToServer(socket, client);
    }
}
