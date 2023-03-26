package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentEvent;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.swing.JRViewer;
import objects.InternalFrame;
import objects.JasperManager;
import objects.JasperViewer;
import objects.images;

@SuppressWarnings("serial")
public class ReportScreen extends InternalFrame {

	private final JasperManager jasperManager = new JasperManager();
	private Object[] parameters;
	private JButton btnRefresh;
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
		jr.setZoomRatio((float) 0.5);
		jr.getJasperToolbar().add(getBtnRefresh(), 3);
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

	private JButton getBtnRefresh() {
		btnRefresh = new JButton();
		btnRefresh.setIcon(images.getInstance().reload());
		btnRefresh.setPreferredSize(new Dimension(23, 23));
		btnRefresh.addActionListener(this);
		btnRefresh.setActionCommand("reload");
		return btnRefresh;
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
		if (intfr.getWidth() == 707 && intfr.getHeight() == 400) {
			jr.setZoomRatio((float) 0.5);
		} else {
			jr.setZoomRatio((float) 1);
		}
	}
}
