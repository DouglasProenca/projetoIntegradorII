package view;

import controller.BrandDAO;
import objects.BrandRingChart;
import objects.Excel;
import enums.Images;
import model.Brand;
import objects.InternalFrame;
import objects.Table;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;

public class BrandReportScreen extends InternalFrame {

	private static final long serialVersionUID = 1L;
	private final JLabel lblNome = new JLabel("Nome:");
	private JPanel panelNorth;
	private JPanel panelWest;
	private JTextField txtPesquisa;
	private JButton btnPesquisa;
	private JButton btnExcluir;
	private JButton btnExportar;
	private JButton btnIncluir;
	private JButton btnSwitch;
	protected Table tblResultado;
	private JScrollPane scroll;
	private final Excel excel = new Excel();
	private final BrandDAO dao = BrandDAO.getInstance();
	private final SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MMM/yyyy");
	protected JPanel cardPanel;
	protected CardLayout cardLayout = new CardLayout();

	public BrandReportScreen() {
		super("Relatório Marcas", true, true, true, true, 707, 400);
		initComponents();
	}

	private void initComponents() {
		this.add(BorderLayout.CENTER, getCardPanel());
		this.add(BorderLayout.NORTH, getPanelNorth());
		this.add(BorderLayout.EAST, getPanelWest());
		this.loadTable();
	}

	public Table getTblResultado() {
		tblResultado = new Table(new String[] { "ID", "Marca", "Pais", "Data", "Usuário" }, 0);
		tblResultado.getSelectionModel().addListSelectionListener(this);
		tblResultado.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblResultado.addMouseListener(this);
		return tblResultado;
	}

	public JPanel getCardPanel() {
		cardPanel = new JPanel(cardLayout);
		cardPanel.add(getScrollPane(), "Tabela");
		cardPanel.add(new BrandRingChart(), "Gráfico");
		return cardPanel;
	}

	protected JScrollPane getScrollPane() {
		scroll = new JScrollPane(getTblResultado());
		return scroll;
	}

	protected JButton getBtnPesquisa() {
		if (btnPesquisa == null) {
			btnPesquisa = new JButton("Pesquisar");
			btnPesquisa.addActionListener(this);
			btnPesquisa.setActionCommand("find");
			btnPesquisa.setPreferredSize(new Dimension(100, 30));
		}
		return btnPesquisa;
	}

	protected JButton getBtnSwitch() {
		if (btnSwitch == null) {
			btnSwitch = new JButton(Images.CHART.getImage());
			btnSwitch.addActionListener(this);
			btnSwitch.setActionCommand("switch");
			btnSwitch.setPreferredSize(new Dimension(60, 30));
		}
		return btnSwitch;

	}

	protected JTextField getTxtPesquisa() {
		if (txtPesquisa == null) {
			txtPesquisa = new JTextField();
			txtPesquisa.setPreferredSize(new Dimension(200, 30));
			txtPesquisa.addKeyListener(this);
			txtPesquisa.setToolTipText("Procure por marca ou digite Refresh, R e tecle enter para atualizar a tabela.");
		}
		return txtPesquisa;
	}

	protected JButton getbtnExportar() {
		if (btnExportar == null) {
			btnExportar = new JButton("Exportar");
			btnExportar.addActionListener(this);
			btnExportar.setActionCommand("Exportar");
			btnExportar.setPreferredSize(new Dimension(100, 30));
			btnExportar.setAlignmentX(Component.CENTER_ALIGNMENT);
		}
		return btnExportar;
	}

	private JButton getbtnExcluir() {
		btnExcluir = new JButton("Excluir");
		btnExcluir.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnExcluir.setPreferredSize(new Dimension(100, 30));
		btnExcluir.addActionListener(this);
		btnExcluir.setActionCommand("excluir");
		btnExcluir.setEnabled(false);
		return btnExcluir;
	}

	protected JButton getBtnIncluir() {
		if (btnIncluir == null) {
			btnIncluir = new JButton("Incluir");
			btnIncluir.setPreferredSize(new Dimension(100, 30));
			btnIncluir.addActionListener(this);
			btnIncluir.setActionCommand("Incluir");
			btnIncluir.setAlignmentX(Component.CENTER_ALIGNMENT);
		}
		return btnIncluir;
	}

