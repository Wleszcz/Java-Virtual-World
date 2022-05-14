package wiktor;

import wiktor.Organisms.Animals.*;
import wiktor.Organisms.Organism;
import wiktor.Organisms.Plants.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;


public class World extends JPanel {

    private static final int SPACE = 32;
    private static final int UP = 38;
    private static final int DOWN = 40;
    private static final int LEFT = 37;
    private static final int RIGHT = 39;
    private static final int SAVE = 83;
    private static final int SUPER = 155;
    private static final String SAVE_PATH = "save.txt";
    private static final boolean GRID = false;

    public static int SCALE = 30;

    private final Dimension tilesDimension;

    private int turn = 0;
    private Organism[][] board = null;
    private final ArrayList<Organism> organisms;

    public boolean ifReamover() {
        return reamover;
    }

    private Human human = null;

    private PrintWriter save = null;
    private BufferedReader load =null;

    private boolean reamover=false;

    private Organism selected = getAllOrganisms().get(0);


    public World(Dimension worldBorders) {
        setSize(worldBorders);
        Random random = new Random();

        tilesDimension = new Dimension(worldBorders.width / SCALE, worldBorders.height / SCALE);
        board = new Organism[tilesDimension.width][tilesDimension.height];

        organisms = new ArrayList<Organism>();

        newGame();

    }

        public void newGame() {
        ArrayList<Organism> allOrganisms;
        allOrganisms = getAllOrganisms();

        Random random = new Random();

        for (int i = 0; i < allOrganisms.size(); i++) {

            for (int j = 0; j < 2 + random.nextInt(10); j++) {
                Point point = new Point(random.nextInt(tilesDimension.width), random.nextInt(tilesDimension.height));

                if (!(allOrganisms.get(i) instanceof Human)) {
                    addOrganism(allOrganisms.get(i).Constructor(point));

                }
            }

        }


        Point point = new Point(random.nextInt(tilesDimension.width), random.nextInt(tilesDimension.height));
        addOrganism(new Human(point, this));

        updateBoard();
        sortOrganisms(organisms);
        //drawWorld();
    }

    public boolean isEmpty(Point point) {
        return board[point.x][point.y] == null;
    }

