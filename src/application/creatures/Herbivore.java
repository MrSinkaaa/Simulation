package application.creatures;

import application.Coordinates;
import application.Map;
import application.entities.Entity;
import application.entities.Grass;

import java.util.function.Predicate;

public class Herbivore extends Creature {


    @Override
    public void makeMove(Map map, Coordinates sourceCoordinates) {
        Coordinates coordinatesToMove = getCoordinatesForMove(map, sourceCoordinates);

        if(isCreatureCanEat(map, map.getEntity(coordinatesToMove))) {
            this.health++;
        }
        map.setEntity(coordinatesToMove, map.getEntity(sourceCoordinates));
        map.deleteEntity(sourceCoordinates);
    }

    @Override
    protected boolean isCreatureCanEat(Map map, Entity entity) {
        if(getGoalPredicate().test(entity)) {
            map.deleteEntity(entity.getCoordinates());
            return true;
        }
        return false;
    }

    @Override
    public Predicate<Entity> getGoalPredicate() {
        return entity -> entity instanceof Grass;
    }

    @Override
    protected boolean isSquareAvailableForMove(Coordinates coordinates, Map map) {
        return map.isSquareEmpty(coordinates) || getGoalPredicate().test(map.getEntity(coordinates));
    }

}
