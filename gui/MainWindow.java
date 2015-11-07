package gui;

import game.Field.Field;

import java.awt.event.*;
import javax.swing.*;

public class MainWindow extends JFrame {
    private PlayerField playerField;
    private PlayerField enemyField;

    public MainWindow() {
        setTitle("Sea Battle Multiplayer with GUI & Sockets");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(1300, 500);

        setLayout(null);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    public void renderPlayerField() {
        this.playerField.renderField();
    }

    public void renderEnemyField() {
        this.enemyField.renderField();
    }

    public PlayerField getPlayerField() {
        return playerField;
    }

    public PlayerField getEnemyField() {
        return enemyField;
    }

    public void createPlayerField(Field field) {
        playerField = new PlayerField(field, false);
        add(playerField);
        playerField.setLocation(10, 10);
    }

    public void createEnemyField(Field field) {
        enemyField = new PlayerField(field, true);
        add(enemyField);
        enemyField.setLocation(GuiConfig.FIELD_WIDTH + 20, 10);
    }
}