	private JPanel getPanelWest() {
		panelWest = new JPanel(new FlowLayout(10, 10, 10));
		panelWest.setLayout(new BoxLayout(panelWest, BoxLayout.Y_AXIS));
		panelWest.add(getbtnExcluir());
		panelWest.add(Box.createVerticalStrut(20));
		panelWest.add(getbtnExportar());
		panelWest.add(Box.createVerticalStrut(20));
		panelWest.add(getBtnIncluir());
		return panelWest;
	}

	private JPanel getPanelNorth() {
		panelNorth = new JPanel(new FlowLayout(10, 10, 10));
		panelNorth.add(lblNome);
		panelNorth.add(getTxtPesquisa());
		panelNorth.add(getBtnPesquisa());
		panelNorth.add(getBtnSwitch());
		return panelNorth;
	}

	public void loadTable() {
		tblResultado.getModel().setRowCount(0);
		BrandDAO.getInstance().getAll().forEach((p) -> {
			tblResultado.getModel().addRow(new Object[] { p.getBrand_id(), p.getBrand_name(), p.getCountry_nome(),
					sdf1.format(p.getDate()), p.getUser() });
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "excluir":
			int lineNumber = tblResultado.getSelectedRow();
			int id = Integer.parseInt(tblResultado.getValueAt(lineNumber, 0).toString());
			if (BrandDAO.getInstance().delete(id)) {
				JOptionPane.showMessageDialog(this, "Marca excluída com sucesso!");
			}
			this.loadTable();
			break;
		case "Incluir":
			RegistrationBrandScreen rbs = new RegistrationBrandScreen(this);
			getParent().add(rbs);
			rbs.setVisible(true);
			break;
		case "Exportar":
			JFileChooser fc = new JFileChooser();
			if (fc.showSaveDialog(this) == JFileChooser.FILES_ONLY) {
				excel.exportExcel(fc.getSelectedFile(), dao.getAll(), "Marcas", tblResultado);
			}
			break;
		case "find":
			tblResultado.getModel().setRowCount(0);
			BrandDAO.getInstance().getBy(txtPesquisa.getText()).forEach((p) -> {
				tblResultado.getModel().addRow(new Object[] { p.getBrand_id(), p.getBrand_name(), p.getCountry_nome(),
						sdf1.format(p.getDate()), p.getUser() });
			});
			break;
		case "switch":

			cardLayout.show(cardPanel, "Gráfico");
			btnSwitch.setActionCommand("switchTable");
			txtPesquisa.setEnabled(false);
			;
			btnPesquisa.setEnabled(false);
			btnExportar.setEnabled(false);
			btnIncluir.setEnabled(false);

			break;
		case "switchTable":

			cardLayout.show(cardPanel, "Tabela");
			btnSwitch.setActionCommand("switch");
			txtPesquisa.setEnabled(true);
			;
			btnPesquisa.setEnabled(true);
			btnExportar.setEnabled(true);
			btnIncluir.setEnabled(true);

			break;
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 2) {
			int lineNumber = tblResultado.getSelectedRow();

			int id = Integer.parseInt(tblResultado.getValueAt(lineNumber, 0).toString());
			String brand_name = tblResultado.getValueAt(lineNumber, 1).toString();
			String pais = tblResultado.getValueAt(lineNumber, 2).toString();
			String user = tblResultado.getValueAt(lineNumber, 4).toString();

			Brand brand = new Brand(id, brand_name, pais, new Date(), user);

			RegistrationBrandScreen rbs = new RegistrationBrandScreen(brand, this);
			this.getParent().add(rbs);
			rbs.setVisible(true);
		}
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (!e.getValueIsAdjusting()) {
			btnExcluir.setEnabled(tblResultado.getSelectedRowCount() > 0);
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == Event.ENTER) {
			if (txtPesquisa.getText().toLowerCase().equals("refresh")
					|| txtPesquisa.getText().toLowerCase().equals("r")) {
				loadTable();
			} else {
				this.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "find"));
			}
		}
	}
}