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
        this.initComponents();
    }

    private void initComponents() {
        this.add(menu);
        this.add(report);
        this.add(help);
    }
}