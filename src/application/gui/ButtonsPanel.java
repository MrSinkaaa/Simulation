package application.gui;

import application.Map;
import application.Simulation;

import javax.swing.*;
import java.awt.*;

public class ButtonsPanel extends JPanel {

    private final JButton startSimulationButton = new JButton("Start Simulation");
    private final JButton pauseSimulationButton = new JButton("Pause Simulation");
    private final JButton resetSimulationButton = new JButton("Reset Simulation");
    private final JButton turnNextButton = new JButton("Turn Next");

    private final Map map;

    public ButtonsPanel() {
        this.add(startSimulationButton);
        this.add(pauseSimulationButton);
        this.add(resetSimulationButton);
        this.add(turnNextButton);

        this.setBackground(Color.LIGHT_GRAY);
        this.setPreferredSize(new Dimension(300,35));
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        this.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);


        this.map = Simulation.getMap();
        turnNextButtonClicked();
        startSimulationButtonClicked();
  //      resetSimulationButtonClicked();
    }

    public void turnNextButtonClicked() {
        turnNextButton.addActionListener(e -> {
            Simulation.getGame().nextTurn(map);
        });
    }

    public void startSimulationButtonClicked() {
        startSimulationButton.addActionListener(e -> {

//            Thread thread = new Thread(() -> {
                try {
                    Simulation.getGame().startGame(map);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
//            });

        });
    }

    public void pauseSimulationButtonClicked() {
        pauseSimulationButton.addActionListener(e -> {
            Simulation.getGame().stopGame();
        });
    }

//    public void resetSimulationButtonClicked() {
//        resetSimulationButton.addActionListener(e -> {
//            Simulation.getGame().stopGame();
//            try {
//                game = new Game();
//                game.startGame(map);
//            } catch (InterruptedException ex) {
//                throw new RuntimeException(ex);
//            }
//        });
//    }
}
