package game.Field;

import game.Ships.AbstractShip;

public class Cell {
    AbstractShip ship;

    public void bindShip(AbstractShip ship){
        this.ship = ship;
    }
}
