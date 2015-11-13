package game.Field;

import game.Ships.AbstractShip;

import java.io.Serializable;

public class Cell implements Cloneable, Serializable{
    int x;
    int y;

    AbstractShip ship;
    boolean attacked;


    Cell(int x, int y){
        this.x = x;
        this.y = y;

        attacked = false;
    }

    public void bindShip(AbstractShip ship){
        this.ship = ship;
    }

    public void setWasAttacked(){
        attacked = true;
    }

    public boolean isAttacked(){
        return attacked;
    }

    public boolean isShip(){
        return ship != null;
    }

    public AbstractShip getShip() {
        return ship;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException{
        return super.clone();
    }
}
