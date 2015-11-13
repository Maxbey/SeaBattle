package controllers;

import game.Field.Cell;
import game.Field.Field;
import game.Field.Point;
import web.Client;

import java.io.IOException;

public class SeaBattleClient extends Client {
    private Field field;

    public SeaBattleClient(String host, int port) throws Exception {
        super(host, port);
        connect(this);
    }

    public Cell makeShoot(Point point) throws Exception {
        return (Cell) sendRequest(point);
    }

    public void setField(Field field) {
        this.field = field;
    }

    public Field getField() {
        return field;
    }

    public Cell getCellOnField(Object object){
        Point point = (Point) object;

        return field.getCell(point.getX(), point.getY());
    }
}
