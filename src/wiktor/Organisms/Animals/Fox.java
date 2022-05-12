package wiktor.Organisms.Animals;

import wiktor.Organisms.Animal;
import wiktor.Organisms.Organism;
import wiktor.World;

import java.awt.*;

public class Fox extends Animal {
    public Fox(Point position, World world) {
        super(position, world);
        strength=3;
        initiative=7;
    }

    public Fox(World world) {
        super(world);
    }

    @Override
    public Organism Constructor(Point point) {
        return new Fox(point,world);
    }

    @Override
    public void action() {
        Point point= nearRandomPoint();

        if(world.isEmpty(point) || world.getOrganism(point).getStrength()<strength){
            move(point);
            world.updateBoard();
        }

    }

    @Override
    public void setColor(Graphics g) {
        g.setColor(Color.decode("#CF692D"));
    }
}
