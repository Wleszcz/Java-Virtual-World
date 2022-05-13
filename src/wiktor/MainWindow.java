package wiktor;

import wiktor.Organisms.Organism;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class MainWindow extends JFrame
{

    public static final String MAIN_WINDOW_TITLE = "Projekt PO";
    public static int MARGIN = 50;
    public static int MIN_MARGIN= 10;

    public static int BOX_WIDTH = 175;
    public static int BOX_HEIGHT = 40;

    public static int BOTTOM_MARGIN=120;

    public static Dimension WORLD_BORDERS = new Dimension((int) (Main.MAIN_WINDOW_DIMENSIONS.width-40),Main.MAIN_WINDOW_DIMENSIONS.height-BOTTOM_MARGIN);
    private World world;

    public MainWindow(Dimension dimension) {
        setTitle(MAIN_WINDOW_TITLE);

        setSize(dimension);
        setResizable(false);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        world = new World(WORLD_BORDERS);
        world.setBounds(MIN_MARGIN, MIN_MARGIN, WORLD_BORDERS.width, WORLD_BORDERS.height);
        add(world);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                world.handleKeyPress(e);
            }
        });

        world.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                world.handleMousePress(e);
            }
        });

        ArrayList<Organism> allOrganisms = world.getAllOrganisms();
        ArrayList<String> allOrganismsNames = new ArrayList<String>();

        JComboBox box = new JComboBox<String>();

        for (int i = 0; i < allOrganisms.size(); i++) {
            allOrganismsNames.add(allOrganisms.get(i).getType());
            box.addItem(allOrganismsNames.get(i));
        }
        

        box.setBounds(200,WORLD_BORDERS.height+3*MIN_MARGIN,BOX_WIDTH,BOX_HEIGHT);
        box.setFocusable(false);

        box.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox b = (JComboBox) (e.getSource());
                Organism selected = allOrganisms.get(b.getSelectedIndex());
                world.selectedOrganism(selected);
                System.out.println(selected.getType());

            }
        });

        add(box);

        JButton button = new JButton();
        button.setFocusable(false);
        button.setBounds(480, WORLD_BORDERS.height+3*MIN_MARGIN, BOX_WIDTH, BOX_HEIGHT);
        button.setText("NEXT TURN");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                world.MakeTurn();

            }
        });
        add(button);

        JButton abilityButton = new JButton();
        abilityButton.setFocusable(false);
        abilityButton.setBounds(200+BOX_WIDTH+MIN_MARGIN, WORLD_BORDERS.height+3*MIN_MARGIN, BOX_WIDTH/2, BOX_HEIGHT);
        abilityButton.setText("ABILITY");
        abilityButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(world.getHuman().isAlive()){
                    world.getHuman().specialAbility();
                }

            }
        });
        add(abilityButton);


        add(box);

        JButton saveButton = new JButton();
        saveButton.setFocusable(false);
        saveButton.setBounds(105, WORLD_BORDERS.height+3*MIN_MARGIN, BOX_WIDTH/2, BOX_HEIGHT);
        saveButton.setText("SAVE");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                world.saveGame();

            }
        });
        add(saveButton);


        JButton loadButton = new JButton();
        loadButton.setFocusable(false);
        loadButton.setBounds(10, WORLD_BORDERS.height+3*MIN_MARGIN, BOX_WIDTH/2, BOX_HEIGHT);
        loadButton.setText("LOAD");
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                world.loadGame();

            }
        });
        add(loadButton);




        setFocusable(true);
        setLayout(null);
        setVisible(true);
        world.setVisible(true);
        button.setVisible(true);
    }

    public void initialPrint() {
        world.drawWorld();
    }
}
