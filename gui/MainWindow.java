package gui;

import game.Field.Field;
import game.SeaBattle;

import java.awt.event.*;
import javax.swing.*;

public class MainWindow extends JFrame {
    private PlayerField playerField;
    private PlayerField enemyField;

    private SeaBattle game;

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
        playerField = new PlayerField(game.getPlayerField(), false, this);
        add(playerField);
        playerField.setLocation(10, 10);

        this.playerField.renderField();
    }

    public void renderEnemyField() throws Exception {
        enemyField = new PlayerField(new Field(10, 10), true, this);
        add(enemyField);
        enemyField.setLocation(GuiConfig.FIELD_WIDTH + 20, 10);
        this.enemyField.renderField();
    }

    public PlayerField getPlayerField() {
        return playerField;
    }

    public PlayerField getEnemyField() {
        return enemyField;
    }

    public void setGame(SeaBattle game) {
        this.game = game;
    }

    public SeaBattle getGame(){
        return game;
    }
}
