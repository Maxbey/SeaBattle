package game.Ships;
import game.Field.Cell;

public class Destroyer extends AbstractShip{
    Destroyer(Cell[] cells){
        super(ShipsConfig.DESTROYER_SIZE, cells);
    }
}

