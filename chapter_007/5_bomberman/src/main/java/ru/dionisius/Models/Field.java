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
     * Field as a storage of figures.
     */
    private final IFigure[][] field;

    /**
     * Constructor.
     * @param sizeX initial x size of this field.
     * @param sizeY initial y size of this field.
     */
    public Field(final int sizeX, final int sizeY) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.field = new IFigure [sizeX][sizeY];
    }

    /**
     * Getter.
     * @return this field.
     */
    public IFigure[][] getField() {
        return this.field;
    }

    /**
     * Sets specified figure to specified cell.
     * @param figure specified figure.
     * @param coordinateX specified coordinate x.
     * @param coordinateY specified coordinate y.
     */
    public void setFigure(final IFigure figure, final int coordinateX, final int coordinateY) {
        this.field[coordinateX][coordinateY] = figure;
    }

    /**
     * Removes any figure from specified cell.
     * @param coordinateX specified coordinate x.
     * @param coordinateY specified coordinate y.
     */
    public void remove(final int coordinateX, final int coordinateY) {
        this.field[coordinateX][coordinateY] = null;
    }
}
