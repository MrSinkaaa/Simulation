package application.finder;

import application.Coordinates;
import application.entities.Entity;


import java.util.*;

public class BFSPathFinder extends PathFinder {

    @Override
    public <T extends Entity> Deque<Coordinates> findPath(Coordinates source, Coordinates targetCoordinates, Class<? extends Entity> target) {
        Deque<Coordinates> path = new ArrayDeque<>();
        Deque<Coordinates> queue = new ArrayDeque<>();
        queue.add(source);

        int distance = getDistanceFromCreatureToTarget(source, targetCoordinates);
        while (!queue.isEmpty()) {
            Coordinates current = queue.removeFirst();
            if (current.equals(targetCoordinates)) {
                return path;
            }
            int newDistance = getDistanceFromCreatureToTarget(current, targetCoordinates);
            if(newDistance < distance) {
                path.addFirst(current);
                distance = newDistance;
            }
            for(Coordinates nextMove : getAvailableMoveSquares(current, target)) {
                newDistance = getDistanceFromCreatureToTarget(nextMove, targetCoordinates);
                if(newDistance < distance) {
                    if(!queue.contains(nextMove)) {
                        queue.addFirst(nextMove);
                    }
                }
            }
        }
        return path;
    }

}
