package application;

import application.actions.InitAction;
import application.actions.RespawnAction;
import application.actions.TurnAction;

import java.util.concurrent.TimeUnit;


public class Game {

    private final InitAction initAction = new InitAction();
    private final TurnAction turnAction = new TurnAction();
    private final RespawnAction respawnAction = new RespawnAction();
    private int movesCounter = 0;
    private boolean isGameStopped = false;

    public void nextTurn(Map map) {
        if(!respawnAction.isGrassEnough()) {
            respawnAction.respawnGrass();
        } else if(!respawnAction.isHerbivoreEnough()) {
            respawnAction.respawnHerbivore();
        }

        turnAction.creatureMoves(map);
        System.out.println("Moves: " + movesCounter);
        System.out.println("Grasses " + map.getCountGrass());
        System.out.println("Herbivores " + map.getCountHerbivore());

    }

    public void startGame(Map map) throws InterruptedException {
        initAction.initMap();
        turnAction.renderMap(map);

        while(!isGameStopped) {
            movesCounter++;
            nextTurn(map);

        }
    }

    public void stopGame() {
        isGameStopped = true;
    }

}
