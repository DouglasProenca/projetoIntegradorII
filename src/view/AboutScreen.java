package view;

import java.awt.BorderLayout;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.User;
import objects.InternalFrame;
import objects.TableModel;

@SuppressWarnings("serial")
public class AboutScreen extends InternalFrame {

	protected JTable tblResultado;
	private LinkedHashMap<String, Object> infos;

	public AboutScreen() {
		super("Informações", false, true, true, true, 549, 260);
		try {
			initComponents();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
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

	private LinkedHashMap<String, Object> getInfos() throws UnknownHostException {
		infos = new LinkedHashMap<String, Object>();
		infos.put("Usuario Aplicação", User.getInstance().getUser());
		infos.put("IP PC", InetAddress.getLocalHost());
		infos.put("Sistema Operacional", System.getProperty("os.name"));
		infos.put("Versão Java", System.getProperty("java.version"));
		infos.put("Usuario PC", System.getProperty("user.name"));
		infos.put("Versão JRE", System.getProperty("java.runtime.version"));
		infos.put("Arq. Sistema Operacional", System.getProperty("os.arch"));
		infos.put("Vendor Java", System.getProperty("java.vm.specification.vendor"));
		infos.put("Pais Uso Aplicação", System.getProperty("user.country"));
		infos.put("Diretorio Aplicação", System.getProperty("user.dir"));
		infos.put("Diretorio Java", System.getProperty("java.home"));
		return infos;
	}

	private void loadTable() throws UnknownHostException {
		DefaultTableModel modelo = (DefaultTableModel) tblResultado.getModel();
		modelo.setRowCount(0);

		for (Map.Entry<String, Object> entry : getInfos().entrySet()) {
			modelo.addRow(new Object[] { entry.getKey(), entry.getValue() });
		};
	}

}
