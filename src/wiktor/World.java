package wiktor;

import wiktor.Organisms.Organism;
import wiktor.Organisms.Plant;
import wiktor.Organisms.Plants.Grass;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;


public class World extends JPanel {

    public static int SCALE = 20;

    public boolean END = false;
    private Dimension tilesDimension;

    private int turn=0;
    private Organism board[][];
    private ArrayList<Organism> organisms;
    public World(Dimension worldBorders) {
        setSize(worldBorders);

        organisms = new ArrayList<Organism>();




        tilesDimension = new Dimension(worldBorders.width / SCALE, worldBorders.height / SCALE);
        board=new Organism[tilesDimension.width][tilesDimension.height];
        UpdateBoard();
        organisms.add(new Grass(new Point(1,1),this));
        organisms.add(new Grass(new Point(1,0),this));
    }

    public boolean IsEmpty(Point point) {
            if (board[point.x][point.y] == null) {
                return true;
            } else {
                return false;
            }
    }


    public int MakeTurn(){
        UpdateBoard();
        for (int i=0;i<organisms.size();i++){
            if(organisms.get(i).isAlive()){
                if(organisms.get(i).getAge()!=0 || organisms.get(i)  instanceof Plant){
                    organisms.get(i).action();
                }

                organisms.get(i).getOlder();
                organisms.get(i).setReady(true);


                turn++;

                if(END){
                    return 1;
                }
            }

        }
        return 0;
    }

    private void UpdateBoard() {
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
        graphics.setColor(Color.GRAY);
        graphics.fillRect(0, 0, getWidth(), getHeight());
        int i=0;
        for(Organism organism : organisms) {
            organism.print(graphics);
            System.out.println(i+"  "+organism.GetType()+"  "+ organism.getPosition().getX()+"  "+organism.getPosition().getY());
            i++;
        }
        System.out.println();
    }

    public void handleMousePress(MouseEvent event) {
        Point point= new Point(event.getPoint().x/SCALE,event.getPoint().y/SCALE);

        System.out.println(point.x + "   "+point.y);

        if(!IsEmpty(point)) {
            board[point.x][point.y].Die();
        }
        addOrganism(new Grass(point,this));
        board[point.x][point.y].print(getGraphics());

    }
    public void handleKeyPress(KeyEvent event) {

    }

    public void addOrganism(Organism organism) {
        organisms.add(organism);
        sortOrganisms(organisms);
        UpdateBoard();
    }

    private void sortOrganisms(ArrayList<Organism> organisms) {}


}
