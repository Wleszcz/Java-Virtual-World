package wiktor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainWindow extends JFrame
{

    public static final String MAIN_WINDOW_TITLE = "Projekt PO";
    public static int MARGIN = 200;
    public static Dimension WORLD_BORDERS = new Dimension(Main.MAIN_WINDOW_DIMENSIONS.width-MARGIN,Main.MAIN_WINDOW_DIMENSIONS.height-MARGIN);
    private World world;

    public MainWindow(Dimension dimension) {
        setTitle(MAIN_WINDOW_TITLE);

        setSize(dimension);
        setResizable(false);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        world = new World(WORLD_BORDERS);
        world.setBounds(25, 25, WORLD_BORDERS.width, WORLD_BORDERS.height);
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

        JButton button = new JButton();
        button.setBounds(25, dimension.height-125, 200, 50);
        button.setText("Następna Tura");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                world.MakeTurn();
                world.rysujSwiat();

            }
        });
        add(button);

        setFocusable(true);
        setLayout(null);
        setVisible(true);
        world.setVisible(true);
        button.setVisible(true);
    }

    public void initialPrint() {
        world.rysujSwiat();
    }
}
