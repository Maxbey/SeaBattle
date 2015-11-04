package game.Ships;
import game.Field.Cell;

public class Battleship extends AbstractShip {
    Battleship(Cell[] cells){
        super(ShipsConfig.BATTLESHIP_SIZE, cells);
    }
}
