package br.senac.view;

import br.senac.objects.JMenuMenu;
import br.senac.objects.ConnectionManager;
import javax.swing.SwingUtilities;

/**
 *
 * @author Douglas
 */
public abstract class Sistema {

    public static void main(String[] args) {
        Runnable r = () -> {
            LookAndFeelScreen.initLookAndFeel();
            MainScreen t = new MainScreen();
            t.setVisible(true);
            JMenuMenu menu = JMenuMenu.getInstance();
            ConnectionManager connectionManager1 = ConnectionManager.getInstance();
            menu.connection(connectionManager1.getConexao());
        };
        SwingUtilities.invokeLater(r);
    }
}
