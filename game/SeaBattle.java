package game;

import controllers.SeaBattleClient;
import game.Field.Field;
import game.Field.Point;
import gui.MainWindow;

import java.io.IOException;

public class SeaBattle {
    private Field playerField;
    private SeaBattleClient client;

    public SeaBattle() throws Exception {
        client = new SeaBattleClient("127.0.0.1", 6666);
        makeNewPlayerField();
    }

    public void makeNewPlayerField() throws Exception {
        playerField = new Field(10, 10);
        playerField.addShips();
    }

    public void play() {

    }

    public void makeShoot(int x, int y) throws IOException {
        Point point = new Point(x, y);

        client.makeShoot("Shoot to x: " + x + " y: " + y);
    }

    public Field getPlayerField(){
        return playerField;
    }
}
