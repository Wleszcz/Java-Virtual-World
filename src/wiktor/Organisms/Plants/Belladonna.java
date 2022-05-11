package wiktor.Organisms.Plants;

import wiktor.Organisms.Organism;
import wiktor.Organisms.Plant;
import wiktor.World;

import java.awt.*;

public class Belladonna extends Plant {
    public Belladonna(World world) {
        super(world);
    }

    @Override
    public void colision(Organism attacker) {

        attacker.die();
        System.out.println(attacker.getType()+ "   was poisoned by  "+ getType());
    }

    public Belladonna(Point position, World world) {
        super(position, world);
        strength=99;
    }

    @Override
    public Organism Constructor(Point point) {
        return new Belladonna(point,world);
    }

    @Override
    public void setColor(Graphics g) {
        g.setColor(Color.decode("#7A3D7A"));
    }
}
