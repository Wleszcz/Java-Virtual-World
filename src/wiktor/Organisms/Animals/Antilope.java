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

    public Antilope(Point position, World world) {
        super(position, world);
        strength=4;
        initiative=4;
        range=2;
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

        if(random.nextInt((int)(1/CHANCE_TO_RUN_AWAY))==0){
            System.out.println(getType()+" doged the "+attacker.getType()+"'s attack");
            move(NearRandomEmptyPoint());
            return true;
        }
        return false;
    }
}

