package application.actions;

import application.Coordinates;
import application.Map;
import application.MapRenderer;
import application.creatures.Herbivore;
import application.creatures.Predator;
import application.entities.Entity;

import java.util.List;
import java.util.stream.Collectors;

public class TurnAction extends Action {

    private List<java.util.Map.Entry<Coordinates, Entity>> herbivores;
    private List<java.util.Map.Entry<Coordinates, Entity>> predators;
    MapRenderer renderer = new MapRenderer();

    public void creatureMoves(Map map) {
        moveHerbivores(map);
        movePredators(map);

    }

    public void renderMap(Map map) {
        renderer.render(map);
    }


    private void movePredators(Map map) {
        predators = map.getEntities().entrySet().stream()
              .filter(entity -> entity.getValue() instanceof Predator)
              .collect(Collectors.toList());

        for (java.util.Map.Entry<Coordinates, Entity> entry : predators) {
            Predator predator = (Predator) entry.getValue();
            predator.makeMove(map, entry.getKey());
        }
    }

    private void moveHerbivores(Map map) {
        herbivores = map.getEntities().entrySet().stream()
              .filter(entity -> entity.getValue() instanceof Herbivore)
              .collect(Collectors.toList());

        for (java.util.Map.Entry<Coordinates, Entity> entry : herbivores) {
            Herbivore herbivore = (Herbivore) entry.getValue();
            herbivore.makeMove(map, entry.getKey());
        }
    }
}
