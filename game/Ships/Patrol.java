package game.Ships;
import game.Field.Cell;

public class Patrol extends AbstractShip {
    Patrol(Cell[] cells){
        super(ShipsConfig.PATROL_SIZE, cells);
    }
}
