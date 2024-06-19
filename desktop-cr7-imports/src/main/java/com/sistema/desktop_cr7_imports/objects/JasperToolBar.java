 package com.sistema.desktop_cr7_imports.objects;

import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import com.sistema.desktop_cr7_imports.enums.Images;

import net.sf.jasperreports.swing.JRViewerController;
import net.sf.jasperreports.swing.JRViewerToolbar;

public class JasperToolBar extends JRViewerToolbar{ 
    
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton btnRefresh;
	
    public JasperToolBar(JRViewerController viewerContext) {
       super(viewerContext);
       super.btnReload.setVisible(false);
       this.add(getBtnRefresh(), 3);
    }
    
	private JButton getBtnRefresh() {
		btnRefresh = new JButton();
		btnRefresh.setIcon(Images.RELOAD.getImage());
		btnRefresh.setPreferredSize(new Dimension(23, 23));
		btnRefresh.setActionCommand("reload");
		return btnRefresh;
	}
	
	public void setBtnRefreshActionListener(ActionListener actionListener) {
		btnRefresh.addActionListener(actionListener);
	}
}

