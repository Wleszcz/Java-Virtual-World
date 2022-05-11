package wiktor.Organisms.Plants;

import wiktor.Organisms.Organism;
import wiktor.Organisms.Plant;
import wiktor.World;

import java.awt.*;

public class Grass extends Plant {

    public Grass(World world) {
        super(world);
    }

    @Override
    public void setColor(Graphics g) {
        g.setColor(Color.GREEN);
    }

    public Grass(Point point, World world)
    {
        super(point,world);
    }

    @Override
    public Organism Constructor(Point point) {
        return new Grass(point,world);
    }


}
