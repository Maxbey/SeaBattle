package game.Field;

import game.Ships.*;

import java.util.Random;

public class Field {
    int rowsSize;
    int colsSize;

    private Cell[][] field;

    public Field(int r, int c) {
        field = new Cell[r][c];

        rowsSize = r;
        colsSize = c;
    }

    public void createField() {
        for (int i = 0; i < rowsSize; i++) {
            for (int j = 0; j < colsSize; j++) {
                field[j][i] = new Cell(j, i);
            }
        }
    }

    public void addShips() throws Exception {
        addBattleships();
        addDestroyers();
        addCruisers();
        addPatrols();
    }

    public AbstractShip[] addBattleships() throws Exception {
        return shipsFabric(FieldConfig.BATTLESHIPS_COUNT, ShipsConfig.BATTLESHIP_SIZE, 'b');
    }

    public AbstractShip[] addDestroyers() throws Exception {
        return shipsFabric(FieldConfig.DESTROYER_COUNT, ShipsConfig.DESTROYER_SIZE, 'd');
    }

    public AbstractShip[] addCruisers() throws Exception {
        return shipsFabric(FieldConfig.CRUISER_COUNT, ShipsConfig.CRUISER_SIZE, 'c');
    }

    public AbstractShip[] addPatrols() throws Exception {
        return shipsFabric(FieldConfig.BOUTS_COUNT, ShipsConfig.PATROL_SIZE, 'p');
    }

    public AbstractShip[] shipsFabric(int cnt, int size, char type) throws Exception {
        AbstractShip[] ships = new AbstractShip[cnt];

        for (int i = 0, c = 0; i < cnt; i++) {
            while (true) {
                Cell firstCell = getEmptyRandomCell();
                boolean rotated = getRandomRotation();

                Cell[] possiblePosition = getRandomShipPosition(firstCell, size, rotated);

                if (checkFreeSpace(possiblePosition, size, rotated)) {
                    switch (type) {
                        case 'b':
                            ships[c] = createBattleship(possiblePosition);
                            break;
                        case 'd':
                            ships[c] = createDestroyer(possiblePosition);
                            break;
                        case 'c':
                            ships[c] = createCruiser(possiblePosition);
                            break;
                        case 'p':
                            ships[c] = createPatrol(possiblePosition);
                            break;

                        default:
                            throw new Exception("Wrong type of ships");
                    }

                    c++;
                    break;
                }
            }
        }

        return ships;
    }

    public Cell getEmptyRandomCell() throws Exception {

        for (int i = 0; i < rowsSize * colsSize; i++) {
            int randomX = new Random().nextInt(rowsSize);
            int randomY = new Random().nextInt(colsSize);

            Cell cell = getCell(randomX, randomY);

            if (!cell.isShip()) {
                return cell;
            }
        }
        throw new Exception("The field is left empty cells");
    }

    public Cell getCell(int x, int y) {
        return field[x][y];
    }

    public Cell[][] getField() {
        return field;
    }

    public Cell[] getRandomShipPosition(Cell firstCell, int size, boolean rotated) throws Exception {
        if (rotated) {
            if (firstCell.getX() < rowsSize - size - 1)
                return getCellSiblings(firstCell, 'r', size);

            return getCellSiblings(firstCell, 'l', size);
        }

        if (firstCell.getY() < colsSize - size - 1)
            return getCellSiblings(firstCell, 'b', size);

        return getCellSiblings(firstCell, 't', size);
    }

    public Cell[] getCellSiblings(Cell cell, char direction, int cnt) throws Exception {
        Cell first = (Cell) cell.clone();
        Cell[] siblings = new Cell[cnt];

        for (int i = 0; i < cnt; i++) {
            Cell next;

            switch (direction) {
                case 't':
                    next = getTopSibling(first);
                    break;
                case 'b':
                    next = getBottomSibling(first);
                    break;
                case 'l':
                    next = getLeftSibling(first);
                    break;
                case 'r':
                    next = getRightSibling(first);
                    break;
                default:

                    throw new Exception("wrong direction");
            }

            siblings[i] = next;
            first = next;
        }

        return siblings;
    }

    private Cell getTopSibling(Cell cell) {
        return getCell(cell.getX(), cell.getY() - 1);
    }

    private Cell getBottomSibling(Cell cell) {
        return getCell(cell.getX(), cell.getY() + 1);
    }

    private Cell getLeftSibling(Cell cell) {
        return getCell(cell.getX() - 1, cell.getY());
    }

    private Cell getRightSibling(Cell cell) {
        return getCell(cell.getX() + 1, cell.getY());
    }

    private boolean getRandomRotation() {
        return new Random().nextBoolean();
    }

    private boolean checkFreeSpace(Cell[] cells, int size, boolean rotated) {
        for (int i = 0; i < size; i++) {
            Cell cell = cells[i];

            if (cell.isShip())
                return false;

            if (rotated || size == 1) {
                if (cell.getY() != 0 && getTopSibling(cell).isShip())
                    return false;

                if (cell.getY() != colsSize - 1 && getBottomSibling(cell).isShip())
                    return false;

                if (i == size - 1) {
                    if (size == 1 || cell.getX() - cells[size - 2].getX() < 0) {
                        if (cell.getX() != 0 && getLeftSibling(cell).isShip())
                            return false;
                    }

                    if (cell.getX() != rowsSize - 1 && getLeftSibling(cell).isShip())
                        return false;
                }
            }

            if (cell.getX() != 0 && getLeftSibling(cell).isShip())
                return false;

            if (cell.getX() != rowsSize - 1 && getRightSibling(cell).isShip())
                return false;

            if (i == size - 1) {
                if (size == 1 || cell.getY() - cells[size - 2].getY() < 0) {
                    if (cell.getY() != 0 && getTopSibling(cell).isShip())
                        return false;
                }

                if (cell.getY() != colsSize - 1 && getBottomSibling(cell).isShip())
                    return false;
            }

        }

        return true;
    }

    private Battleship createBattleship(Cell[] cells) {
        return new Battleship(cells);
    }

    private Destroyer createDestroyer(Cell[] cells) {
        return new Destroyer(cells);
    }

    private Cruiser createCruiser(Cell[] cells) {
        return new Cruiser(cells);
    }

    private Patrol createPatrol(Cell[] cells) {
        return new Patrol(cells);
    }
}