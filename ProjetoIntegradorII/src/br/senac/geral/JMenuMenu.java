package br.senac.geral;

import br.senac.view.LookAndFeelScreen;
import br.senac.view.MainScreen;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

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
        this.add(getNotes());
        this.add(getCalculator());
        this.add(getMenuLookAndFeel());
        this.add(new JSeparator());
        this.add(getExit());
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

    private JMenu getMenuLookAndFeel(){
        JMenu lookAndFeel = new JMenu("Look And Feel");
        lookAndFeel.setIcon(images.imagemLookAndFeel());
        lookAndFeel.add(getLookAndFeel());
        return lookAndFeel;
    }
    
    private JMenuItem getLookAndFeel() {
        JMenuItem lookAndFeel = new JMenuItem("Temas");
        lookAndFeel.addActionListener(this);
        lookAndFeel.setActionCommand("lookAndFeel");
        return lookAndFeel;
    }

    private JMenuItem getExit() {
        JMenuItem exit = new JMenuItem("Sair", images.imagemSair());
        exit.addActionListener(this);
        exit.setActionCommand("exit");
        exit.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, 0));
        return exit;
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
