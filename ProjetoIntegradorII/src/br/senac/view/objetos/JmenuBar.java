package br.senac.view.objetos;

import br.senac.geral.JMenuHelp;
import br.senac.geral.JMenuMenu;
import br.senac.geral.JMenuReport;
import javax.swing.JMenuBar;

/**
 *
 * @author Douglas
 */
public class JmenuBar extends JMenuBar {

    private static JmenuBar uniqueInstance;

    public JmenuBar() {
        super();
        this.initComponents();
    }

    private void initComponents() {
        this.add(JMenuMenu.getInstance());
        this.add(JMenuReport.getInstance());
        this.add(JMenuHelp.getInstance());
    }

    public static synchronized JmenuBar getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new JmenuBar();
        }
        return uniqueInstance;
    }
}