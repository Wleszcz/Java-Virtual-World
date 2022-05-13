package wiktor.Organisms.Animals;

import wiktor.Organisms.Animal;
import wiktor.Organisms.Organism;
import wiktor.World;

import java.awt.*;
import java.io.PrintWriter;


public class Human extends Animal {

    private static final int UP = 38;
    private static final int DOWN = 40;
    private static final int LEFT = 37;
    private static final int RIGHT = 39;
    private static final int SAVE = 83;
    private static final int SUPER = 155;

    private static final int COOL_DOWN = 5;
    private static final int ABILITY_STRENGTH=10;

    private static final int BASE_STRENGTH = 5;
    private static final int BASE_INITIATIVE = 4;


    boolean abilityActive = false;
    int abilityCooldown = 0;

    @Override
    public void die() {
        super.die();
        world.humanIsDead();
    }


    public Human(Point position, World world) {
        super(position, world);
        strength= BASE_STRENGTH;
        initiative=4;
        world.setHuman(this);
    }
    public Human(Point position, World world, int strenght,boolean abilityActive,int cooldown){
        super(position, world);
        strength= strenght;
        initiative=4;
        world.setHuman(this);
        this.abilityActive=abilityActive;
        this.abilityCooldown=cooldown;
    }


    public Human(World world) {
        super(world);
    }

    @Override
    public void action() {

    }

    public void handleKeys(int code){
        Point point;
        switch (code){
            case UP:
                point = new Point(position.x, position.y-1);
                move(point);
                break;
            case DOWN:
                point = new Point(position.x, position.y+1);
                move(point);
                break;
            case LEFT:
                point = new Point(position.x-1, position.y);
                move(point);
                break;
            case RIGHT:
                point = new Point(position.x+1, position.y);
                move(point);
                break;
            case SUPER:
                specialAbility();
                break;

        }
        world.MakeTurn();
    }


    @Override
    public void getOlder() {
        if(abilityActive) {

            if (strength > BASE_STRENGTH) {
                strength--;

            } else if ( strength == BASE_STRENGTH) {
                abilityCooldown = COOL_DOWN;
                abilityActive = false;
            }
        }
        else {
            if(abilityCooldown!=0){
                abilityCooldown--;
            }
        }

        super.getOlder();
    }

    public boolean isAbilityActive() {
        return abilityActive;
    }

    public int getAbilityCooldown() {
        return abilityCooldown;
    }

    public void specialAbility() {
        if(abilityCooldown!=0) {
            System.out.println("Ability was already used and will be ready in "+abilityCooldown+" turns");
        }
        else{
            if(abilityActive){
                System.out.println("Ability is active");

            }
            else if(strength<ABILITY_STRENGTH){
                System.out.println("Ability activated");
                abilityActive=true;
                strength=ABILITY_STRENGTH;
        }
        }
    }

    public void setAbilityActive(boolean abilityActive) {
        this.abilityActive = abilityActive;
    }

    public void setAbilityCooldown(int abilityCooldown) {
        this.abilityCooldown = abilityCooldown;
    }

    @Override
    public String toString() {
        String organism=super.toString();
        organism+="\n Ability ";
        if(abilityActive){
            organism+= "is Active";
        }
        else {
            organism+="isn't active";
        }
        organism+="\n Ability cooldown:  ";
        organism+=abilityCooldown;
        return organism;
    }

    @Override
    public void save(PrintWriter save) {
        super.save(save);
        save.println("Ability active:");
        save.println(abilityActive);
        save.println("Ability cooldown:");
        save.println(abilityCooldown);
    }

    @Override
    public Organism Constructor(Point point) {
        return new Human(point,world);
    }

    @Override
    public void setColor(Graphics g) {
        g.setColor(Color.cyan);
    }
}
