package game;

import controllers.SeaBattleClient;
import game.Field.Cell;
import game.Field.Field;
import game.Field.FieldConfig;
import game.Field.Point;
import game.Ships.AbstractShip;
import game.Ships.ShipsConfig;
import gui.MainWindow;
import gui.PlayerField;
import web.Request;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class SeaBattle {
    private Field playerField;
    private SeaBattleClient client;

    private MainWindow window;

    public SeaBattle() throws Exception {
        client = new SeaBattleClient("127.0.0.1", 6666);
        client.setGame(this);

        makeNewPlayerField();
    }

    public void makeNewPlayerField() throws Exception {
        playerField = new Field(10, 10);
        playerField.addShips();

        client.setField(playerField);
    }

    public Cell takeAttack(Point point) throws Exception {
        Cell cell = playerField.getCell(point.getX(), point.getY());
        cell.setWasAttacked();

        if(cell.isShip()){
            AbstractShip ship = cell.getShip();
            ship.minusStrength();

            if(!ship.isAlive()){
                window.getPlayerField().repaintDeadShipCells(cell.getShip());
            }
        }
        else{
            window.getEnemyField().unblock();
        }

        window.getPlayerField().repaintCell(point.getX(), point.getY());

        if(checkGameEnd()){
            loose();
            sendWin();
        }

        return cell;
    }

    public void attack(int x, int y) throws Exception {
        Point point = new Point(x, y);

        client.attack(point);
    }

    public void setWindow(MainWindow window) {
        this.window = window;
    }

    public Field getPlayerField(){
        return playerField;
    }

    public void updateEnemyCell(Cell cell) throws InterruptedException {
        PlayerField field = window.getEnemyField();

        field.updateCell(cell);

        if(!cell.isShip())
            field.block();
    }

    public boolean checkGameEnd(){
        return playerField.getDeadShipsCellsCnt() == playerField.getShipCellsCnt();
    }

    public void win(){
        System.out.println("You win");

        window.getPlayerField().block();
        window.getEnemyField().block();
    };

    public void loose(){
        System.out.println("You loose");
        window.getPlayerField().block();
        window.getEnemyField().block();
    };

    public void sendWin() throws Exception {
        client.sendWin();
    }
}
