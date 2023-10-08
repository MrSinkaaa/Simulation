package application;

import application.entities.Entity;

import java.util.Map.Entry;
import java.util.HashMap;
import java.util.Random;
import java.util.stream.Collectors;

public class Map {

    private static final int columnBorder = 15;
    private static final int rowBorder = 15;

    private final HashMap<Coordinates, Entity> entities = new HashMap<>();


    public static int getColumnBorder() {
        return columnBorder;
    }

    public static int getRowBorder() {
        return rowBorder;
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
