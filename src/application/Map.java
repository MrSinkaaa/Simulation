package application;

import application.entities.Entity;

import java.util.HashMap;
import java.util.Random;

public class Map {

    private static final int columnBorder = 20;
    private static final int rowBorder = 20;

    private final HashMap<Coordinates, Entity> entities = new HashMap<>();


    public static int getColumnBorder() {
        return columnBorder;
    }

    public static int getRowBorder() {
        return rowBorder;
    }

    public HashMap<Coordinates, Entity> getEntities() {
        return entities;
    }

    public Entity getEntity(Coordinates coordinates) {
        return entities.get(coordinates);
    }
    public void setEntity(Coordinates coordinates, Entity entity) {
        entity.setCoordinates(coordinates);
        entities.put(coordinates, entity);
    }

    public Entity deleteEntity(Coordinates coordinates) {
        return entities.remove(coordinates);
    }

    public boolean isSquareEmpty(Coordinates coordinates) {
        return !entities.containsKey(coordinates);
    }

    public void spawnEntities(Entity entity, int countEntities) {
        for (int i = 0; i < countEntities; i++) {
            setEntity(getRandomCoordinates(), entity);
        }
    }

    private Coordinates getRandomCoordinates() {
        Random random = new Random();
        Coordinates coordinates = new Coordinates(random.nextInt(rowBorder) + 1, random.nextInt(columnBorder) + 1);
        return isSquareEmpty(coordinates) ? coordinates : getRandomCoordinates();
    }


}
