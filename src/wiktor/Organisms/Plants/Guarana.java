package wiktor.Organisms.Plants;

import wiktor.Organisms.Organism;
import wiktor.Organisms.Plant;
import wiktor.World;

import java.awt.*;

public class Guarana extends Plant {
    public Guarana(Point position, World world) {
        super(position, world);
    }

    public Guarana(World world) {
        super(world);
    }

    @Override
    public Organism Constructor(Point point) {
        return new Guarana(point,world);
    }

    @Override
    public void colision(Organism attacker) {
        attacker.bonusStrength();
        die();
        getClass().getSimpleName();
    }

    @Override
    public void setColor(Graphics g) {
        g.setColor(Color.decode("#9E3633"));
    }
}
