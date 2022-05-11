package wiktor.Organisms;

import wiktor.World;

import java.awt.*;
import java.util.Random;

public abstract class Plant extends Organism{
    public Plant(Point position, World world) {
        super(position,world);
        initiative=0;
        strength=0;
    }

    public Plant(World world) {
        super(world);
    }

    @Override
    public void colision(Organism attacker) {
        if(strength>attacker.getStrength()){
            attacker.die();
        }
        else if (strength < attacker.getStrength()) {
            die();
        }
        else{
            die();
            attacker.die();
        }
    }

    @Override
    public void action() {
        Random random= new Random();

        if(random.nextInt(9)==0 && !isSurrounded() ){
            if(isSurrounded()){
                return;
            }
            Point point = this.nearRandomPoint();

            if (world.isEmpty(point)){
                Organism organism = Constructor(point);
                world.addOrganism(organism);

            }
        }
    }


}
