package application.creatures;

import application.Coordinates;
import application.CoordinatesShift;
import application.Game;
import application.Map;
import application.entities.Entity;
import application.entities.Grass;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.function.Predicate;

public abstract class Creature extends Entity {

    int speed = 1;
    int health = 5;

    public abstract void makeMove(Map map, Coordinates sourceCoordinates);

    protected Coordinates getCoordinatesForMove(Map map, Coordinates sourceCoordinates) {
        Set<Coordinates> targetsCoordinates = getAllTargetsCoordinates(map, sourceCoordinates);

        Coordinates nearestTarget = null;
        int distance = Integer.MAX_VALUE;

        for(Coordinates target : targetsCoordinates) {
            int newDistance = getDistanceFromCreatureToTarget(sourceCoordinates, target);
            if(distance > newDistance) {
                distance = newDistance;
                nearestTarget = target;
            }
        }

        Coordinates newCoordinates = null;
        for(Coordinates coordinates : getAvailableMoveSquares(sourceCoordinates, map)) {
            int newDistance = getDistanceFromCreatureToTarget(coordinates, nearestTarget);
            if(distance > newDistance) {
                distance = newDistance;
                newCoordinates = coordinates;
            }
        }

        return newCoordinates;
    }
    private int getDistanceFromCreatureToTarget(Coordinates sourceCoordinates, Coordinates target) {
        return (int) Math.sqrt(Math.pow(sourceCoordinates.row() - target.row(),2)
              + Math.pow(sourceCoordinates.column() - target.column(), 2));
    }

    private Set<Coordinates> getAllTargetsCoordinates(Map map, Coordinates sourceCoordinates) {
        Set<Coordinates> targetsCoordinates = new HashSet<>();

        HashSet<Coordinates> visited = new HashSet<>();
        LinkedList<Coordinates> queue = new LinkedList<>();
        visited.add(sourceCoordinates);
        queue.add(sourceCoordinates);

        Coordinates coordinates;
        while (!queue.isEmpty()) {
            coordinates = queue.poll();

            for (Coordinates coord : getAvailableMoveSquares(coordinates, map)) {
                if (getGoalPredicate().test(map.getEntity(coord))) {
                    targetsCoordinates.add(coord);
                } else if (visited.contains(coord)) {
                    continue;
                } else {
                    queue.add(coord);
                }
                visited.add(coord);
            }
        }
        return targetsCoordinates;
    }

    private Set<Coordinates> getAvailableMoveSquares(Coordinates coordinates, Map map) {
        Set<Coordinates> result = new HashSet<>();

        for(CoordinatesShift shift : getCreatureMoves()) {
            if(coordinates.canShift(shift)) {
                Coordinates newCoordinates = coordinates.shift(shift);

                if(isSquareAvailableForMove(newCoordinates, map)) {
                    result.add(newCoordinates);
                }
            }
        }
        return result;
    }

    protected abstract boolean isCreatureCanEat(Map map, Entity entity);
    protected abstract Predicate<Entity> getGoalPredicate();
    protected abstract boolean isSquareAvailableForMove(Coordinates coordinates, Map map);

    private Set<CoordinatesShift> getCreatureMoves() {
        return new HashSet<>(Arrays.asList(
        new CoordinatesShift( - 1, - 1),
        new CoordinatesShift(- 1, 0),
        new CoordinatesShift(- 1,  + 1),
        new CoordinatesShift( + 1,  + 1),
        new CoordinatesShift(+ 1, 0),
        new CoordinatesShift( + 1,  - 1),
        new CoordinatesShift(0,  - 1),
        new CoordinatesShift(0, + 1)
        ));
    };
}
