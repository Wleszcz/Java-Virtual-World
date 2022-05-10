package wiktor.Organisms.Plants;

import wiktor.Organisms.Organism;
import wiktor.Organisms.Plant;
import wiktor.World;

import java.awt.*;

public class Grass extends Plant {
    @Override
    public void setSolor(Graphics g) {
        g.setColor(Color.GREEN);
    }

    public Grass(Point point, World world)
    {
        super(point,world);
        type="Grass";
    }

    @Override
    public Organism Constructor(Point point) {
        return new Grass(point,world);
    }
}
