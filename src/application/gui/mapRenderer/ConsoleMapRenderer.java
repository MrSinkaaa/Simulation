package application.gui.mapRenderer;

import application.Coordinates;
import application.Map;
import application.entities.Entity;

public class ConsoleMapRenderer extends MapRenderer {

    private static final String ANSI_RESET = "\u001b[0m";
    private static final String ANSI_BACKGROUND_COLOR_GRASS = "\u001b[42;1m";
    private static final String ANSI_BACKGROUND_COLOR_TREE = "\u001b[46;1m";
    private static final String ANSI_BACKGROUND_COLOR_PREDATOR = "\u001b[41;1m";
    private static final String ANSI_BACKGROUND_COLOR_HERBIVORE = "\u001b[44;1m";
    private static final String ANSI_BACKGROUND_COLOR_ROCK = "\u001b[40;1m";
    private static final String ANSI_BACKGROUND_COLOR_WHITE = "\u001b[47;1m";


    @Override
    public void renderMap(Map map) {
        for(int row = 1; row <= Map.getColumnBorder(); row++ ) {
            StringBuilder line = new StringBuilder();
            for(int col = 1; col <= Map.getRowBorder(); col++) {
                Coordinates coordinates = new Coordinates(row, col);
                if(map.isSquareEmpty(coordinates)) {
                     line.append(getEmptySquare());
                } else {
                    line.append(colorizeEntity(map.getEntity(coordinates)));
                }

            }
            System.out.println(line.append(ANSI_RESET));
        }
    }

    private String colorizeEntity(Entity entity) {
        return switch (entity.getClass().getSimpleName()) {
            case "Herbivore" -> ANSI_BACKGROUND_COLOR_HERBIVORE + " H ";
            case "Predator" -> ANSI_BACKGROUND_COLOR_PREDATOR + " P ";
            case "Rock" -> ANSI_BACKGROUND_COLOR_ROCK + " R ";
            case "Tree" -> ANSI_BACKGROUND_COLOR_TREE + " T ";
            case "Grass" -> ANSI_BACKGROUND_COLOR_GRASS + " G ";
            default -> "  ";
        };
    }

    private String getEmptySquare() {
        return ANSI_BACKGROUND_COLOR_WHITE + "   ";
    }
}
