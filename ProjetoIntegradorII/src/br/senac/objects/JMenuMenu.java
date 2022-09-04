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
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JColorChooser;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.event.InternalFrameEvent;

/**
 *
 * @author Douglas
 */
public class JMenuMenu extends Menu {

    protected JMenuMenu() {
        super("Menu", images.getInstance().imagemMenu());
        this.initComponents();
    }

    private void initComponents() {
        this.setMnemonic('M');
        this.add(getMenuDatabase());
        this.add(getNotes());
        this.add(getCalculator());
        this.add(getMenuLookAndFeel());
        this.add(getMail());
        this.add(getSale());
        this.add(getMenuUser());
        this.add(new JSeparator());
        this.add(getExit());
    }

    private JMenu getMenuUser() {
        JMenu database = new JMenu("Usuário");
        database.setIcon(images.getInstance().imagemAdministrator());
        database.add(getUser());
        database.add(getLogout());
        return database;
    }

    private JMenuItem getLogout() {
        JMenuItem logout = new JMenuItem("Logout");
        logout.addActionListener(this);
        logout.setActionCommand("logout");
        return logout;
    }

    private JMenuItem getUser() {
        JMenuItem user = new JMenuItem("Usuários");
        user.addActionListener(this);
        user.setActionCommand("user");
        return user;
    }

    private JMenu getMenuDatabase() {
        JMenu database = new JMenu("Banco de Dados");
        database.setIcon(images.getInstance().imagemDatabase());
        database.add(getBackup());
        database.add(getJDBC());
        return database;
    }

    private JMenuItem getBackup() {
        JMenuItem backup = new JMenuItem("Backup");
        backup.addActionListener(this);
        backup.setActionCommand("backup");
        return backup;
    }

    private JMenuItem getJDBC() {
        JMenuItem backup = new JMenuItem("JDBC");
        backup.addActionListener(this);
        backup.setActionCommand("jdbc");
        return backup;
    }

    private JMenuItem getNotes() {
        JMenuItem notes = new JMenuItem("Bloco de Notas", images.getInstance().imagemBlocoNotas());
        notes.addActionListener(this);
        notes.setActionCommand("notes");
        return notes;
    }

    private JMenuItem getCalculator() {
        JMenuItem calculator = new JMenuItem("Calculadora", images.getInstance().imagemCalculadora());
        calculator.addActionListener(this);
        calculator.setActionCommand("calculator");
        return calculator;
    }

    private JMenu getMenuLookAndFeel() {
        JMenu lookAndFeel = new JMenu("Look And Feel");
        lookAndFeel.setIcon(images.getInstance().imagemLookAndFeel());
        lookAndFeel.add(getLookAndFeel());
        lookAndFeel.add(getCanvasBackground());
        return lookAndFeel;
    }

    private JMenuItem getLookAndFeel() {
        JMenuItem lookAndFeel = new JMenuItem("Temas");
        lookAndFeel.addActionListener(this);
        lookAndFeel.setActionCommand("lookAndFeel");
        return lookAndFeel;
    }

    private JMenuItem getCanvasBackground() {
        JMenuItem canvas = new JMenuItem("Fundo de Tela");
        canvas.addActionListener(this);
        canvas.setActionCommand("CanvasBackground");
        return canvas;
    }

    private JMenuItem getExit() {
        JMenuItem exit = new JMenuItem("Sair", images.getInstance().imagemSair());
        exit.addActionListener(this);
        exit.setActionCommand("exit");
        exit.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.ALT_MASK));
        return exit;
    }

    private JMenuItem getMail() {
        JMenuItem mail = new JMenuItem("E-mail", images.getInstance().imagemMail());
        mail.addActionListener(this);
        mail.setActionCommand("mail");
        return mail;
    }

    private JMenuItem getSale() {
        JMenuItem sale = new JMenuItem("Venda", images.getInstance().imagemSale());
        sale.addActionListener(this);
        sale.setActionCommand("sale");
        sale.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.ALT_MASK));
        return sale;
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
                    MainScreen.desktopPane.add(laf);
                    MainScreen.jToolBar.add(laf.getDesktopIcon());
                    laf.setVisible(true);
                    MainScreen.centralizaForm(laf);
                    laf.addInternalFrameListener(this);
                    break;
                case "CanvasBackground":
                    Color newColor = JColorChooser.showDialog(null, "Cor de Fundo",
                            new Color(60, 96, 124));
                    if (newColor != null) {
                        MainScreen.desktopPane.setBackground(newColor);
                        PropertiesSystem ps = new PropertiesSystem();
                        int r = newColor.getRed();
                        int g = newColor.getGreen();
                        int b = newColor.getBlue();
                        String rgb = r + "," + g + "," + b;
                        ps.changeColor(rgb);
                    }
                    break;
                case "backup":
                    BackupScreen backup = new BackupScreen();
                    MainScreen.desktopPane.add(backup);
                    MainScreen.jToolBar.add(backup.getDesktopIcon());
                    backup.setVisible(true);
                    MainScreen.centralizaForm(backup);
                    backup.addInternalFrameListener(this);
                    break;
                case "jdbc":
                    DatabaseConnectionScreen bd = new DatabaseConnectionScreen();
                    MainScreen.desktopPane.add(bd);
                    MainScreen.jToolBar.add(bd.getDesktopIcon());
                    MainScreen.centralizaForm(bd);
                    bd.setVisible(true);
                    bd.addInternalFrameListener(this);
                    break;
                case "mail":
                    MailScreen mail = new MailScreen();
                    MainScreen.desktopPane.add(mail);
                    MainScreen.jToolBar.add(mail.getDesktopIcon());
                    MainScreen.centralizaForm(mail);
                    mail.setVisible(true);
                    mail.addInternalFrameListener(this);
                    break;
                case "logout":
                    LoginScreen login = new LoginScreen();
                    MainScreen.removeForms();
                    User.getInstance().setEmpty();
                    MainScreen.desktopPane.add(login);
                    MainScreen.centralizaForm(login);
                    login.setVisible(true);
                    break;
                case "sale":
                    SaleScreen sl = new SaleScreen();
                    MainScreen.desktopPane.add(sl);
                    MainScreen.jToolBar.add(sl.getDesktopIcon());
                    MainScreen.centralizaForm(sl);
                    sl.setVisible(true);
                    sl.addInternalFrameListener(this);
                    break;
                case "user":
                    ReportUserScreen userScreen = new ReportUserScreen();
                    MainScreen.desktopPane.add(userScreen);
                    MainScreen.jToolBar.add(userScreen.getDesktopIcon());
                    MainScreen.centralizaForm(userScreen);
                    userScreen.setVisible(true);
                    userScreen.addInternalFrameListener(this);
                    break;
            }
        } catch (IOException ex) {
            Logger.getLogger(JMenuMenu.class.getName()).log(Level.SEVERE, null, ex);
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
