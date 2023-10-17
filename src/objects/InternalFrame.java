package objects;

import view.MainScreen;
import static view.MainScreen.desktopPane;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JInternalFrame;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


public abstract class InternalFrame extends JInternalFrame implements ActionListener, MouseListener, KeyListener,
		ListSelectionListener, InternalFrameListener, ItemListener, FocusListener, ComponentListener {

	private static final long serialVersionUID = 1L;

	public InternalFrame(String titulo, boolean resizable, boolean closabe, boolean maximizable, boolean iconifiable,
			int width, int height) {
		super(titulo, resizable, closabe, maximizable, iconifiable);
		super.setFrameIcon(Images.SYSTEM.getImage());
		super.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		super.setSize(new Dimension(width, height));
		super.setMinimumSize(new Dimension(width, height));
		super.addInternalFrameListener(this);
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

	@Override
	public void itemStateChanged(ItemEvent e) {
	}

	@Override
	public void internalFrameClosed(InternalFrameEvent e) {
		InternalFrame frame = (InternalFrame) e.getSource();
		frame.setClosable(true);
		MainScreen.jToolBar.remove(frame.getDesktopIcon());
	}

	@Override
	public void internalFrameIconified(InternalFrameEvent e) {
		InternalFrame frame = (InternalFrame) e.getSource();
		MainScreen.jToolBar.add(frame.getDesktopIcon());
	}

	@Override
	public void internalFrameDeiconified(InternalFrameEvent e) {
		InternalFrame frame = (InternalFrame) e.getSource();
		frame.setIconifiable(true);
		MainScreen.desktopPane.add(frame);
	}

	@Override
	public void internalFrameActivated(InternalFrameEvent e) {
	}

	@Override
	public void internalFrameDeactivated(InternalFrameEvent e) {
	}

	@Override
	public void internalFrameOpened(InternalFrameEvent e) {
		JInternalFrame frame = (JInternalFrame) e.getSource();
		this.centralizaForm(frame);
		MainScreen.desktopPane.add(frame);
		MainScreen.jToolBar.add(frame.getDesktopIcon());
	}

	public void centralizaForm(JInternalFrame frame) {
		Dimension desktopSize = desktopPane.getSize();
		Dimension jInternalFrameSize = frame.getSize();
		frame.setLocation((desktopSize.width - jInternalFrameSize.width) / 2,
				(desktopSize.height - jInternalFrameSize.height) / 2);
	}

	@Override
	public void focusGained(FocusEvent e) {
	}

	@Override
	public void focusLost(FocusEvent e) {
	}

	@Override
	public void internalFrameClosing(InternalFrameEvent e) {
	}
	
	@Override
	public void componentHidden(ComponentEvent e) {
	}

	@Override
	public void componentMoved(ComponentEvent e) {
	}

	@Override
	public void componentResized(ComponentEvent e) {
	}

	@Override
	public void componentShown(ComponentEvent e) {
	}
}
