package wiktor.Organisms;

import wiktor.World;

import java.awt.*;
import java.sql.SQLOutput;
import java.util.Random;

public abstract class Animal extends Organism{
    public Animal(Point position, World world) {
        super(position,world);
    }

    public Animal(World world) {
        super(world);
    }

    @Override
    public void action() {
        Point point= nearRandomPoint();
        move(point);
        world.updateBoard();
    }

    protected void move(Point point) {
        if(!world.isEmpty(point)){
            Organism attacked = world.getOrganism(point);

            if(attacked.getClass().isInstance(this)){
                if(attacked!=this){
                    mulitply(attacked);
                    ready=false;
                    attacked.setReady(false);
                }
                return;
            }
            else {


                if(attacked instanceof IDeflect) {
                    IDeflect d = (IDeflect) attacked;
                    if(!d.deflectedAttack(this)) {
                        //attacked.colision(this);
                        //position.setLocation(point);
                    }
                }
                attacked.colision(this);
                if(this.alive){
                    position.setLocation(point);
                    world.updateBoard();
                }
            }
        }
        position.setLocation(point);
        world.updateBoard();

    }

    @Override
    public void colision(Organism attacker) {
        if(attacker.getStrength() > strength){
            System.out.println(getType()+" was eaten by "+ attacker.getType());
            die();
        }
        else if(attacker.getStrength()<strength){
            attacker.die();
            System.out.println(attacker.getType()+" was eaten by "+ getType());
        }
        else{
            die();
            attacker.die();
            System.out.println(attacker.getType() +" and "+getType()+" ate one ather");
        }
        world.updateBoard();
    }

    private void mulitply(Organism attacked) {
        if(!isSurrounded()){
            Point point=nearPointWithoutAnimal();

            Organism organism= this.Constructor(point);
            //world.getOrganism(point).die();
            world.addOrganism(organism);


            System.out.println(getType() + "  multiplied");
            return;
        }

    }

    private boolean isSurroundedbyAnimals() {
        int range = 1;

        for (int i = -range; i <= range; i++) {
            for (int j = -range; j <= range; j++) {
                Point point =new Point(this.getPosition().x+i,this.getPosition().y+j);

                if(i==0 && j==0){
                    continue;
                }
                if(world.InBounds(point) && (world.isEmpty(point) || !(world.getOrganism(point)instanceof Animal))){
                    return false;
                }
            }
        }
        return true;
    }

    private Point nearPointWithoutAnimal(){
        Point point;


        Random random = new Random();

        int i=0;
        while (true) {
            point= nearRandomPoint();
            if (world.isEmpty(point) || !(world.getOrganism(point) instanceof Animal)){
                return point;
            }
            i++;

        }

    }
}

