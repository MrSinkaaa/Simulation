package application.gui;

import application.Map;
import application.entities.Entity;

import javax.swing.*;
import java.awt.*;

public class EntityContainer extends JLabel {

    private final Entity entity;

    public EntityContainer(Entity entity, ImageIcon icon) {
        this.entity = entity;

        this.setPreferredSize(new Dimension(Map.getRowBorder(), Map.getColumnBorder()));
        this.setBackground(Color.WHITE);
        this.setHorizontalTextPosition(JLabel.CENTER);
        this.setVerticalTextPosition(JLabel.CENTER);
        this.setIcon(icon);
    }

    public Entity getEntity() {
        return entity;
    }

}
