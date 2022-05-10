package wiktor.Organisms;

import wiktor.World;

import java.awt.*;
import java.util.Random;

public abstract class Plant extends Organism{
    public Plant(Point position, World world) {
        super(position,world);
    }

    @Override
    public void colision() {

    }

    @Override
    public void action() {
        Random random= new Random();

        if(random.nextInt(4)==0 && !isSurrounded() ){
            if(isSurrounded()){
                return;
            }
            Point point = this.NearRandomPoint();

            if (world.IsEmpty(point)){
                Organism organism = Constructor(point);
                world.addOrganism(organism);

            }
        }
    }
}
