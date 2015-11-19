package gui;

import game.Field.*;
import game.Field.Point;
import game.Ships.AbstractShip;

import javax.swing.*;
import java.awt.*;

public class PlayerField extends JComponent {

    private Field field;

    private GraphicalCell[][] cells;
    private boolean hidden;

    private MainWindow window;

    private boolean block;

    public PlayerField(Field field, boolean hidden, MainWindow window) {
        this.field = field;
        this.hidden = hidden;
        this.window = window;

        createGraphicalCells();

        setSize(GuiConfig.FIELD_WIDTH, GuiConfig.FIELD_HEIGHT);
        renderGamePanel();
    }

    private void createGraphicalCells(){
        cells =  new GraphicalCell[GuiConfig.CELLS_COUNT][GuiConfig.CELLS_COUNT];

        for(int i = 0; i < GuiConfig.CELLS_COUNT; i++){
            for(int j = 0; j < GuiConfig.CELLS_COUNT; j++){
                cells[j][i] = new GraphicalCell(this, field.getCell(j , i));
            }
        }
    }

    private void renderGamePanel() {
        char[] letters = {'A', 'Б', 'В', 'Г', 'Д', 'Е', 'Ж', 'З', 'И', 'К'};

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
                    (3 + GuiConfig.SPACE_BETWEEN) * i, GuiConfig.SPACE_BETWEEN);
            vr.setLocation(GuiConfig.SPACE_BETWEEN, GuiConfig.HEADER_ZONE + GuiConfig.SPACE_BETWEEN * 2 +
                    (3 + GuiConfig.SPACE_BETWEEN) * i);

            add(hr);
            add(vr);
        }
    }

    public void renderField() {
        for(GraphicalCell[] row : cells){
            for(GraphicalCell cell : row){
                cell.repaint();
            }
        }
    }

    public void repaintDeadShipCells(AbstractShip ship){
        for(Cell cell : ship.getCells()){
            GraphicalCell graphicalCell = cells[cell.getX()][cell.getY()];
            graphicalCell.repaint();
        }
    }

    public void repaintCell(int x, int y){
        GraphicalCell cell = cells[x][y];

        cell.repaint();
    }

    public void updateCell(Cell cell){
        GraphicalCell gc = cells[cell.getX()][cell.getY()];
        cell.setWasAttacked();

        gc.setCell(cell);

        if(cell.isShip() && !cell.getShip().isAlive()){
            repaintDeadShipCells(cell.getShip());
        }

        gc.repaint();
    }

    public MainWindow getWindow() {
        return window;
    }

    public boolean isHidden(){
        return hidden;
    }

    public void block(){
        block = true;
    }

    public void unblock(){
        block = false;
    }

    public boolean isBlocked(){
        return block;
    }
}
