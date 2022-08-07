package br.senac.objects;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JInternalFrame;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class InternalFrame extends JInternalFrame implements ActionListener, MouseListener, KeyListener,ListSelectionListener {

    public InternalFrame(String titulo, boolean resizable, boolean closabe, boolean maximizable, boolean iconifiable,
            int width, int height) {
        super(titulo, resizable, closabe, maximizable, iconifiable);
        this.setFrameIcon(images.imagemPrincipal());
        this.setSize(width, height);
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

    @Override
    public void valueChanged(ListSelectionEvent e) {
    }
}
