package com.sistema.desktop_cr7_imports.controller;

import java.awt.BorderLayout;
import java.net.UnknownHostException;
import java.util.Map;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.sistema.desktop_cr7_imports.objects.InternalFrame;
import com.sistema.desktop_cr7_imports.objects.TableModel;
import com.sistema.desktop_cr7_imports.service.AboutScreenService;



public class AboutScreen extends InternalFrame {

	private static final long serialVersionUID = 1L;
	private JTable tblResultado;
	private final AboutScreenService aboutScreenService = new AboutScreenService();

	public AboutScreen() throws UnknownHostException {
		super("Informações", false, true, true, true, 549, 260);
		initComponents();
	}

	private void initComponents() throws UnknownHostException {
		this.add(BorderLayout.CENTER, getTblResultado());
		this.loadTable();
	}

	public JTable getTblResultado() {
		tblResultado = new JTable(new TableModel(new String[] { "Descrição", "Informação" }, 0));
		tblResultado.setEnabled(false);
		return tblResultado;
	}

	private void loadTable() throws UnknownHostException {
		DefaultTableModel modelo = (DefaultTableModel) tblResultado.getModel();
		modelo.setRowCount(0);

		for (Map.Entry<String, Object> entry : aboutScreenService.getInfos().entrySet()) {
			modelo.addRow(new Object[] { entry.getKey(), entry.getValue() });
		};
	}
}
