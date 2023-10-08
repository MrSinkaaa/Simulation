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

    private int countGrass = Map.getColumnBorder() * Map.getRowBorder() / 4;
    private int countHerbivore = countGrass / 8;
    private final int countPredator = countGrass / 12;
    private final int countTree = countGrass / 3;
    private final int countRock = countGrass / 4;


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
        mapPoolEntities.put("Herbivore", countHerbivore);
        mapPoolEntities.put("Predator", countPredator);
        mapPoolEntities.put("Grass", countGrass);
        mapPoolEntities.put("Tree", countTree);
        mapPoolEntities.put("Rock", countRock);
    }


}
