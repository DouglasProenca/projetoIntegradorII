package objects;

import view.AboutScreen;
import view.CalendarScreen;
import view.MainScreen;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;


@SuppressWarnings("serial")
public class MenuHelp extends Menu {

    private JMenuItem calendar;
    private JMenuItem about;
    private JMenuItem version;
    private JMenuItem site;
    
    protected MenuHelp() {
        super("Ajuda",Images.INFO.getImage());
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
        calendar = new JMenuItem("Calendário", Images.CALENDAR.getImage());
        calendar.addActionListener(this);
        calendar.setActionCommand("calendar");
        return calendar;
    }

    private JMenuItem getAbout() {
        about = new JMenuItem("Sobre", Images.INFO.getImage());
        about.addActionListener(this);
        about.setActionCommand("about");
        return about;
    }

    private JMenuItem getVersion() {
        version = new JMenuItem("Versão", Images.VERSION.getImage());
        version.addActionListener(this);
        version.setActionCommand("version");
        return version;
    }

    private JMenuItem getSite() {
        site = new JMenuItem("Site", Images.SITE.getImage());
        site.addActionListener(this);
        site.setActionCommand("site");
        return site;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            switch (e.getActionCommand()) {
                case "version":
                    JOptionPane.showMessageDialog(MainScreen.getInstance().getDesktopPane(), "Versão 1.0", "Informação", 1);
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
        } catch ( URISyntaxException | IOException ex) {
            Logger.getLogger(MenuHelp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}