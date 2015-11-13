package gui;

import game.Field.*;
import game.Ships.AbstractShip;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class GraphicalCell extends Component implements MouseListener {
    private PlayerField field;
    private Cell cell;

    boolean selected;

    public GraphicalCell(PlayerField field, Cell cell) {
        this.field = field;
        this.cell = cell;

        selected = false;

        this.field.add(this);

        setSize(GuiConfig.CELL_WIDTH, GuiConfig.CELL_HEIGHT);
        setLocation(GuiConfig.HEADER_ZONE + GuiConfig.SPACE_BETWEEN * 2 + (WIDTH + GuiConfig.SPACE_BETWEEN) * cell.getX(),
                GuiConfig.HEADER_ZONE + GuiConfig.SPACE_BETWEEN * 2 + (HEIGHT + GuiConfig.SPACE_BETWEEN) * cell.getY());

        addMouseListener(this);
    }


    public Cell getCell() {
        return cell;
    }

    public void paint(Graphics graphics) {
        Graphics2D graphics2D = (Graphics2D) graphics;
        renderCell(graphics2D, Color.black);
        if (cell.isShip()) {
            AbstractShip ship = cell.getShip();

            if (!ship.isAlive())
                renderCell(graphics2D, Color.black);
            else if (cell.isAttacked())
                renderCell(graphics2D, Color.red);
            else{
                if(field.isHidden())
                    renderCell(graphics2D, Color.lightGray);
                else
                    renderCell(graphics2D, Color.orange);
            }

        } else {

            if (cell.isAttacked())
                renderCell(graphics2D, Color.blue);
            else
                renderCell(graphics2D, Color.lightGray);
        }

        if (selected) {
            renderCell(graphics2D, Color.green);
        }
    }

    private void renderCell(Graphics2D graphics2D, Color color) {
        int lw = getWidth() - 1;
        int lh = getHeight() - 1;
        int rw = 4;
        int rh = 4;
        graphics2D.setPaint(new GradientPaint(lw, lh, Color.white, lw / 4, lh / 4, color, true));
        graphics2D.fillRoundRect(0, 0, lw, lh, rw, rh);
        graphics2D.setColor(color);
        graphics2D.drawRoundRect(0, 0, lw, lh, rw, rh);
    }

    public void mouseEntered(MouseEvent e) {
        if(field.isHidden()){
            selected = true;
            repaint();
        }

    }

    public void mouseExited(MouseEvent e) {
        if(field.isHidden()){
            selected = false;
            repaint();
        }
    }

    public void mouseClicked(MouseEvent e) {
        try {
            Cell answ = field.getWindow().getGame().makeShoot(cell.getX(), cell.getY());
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        if(!cell.isAttacked() && field.isHidden()){
            cell.setWasAttacked();

            if (cell.isShip()) {
                cell.getShip().minusStrength();
                if(!cell.getShip().isAlive()){
                    field.repaintDeadShipCells(cell.getShip());
                }
                repaint();
            }
        }
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }
}
