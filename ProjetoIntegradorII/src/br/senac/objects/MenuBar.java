package br.senac.objects;

import javax.swing.JMenuBar;

/**
 *
 * @author Douglas
 */
public class MenuBar extends JMenuBar {

    private final JMenuMenu menu = new JMenuMenu();
    private final MenuReport report = new MenuReport();
    private final JMenuHelp help = new JMenuHelp();

    public MenuBar() {
        super();
        this.initComponents();
    }

    private void initComponents() {
        this.add(menu);
        this.add(report);
        this.add(help);
    }
}