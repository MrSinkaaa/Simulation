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

    private final Map map;

    public Game() {
        map = Simulation.getMap();
        initAction.initMap();
        turnAction.renderMap(map);
    }

    public void nextTurn() {
        turnAction.creatureMoves(map);

        if(!respawnAction.isGrassEnough()) {
            respawnAction.respawnGrass();
        }
        if(!respawnAction.isHerbivoreEnough()) {
            respawnAction.respawnHerbivore();
        }
        movesCounter++;
        System.out.println("Moves: " + movesCounter);
    }

    public void startGame() {
        isGameStopped = false;
        turnAction.renderMap(map);

        while(!isGameStopped) {
            nextTurn();
        }
    }

    public void stopGame() {
        isGameStopped = true;
    }

}
