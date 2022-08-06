package br.senac.view;

import br.senac.geral.JMenuMenu;
import br.senac.view.objetos.GerenciadorConexao;
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
            menu.connection(GerenciadorConexao.getConexao());
        };
        SwingUtilities.invokeLater(r);
    }
}
