package br.senac.geral;

import br.senac.view.BackupScreen;
import br.senac.view.LookAndFeelScreen;
import br.senac.view.MainScreen;
import br.senac.view.TelaConexaoBD;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JColorChooser;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import br.senac.view.objetos.PropertiesSystem;

/**
 *
 * @author Douglas
 */
public class JMenuMenu extends JMenu implements ActionListener, InternalFrameListener {

    private static JMenu uniqueInstance;

    public JMenuMenu() {
        super("Menu");
        this.initComponents();
    }

    private void initComponents() {
        this.setIcon(images.imagemMenu());
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
        database.setIcon(images.imagemAdministrator());
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
        database.setIcon(images.imagemDatabase());
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
        JMenuItem notes = new JMenuItem("Bloco de Notas", images.imagemBlocoNotas());
        notes.addActionListener(this);
        notes.setActionCommand("notes");
        return notes;
    }

    private JMenuItem getCalculator() {
        JMenuItem calculator = new JMenuItem("Calculadora", images.imagemCalculadora());
        calculator.addActionListener(this);
        calculator.setActionCommand("calculator");
        return calculator;
    }

    private JMenu getMenuLookAndFeel() {
        JMenu lookAndFeel = new JMenu("Look And Feel");
        lookAndFeel.setIcon(images.imagemLookAndFeel());
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
        JMenuItem exit = new JMenuItem("Sair", images.imagemSair());
        exit.addActionListener(this);
        exit.setActionCommand("exit");
        exit.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.ALT_MASK));
        return exit;
    }

    private JMenuItem getMail() {
        JMenuItem mail = new JMenuItem("E-mail", images.imagemMail());
        mail.addActionListener(this);
        mail.setActionCommand("mail");
        return mail;
    }

    private JMenuItem getSale() {
        JMenuItem sale = new JMenuItem("Venda", images.imagemSale());
        sale.addActionListener(this);
        sale.setActionCommand("sale");
        sale.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.ALT_MASK));
        return sale;
    }

    public static synchronized JMenu getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new JMenuMenu();
        }
        return uniqueInstance;
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
                    Color newColor = JColorChooser.showDialog(MainScreen.desktopPane, "Cor de Fundo",
                            new Color(60, 96, 124));
                    MainScreen.desktopPane.setBackground(newColor);
                    PropertiesSystem ps = new PropertiesSystem();
                    int r = newColor.getRed();
                    int g = newColor.getGreen();
                    int b = newColor.getBlue();
                    String rgb = r + "," + g + "," + b;
                    ps.changeColor(rgb);
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
                    TelaConexaoBD bd = new TelaConexaoBD();
                    MainScreen.desktopPane.add(bd);
                    MainScreen.jToolBar.add(bd.getDesktopIcon());
                    bd.setVisible(true);
                    MainScreen.centralizaForm(bd);
                    bd.addInternalFrameListener(this);
                    break;
                case "mail":
                    break;
                case "logout":
                    break;
                case "sale":
                    break;
                case "user":
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
