package gui;

import game.*;
import game.Field.Field;

import java.awt.event.*;
import javax.swing.*;

public class MainWindow extends JFrame {
    private PlayerField playerField;
    private PlayerField enemyField;

    public MainWindow(){
        setTitle("Sea Battle Multiplayer with GUI & Sockets");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(300, 300);

        playerField = new PlayerField(this, true);
        enemyField = new PlayerField(this, false);

        add(playerField);
        add(enemyField);

        playerField.setLocation(10, 10);
        enemyField.setLocation(GuiConfig.FIELD_WIDTH + 20, 10);

        setLayout(null);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    public void renderGameField(Field playerField, Field enemyField){
        //
    }

    public void renderMenuBar(){
        //
    }
}
