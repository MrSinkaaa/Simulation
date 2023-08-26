package application.actions;

import application.Map;
import application.Simulation;
import application.creatures.Herbivore;
import application.creatures.Predator;
import application.entities.Grass;
import application.entities.Rock;
import application.entities.Tree;

public class InitAction extends Action {

    private Map map;

    private final int countGrass = Map.getColumnBorder() * Map.getRowBorder() / 4;
    private final int countHerbivore = countGrass / 5;
    private final int countPredator = countHerbivore / 5;
    private final int countTree = countGrass / 7;
    private final int countRock = countGrass / 8;

    public void initMap() {
        map = Simulation.getMap();

        generateMapWithEntity();
    }


    private void generateMapWithEntity() {
        //Spawn creatures
        map.spawnEntities(new Herbivore(),countHerbivore);
        map.spawnEntities(new Predator(5), countPredator);

        //Spawn other entities
        map.spawnEntities(new Grass(), countGrass);
        map.spawnEntities(new Tree(), countTree);
        map.spawnEntities(new Rock(), countRock);
    }
}
