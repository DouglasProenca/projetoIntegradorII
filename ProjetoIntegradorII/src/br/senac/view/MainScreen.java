package br.senac.view;

import br.senac.objects.JmenuBar;
import br.senac.objects.images;
import br.senac.objects.DesktopPane;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
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
    private final LoginScreen loginScreen = new LoginScreen();

    public MainScreen() {
        super("CR7 Imports");
        initComponents();
    }

    private void initComponents() {
        this.addWindowStateListener(this);
        this.setSize(new Dimension(800, 500));
        this.setExtendedState(MAXIMIZED_BOTH);
        this.setLocationRelativeTo(null);
        this.addKeyListener(this);

        this.add(getDesktopPane(), BorderLayout.CENTER);
        this.add(getJToolBar(), BorderLayout.PAGE_END);

        ImageIcon icone = images.imagemPrincipal();
        this.setIconImage(icone.getImage());

        this.setJMenuBar(JmenuBar.getInstance());
    }

    private JDesktopPane getDesktopPane() {
        desktopPane = new DesktopPane(new Dimension(this.getSize().width, this.getSize().height - 40));
        desktopPane.add(loginScreen);
        loginScreen.setVisible(true);
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
        if (desktopPane.getSelectedFrame() != null) {
            JInternalFrame frame = desktopPane.getSelectedFrame();
            int s1 = e.getNewState();
            if (s1 == MainScreen.MAXIMIZED_BOTH) {
                Dimension jInternalFrameSize = frame.getSize();
                frame.setLocation((1300 - jInternalFrameSize.width) / 2, (600 - jInternalFrameSize.height) / 2);
            } else {
                Dimension jInternalFrameSize = frame.getSize();
                frame.setLocation(400 - jInternalFrameSize.width / 2, (400 - jInternalFrameSize.height) / 2);
            }
        }
    }
}
