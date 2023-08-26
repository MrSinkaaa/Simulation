package application;

import application.entities.Entity;

public class MapRenderer {

    private static final String ANSI_RESET = "\u001b[0m";
    private static final String ANSI_COLOR_GRASS = "\u001b[92m";
    private static final String ANSI_COLOR_TREE = "\u001b[92m";
    private static final String ANSI_COLOR_PREDATOR = "\u001b[31m";
    private static final String ANSI_COLOR_HERBIVORE = "\u001b[96m";
    private static final String ANSI_COLOR_ROCK = "\u001b[90m";
    private static final String ANSI_BACKGROUND_COLOR_WHITE = "\u001b[47;1m";


    public void render(Map map) {
        for(int row = 1; row <= Map.getColumnBorder(); row++ ) {
            StringBuilder line = new StringBuilder();
            for(int col = 1; col <= Map.getRowBorder(); col++) {
                Coordinates coordinates = new Coordinates(row, col);
                line.append(ANSI_BACKGROUND_COLOR_WHITE);
                if(map.isSquareEmpty(coordinates)) {
                     line.append(getEmptySquare(coordinates));
                } else {
                    line.append(colorizeEntity(map.getEntity(coordinates)));
                }

            }
            System.out.println(line.append(ANSI_RESET).toString());
        }
    }

    private String colorizeEntity(Entity entity) {
        switch (entity.getClass().getSimpleName()) {
            case "Herbivore":
                return ANSI_COLOR_HERBIVORE + " H ";
            case "Predator":
                return ANSI_COLOR_PREDATOR + " P ";
            case "Rock":
                return ANSI_COLOR_ROCK + " R ";
            case "Tree":
                return ANSI_COLOR_TREE + " T ";
            case "Grass":
                return ANSI_COLOR_GRASS + " G ";

            default:
                return "  ";
        }
    }

    private String getEmptySquare(Coordinates coordinates) {
        return "   ";
    }
}
