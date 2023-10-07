package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentEvent;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.swing.JRViewer;
import objects.InternalFrame;
import objects.JasperToolBar;
import objects.JasperViewer;

public class ReportScreen extends InternalFrame {

	private static final long serialVersionUID = 1L;
	private JasperPrint parameters;
	private JasperViewer jr;

	public ReportScreen(String titulo, JasperPrint report) throws JRException {
		super(titulo, true, true, true, true, 707, 400);
		this.addComponentListener(this);
		parameters = report;
		this.initComponents(report);
	}

	private void initComponents(JasperPrint report) throws JRException {
		this.getContentPane().add(jasper(report), BorderLayout.CENTER);
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
				return (JasperToolBar) jr.getComponent(i);
			}
		}
		return null;
	}

	private JRViewer jasper(JasperPrint report) throws JRException {
		return getJRViewer(report);
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
				this.getContentPane().add(jasper(parameters), BorderLayout.CENTER);
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
