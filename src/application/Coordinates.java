package application;

import java.util.Objects;

public class Coordinates {
    private final int row;
    private final int column;

    public Coordinates(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public Coordinates shift(CoordinatesShift shift) {
        return new Coordinates(this.row + shift.rowShift(), this.column + shift.columnShift());
    }

    public boolean canShift(CoordinatesShift shift) {
        int r = getRow() + shift.rowShift();
        int c = getColumn() + shift.columnShift();
        if ((r < 1) || (r > Map.getRowBorder())) return false;
        if ((c < 1) || (c > Map.getColumnBorder())) return false;

        return true;
    }

    @Override
    public String toString() {
        return "Coordinates{" +
              "row=" + row +
              ", column=" + column +
              '}';
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Coordinates) obj;
        return this.row == that.row &&
              this.column == that.column;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }

}
