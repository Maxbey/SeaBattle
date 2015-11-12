package web;

import java.io.*;
import java.net.Socket;

public class Client {
    private ConnectionToServer server;
    Socket socket;

    public Client(String ip, int port) throws IOException {
        socket = new Socket(ip, port);
        server = new ConnectionToServer(socket);
    }

    public void sendRequest(String str) throws IOException {
        server.write(str);
    }
}
