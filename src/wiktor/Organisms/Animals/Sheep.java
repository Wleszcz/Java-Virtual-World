package wiktor.Organisms.Animals;

import wiktor.Organisms.Animal;
import wiktor.Organisms.Organism;
import wiktor.World;

import java.awt.*;

public class Sheep extends Animal {

    private static final int BASE_STRENGTH = 4;
    private static final int BASE_INITIATIVE = 4;

    public Sheep(Point position, World world) {
        super(position, world);
        strength= BASE_STRENGTH;
        initiative = BASE_INITIATIVE;
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
