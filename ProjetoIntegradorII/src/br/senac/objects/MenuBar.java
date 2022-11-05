package br.senac.objects;

import javax.swing.JMenuBar;

/**
 *
 * @author Douglas
 */
public class MenuBar extends JMenuBar {

    private final MainMenu menu = new MainMenu();
    private final MenuReport report = new MenuReport();
    private final MenuHelp help = new MenuHelp();

    public MenuBar() {
        super();
        this.initComponents();
    }

    private void initComponents() {
        super.add(menu);
        super.add(report);
        super.add(help);
    }
}