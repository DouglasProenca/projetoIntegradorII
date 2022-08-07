package br.senac.objects;


import br.senac.view.MainScreen;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JPopupMenu;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

/**
 *
 * @author Douglas
 */
public class MenuPopup extends JPopupMenu implements ActionListener, InternalFrameListener {

        private static MenuPopup uniqueInstance;
    
	public MenuPopup() {
		super();
		initComponents();
	}


	private void initComponents() {
            //this.add(JMenuMenu.getInstance());
	}

        public static synchronized JPopupMenu getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new MenuPopup();
        }
        return uniqueInstance;
    }
        
	@Override
	public void actionPerformed(ActionEvent e) {
	
	
	}

	@Override
	public void internalFrameOpened(InternalFrameEvent e) {

	}

	@Override
	public void internalFrameClosing(InternalFrameEvent e) {

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
