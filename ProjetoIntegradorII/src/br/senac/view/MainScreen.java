package br.senac.view;

import br.senac.geral.JmenuBar;
import br.senac.geral.images;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JToolBar;
import objetos.DesktopPane;

/**
 *
 * @author Douglas
 */
public class MainScreen extends JFrame implements KeyListener {

    public static DesktopPane desktopPane = new DesktopPane();
    public static JToolBar jToolBar = new JToolBar();

    public MainScreen() {
        super("CR7 Imports");
        initComponents();
    }

    private void initComponents() {
        setSize(new Dimension(800, 400));
        this.setExtendedState(MAXIMIZED_BOTH);
        this.setLocationRelativeTo(null);
        this.addKeyListener(this);

        desktopPane.setPreferredSize(new Dimension(this.getSize().width, this.getSize().height - 40));
        this.add(desktopPane, BorderLayout.CENTER);

        jToolBar.setPreferredSize(new Dimension(this.getSize().width, 40));
        jToolBar.setFloatable(false);
        this.add(jToolBar, BorderLayout.PAGE_END);

        ImageIcon icone = images.imagemPrincipal();
        this.setIconImage(icone.getImage());
        
        this.setJMenuBar(JmenuBar.getInstance());
    }

    public static void centralizaForm(JInternalFrame frame) {
        Dimension desktopSize = desktopPane.getSize();
        Dimension jInternalFrameSize = frame.getSize();
        frame.setLocation((desktopSize.width - jInternalFrameSize.width) / 2,
                (desktopSize.height - jInternalFrameSize.height) / 2);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            System.exit(0);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
