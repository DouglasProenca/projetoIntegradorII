package view;

import objects.InternalFrame;
import objects.JasperManager;
import objects.JasperViewer;
import java.awt.BorderLayout;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.swing.JRViewer;

@SuppressWarnings("serial")
public class ReportScreen extends InternalFrame {

    private final JasperManager jasperManager = new JasperManager();

    public ReportScreen(String titulo, Object[] params) {
        super(titulo, true, true, true, true, 707, 400);

        try {
            this.initComponents(params);
        } catch (JRException ex) {
            Logger.getLogger(ReportScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void initComponents(Object[] params) throws JRException {
        jasperManager.start();
        this.getContentPane().add(jasper(jasperManager, params), BorderLayout.CENTER);
    }

    private JRViewer getJRViewer(JasperPrint jasperprint) {
        JRViewer jr = new JasperViewer(jasperprint);
        jr.setZoomRatio((float) 0.5);
        return jr;
    }

    private JRViewer jasper(JasperManager jasperManager, Object[] params) throws JRException {
        switch (this.getTitle()) {
            case "Relatório Gerencial":
                 return getJRViewer(jasperManager.gerarManagetmentReport(params));
            case "Relatório Sintetico":
                return getJRViewer(jasperManager.gerarSyntheticReport(params));
            default:
                return getJRViewer(jasperManager.gerarAnalyticalReport(params));
        }
    }
}