    public Organism getOrganism(Point point) {
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

    public void MakeTurn() {
        updateBoard();
        System.out.println("\n"+"--- Turn "+turn+"---");
        for (int i = 0; i < organisms.size(); i++) {
            if (organisms.get(i).isAlive()) {

                    organisms.get(i).action();


                organisms.get(i).getOlder();
                organisms.get(i).setReady(true);

            }

        }

        deleteKilled();
        drawWorld();

        turn++;
    }

    private void deleteKilled() {
        for (int i = organisms.size() - 1; i >= 0; i--) {
            if (!organisms.get(i).isAlive()) {
                organisms.remove(i);

            }
        }
    }


    public void updateBoard() {
        for (int i = 0; i < tilesDimension.width; i++) {
            for (int j = 0; j < tilesDimension.height; j++) {
                board[i][j] = null;
            }
        }
        for (Organism organism : organisms)
            board[organism.getPosition().x][organism.getPosition().y] = organism;
    }

    public boolean InBounds(Point point) {
        return point.x >= 0 && point.x < tilesDimension.width && point.y >= 0 && point.y < tilesDimension.height;
    }

    public void drawWorld() {
        Graphics graphics = getGraphics();
        graphics.setColor(Color.decode("#DEDEDE"));
        graphics.fillRect(0, 0, getWidth(), getHeight());



        for (Organism organism : organisms) {
            if (organism.isAlive()) {
                organism.print(graphics);
            }
        }

        if(GRID) {
            for (int i = 0; i < tilesDimension.width; i++) {
                for (int j = 0; j < tilesDimension.height; j++) {
                    graphics.setColor(Color.black);
                    graphics.drawRect(i * World.SCALE, j * World.SCALE, World.SCALE, World.SCALE);

                }
            }

        }

    }

    public void handleMousePress(MouseEvent event) {
        Point point = new Point(event.getPoint().x / SCALE, event.getPoint().y / SCALE);

        if(point.x>tilesDimension.width-1 || point.y>tilesDimension.height -1|| point.x<0 ||point.y<0){
            System.out.println("Nothing to see here :)");
            return;
        }
        if(reamover){
            if(board[point.x][point.y]!=null)
            board[point.x][point.y].die();
            board[point.x][point.y].print(getGraphics());
            deleteKilled();
            drawWorld();

        }
        else {
            if (isEmpty(point)) {
                if (selected != null) {
                    addOrganism(selected.Constructor(point));
                    getOrganism(point).print(getGraphics());
                }
            } else {

                Organism organism = getOrganism(point);

                System.out.println(organism.toString());
            }
        }
    }

    public void handleKeyPress(KeyEvent event) {
        int code = event.getKeyCode();
        //System.out.println(code);
        if (code == SPACE) {
            MakeTurn();

        } else if ((code == UP || code == DOWN || code == LEFT || code == RIGHT || code == SAVE || code == SUPER) && human != null) {
            human.handleKeys(code);
        }

    }

    public void setHuman(Human human) {
        this.human = human;
    }

    public void humanIsDead() {
        human = null;
    }

    public Human getHuman() {
        return human;
    }


    public void addOrganism(Organism organism) {
        if (isEmpty(organism.getPosition())) {

            organisms.add(organism);
            sortOrganisms(organisms);
            updateBoard();
        }
    }

    private void sortOrganisms(ArrayList<Organism> organisms) {

        Collections.sort(organisms, new Comparator<Organism>() {
            @Override
            public int compare(Organism o1, Organism o2) {
                return o2.getInitiative() - o1.getInitiative();
            }
        });

    }


    public void selectedOrganism(Organism selected) {
        this.selected = selected;
    }

    public void saveGame() {
        deleteKilled();
        clearSave();
        openSave();

        save.println("Turn:");
        save.println(turn);
        save.println("Amount of organisms:");
        save.println(organisms.size());

        for (Organism organism : organisms) {
            organism.save(save);
        }
        closeSave();
        System.out.println("GAME SAVED !");
    }

    private void clearSave() {
        File SaveFile = new File(SAVE_PATH);
        if(!SaveFile.delete()){
            System.out.println("File deleting problem !");
        };
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public void loadGame() {
        openSave();
        judgementDay();
        int newTurn=0;
        ArrayList<Organism> newOrganisms= new ArrayList<Organism>();

        try {
            int organismsAmount=0;

            load.readLine();                                //Comment About turn
            newTurn= Integer.parseInt(load.readLine());
            load.readLine();                                //Comment about amount
            organismsAmount = Integer.parseInt(load.readLine());



            for (int i = 0; i < organismsAmount; i++) {
                load.readLine();
                String type= load.readLine();
                load.readLine();
                int age= Integer.parseInt(load.readLine());
                load.readLine();
                int strength= Integer.parseInt(load.readLine());
                load.readLine();
                int x= Integer.parseInt(load.readLine());
                load.readLine();
                int y= Integer.parseInt(load.readLine());


                for (Organism baseOrganism:getAllOrganisms()) {
                    if(Objects.equals(baseOrganism.getType(), type)){
                        Organism organism = baseOrganism.Constructor(new Point(x,y));
                        organism.setStrength(strength);
                        organism.setAge(age);

                        if(organism instanceof Human){

                            String halo0=load.readLine();
                            boolean active= Boolean.parseBoolean(load.readLine());
                            String halo2=load.readLine();
                            int coolDown= Integer.parseInt(load.readLine());
                            int h=0;

                            ((Human) organism).setAbilityActive(active);
                            ((Human) organism).setAbilityCooldown(coolDown);
                            setHuman((Human) organism);
                        }
                        newOrganisms.add(organism);
                        //addOrganism(organism);
                    }


                }


            }


        } catch (IOException e) {
            e.printStackTrace();
        }

        turn=newTurn;
        for (Organism organism:newOrganisms) {
            addOrganism(organism);
            organism.print(getGraphics());
        }
        closeSave();
        System.out.println("GAME LOADED !");

    }

    public void judgementDay() {

        for (Organism organism:organisms) {
            organism.die();
        }
        deleteKilled();
        updateBoard();
        drawWorld();
    }

    private void closeSave() {
        if (save != null) {
            save.close();
        }
        try{
            load.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void openSave() {
        try {
            File SaveFile = new File(SAVE_PATH);


            FileWriter Fwriter = new FileWriter(SaveFile, true);
            FileReader Freader = new FileReader(SaveFile);
            save = new PrintWriter(Fwriter);

            load = new BufferedReader(Freader);

        } catch (IOException e) {
            System.out.println("Problem with saving into file !");
            e.printStackTrace();
        }
    }


    public void setRemover(boolean b) {
        reamover = b;
    }
}
