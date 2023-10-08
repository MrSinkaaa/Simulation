package application.actions;

import application.Map;
import application.Simulation;
import application.entities.creatures.Herbivore;
import application.entities.Grass;

public class RespawnAction extends Action {

    private final Map map;

    private final int maxGrassPopulation = 50;
    private final int maxHerbivorePopulation = 10;

    public RespawnAction() {
        map = Simulation.getMap();
    }

    public void respawnGrass() {
        int spawnCount = Math.max(0, maxGrassPopulation - map.getEntitiesOfType(Grass.class).size());

        for(int i = 0; i < spawnCount; i++) {
            map.setEntity(map.getRandomEmptyCoordinates(), new Grass());
        }
    }

    public void respawnHerbivore() {
        int spawnCount = Math.max(0, maxHerbivorePopulation - map.getEntitiesOfType(Herbivore.class).size());

        for(int i = 0; i < spawnCount; i++) {
            map.setEntity(map.getRandomEmptyCoordinates(), new Herbivore(10, 1));
        }

    }

    public boolean isGrassEnough() {
        return map.getEntitiesOfType(Grass.class).size() >= maxGrassPopulation / 3;
    }

    public boolean isHerbivoreEnough() {
        return map.getEntitiesOfType(Herbivore.class).size() >= maxHerbivorePopulation / 2;
    }
}
