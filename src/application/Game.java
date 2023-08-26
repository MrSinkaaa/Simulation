package application;

import application.actions.InitAction;
import application.actions.TurnAction;

import java.util.concurrent.TimeUnit;


public class Game {

    private final InitAction initAction = new InitAction();
    private final TurnAction turnAction = new TurnAction();
    private int movesCounter = 0;
    private boolean isGameStopped = false;

    public void nextTurn(Map map) {
        System.out.println("Staged moves: " + movesCounter);
        turnAction.creatureMoves(map);
        turnAction.renderMap(map);
    }

    public void startGame(Map map) throws InterruptedException {
        initAction.initMap();
        turnAction.renderMap(map);

        while(!isGameStopped) {
            movesCounter++;
            nextTurn(map);

            TimeUnit.SECONDS.sleep(2);
        }
    }

    private void stopGame() {
        isGameStopped = true;
    }

}
