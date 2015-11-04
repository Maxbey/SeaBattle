package game;
import game.Field.*;
import game.Ships.AbstractShip;
import game.Ships.Battleship;

public class test {
    public static void main(String[] args) throws Exception {
        Field field = new Field(10, 10);

        field.createField();

            field.addBattleships();
            field.addDestroyers();
            field.addCruisers();
            field.addPatrols();
            int a = 0;
            for(Cell[] row : field.getField()){
                for(Cell cell : row){
                    if(cell.isShip())
                        a++;
                }
            }

            System.out.println("Cells with ships " + a);
    }
}
