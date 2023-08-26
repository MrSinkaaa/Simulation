package application;

public record Coordinates(int row, int column) {

    public Coordinates shift(CoordinatesShift shift) {
        return new Coordinates(this.row + shift.rowShift(), this.column + shift.columnShift());
    }

    public boolean canShift(CoordinatesShift shift) {
        int r = row() + shift.rowShift();
        int c = column() + shift.columnShift();
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
}
