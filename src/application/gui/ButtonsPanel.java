package application.gui;

import application.Simulation;

import javax.swing.*;
import java.awt.*;

public class ButtonsPanel extends JPanel {

    private final JButton startButton = new JButton("start");
    private final JButton pauseButton = new JButton("pause");
    private final JButton turnNextButton = new JButton("turn next");

    public ButtonsPanel() {
        this.add(startButton);
        this.add(pauseButton);
        this.add(turnNextButton);

        this.setBackground(Color.LIGHT_GRAY);
        this.setPreferredSize(new Dimension(300, 35));
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        this.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);


        turnNextButtonClicked();
        startSimulationButtonClicked();
        pauseSimulationButtonClicked();
    }

    public void turnNextButtonClicked() {
        turnNextButton.addActionListener(e -> Simulation.getGame().nextTurn());
    }

    public void startSimulationButtonClicked() {
        startButton.addActionListener(e -> {
            startButton.setEnabled(false);
            new StartButtonWorker().execute();
        });
    }

    public void pauseSimulationButtonClicked() {
        pauseButton.addActionListener(e -> Simulation.getGame().stopGame());

    }

    private class StartButtonWorker extends SwingWorker<Void, Void> {

        @Override
        protected Void doInBackground() {
            Simulation.getGame().startGame();
            return null;
        }

        @Override
        protected void done() {
            startButton.setEnabled(true);
        }
    }

}
