package application.entities.creatures;

import application.entities.Entity;
import application.entities.Grass;

import java.util.ArrayDeque;


public class Herbivore extends Creature {


    public Herbivore(int health, int speed) {
        this.pathToTarget = new ArrayDeque<>();
        this.speed = speed;
        this.health = health;

    }


    @Override
    protected Class<? extends Entity> getTarget() {
        return Grass.class;
    }


}
