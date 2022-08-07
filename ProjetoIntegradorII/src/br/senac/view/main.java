package br.senac.view;

import br.senac.objects.JMenuMenu;
import br.senac.objects.ConnectionManager;
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
            JMenuMenu menu = new JMenuMenu();
            menu.connection(ConnectionManager.getConexao());
        };
        SwingUtilities.invokeLater(r);
    }
}
