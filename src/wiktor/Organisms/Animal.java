package wiktor.Organisms;

import wiktor.World;

import java.awt.*;

public abstract class Animal extends Organism{
    public Animal(Point position, World world) {
        super(position,world);
    }

    @Override
    public void action() {

    }

    @Override
    public void colision() {

    }
}
