package wiktor.Organisms.Animals;

import wiktor.Organisms.Animal;
import wiktor.Organisms.Organism;
import wiktor.World;

import java.awt.*;

public class Wolf extends Animal {
    public Wolf(Point position, World world) {
        super(position, world);
        strength=9;
        initiative=5;
    }

    public Wolf(World world) {
        super(world);
    }

    @Override
    public Organism Constructor(Point point) {
        return new Wolf(point,world);
    }

    @Override
    public void setColor(Graphics g) {
        g.setColor(Color.GRAY);
    }
}
