package application.gui;


import application.Map;

import javax.swing.*;
import java.awt.*;

public class MapPanel extends JPanel {

    //SCREEN SETTINGS
    private final int maxCol = Map.getColumnBorder();
    private final int maxRow = Map.getRowBorder();

    private final int gridSize = 45;

    private final int screenWidth = gridSize * maxCol;
    private final int screenHeight = gridSize * maxRow;


    public MapPanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.DARK_GRAY);
        this.setLayout(new GridLayout(maxRow, maxCol));


    }

}
