package application.finder;

import application.Coordinates;
import application.CoordinatesShift;
import application.Simulation;
import application.entities.Entity;

import java.util.*;

public abstract class PathFinder {

    public abstract Deque<Coordinates> findPath(Coordinates source, Coordinates targetCoordinates, Class<? extends Entity> target);

    public <T> Coordinates getNearestTarget(Coordinates source, Class<T> target) {
        List<Coordinates> targets = Simulation.getMap().getEntitiesOfType(target).keySet().stream().toList();

        Coordinates nearestTarget = null;
        int minDistance = Integer.MAX_VALUE;
        for(Coordinates coordinates : targets) {
            int distance = getDistanceFromCreatureToTarget(source, coordinates);
            if(minDistance > distance) {
                minDistance = distance;
                nearestTarget = coordinates;
            }
        }
        return nearestTarget;
    }

    public int getDistanceFromCreatureToTarget(Coordinates source, Coordinates target) {
        return (int) Math.sqrt(Math.pow(source.getRow() - target.getRow(),2)
              + Math.pow(source.getColumn() - target.getColumn(), 2));
    }

    protected <T> Set<Coordinates> getAvailableMoveSquares(Coordinates coordinates, Class<? extends Entity> target) {
        Set<Coordinates> result = new HashSet<>();

        for(CoordinatesShift shift : getCreatureMoves()) {
            if(coordinates.canShift(shift)) {
                Coordinates newCoordinates = coordinates.shift(shift);
                Entity entity = Simulation.getMap().getEntity(newCoordinates);
                if(Simulation.getMap().isSquareEmpty(newCoordinates) || entity.getClass().equals(target)) {
                    result.add(newCoordinates);
                }
            }
        }
        return result;
    }
    protected Set<CoordinatesShift> getCreatureMoves() {
        HashSet<CoordinatesShift> result = new HashSet<>();
        for(int row = -1; row <= 1; row++) {
            for(int column = -1; column <= 1; column++) {
                if(row!= 0 || column!= 0) {
                    result.add(new CoordinatesShift(row, column));
                }
            }
        }
        return result;
    }

}
