package br.senac.view;

import br.senac.objects.InternalFrame;
import br.senac.objects.JasperManager;
import java.awt.BorderLayout;
import net.sf.jasperreports.swing.JRViewer;

/**
 *
 * @author Douglas
 */
public class ReportScreen extends InternalFrame {

    private static InternalFrame uniqueInstance;
    private final JasperManager jasperManager = new JasperManager();

    public ReportScreen(String titulo, Object[] params) {
        super(titulo, true, true, true, true, 707, 400);
        this.initComponents(params);
    }

    private void initComponents(Object[] params) {
        JRViewer jr = new JRViewer(jasperManager.gerarManagetmentReport(params));
        jr.setZoomRatio((float) 0.5);
        this.getContentPane().add(jr, BorderLayout.CENTER);
    }

    public static synchronized InternalFrame getInstance(String titulo, Object[] params) {
        if (uniqueInstance == null) {
            uniqueInstance = new ReportScreen(titulo, params);
        }
        return uniqueInstance;
    }
}
