package wiktor.Organisms.Animals;

import wiktor.Organisms.Animal;
import wiktor.Organisms.Organism;
import wiktor.World;

import java.awt.*;

public class Sheep extends Animal {
    public Sheep(Point position, World world) {
        super(position, world);
        strength= 4;
        initiative = 4;
    }

    public Sheep(World world) {
        super(world);
    }

    @Override
    public Organism Constructor(Point point) {
        return new Sheep(point,world);
    }

    @Override
    public void setColor(Graphics g) {
        g.setColor(Color.WHITE);
    }
}
