package br.senac.geral;

import br.senac.view.AboutScreen;
import br.senac.view.CalendarScreen;
import br.senac.view.MainScreen;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

/**
 *
 * @author Douglas
 */
public class JMenuHelp extends JMenu implements ActionListener, InternalFrameListener {

    private static JMenu uniqueInstance;

    public JMenuHelp() {
        super("Ajuda");
        this.initComponents();
    }

    private void initComponents() {
        this.setIcon(images.imagemHelp());
        this.setMnemonic('A');
        this.add((getCalendar()));
        this.add(getSite());
        this.add(getAbout());
        this.add(getVersion());
    }

    private JMenuItem getCalendar() {
        JMenuItem about = new JMenuItem("Calendario", images.imagemCalendar());
        about.addActionListener(this);
        about.setActionCommand("calendar");
        return about;
    }

    private JMenuItem getAbout() {
        JMenuItem about = new JMenuItem("Sobre", images.imagemHelp());
        about.addActionListener(this);
        about.setActionCommand("about");
        return about;
    }

    private JMenuItem getVersion() {
        JMenuItem version = new JMenuItem("Versão", images.imagemVersion());
        version.addActionListener(this);
        version.setActionCommand("version");
        return version;
    }

    private JMenuItem getSite() {
        JMenuItem site = new JMenuItem("Site", images.imagemSite());
        site.addActionListener(this);
        site.setActionCommand("site");
        return site;
    }

    public static synchronized JMenu getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new JMenuHelp();
        }
        return uniqueInstance;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            switch (e.getActionCommand()) {
                case "version":
                    JOptionPane.showMessageDialog(MainScreen.desktopPane, "Versão 1.0", "Informação", 1);
                    break;
                case "site":
                    Desktop.getDesktop().browse(new URI("www.linkedin.com/in/douglas-proença"));
                    break;
                case "about":
                    AboutScreen about = new AboutScreen("Douglas");
                    MainScreen.desktopPane.add(about);
                    MainScreen.jToolBar.add(about.getDesktopIcon());
                    about.setVisible(true);
                    MainScreen.centralizaForm(about);
                    about.addInternalFrameListener(this);
                    break;
                case "calendar":
                    CalendarScreen calendar = new CalendarScreen();
                    MainScreen.desktopPane.add(calendar);
                    MainScreen.jToolBar.add(calendar.getDesktopIcon());
                    calendar.setVisible(true);
                    MainScreen.centralizaForm(calendar);
                    calendar.addInternalFrameListener(this);
            }
        } catch (URISyntaxException | IOException ex) {
            Logger.getLogger(JMenuHelp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void internalFrameOpened(InternalFrameEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void internalFrameClosing(InternalFrameEvent e) {
        JInternalFrame frame = (JInternalFrame) e.getSource();
        frame.setClosable(true);
    }

    @Override
    public void internalFrameClosed(InternalFrameEvent e) {
        JInternalFrame frame = (JInternalFrame) e.getSource();
        frame.setClosable(true);
    }

    @Override
    public void internalFrameIconified(InternalFrameEvent e) {
        JInternalFrame frame = (JInternalFrame) e.getSource();
        frame.setIconifiable(false);
        MainScreen.jToolBar.add(frame.getDesktopIcon());
    }

    @Override
    public void internalFrameDeiconified(InternalFrameEvent e) {
        JInternalFrame frame = (JInternalFrame) e.getSource();
        MainScreen.jToolBar.remove(frame.getDesktopIcon());
        frame.setIconifiable(true);
        MainScreen.desktopPane.add(frame);
    }

    @Override
    public void internalFrameActivated(InternalFrameEvent e) {
        JInternalFrame frame = (JInternalFrame) e.getSource();
        MainScreen.jToolBar.add(frame.getDesktopIcon());
    }

    @Override
    public void internalFrameDeactivated(InternalFrameEvent e) {
        JInternalFrame frame = (JInternalFrame) e.getSource();
        MainScreen.jToolBar.add(frame.getDesktopIcon());
    }
}
