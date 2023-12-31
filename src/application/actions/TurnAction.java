package application.actions;

import application.Map;
import application.entities.creatures.Creature;
import application.gui.mapRenderer.MapRenderer;
import application.gui.mapRenderer.SwingMapRenderer;


public class TurnAction extends Action {
    private final MapRenderer renderer = new SwingMapRenderer();


    public void creatureMoves(Map map) {
        for (Creature creature : map.getEntitiesOfType(Creature.class).values()) {
            creature.makeMove(map);
        }
        renderMap(map);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void renderMap(Map map) {
        System.out.println();
        renderer.renderMap(map);
    }

}
