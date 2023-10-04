package application.finder;

import application.Coordinates;
import application.Node;
import application.entities.Entity;

import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class AStarPathFinder extends PathFinder {

    public <T extends Entity> LinkedList<Coordinates> findPath(Coordinates source, Coordinates targetCoordinates, Class<? extends Entity> target) {
        PriorityQueue<Node> openList = new PriorityQueue<>();
        PriorityQueue<Node> closedList = new PriorityQueue<>();

        Node start = new Node(source);
        Node end = new Node(targetCoordinates);
        start.setF(start.getG() + getDistanceFromCreatureToTarget(start, targetCoordinates));
        openList.add(start);

        while (!openList.isEmpty()) {
            Node current = openList.peek();
            if (current.equals(end)) {
                return getPathFromResultNode(current);
            }
            for (Coordinates coordinates : getAvailableMoveSquares(current.getCoordinates(), target)) {
                Node node = new Node(coordinates);
                if (!closedList.contains(node)) {
                    node.setSource(current);
                    node.setG(current.getG() + 1);
                    node.setH(getDistanceFromCreatureToTarget(node, targetCoordinates));
                    node.setF(node.getG() + node.getH());
                    openList.add(node);
                }
            }
            openList.remove(current);
            closedList.add(current);
        }
        return null;
    }

    private LinkedList<Coordinates> getPathFromResultNode(Node targetNode) {
        Node node = targetNode;
        LinkedList<Coordinates> coordinates = new LinkedList<>();
        while (node.getSource() != null) {
            coordinates.add(new Coordinates(node.getRow(), node.getColumn()));
            node = node.getSource();
        }
        Collections.reverse(coordinates);
        return coordinates;
    }


}
