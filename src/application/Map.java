package application;

import application.entities.Entity;

import java.util.Map.Entry;
import java.util.HashMap;
import java.util.Random;
import java.util.stream.Collectors;

public class Map {

    private static final int columnBorder = 15;
    private static final int rowBorder = 15;

    private int countGrass = Map.getColumnBorder() * Map.getRowBorder() / 4;
    private int countHerbivore = countGrass / 8;
    private final int countPredator = countGrass / 12;
    private final int countTree = countGrass / 5;
    private final int countRock = countGrass / 6;

    private final HashMap<Coordinates, Entity> entities = new HashMap<>();


    public static int getColumnBorder() {
        return columnBorder;
    }

    public static int getRowBorder() {
        return rowBorder;
    }
    public int getCountGrass() {
        return countGrass;
    }

    public void setCountGrass(int countGrass) {
        this.countGrass = countGrass;
    }

    public int getCountHerbivore() {
        return countHerbivore;
    }

    public void setCountHerbivore(int countHerbivore) {
        this.countHerbivore = countHerbivore;
    }

    public int getCountPredator() {
        return countPredator;
    }

    public int getCountTree() {
        return countTree;
    }

    public int getCountRock() {
        return countRock;
    }

    public Entity getEntity(Coordinates coordinates) {
        return entities.get(coordinates);
    }
    public void setEntity(Coordinates coordinates, Entity entity) {
        entity.setCoordinates(coordinates);
        entities.put(coordinates, entity);
    }

    public <T> HashMap<Coordinates, T> getEntitiesOfType(Class<T> type) {
        return entities.entrySet().stream().filter(e -> type.isInstance(e.getValue()))
                .map(e -> (Entry<Coordinates, T>) e)
                .collect(Collectors.toMap(Entry::getKey,Entry::getValue,(a,b) -> b, HashMap::new));
    }

    public void deleteEntity(Coordinates coordinates) {
        entities.remove(coordinates);
    }

    public boolean isSquareEmpty(Coordinates coordinates) {
        return !entities.containsKey(coordinates);
    }

    public Coordinates getRandomEmptyCoordinates() {
        Random random = new Random();
        Coordinates coordinates = new Coordinates(random.nextInt(rowBorder) + 1, random.nextInt(columnBorder) + 1);
        return isSquareEmpty(coordinates) ? coordinates : getRandomEmptyCoordinates();
    }

}
