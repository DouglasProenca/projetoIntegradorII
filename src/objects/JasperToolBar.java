 package objects;

import net.sf.jasperreports.swing.JRViewerController;
import net.sf.jasperreports.swing.JRViewerToolbar;

@SuppressWarnings("serial")
public class JasperToolBar extends JRViewerToolbar{
    
    public JasperToolBar(JRViewerController viewerContext) {
        super(viewerContext);
       btnReload.setVisible(false);
    }
}
