package controllers;

import web.Server;

import java.io.IOException;

public class SeaBattleServer {
    Server server;

    public SeaBattleServer(int port) throws Exception {
        server = new Server(port);
        server.start();
    }


}
