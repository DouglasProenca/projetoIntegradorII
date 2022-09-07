package br.senac.objects;

import javax.swing.JMenuBar;

/**
 *
 * @author Douglas
 */
public class JmenuBar extends JMenuBar {

    private final JMenuMenu menu = new JMenuMenu();
    private final JMenuReport report = new JMenuReport();
    private final JMenuHelp help = new JMenuHelp();

    public JmenuBar() {
        super();
        this.initComponents();
    }

    private void initComponents() {
        this.add(menu);
        this.add(report);
        this.add(help);
    }
}