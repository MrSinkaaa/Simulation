package application.entities.creatures;

import application.Coordinates;
import application.Map;
import application.entities.Entity;
import application.finder.PathFinder;
import application.finder.AStarPathFinder;


import java.util.Deque;

public abstract class Creature extends Entity {

    int speed;
    int health;
    protected Deque<Coordinates> pathToTarget;
    protected final PathFinder pathFinder = new AStarPathFinder();

    public void makeMove(Map map) {
        Coordinates source = this.getCoordinates();

        for (int move = 0; move < this.speed; move++) {
            Coordinates coordinatesTarget = pathFinder.getNearestTarget(source, getTarget());

            if (coordinatesTarget != null) {
                pathToTarget = pathFinder.findPath(this.getCoordinates(), coordinatesTarget, getTarget());
                if (pathToTarget == null) return;
            } else {
                return;
            }

            Coordinates moveTo = pathToTarget.peek();
            if (pathFinder.getDistanceFromCreatureToTarget(source, coordinatesTarget) == 1) {
                this.health += 5;

                map.deleteEntity(coordinatesTarget);

                pathToTarget.clear();
                break;
            } else {
                map.setEntity(moveTo, this);
                map.deleteEntity(source);
            }
        }

    }


    protected abstract Class<? extends Entity> getTarget();

}
