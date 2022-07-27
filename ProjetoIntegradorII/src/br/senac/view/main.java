package br.senac.view;

import javax.swing.SwingUtilities;

/**
 *
 * @author Douglas
 */
public class main {
        public static void main(String[] args) {
        Runnable r = () -> {
            LookAndFeelScreen.initLookAndFeel();
            MainScreen t = new MainScreen();
            t.setVisible(true);
        };
        SwingUtilities.invokeLater(r);
    }
}
