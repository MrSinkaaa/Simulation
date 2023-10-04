package application.actions;

import application.Coordinates;
import application.Map;
import application.Simulation;
import application.entities.Grass;
import application.entities.Rock;
import application.entities.Tree;
import application.entities.creatures.Herbivore;
import application.entities.creatures.Predator;

import java.util.Map.Entry;

import java.util.HashMap;

public class InitAction extends Action {

    private Map map;

    private final HashMap<String, Integer> mapPoolEntities = new HashMap<>();


    public void initMap() {
        map = Simulation.getMap();

        generateMapWithEntity();
    }

    private void generateMapWithEntity() {
        //Create map pool entities
        createMapPoolEntities();

        //Spawn entities
        for(Entry<String, Integer> entity : mapPoolEntities.entrySet()) {

            int countEntities = entity.getValue();
            for(int i = 0; i < countEntities; i++) {

                Coordinates coordinates = map.getRandomEmptyCoordinates();

                switch (entity.getKey()) {
                    case "Herbivore" -> map.setEntity(coordinates, new Herbivore(10,1));
                    case "Predator" -> map.setEntity(coordinates, new Predator(15,1,5));
                    case "Grass" -> map.setEntity(coordinates, new Grass());
                    case "Tree" -> map.setEntity(coordinates, new Tree());
                    case "Rock" -> map.setEntity(coordinates, new Rock());
                }
            }
        }
    }

    private void createMapPoolEntities() {
        mapPoolEntities.put("Herbivore", map.getCountHerbivore());
        mapPoolEntities.put("Predator", map.getCountPredator());
        mapPoolEntities.put("Grass", map.getCountGrass());
        mapPoolEntities.put("Tree", map.getCountTree());
        mapPoolEntities.put("Rock", map.getCountRock());
    }


}
