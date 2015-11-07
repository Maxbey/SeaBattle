package gui;

import game.Field.*;

import javax.swing.*;
import java.awt.*;

public class PlayerField extends JComponent {

    private Field field;
    private MainWindow window;

    private boolean gameEnd = false;
    private boolean isOpen;

    private GraphicalCell[][] cells;
    private GraphicalCell selectedCell;

    public PlayerField(MainWindow window, boolean isOpen) {
        this.window = window;
        this.isOpen = isOpen;

        cells =  new GraphicalCell[GuiConfig.CELLS_COUNT][GuiConfig.CELLS_COUNT];

        for(int i = 0; i < GuiConfig.CELLS_COUNT; i++){
            for(int j = 0; j < GuiConfig.CELLS_COUNT; j++){
                cells[j][i] = new GraphicalCell(this, field.getCell(j , i));
            }
        }

        selectedCell = null;

        setSize(GuiConfig.FIELD_WIDTH, GuiConfig.FIELD_HEIGHT);
        renderGamePanel();
    }

    private void renderGamePanel() {
        char[] letters = {'A', '�', '�', '�', '�', '�', '�', '�', '�', '�'};

        for (int i = 0; i < 10; i++) {
            JLabel hr = new JLabel(String.valueOf(i + 1));
            hr.setText(String.valueOf(letters[i]));
            JLabel vr = new JLabel(String.valueOf(i + 1));

            hr.setHorizontalAlignment(JLabel.CENTER);
            hr.setVerticalAlignment(JLabel.CENTER);
            vr.setHorizontalAlignment(JLabel.CENTER);
            vr.setVerticalAlignment(JLabel.CENTER);
            hr.setSize(15, GuiConfig.HEADER_ZONE);
            vr.setSize(GuiConfig.HEADER_ZONE, 15);
            hr.setLocation(GuiConfig.HEADER_ZONE + GuiConfig.SPACE_BETWEEN * 2 +
                    (15 + GuiConfig.SPACE_BETWEEN) * i, GuiConfig.SPACE_BETWEEN);
            vr.setLocation(GuiConfig.SPACE_BETWEEN, GuiConfig.HEADER_ZONE + GuiConfig.SPACE_BETWEEN * 2 +
                    (15 + GuiConfig.SPACE_BETWEEN) * i);

            add(hr);
            add(vr);
        }
    }

    public void render(Graphics graphics) {
        Graphics2D graphics2D = (Graphics2D) graphics;

    }

    private void paintGameResult(Graphics2D graphics2D) {

    }

    public void setSelectedCell(GraphicalCell selectedCell) {
        this.selectedCell = selectedCell;
    }

    public void setGameEnd() {
        gameEnd = true;
    }

    public void renderCells() {

    }

    public void renderSelectedCell(Graphics2D graphics2D){
        //
    }

    public boolean isOpen(){
        return isOpen;
    }

    public void setField(Field field){
        cells = field.getField();
    }
}