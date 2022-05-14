package wiktor.Organisms.Animals;

import org.w3c.dom.ranges.Range;
import wiktor.Organisms.Animal;
import wiktor.Organisms.IDeflect;
import wiktor.Organisms.Organism;
import wiktor.World;

import java.awt.*;
import java.util.Random;

public class Antilope extends Animal implements IDeflect {
    private static final double CHANCE_TO_RUN_AWAY = 0.5;
    private static final int BASE_STRENGTH = 4;
    private static final int BASE_INITIATIVE = 4;
    private static final int RANGE = 2;

    public Antilope(Point position, World world) {
        super(position, world);
        strength=BASE_STRENGTH;
        initiative=BASE_INITIATIVE;
        range=RANGE;
    }

    public Antilope(World world) {
        super(world);
    }



    @Override
    public Organism Constructor(Point point) {
        return new Antilope(point,world);
    }


    @Override
    public void setColor(Graphics g) {
        g.setColor(Color.decode("#F0C711"));

    }

    @Override
    public boolean deflectedAttack(Organism attacker) {
        Random random=new Random();

        if(random.nextInt((int)(1/CHANCE_TO_RUN_AWAY))==0 && !isSurroundedbyAnimals()){
            System.out.println(getType()+" doged the "+attacker.getType()+"'s attack");
            move(NearRandomEmptyPoint());
            return true;
        }
        return false;
    }
}

