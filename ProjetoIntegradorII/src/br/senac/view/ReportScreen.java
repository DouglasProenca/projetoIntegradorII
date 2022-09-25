package br.senac.view;

import br.senac.objects.InternalFrame;
import br.senac.objects.JasperManager;
import br.senac.objects.JasperViewer;
import java.awt.BorderLayout;
import net.sf.jasperreports.swing.JRViewer;

/**
 *
 * @author Douglas
 */
public class ReportScreen extends InternalFrame {

    private final JasperManager jasperManager = new JasperManager();

    public ReportScreen(String titulo, Object[] params) {
        super(titulo, true, true, true, true, 707, 400);
        this.initComponents(params);
    }

    private void initComponents(Object[] params) {
        jasperManager.start();
        JRViewer jr = null;
        switch (this.getTitle()) {
            case "Relatório Gerencial":
                jr = new JasperViewer(jasperManager.gerarManagetmentReport(params));
                break;
            case "Relatório Sintetico":
                jr = new JasperViewer(jasperManager.gerarSyntheticReport(params));
                break;
            default:
                jr = new JasperViewer(jasperManager.gerarAnalyticalReport(params));
                break;
        }
        jr.setZoomRatio((float) 0.5);
        this.getContentPane().add(jr, BorderLayout.CENTER);
    }
    
}
