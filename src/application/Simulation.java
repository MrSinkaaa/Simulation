package application;

public class Simulation {

    private static final Map map = new Map();
    private static Game game;


    public static void main(String[] args) {
        game = createNewGame();
 //       game.startGame(map);
    }

    public static Map getMap() {
        return map;
    }

    public static Game getGame() {
        return game;
    }

    public static Game createNewGame() {
        return new Game();
    }
}
