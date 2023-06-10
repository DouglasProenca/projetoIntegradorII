package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.swing.JRViewer;
import objects.InternalFrame;
import objects.JasperManager;
import objects.JasperToolBar;
import objects.JasperViewer;

@SuppressWarnings("serial")
public class ReportScreen extends InternalFrame {

	private final JasperManager jasperManager = new JasperManager();
	private Object[] parameters;
	private JasperViewer jr;

	public ReportScreen(String titulo, Object[] params) {
		super(titulo, true, true, true, true, 707, 400);
		this.addComponentListener(this);
		parameters = params;
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

	private JasperViewer getJRViewer(JasperPrint jasperprint) {
		jr = new JasperViewer(jasperprint);
		zoomJasper(jr, this.getWidth(), this.getHeight());
		getJasperToolbar().setBtnRefreshActionListener(this);
		return jr;
	}
	
	public JasperToolBar getJasperToolbar() {
		for (int i = 0; i < jr.getComponentCount(); i++) {
			if (jr.getComponent(i) instanceof JasperToolBar) {
				System.out.println(i);
				return (JasperToolBar) jr.getComponent(i);
			}
		}
		return null;
	}

	private JRViewer jasper(JasperManager jasperManager, Object[] params) throws JRException {
		switch (this.getTitle()) {
		case "Relatório Gerencial":
			return getJRViewer(jasperManager.gerarManagetmentReport());
		case "Relatório Sintetico":
			return getJRViewer(jasperManager.gerarSyntheticReport(params));
		default:
			return getJRViewer(jasperManager.gerarAnalyticalReport(params));
		}
	}

	private void zoomJasper(JasperViewer jasperViwer, int width, int height) {
		if (width == 707 && height == 400) {
			jasperViwer.setZoomRatio((float) 0.5);
		} else {
			jasperViwer.setZoomRatio((float) 1);
		}
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "reload":
			this.getContentPane().removeAll();
			try {
				this.getContentPane().add(jasper(jasperManager, parameters), BorderLayout.CENTER);
			} catch (JRException e1) {
				e1.printStackTrace();
			}
			this.getContentPane().revalidate();
			break;
		}

	}

	@Override
	public void componentResized(ComponentEvent e) {
		InternalFrame intfr = (InternalFrame) e.getSource();
		zoomJasper(jr, intfr.getWidth(), intfr.getHeight());
	}
}
