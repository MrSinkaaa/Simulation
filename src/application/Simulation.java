package application;


public class Simulation {

    private static final Map map = new Map();


    public static void main(String[] args) throws InterruptedException {
        Game game = new Game();
        game.startGame(map);
    }

    public static Map getMap() {
        return map;
    }
}
