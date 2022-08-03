package br.senac.view.objetos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;

public class InternalFrame extends JInternalFrame implements ActionListener, MouseListener, KeyListener {

    public InternalFrame(String titulo, boolean resizable, boolean closabe, boolean maximizable, boolean iconifiable,
            int width, int height) {
        super(titulo, resizable, closabe, maximizable, iconifiable);
        setFrameIcon(new ImageIcon(this.getClass().getResource("/Resources/System-computer-icon.png")));
        setSize(width, height);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
