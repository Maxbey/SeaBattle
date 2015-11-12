package controllers;

import game.Field.Point;
import web.Client;

import java.io.IOException;

public class SeaBattleClient {
    Client client;

    public SeaBattleClient(String host, int port) throws IOException {
        client = new Client(host, port);
    }

    public void makeShoot(String str) throws IOException {
        client.sendRequest(str);
    }
}
