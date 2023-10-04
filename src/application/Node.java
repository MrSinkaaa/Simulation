package application;

import java.util.Objects;

public class Node extends Coordinates implements Comparable<Node> {

    private int f;
    private int g;
    private int h;
    private Node source;
    private Coordinates coordinates;

    public Node(Coordinates coordinates) {
        super(coordinates.getRow(), coordinates.getColumn());
        this.coordinates = coordinates;

    }

    @Override
    public int compareTo(Node node) {
        return Integer.compare(this.f, node.f);
    }

    public int getF() {
        return f;
    }

    public void setF(int f) {
        this.f = f;
    }

    public int getG() {
        return g;
    }

    public void setG(int g) {
        this.g = g;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public Node getSource() {
        return source;
    }

    public void setSource(Node source) {
        this.source = source;
    }


    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Node node = (Node) o;
        return Objects.equals(coordinates, node.coordinates);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), coordinates);
    }
}
