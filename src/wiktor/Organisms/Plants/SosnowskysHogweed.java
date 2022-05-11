package wiktor.Organisms.Plants;

import wiktor.Organisms.Animal;
import wiktor.Organisms.Organism;
import wiktor.Organisms.Plant;
import wiktor.World;

import java.awt.*;
import java.util.Random;



public class SosnowskysHogweed extends Plant {

    private static int  RANGE = 1;
    public SosnowskysHogweed(Point position, World world) {
        super(position, world);
        strength=10;
    }

    @Override
    public void colision(Organism attacker) {
        attacker.die();
    }

    @Override
    public void action() {
        Random random= new Random();
        Point point;

        for (int i = -RANGE; i <= RANGE; i++) {
            for (int j = -RANGE; j <= RANGE; j++) {
                point= new Point(position.x+i,position.y+j);
                if(world.InBounds(point) && !world.isEmpty(point) && world.getOrganism(point) instanceof Animal){
                    world.getOrganism(point).die();
                    System.out.println(world.getOrganism(point).getType()+"  was killed by "+getType());
                }
            }
        }
    }

    public SosnowskysHogweed(World world) {
        super(world);
    }

    @Override
    public Organism Constructor(Point point) {
        return new SosnowskysHogweed(point,world);
    }

    @Override
    public void setColor(Graphics g) {
        g.setColor(Color.decode("#000000"));
    }
}
