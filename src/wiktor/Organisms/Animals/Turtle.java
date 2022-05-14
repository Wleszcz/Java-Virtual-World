package wiktor.Organisms.Animals;

import wiktor.Organisms.Animal;
import wiktor.Organisms.IDeflect;
import wiktor.Organisms.Organism;
import wiktor.World;

import java.awt.*;
import java.util.Random;

public class Turtle extends Animal implements IDeflect {
    private static final int BASE_STRENGTH = 2;
    private static final int BASE_INITIATIVE = 1;

    public Turtle(Point position, World world) {
        super(position, world);
        strength=BASE_STRENGTH;
        initiative=BASE_INITIATIVE;
    }

    public Turtle(World world) {
        super(world);
    }

    @Override
    public Organism Constructor(Point point) {
        return new Turtle(point,world);
    }

    @Override
    public void action() {
        Random random = new Random();
        if(random.nextInt(4)==0){
            super.action();
        }
    }

    @Override
    public void setColor(Graphics g) {
        g.setColor(Color.decode("#4F4F20"));
    }

    @Override
    public boolean deflectedAttack(Organism attacker) {
        return attacker.getStrength() < 5;
    }
}
