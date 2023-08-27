package application.actions;

import application.Map;
import application.Simulation;
import application.creatures.Herbivore;
import application.entities.Grass;

public class RespawnAction extends Action {

    private Map map;

    public RespawnAction() {
        map = Simulation.getMap();
    }

    public void respawnGrass() {
        map.spawnEntities(new Grass(), map.getCountGrass() * 5);
    }

    public void respawnHerbivore() {
        map.spawnEntities(new Herbivore(), map.getCountHerbivore() * 2);
    }

    public boolean isGrassEnough() {
        return map.getCountGrass() >= 7;
    }

    public boolean isHerbivoreEnough() {
        return map.getCountHerbivore() >= 5;
    }
}
