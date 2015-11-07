import game.Field.*;
import gui.*;
import javax.swing.*;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                MainWindow window = new MainWindow();
                try {
                    Field playerField = new Field(10 , 10);
                    //Field enemyField = new Field(10, 10);

                    window.createPlayerField(playerField);
                    //window.createEnemyField(enemyField);

                    window.renderPlayerField();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
