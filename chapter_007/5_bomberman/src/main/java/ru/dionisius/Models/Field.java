package ru.dionisius.Models;

/**
 * Created by Dionisius on 24.03.2017.
 */
public class Field {

    /**
     * Initial x size of this field.
     */
    private final int sizeX;
    /**
     * Initial y size of this field.
     */
    private final int sizeY;
    /**
     * Field as a storage of cells with figures.
     */
    private final Cell[][] field;

    /**
     * Constructor.
     * @param sizeX initial x size of this field.
     * @param sizeY initial y size of this field.
     */
    public Field(final int sizeX, final int sizeY) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.field = new Cell[sizeX][sizeY];
        for (int i = 0; i < this.field.length; i++) {
            for (int j = 0; j < this.field[0].length; j++) {
                this.field[i][j] = new Cell(null);
            }
        }
    }

    /**
     * Getter.
     * @return this field.
     */
    public Cell[][] getField() {
        return this.field;
    }

    /**
     * Sets specified figure to specified cell.
     * @param figure specified figure.
     * @param coordinateX specified cell's coordinate x.
     * @param coordinateY specified cell's coordinate y.
     */
    public void setFigure(final IFigure figure, final int coordinateX, final int coordinateY) {
        synchronized (this.field[coordinateX][coordinateY]) {
            this.field[coordinateX][coordinateY].setFigure(figure);
        }
    }
//
//    /**
//     * Removes any figure from specified cell.
//     * @param coordinateX specified cell's coordinate x.
//     * @param coordinateY specified cell's coordinate y.
//     */
//    public void remove(final int coordinateX, final int coordinateY) {
//        synchronized (this.field[coordinateX][coordinateY]) {
//            this.field[coordinateX][coordinateY].setFigure(null);
//        }
//    }

    /**
     * Moves specified figure from the start position to the finish position.
     * @param figure specified figure.
     * @param startCoordinateX start position coordinate X.
     * @param startCoordinateY start position coordinate Y.
     * @param finishCoordinateX finish position coordinate X.
     * @param finishCoordinateY finish position coordinate Y.
     */
    public void moveFigure(final IFigure figure, final int startCoordinateX, final int startCoordinateY,
                           final int finishCoordinateX, final int finishCoordinateY) {
        synchronized (this.field[startCoordinateX][startCoordinateY]) {
            synchronized (this.field[finishCoordinateX][finishCoordinateY]) {
                this.field[startCoordinateX][startCoordinateY].setFigure(null);
                this.field[finishCoordinateX][finishCoordinateY].setFigure(figure);
            }
        }
    }
}
