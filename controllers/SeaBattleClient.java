package controllers;

import game.Field.Cell;
import game.Field.Field;
import game.Field.Point;
import game.SeaBattle;
import web.Client;
import web.Request;
import web.RequestConfig;

import java.io.IOException;

public class SeaBattleClient extends Client {
    private Field field;
    private SeaBattle game;

    public SeaBattleClient(String host, int port) throws Exception {
        super(host, port);
        connect(this);
    }

    public void setField(Field field) {
        this.field = field;
    }

    public Field getField() {
        return field;
    }

    public void attack(Point point) throws Exception {
        Request request = new Request(RequestConfig.POINT_REQUEST, point);

        getConnection().write(request);
    }

    public Cell takeAttack(Point point){
        return game.takeAttack(point);
    }

    public void setGame(SeaBattle game) {
        this.game = game;
    }

    public void updateEnemyCell(Cell cell){
        game.updateEnemyCell(cell);
    }
}
