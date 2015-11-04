package game.Ships;
import game.Field.Cell;

public class Cruiser extends AbstractShip {
    Cruiser(Cell[] cells){
        super(ShipsConfig.CRUISER_SIZE, cells);
    }
}
