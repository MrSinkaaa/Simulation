package application.gui.mapRenderer;

import application.Coordinates;
import application.Map;
import application.entities.Entity;
import application.gui.ButtonsPanel;
import application.gui.EntityContainer;
import application.gui.MapPanel;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class SwingMapRenderer extends MapRenderer {

    private final MapPanel simulationPanel = new MapPanel();
    private final String URL = "resource/images/";

    public SwingMapRenderer() {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Game of Life");

        window.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridy = 0;
        window.add(simulationPanel, constraints);

        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridy = 1;
        constraints.weightx = 1;
        constraints.anchor = GridBagConstraints.PAGE_END;
        window.add(new ButtonsPanel(), constraints);

        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }

    @Override
    public void renderMap(Map map) {
        simulationPanel.removeAll();

        for(int row = 1; row <= Map.getRowBorder(); row++) {
            for(int col = 1; col <= Map.getColumnBorder(); col++) {
                Coordinates coordinates = new Coordinates(row, col);
                Entity entity = map.getEntity(coordinates);
                simulationPanel.add(new EntityContainer(entity, getIcon(entity)));
            }
        }
        simulationPanel.revalidate();
        simulationPanel.repaint();
    }

    private ImageIcon getIcon(Entity entity) {
        if(entity == null) return new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource(URL + "void.png")));

        return switch(entity.getClass().getSimpleName()) {
            case "Grass" -> new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource(URL + "grass.png")));
            case "Herbivore" -> new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource(URL + "herbivore.png")));
            case "Predator" -> new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource(URL + "predator.png")));
            case "Tree" -> new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource(URL + "tree.png")));
            case "Rock" -> new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource(URL + "rock.png")));
            default -> new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource(URL + "void.png")));
        };
    }
}
