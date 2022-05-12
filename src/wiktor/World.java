package wiktor;

import wiktor.Organisms.Animals.*;
import wiktor.Organisms.Organism;
import wiktor.Organisms.Plant;
import wiktor.Organisms.Plants.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;


public class World extends JPanel {

    private static final int SPACE = 32;
    private static final int UP = 38;
    private static final int DOWN = 40;
    private static final int LEFT = 37;
    private static final int RIGHT = 39;
    private static final int SAVE = 83;
    private static final int SUPER = 155;

    public static int SCALE = 20;

    private Dimension tilesDimension;

    private int turn=0;
    private Organism board[][]=null;
    private ArrayList<Organism> organisms;
    private Human human=null;

    private Organism selected=getAllOrganisms().get(0);


    public World(Dimension worldBorders) {
        setSize(worldBorders);


        tilesDimension = new Dimension(worldBorders.width / SCALE, worldBorders.height / SCALE);
        board=new Organism[tilesDimension.width][tilesDimension.height];


        ArrayList<Organism> allOrganisms;
        organisms = new ArrayList<Organism>();
        allOrganisms = getAllOrganisms();


        Random random= new Random();


        for (int i = 0; i < allOrganisms.size(); i++) {

            for (int j = 0; j < 2+random.nextInt(10); j++) {
                Point point=new Point(random.nextInt(tilesDimension.width),random.nextInt(tilesDimension.height));

                if(!(allOrganisms.get(i)instanceof Human)) {
                    organisms.add(allOrganisms.get(i).Constructor(point));
                }
            }

        }


        Point point=new Point(random.nextInt(tilesDimension.width),random.nextInt(tilesDimension.height));
        organisms.add(new Human(point,this));

        updateBoard();
        sortOrganisms(organisms);
    }

    public boolean isEmpty(Point point) {
            if (board[point.x][point.y] == null) {
                return true;
            } else {
                return false;
            }
    }

    public Organism getOrganism(Point point){
        return board[point.x][point.y];
    }

    public ArrayList<Organism> getAllOrganisms() {
        ArrayList<Organism> baseOrganisms = new ArrayList<Organism>();

        baseOrganisms.add(new Grass(this));
        baseOrganisms.add(new Belladonna(this));
        baseOrganisms.add(new Dandelion(this));
        baseOrganisms.add(new Guarana(this));
        baseOrganisms.add(new SosnowskysHogweed(this));
        baseOrganisms.add(new Wolf(this));
        baseOrganisms.add(new Sheep(this));
        baseOrganisms.add(new Fox(this));
        baseOrganisms.add(new Turtle(this));
        baseOrganisms.add(new Antilope(this));

        baseOrganisms.add(new Human(this));

        return baseOrganisms;
    }

    public void MakeTurn(){
        updateBoard();
        //System.out.println(human.getStrength());
        for (int i=0;i<organisms.size();i++){
            if(organisms.get(i).isAlive()){

                if(organisms.get(i).getAge()!=0 || organisms.get(i)  instanceof Plant){
                    organisms.get(i).action();
                }

                organisms.get(i).getOlder();
                organisms.get(i).setReady(true);


                turn++;

            }

        }
        delateKilled();
        drawWorld();
    }

    private void delateKilled() {
        for (int i = organisms.size()-1; i >= 0; i--) {
            if(!organisms.get(i).isAlive()){
                organisms.remove(i);

            }
        }
    }



    public void updateBoard() {
        for (int i=0 ; i<tilesDimension.width;i++){
            for (int j=0 ; j<tilesDimension.height;j++){
                board[i][j]=null;
            }
        }
        for(Organism organism:organisms)
            board[organism.getPosition().x][organism.getPosition().y]=organism;
    }

    public boolean InBounds(Point point){
        return point.x >= 0 && point.x < tilesDimension.width && point.y >= 0 && point.y < tilesDimension.height;
    }

    public void drawWorld() {
        Graphics graphics = getGraphics();
        graphics.setColor(Color.decode("#DEDEDE"));
        graphics.fillRect(0, 0, getWidth(), getHeight());


        for (int i = 0; i < organisms.size(); i++) {
            if(organisms.get(i).isAlive()) {
                organisms.get(i).print(graphics);
            }
        }



    }

    public void handleMousePress(MouseEvent event) {
        Point point= new Point(event.getPoint().x/SCALE,event.getPoint().y/SCALE);

        if(isEmpty(point)){
            if(selected!=null) {
                addOrganism(selected.Constructor(point));
                getOrganism(point).print(getGraphics());
            }
        }
        else{

            Organism  organism=getOrganism(point);

            System.out.println(organism.toString());
        }


    }
    public void handleKeyPress(KeyEvent event) {
        int code = event.getKeyCode();
        //System.out.println(code);
            if(code == SPACE){
                MakeTurn();

            }
            else if((code == UP || code == DOWN || code == LEFT || code == RIGHT || code== SAVE ||code == SUPER)&& human!=null){
                human.handleKeys(code);
            }

    }
    public void setHuman(Human human){
        this.human=human;
    }

    public void humanIsDead(){
        human=null;
    }

    public Human getHuman(){
        return human;
    }


    public void addOrganism(Organism organism) {

        if(isEmpty(organism.getPosition())){
            organisms.add(organism);
            sortOrganisms(organisms);
            updateBoard();
        }
    }

    private void sortOrganisms(ArrayList<Organism> organisms) {

        Collections.sort(organisms, new Comparator<Organism>() {
            @Override
            public int compare(Organism o1, Organism o2) {
                return  o2.getInitiative() - o1.getInitiative() ;
            }
        });

    }


    public void selectedOrganism(Organism selected) {
        this.selected=selected;
    }
}
