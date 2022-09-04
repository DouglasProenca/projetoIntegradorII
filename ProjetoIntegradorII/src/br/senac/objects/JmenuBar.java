package br.senac.objects;

import javax.swing.JMenuBar;

/**
 *
 * @author Douglas
 */
public class JmenuBar extends JMenuBar {

    private static JmenuBar uniqueInstance;
    private final JMenuMenu menu = new JMenuMenu();
    private final JMenuReport report = new JMenuReport();
    private final JMenuHelp help = new JMenuHelp();

    private JmenuBar() {
        super();
        this.initComponents();
    }

    private void initComponents() {
        this.add(menu);
        this.add(report);
        this.add(help);
    }

    public static synchronized JmenuBar getInstance() {
        if (uniqueInstance == null) uniqueInstance = new JmenuBar();
        return uniqueInstance;
    }
}