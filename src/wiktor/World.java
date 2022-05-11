package wiktor;

import wiktor.Organisms.Animals.Fox;
import wiktor.Organisms.Animals.Sheep;
import wiktor.Organisms.Animals.Wolf;
import wiktor.Organisms.Organism;
import wiktor.Organisms.Plant;
import wiktor.Organisms.Plants.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;


public class World extends JPanel {

    public static int SCALE = 20;

    private Dimension tilesDimension;

    private int turn=0;
    private Organism board[][]=null;
    private ArrayList<Organism> organisms;

    public World(Dimension worldBorders) {
        setSize(worldBorders);

         ArrayList<Organism> allOrganisms;



        organisms = new ArrayList<Organism>();
        allOrganisms = new ArrayList<Organism>();

        Random random= new Random();


        tilesDimension = new Dimension(worldBorders.width / SCALE, worldBorders.height / SCALE);
        board=new Organism[tilesDimension.width][tilesDimension.height];



        allOrganisms.add(new Grass(this));
        allOrganisms.add(new Belladonna(this));
        allOrganisms.add(new Dandelion(this));
        allOrganisms.add(new Guarana(this));
        allOrganisms.add(new SosnowskysHogweed(this));
        allOrganisms.add(new Wolf(this));
        allOrganisms.add(new Sheep(this));
        allOrganisms.add(new Fox(this));



        for (int i = 0; i < allOrganisms.size(); i++) {

            for (int j = 0; j < 2+random.nextInt(10); j++) {
                Point point=new Point(random.nextInt(tilesDimension.width),random.nextInt(tilesDimension.height));
                organisms.add(allOrganisms.get(i).Constructor(point));
            }

        }
//        organisms.add(new Sheep(new Point(0,0),this));
//        organisms.add(new Sheep(new Point(1,1),this));
//        organisms.add(new Sheep(new Point(1,2),this));

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


    public void MakeTurn(){
        updateBoard();
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
        if(point.x >= 0 && point.x < tilesDimension.width && point.y >= 0 && point.y < tilesDimension.height){
            return true;
        }
        return false;
    }

    public void rysujSwiat() {
        Graphics graphics = getGraphics();
        graphics.setColor(Color.decode("#DEDEDE"));
        graphics.fillRect(0, 0, getWidth(), getHeight());

//        for (int i = organisms.size()-1; i >=0; i--) {
//            organisms.get(i).print(graphics);
//        }
        for (int i = 0; i < organisms.size(); i++) {
            if(organisms.get(i).isAlive()) {
                organisms.get(i).print(graphics);
            }
        }



    }

    public void handleMousePress(MouseEvent event) {
        Point point= new Point(event.getPoint().x/SCALE,event.getPoint().y/SCALE);

        Organism organism=board[point.x][point.y];
        System.out.println(organism.getType()+"   "+ organism.getStrength());



        System.out.println(point.x + "   "+point.y);
 /*
        if(!isEmpty(point)) {
            board[point.x][point.y].die();
        }
        addOrganism(new Grass(point,this));
        board[point.x][point.y].print(getGraphics());
*/
    }
    public void handleKeyPress(KeyEvent event) {

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


}
