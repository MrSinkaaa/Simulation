package application.creatures;

import application.Coordinates;
import application.Map;
import application.entities.Entity;

import java.util.function.Predicate;

public class Predator extends Creature {

    private final int attackPower;

    public Predator(int attackPower) {
        this.attackPower = attackPower;
    }

    public int getAttackPower() {
        return attackPower;
    }

    @Override
    public void makeMove(Map map, Coordinates sourceCoordinates) {
        Coordinates coordinatesToMove = getCoordinatesForMove(map, sourceCoordinates);

        if(isCreatureCanEat(map, map.getEntity(coordinatesToMove))) {
            map.setCountHerbivore(map.getCountHerbivore() - 1);
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
        return entity -> entity instanceof Herbivore;
    }

    @Override
    protected boolean isSquareAvailableForMove(Coordinates coordinates, Map map) {
        return map.isSquareEmpty(coordinates) || getGoalPredicate().test(map.getEntity(coordinates));
    }
}
