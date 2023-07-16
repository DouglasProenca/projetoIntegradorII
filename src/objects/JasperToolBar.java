 package objects;

import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import net.sf.jasperreports.swing.JRViewerController;
import net.sf.jasperreports.swing.JRViewerToolbar;

@SuppressWarnings("serial")
public class JasperToolBar extends JRViewerToolbar{ 
    
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

