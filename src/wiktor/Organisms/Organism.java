package wiktor.Organisms;

import wiktor.World;

import java.awt.*;
import java.io.PrintWriter;
import java.lang.annotation.Repeatable;
import java.util.Random;

public abstract class Organism {
    protected Point position;
    protected int strength=0,initiative=0,age=0;
    protected boolean alive = true, ready=true;
    protected World world;
    protected int  range=1;



    public Organism(Point position, World world) {
        this.position = position;
        this.world = world;
    }


    public Organism(World world) {
        this.world=world;
    }

    abstract public  void collision(Organism attacker);

    public abstract void action();

    public abstract Organism Constructor(Point  point);


    public void setStrength(int strength) {
        this.strength = strength;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public abstract void setColor(Graphics g);
    public void print(Graphics g) {
        setColor(g);
        g.fillRect(position.x * World.SCALE, position.y * World.SCALE, World.SCALE, World.SCALE);
    }


    public void setReady(boolean state){
        ready=state;
    }

    public Point NearRandomEmptyPoint() {
        Point point;

        Random random = new Random();

        int i=0;
        while (true) {
            point= nearRandomPoint();
            if (world.isEmpty(point)) {
                return point;
            }
            i++;
            i++;

        }
    }

    @Override
    public String toString() {

        String organism="\n";
        organism+=getType();
        organism+="\n Strength: ";
        organism+=strength;
        organism+="\n AGE:      ";
        organism+=age;
        organism+="\n Position: ";
        organism+=position.x;
        organism+="   ";
        organism+=position.x;
        return organism;
    }

    public Point nearRandomPoint(){
        Point point;
        Random random = new Random();




        while (true){

            int xRand = random.nextInt(2*range+1);
            int yRand = random.nextInt(2*range+1);

            point = new Point(position.x - range + xRand, position.y - range + yRand);

            if(point.x==position.x && point.y==position.y){
                continue;
            }
            if (world.InBounds(point) ){
                return point;
            }


        }
    }

    public boolean isSurrounded(){
        for (int i = -range; i <= range; i++) {
            for (int j = -range; j <= range; j++) {
                Point point =new Point(this.getPosition().x-1+i,this.getPosition().y-1+j);
                if(i==0 && j==0){
                    continue;
                }

                if(world.InBounds(point) && world.isEmpty(point)){
                    return false;
                }
            }
        }
        return true;
    }

    public void getOlder(){
        age++;
    }

    public Point getPosition() {
        return position;
    }

    public int getStrength() {
        return strength;
    }

    public int getInitiative() {
        return initiative;
    }

    public int getAge() {
        return age;
    }

    public boolean isAlive() {
        return alive;
    }

    public boolean isReady() {
        return ready;
    }

    public void bonusStrength(){
        strength=strength+3;
        System.out.println(getType()+"  got bonus to strength");
    }

    public String getType(){
        return this.getClass().getSimpleName();
    }


    public void die(){
        alive=false;
        world.updateBoard();
    }


    public void save(PrintWriter save) {
        save.println("\n"+getType());
        save.println("Age:");
        save.println(age);
        save.println("Strength:");
        save.println(strength);
        save.println("Position X:");
        save.println(position.x);
        save.println("Position Y:");
        save.println(position.y);
    }
}
