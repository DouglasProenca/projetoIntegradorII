package br.senac.view;

import br.senac.objects.ConnectionManager;
import br.senac.objects.MenuBar;
import br.senac.objects.DesktopPane;
import br.senac.objects.InternalFrame;
import br.senac.objects.images;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JToolBar;

/**
 *
 * @author Douglas
 */
public class MainScreen extends JFrame implements KeyListener, WindowStateListener {

    public static DesktopPane desktopPane;
    public static JToolBar jToolBar;
    protected static MenuBar menubar = new MenuBar();
    private final LoginScreen loginScreen = new LoginScreen();
    private final DatabaseConnectionScreen bd = new DatabaseConnectionScreen(false, false);
    private ImageIcon icone;

    public MainScreen() {
        super("CR7 Imports");
        LookAndFeelScreen.initLookAndFeel();
        initComponents();
    }

    private void initComponents() {
        this.addWindowStateListener(this);
        this.setSize(new Dimension(800, 500));
        this.setExtendedState(MAXIMIZED_BOTH);
        this.setLocationRelativeTo(null);
        this.addKeyListener(this);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.add(getDesktopPane(), BorderLayout.CENTER);
        this.add(getJToolBar(), BorderLayout.PAGE_END);
        this.setIconImage(getIcone().getImage());
        this.setJMenuBar(menubar);
        this.getFirst();
    }
    
    private ImageIcon getIcone() {
        icone = images.getInstance().imagemPrincipal();
        return icone;
    }

    private void getFirst() {
        Thread t = new Thread(() -> {
            try {
                Thread.sleep(800);
            } catch (InterruptedException ex) {
                Logger.getLogger(MainScreen.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (ConnectionManager.getInstance().getConexao() == null) {
                desktopPane.add(bd);
                centralizaForm(bd);
                bd.setVisible(true);
            } else {
                desktopPane.add(loginScreen);
                centralizaForm(loginScreen);
                loginScreen.setVisible(true);
            }
        });
        t.start();
    }

    private JDesktopPane getDesktopPane() {
        desktopPane = new DesktopPane(new Dimension(this.getSize().width, this.getSize().height - 40));
        return desktopPane;
    }

    private JToolBar getJToolBar() {
        jToolBar = new JToolBar();
        jToolBar.setPreferredSize(new Dimension(this.getSize().width, 40));
        jToolBar.setFloatable(false);
        return jToolBar;
    }

    public static void centralizaForm(JInternalFrame frame) {
        Dimension desktopSize = desktopPane.getSize();
        Dimension jInternalFrameSize = frame.getSize();
        frame.setLocation((desktopSize.width - jInternalFrameSize.width) / 2,
                (desktopSize.height - jInternalFrameSize.height) / 2);
    }

    public static void removeForms() {
        int qtd = desktopPane.getComponentCount();
        for (int i = 0; i < qtd; i++) {
            if (desktopPane.getComponent(i) instanceof InternalFrame) {
                InternalFrame frame = (InternalFrame) desktopPane.getComponent(i);
                frame.dispose();
                i = -1;
                qtd = desktopPane.getComponentCount();
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            System.exit(0);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void windowStateChanged(WindowEvent e) {
        int s1 = e.getNewState();
        int qtd = desktopPane.getComponentCount();
        for (int i = 0; i < qtd; i++) {
            if (desktopPane.getComponent(i) instanceof InternalFrame) {
                InternalFrame frame = (InternalFrame) desktopPane.getComponent(i);
                Dimension jInternalFrameSize = frame.getSize();
                if (s1 == MainScreen.MAXIMIZED_BOTH) {
                    frame.setLocation((1300 - jInternalFrameSize.width) / 2, (600 - jInternalFrameSize.height) / 2);
                } else {
                    frame.setLocation(400 - jInternalFrameSize.width / 2, (400 - jInternalFrameSize.height) / 2);
                }
            }
        }
    }
}
