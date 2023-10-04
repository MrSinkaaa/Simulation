package application.actions;

import application.Map;
import application.Simulation;
import application.entities.creatures.Herbivore;
import application.entities.Grass;

public class RespawnAction extends Action {

    private final Map map;

    public RespawnAction() {
        map = Simulation.getMap();
    }

    public void respawnGrass() {
        int countGrasses = map.getCountGrass() * 6;
        for(int i = 0; i <= countGrasses - map.getCountGrass(); i++) {
            map.setEntity(map.getRandomEmptyCoordinates(), new Grass());
        }
        map.setCountGrass(countGrasses);
    }

    public void respawnHerbivore() {
        int countHerbivores = map.getCountHerbivore() * 2;
        for(int i = 0; i <= countHerbivores - map.getCountHerbivore(); i++) {
            map.setEntity(map.getRandomEmptyCoordinates(), new Herbivore(10, 1));
        }
        map.setCountHerbivore(countHerbivores);
    }

    public boolean isGrassEnough() {
        return map.getCountGrass() >= Map.getColumnBorder() * Map.getRowBorder() / 25;
    }

    public boolean isHerbivoreEnough() {
        return map.getCountHerbivore() >= Map.getColumnBorder() * Map.getRowBorder() / 45;
    }
}
