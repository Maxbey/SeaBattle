package gui;

import game.Field.*;
import game.Ships.AbstractShip;

import java.awt.*;
import java.awt.event.*;

public class GraphicalCell extends Component implements MouseListener {
    private PlayerField field;
    private Cell cell;

    private boolean selected;

    public GraphicalCell(PlayerField field, Cell cell) {
        this.field = field;
        this.cell = cell;

        selected = false;

        this.field.bindCell(this);

        setSize(GuiConfig.CELL_HEIGHT, GuiConfig.CELL_WIDTH);
        setLocation(GuiConfig.HEADER_ZONE + GuiConfig.SPACE_BETWEEN * 2 + (WIDTH + GuiConfig.SPACE_BETWEEN) * cell.getX(),
                GuiConfig.HEADER_ZONE + GuiConfig.SPACE_BETWEEN * 2 + (HEIGHT + GuiConfig.SPACE_BETWEEN) * cell.getX());

        addMouseListener(this);
    }

    public Cell getCell() {
        return cell;
    }

    public void renderCellState(Graphics graphics) {
        Graphics2D graphics2D = (Graphics2D) graphics;
            if(cell.isShip()) {
                AbstractShip ship = cell.getShip();

                if(!ship.isAlive())
                    renderCell(graphics2D, Color.black);
                else if(cell.isAttacked())
                    renderCell(graphics2D, Color.red);
                else
                    renderCell(graphics2D, Color.orange);

            }
            else{

                if(cell.isAttacked())
                    renderCell(graphics2D, Color.blue);
                else
                    renderCell(graphics2D, Color.lightGray);
            }

            if(isSelected()) {
                renderCell(graphics2D, Color.green);
            }
    }

    private void renderCell(Graphics2D graphics2D, Color color) {
        int lw = getWidth() - 1;
        int lh = getHeight() - 1;
        int rw = GuiConfig.SPACE_BETWEEN * 2;
        int rh = GuiConfig.SPACE_BETWEEN * 2;
        graphics2D.setPaint(new GradientPaint(lw, lh, Color.white, lw / 4, lh / 4, color, true));
        graphics2D.fillRoundRect(0, 0, lw, lh, rw, rh);
        graphics2D.setColor(color);
        graphics2D.drawRoundRect(0, 0, lw, lh, rw, rh);
    }

    public boolean isSelected(){
        return selected;
    }

    public void mouseEntered(MouseEvent e) {
        if (!field.isOpen()) {
            selected = true;
            repaint();
        }
    }

    public void mouseExited(MouseEvent e) {
        if (!field.isOpen()) {
            selected = false;
            repaint();
        }
    }

    public void mouseClicked(MouseEvent e){
        if(!field.isOpen()) {

            if(!cell.isAttacked()) {
                cell.setWasAttacked();
            }

            field.setSelectedCell(null);
            repaint();
        }
    }

    public void mousePressed(MouseEvent e) { }
    public void mouseReleased(MouseEvent e) { }
}
