package view;

import javax.swing.SwingUtilities;

public final class Sistema {

    public static void main(String[] args) {
        Runnable r = () -> {
            LookAndFeelScreen.initLookAndFeel();
            IntroScreen introScreen = new IntroScreen();
            introScreen.setVisible(true);
        }; 
        SwingUtilities.invokeLater(r);
    }
}