package wiktor;

import java.awt.*;


public class Main {
    public static Dimension MAIN_WINDOW_DIMENSIONS = new Dimension(800, 600);



    public static void main(String[] args) {
        MainWindow mainWindow = new MainWindow(MAIN_WINDOW_DIMENSIONS);
        EventQueue.invokeLater(() -> {
            mainWindow.initialPrint();
        });
    }
}