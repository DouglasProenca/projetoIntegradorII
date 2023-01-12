package view;

import javax.swing.SwingUtilities;

public final class Sistema {

    public static void main(String[] args) {
        Runnable r = () -> {
            LookAndFeelScreen.initLookAndFeel();
            MainScreen mainScreen = new MainScreen();
            mainScreen.setVisible(true);
        };
        SwingUtilities.invokeLater(r);
    }
}