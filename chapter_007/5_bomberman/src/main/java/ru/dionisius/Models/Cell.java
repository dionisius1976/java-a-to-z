package ru.dionisius.Models;

/**
 * Created by Dionisius on 27.03.2017.
 * Cell of Board. Contains specified figure.
 */
public class Cell {

    /**
     * The figure that is stored in this cell;
     */
    private IFigure figure;

    /**
     * Constructor.
     * @param figure specified figure for this cell.
     */
    public Cell(final IFigure figure) {
        this.figure = figure;
    }

    /**
     * Getter of specified figure that is stored in this cell.
     * @return specified figure that is stored in this cell.
     */
    public IFigure getFigure() {
        return this.figure;
    }

    /**
     * Setter for specified figure for this cell.
     * @param figure specified figure for this cell.
     */
    public void setFigure(final IFigure figure) {
        this.figure = figure;
    }
}
