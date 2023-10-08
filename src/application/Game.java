package application;

import application.actions.InitAction;
import application.actions.RespawnAction;
import application.actions.TurnAction;


public class Game {

    private final InitAction initAction = new InitAction();
    private final TurnAction turnAction = new TurnAction();
    private final RespawnAction respawnAction = new RespawnAction();
    private int movesCounter = 0;
    private boolean isGameStopped = false;

    public void nextTurn(Map map) {
        turnAction.creatureMoves(map);


        if(!respawnAction.isGrassEnough()) {
            respawnAction.respawnGrass();
        }
        if(!respawnAction.isHerbivoreEnough()) {
            respawnAction.respawnHerbivore();
        }

        System.out.println("Moves: " + movesCounter);
    }

    public void startGame(Map map) {
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
