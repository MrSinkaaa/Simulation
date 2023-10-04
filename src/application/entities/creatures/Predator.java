package application.entities.creatures;

import application.entities.Entity;

import java.util.ArrayDeque;

public class Predator extends Creature {

    private final int attackPower;

    public Predator(int health, int speed, int attackPower) {
        this.attackPower = attackPower;
        this.pathToTarget = new ArrayDeque<>();
        this.speed = speed;
        this.health = health;

    }

    public int getAttackPower() {
        return attackPower;
    }


    @Override
    protected Class<? extends Entity> getTarget() {
        return Herbivore.class;
    }
}
