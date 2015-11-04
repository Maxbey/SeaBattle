package game.Ships;
import game.Field.Cell;

public class Battleship extends AbstractShip {
    public Battleship(Cell[] cells){
        super(ShipsConfig.BATTLESHIP_SIZE, cells);
    }
}
