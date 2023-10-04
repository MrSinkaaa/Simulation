package application;

public class Simulation {

    private static final Map map = new Map();
    private static Game game;


    public static void main(String[] args) throws InterruptedException {
        game = new Game();
        game.startGame(map);
    }

    public static Map getMap() {
        return map;
    }

    public static Game getGame() {
        return game;
    }
}
