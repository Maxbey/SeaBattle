package game.Ships;

import game.Field.Cell;

public class AbstractShip {
    protected int size;
    protected int strength;

    protected Cell[] cells;

    AbstractShip(int size, Cell[] cells){
        this.size = size;
        strength = size;
        bindCells(cells);
    }

    public int getSize() {
        return size;
    }

    public int getStrength() {
        return strength;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void minusStrength(){
        strength -= 1;
    }

    public boolean isAlive(){
        return strength > 0;
    }

    protected void bindCells(Cell[] cells){
        this.cells = cells;

        for(Cell cell : cells){
            cell.bindShip(this);
        }
    }
}
