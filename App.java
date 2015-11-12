import game.Field.*;
import game.SeaBattle;
import gui.*;

import javax.swing.*;

public class App {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {

            public void run() {;
                try {
                    SeaBattle game = new SeaBattle();
                    MainWindow window = new MainWindow();

                    window.setGame(game);
                    window.renderPlayerField();
                    window.renderEnemyField();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
