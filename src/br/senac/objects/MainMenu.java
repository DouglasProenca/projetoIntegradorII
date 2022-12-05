package br.senac.objects;

import br.senac.model.User;
import br.senac.view.BackupScreen;
import br.senac.view.LookAndFeelScreen;
import br.senac.view.MainScreen;
import br.senac.view.DatabaseConnectionScreen;
import br.senac.view.LoginScreen;
import br.senac.view.MailScreen;
import br.senac.view.ReportUserScreen;
import br.senac.view.SaleScreen;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JColorChooser;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.KeyStroke;

/**
 *
 * @author Douglas
 */
public class MainMenu extends Menu {

    private JMenu menuUser;
    private JMenuItem user;
    private JMenuItem logout;
    private JMenuItem backup;
    private JMenuItem notes;
    private JMenuItem exit;
    private JMenuItem mail;
    private JMenuItem sale;
    private JMenuItem calculator;
    private JMenuItem jdbc;
    private JMenu MenuDatabase;
    private JMenu menuLookAndFeel;
    private JMenuItem lookAndFeel;
    private JMenuItem canvas; 
    
    
    protected MainMenu() {
        super("Menu", images.getInstance().imagemMenu());
        this.initComponents();
    }

    private void initComponents() {
        super.setMnemonic('M');
        super.add(getMenuDatabase());
        super.add(getNotes());
        super.add(getCalculator());
        super.add(getMenuLookAndFeel());
        super.add(getMail());
        super.add(getSale());
        super.add(getMenuUser());
        super.add(new JSeparator());
        super.add(getExit());
    }

    private JMenu getMenuUser() {
        menuUser = new JMenu("Usuário");
        menuUser.setIcon(images.getInstance().imagemAdministrator());
        menuUser.add(getUser());
        menuUser.add(getLogout());
        return menuUser;
    }

    private JMenuItem getLogout() {
        logout = new JMenuItem("Logout");
        logout.addActionListener(this);
        logout.setActionCommand("logout");
        return logout;
    }

    private JMenuItem getUser() {
        user = new JMenuItem("Usuários");
        user.addActionListener(this);
        user.setActionCommand("user");
        return user;
    }

    private JMenu getMenuDatabase() {
        MenuDatabase = new JMenu("Banco de Dados");
        MenuDatabase.setIcon(images.getInstance().imagemDatabase());
        MenuDatabase.add(getBackup());
        MenuDatabase.add(getJDBC());
        return MenuDatabase;
    }

    private JMenuItem getBackup() {
        backup = new JMenuItem("Backup");
        backup.addActionListener(this);
        backup.setActionCommand("backup");
        return backup;
    }

    private JMenuItem getJDBC() {
        jdbc = new JMenuItem("JDBC");
        jdbc.addActionListener(this);
        jdbc.setActionCommand("jdbc");
        return jdbc;
    }

    private JMenuItem getNotes() {
        notes = new JMenuItem("Bloco de Notas", images.getInstance().imagemBlocoNotas());
        notes.addActionListener(this);
        notes.setActionCommand("notes");
        return notes;
    }

    private JMenuItem getCalculator() {
        calculator = new JMenuItem("Calculadora", images.getInstance().imagemCalculadora());
        calculator.addActionListener(this);
        calculator.setActionCommand("calculator");
        return calculator;
    }

    private JMenu getMenuLookAndFeel() {
        menuLookAndFeel = new JMenu("Look And Feel");
        menuLookAndFeel.setIcon(images.getInstance().imagemLookAndFeel());
        menuLookAndFeel.add(getLookAndFeel());
        menuLookAndFeel.add(getCanvasBackground());
        return menuLookAndFeel;
    }

    private JMenuItem getLookAndFeel() {
        lookAndFeel = new JMenuItem("Temas");
        lookAndFeel.addActionListener(this);
        lookAndFeel.setActionCommand("lookAndFeel");
        return lookAndFeel;
    }

    private JMenuItem getCanvasBackground() {
        canvas = new JMenuItem("Fundo de Tela");
        canvas.addActionListener(this);
        canvas.setActionCommand("CanvasBackground");
        return canvas;
    }

    private JMenuItem getExit() {
        exit = new JMenuItem("Sair", images.getInstance().imagemSair());
        exit.addActionListener(this);
        exit.setActionCommand("exit");
        exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.ALT_MASK));
        return exit;
    }

    private JMenuItem getMail() {
        mail = new JMenuItem("E-mail", images.getInstance().imagemMail());
        mail.addActionListener(this);
        mail.setActionCommand("mail");
        return mail;
    }

    private JMenuItem getSale() {
        sale = new JMenuItem("Venda", images.getInstance().imagemSale());
        sale.addActionListener(this);
        sale.setActionCommand("sale");
        sale.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.ALT_MASK));
        return sale;
    }

    private String ColorToString(Color color) {
        int r = color.getRed();
        int g = color.getGreen();
        int b = color.getBlue();
        return r + "," + g + "," + b;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            switch (e.getActionCommand()) {
                case "calculator":
                    Runtime.getRuntime().exec("C:\\windows\\System32\\calc.exe");
                    break;
                case "exit":
                    System.exit(0);
                case "notes":
                    Runtime.getRuntime().exec("C:\\windows\\System32\\notepad.exe");
                    break;
                case "lookAndFeel":
                    LookAndFeelScreen laf = new LookAndFeelScreen();
                    laf.setVisible(true);
                    break;
                case "CanvasBackground":
                    Color newColor = JColorChooser.showDialog(null, "Cor de Fundo",
                            new Color(60, 96, 124));
                    if (newColor != null) {
                        MainScreen.desktopPane.setBackground(newColor);
                        PropertiesSystem ps = new PropertiesSystem();
                        ps.setColor(ColorToString(newColor));
                    }
                    break;
                case "backup":
                    BackupScreen backup = new BackupScreen();
                    backup.setVisible(true);
                    break;
                case "jdbc":
                    DatabaseConnectionScreen bd = new DatabaseConnectionScreen();
                    bd.setVisible(true);
                    break;
                case "mail":
                    MailScreen mail = new MailScreen();
                    mail.setVisible(true);
                    break;
                case "logout":
                    LoginScreen login = new LoginScreen();
                    MainScreen.removeForms();
                    User.getInstance().setEmpty();
                    login.setVisible(true);
                    break;
                case "sale":
                    SaleScreen sl = new SaleScreen();
                    sl.setVisible(true);
                    break;
                case "user":
                    ReportUserScreen userScreen = new ReportUserScreen();
                    userScreen.setVisible(true);
                    break;
            }
        } catch (IOException ex) {
            Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
