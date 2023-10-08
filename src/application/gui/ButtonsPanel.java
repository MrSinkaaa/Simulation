package application.gui;

import application.Map;
import application.Simulation;

import javax.swing.*;
import java.awt.*;

public class ButtonsPanel extends JPanel {

    private final JButton startButton = new JButton("start");
    private final JButton pauseButton = new JButton("pause");
    private final JButton resetSimulationButton = new JButton("reset");
    private final JButton turnNextButton = new JButton("turn next");

    private final Map map;

    public ButtonsPanel() {
        this.add(startButton);
        this.add(pauseButton);
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
        startButton.addActionListener(e -> {

//            Thread thread = new Thread(() -> {
            Simulation.getGame().startGame(map);
            //            });

        });
    }

    public void pauseSimulationButtonClicked() {
        pauseButton.addActionListener(e -> {
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
