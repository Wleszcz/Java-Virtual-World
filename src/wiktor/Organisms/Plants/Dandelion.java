package wiktor.Organisms.Plants;

import wiktor.Organisms.Organism;
import wiktor.Organisms.Plant;
import wiktor.World;

import java.awt.*;

public class Dandelion extends Plant {


    public Dandelion(World world) {
        super(world);
    }

    @Override
    public void action() {
        for (int i = 0; i < 3; i++) {
            super.action();
        }
    }

    public Dandelion(Point position, World world) {
        super(position, world);
    }

    @Override
    public Organism Constructor(Point point) {
        return new Dandelion(point,world);
    }


    @Override
    public void setColor(Graphics g) {
        g.setColor(Color.YELLOW);
    }
}
