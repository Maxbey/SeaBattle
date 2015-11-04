package game.Ships;
import game.Field.Cell;

public class Patrol extends AbstractShip {
    public Patrol(Cell[] cells){
        super(ShipsConfig.PATROL_SIZE, cells);
    }
}
