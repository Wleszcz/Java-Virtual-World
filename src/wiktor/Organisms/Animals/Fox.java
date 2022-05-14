package wiktor.Organisms.Animals;

import wiktor.Organisms.Animal;
import wiktor.Organisms.Organism;
import wiktor.World;

import java.awt.*;

public class Fox extends Animal {
    private static final int BASE_STRENGTH = 3;
    private static final int BASE_INITIATIVE = 7;

    public Fox(Point position, World world) {
        super(position, world);
        strength=BASE_STRENGTH;
        initiative=BASE_INITIATIVE;
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
