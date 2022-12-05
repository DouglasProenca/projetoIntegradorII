package br.senac.objects;

import br.senac.view.AboutScreen;
import br.senac.view.CalendarScreen;
import br.senac.view.MainScreen;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

/**
 *
 * @author Douglas
 */
public class MenuHelp extends Menu {

    private JMenuItem calendar;
    private JMenuItem about;
    private JMenuItem version;
    private JMenuItem site;
    
    protected MenuHelp() {
        super("Ajuda",images.getInstance().imagemHelp());
        this.initComponents();
    }

    private void initComponents() {
        this.setMnemonic('A');
        this.add(getCalendar());
        this.add(getSite());
        this.add(getAbout());
        this.add(getVersion());
    }

    private JMenuItem getCalendar() {
        calendar = new JMenuItem("Calendário", images.getInstance().imagemCalendar());
        calendar.addActionListener(this);
        calendar.setActionCommand("calendar");
        return calendar;
    }

    private JMenuItem getAbout() {
        about = new JMenuItem("Sobre", images.getInstance().imagemHelp());
        about.addActionListener(this);
        about.setActionCommand("about");
        return about;
    }

    private JMenuItem getVersion() {
        version = new JMenuItem("Versão", images.getInstance().imagemVersion());
        version.addActionListener(this);
        version.setActionCommand("version");
        return version;
    }

    private JMenuItem getSite() {
        site = new JMenuItem("Site", images.getInstance().imagemSite());
        site.addActionListener(this);
        site.setActionCommand("site");
        return site;
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
                    AboutScreen about = new AboutScreen();
                    about.setVisible(true);
                    break;
                case "calendar":
                    CalendarScreen calendar = new CalendarScreen();
                    calendar.setVisible(true);
            }
        } catch (URISyntaxException | IOException ex) {
            Logger.getLogger(MenuHelp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}