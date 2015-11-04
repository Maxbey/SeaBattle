package game.Ships;
import game.Field.Cell;

public class Cruiser extends AbstractShip {
    public Cruiser(Cell[] cells){
        super(ShipsConfig.CRUISER_SIZE, cells);
    }
}
