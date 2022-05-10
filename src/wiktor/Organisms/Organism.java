package wiktor.Organisms;

import wiktor.World;

import java.awt.*;
import java.util.Random;

public abstract class Organism {
    protected Point position;
    protected int strength=0,initiative=0,age=0;
    protected boolean alive = true, ready=true;
    protected String type;
    protected World world;



    public Organism(Point position, World world) {
        this.position = position;
        this.world = world;
    }

    public abstract void action();

    public abstract Organism Constructor(Point  point);
    public abstract void colision();

    public abstract void setSolor(Graphics g);
    public void print(Graphics g) {
        setSolor(g);
        g.fillRect(position.x * World.SCALE, position.y * World.SCALE, World.SCALE, World.SCALE);
    }


    public void setReady(boolean state){
        ready=state;
    }

    public Point NearRandomPoint() {
        Point point;
        Random random = new Random();

        int i=0;
        while (true) {
            int xRand = random.nextInt(3);
            int yRand = random.nextInt(3);

            point = new Point(position.x - 1 + xRand, position.y - 1 + yRand);

            if (world.InBounds(point) && !(xRand == 0 && yRand == 0) && world.IsEmpty(point)) {
                return point;
            }
            i++;
        }
    }

    public boolean isSurrounded(){
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Point point =new Point(this.getPosition().x-1+i,this.getPosition().y-1+j);
                if(world.InBounds(point) && world.IsEmpty(point)){
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

    public void BonusStrength(){
        strength=strength*3;
    }

    public String GetType(){
        return type;
    }


    public void Die(){
        alive=false;
    }



}
