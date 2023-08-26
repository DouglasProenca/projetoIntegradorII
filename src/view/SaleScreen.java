package view;

import controller.ClientDAO;
import controller.ProductDAO;
import controller.SaleDAO;
import model.Sale;
import model.User;
import objects.InternalFrame;
import objects.SpinnerEditor;
import objects.SpinnerNumberInt;
import objects.Table;
import objects.TextField;

import com.toedter.calendar.JDateChooser;
import java.awt.Component;
import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.text.MaskFormatter;

@SuppressWarnings("serial")
public class SaleScreen extends InternalFrame {

	// 1º Painel
	private JPanel panelOne;
	private JLabel lblPanelOneTitle;
	private JButton btnPanelOneAdd;
	private JLabel lblPanelOneName;
	private TextField txtPanelOneName;
	private JLabel lblPanelOneCPF;
	private JFormattedTextField txtPanelOneCPF;
	private JButton btnPanelOneFind;
	// 2º Painel
	private JPanel panelTwo;
	private JScrollPane scrollPanelTwoClientTable;
	private Table tblPanelTwo;
	// 3º Painel
	private JPanel panelThree;
	private JLabel lblClientPanelThree;
	private JLabel lblCPFPanelThree;
	private TextField txtClientPanelThree;
	private TextField txtCPFPanelThree;
	private JPanel FilterPanelThree;
	private TextField txtFilterPanelThree;
	private JButton btnFilterPanelThreeFind;
	private JPanel paneltblPanelThree;
	private JScrollPane scrollPanelThreeTbl;
	private Table tblPanelThree;
	// 4º Painel
	private JPanel panelFour;
	private JDateChooser chooserDatePanelFour;
	private JLabel lblChooserDatePanelFour;
	private JLabel lblTotalPanelFour;
	private TextField txtTotalPanelFour;
	private JScrollPane scrollTblPanelFour;
	private Table tblPanelFour;
	// Internal Frame
	private JButton btnConcluir;
	private JButton btnExcluir;
	private final String tblProducts[] = { "ID", "Nome", "Preço", "quantidade" };
	private int id_cliente;
	private final SpinnerNumberInt spinner = new SpinnerNumberInt();

	public SaleScreen() throws ParseException {
		super("Nova Venda", false, true, false, true, 800, 600);
		this.initComponents();
	}

	private void initComponents() throws ParseException {
		this.setLayout(null);
		this.add(getPanelOne());
		this.add(getPanelTwo());
		this.add(getPanelThree());
		this.add(getPanelFour());
		this.add(getBtnConcluir());
		this.add(getBtnExcluir());
		this.loadTable("All");
	}

	private JPanel getPanelOne() throws ParseException {
		panelOne = new JPanel(null);
		panelOne.setBorder(new LineBorder(MainScreen.desktopPane.getBackground()));
		panelOne.setBounds(10, 10, 310, 150);
		panelOne.add(getLblPanelOneTitle());
		panelOne.add(getLblPanelOneName());
		panelOne.add(getTxtPanelOneName());
		panelOne.add(lblPanelOneCPF());
		panelOne.add(getTxtPanelOneCPF());
		panelOne.add(getBtnPanelOneFind());
		panelOne.add(getBtnPanelOneAdd());
		return panelOne;
	}

	private JLabel getLblPanelOneTitle() {
		lblPanelOneTitle = new JLabel("Filtrar Clientes");
		lblPanelOneTitle.setBounds(10, 10, 150, 20);
		return lblPanelOneTitle;
	}

	private JLabel getLblPanelOneName() {
		lblPanelOneName = new JLabel("Nome:");
		lblPanelOneName.setBounds(10, 40, 40, 20);
		return lblPanelOneName;
	}

	private TextField getTxtPanelOneName() {
		txtPanelOneName = new TextField("Letter");
		txtPanelOneName.setBounds(50, 40, 250, 20);
		txtPanelOneName.addKeyListener(this);
		return txtPanelOneName;
	}

	private JLabel lblPanelOneCPF() {
		lblPanelOneCPF = new JLabel("CPF:");
		lblPanelOneCPF.setBounds(10, 70, 40, 20);
		lblPanelOneCPF.addKeyListener(this);
		return lblPanelOneCPF;
	}

	private JFormattedTextField getTxtPanelOneCPF() throws ParseException {
		txtPanelOneCPF = new JFormattedTextField(new MaskFormatter("###.###.###-##"));
		txtPanelOneCPF.setBounds(50, 70, 250, 20);
		txtPanelOneCPF.addKeyListener(this);
		return txtPanelOneCPF;
	}

	private JButton getBtnPanelOneFind() {
		btnPanelOneFind = new JButton("Pesquisar");
		btnPanelOneFind.setBounds(210, 100, 90, 25);
		btnPanelOneFind.addActionListener(this);
		btnPanelOneFind.setActionCommand("findClient");
		return btnPanelOneFind;
	}

	private JButton getBtnPanelOneAdd() {
		btnPanelOneAdd = new JButton("Adicionar");
		btnPanelOneAdd.setBounds(10, 100, 100, 25);
		btnPanelOneAdd.addActionListener(this);
		btnPanelOneAdd.setActionCommand("adicionar");
		return btnPanelOneAdd;
	}

	public JPanel getPanelTwo() {
		panelTwo = new JPanel(null);
		panelTwo.setBorder(new LineBorder(MainScreen.desktopPane.getBackground()));
		panelTwo.setBounds(350, 10, 425, 150);
		panelTwo.add(getScrollPanelTwoClientTable());
		return panelTwo;
	}

	private Table getTblPanelTwo() {
		tblPanelTwo = new Table(new String[] { "ID", "CPF", "Nome" }, 0);
		tblPanelTwo.addMouseListener(this);
		tblPanelTwo.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		return tblPanelTwo;
	}

	private JScrollPane getScrollPanelTwoClientTable() {
		scrollPanelTwoClientTable = new JScrollPane(getTblPanelTwo());
		scrollPanelTwoClientTable.setBounds(10, 10, 405, 130);
		return scrollPanelTwoClientTable;
	}

	public JPanel getPanelThree() {
		panelThree = new JPanel(null);
		panelThree.setBorder(new LineBorder(MainScreen.desktopPane.getBackground()));
		panelThree.setBounds(10, 175, 765, 150);
		panelThree.add(getPaneltblPanelThree());
		panelThree.add(getlblClientPanelThree());
		panelThree.add(getTxtClientPanelThree());
		panelThree.add(getLblCPFPanelThree());
		panelThree.add(getTxtCPFPanelThree());
		panelThree.add(getFilterPanelThree());
		return panelThree;
	}

	private JPanel getFilterPanelThree() {
		FilterPanelThree = new JPanel(null);
		FilterPanelThree.setBorder(
				new TitledBorder(new LineBorder(MainScreen.desktopPane.getBackground()), "Filtrar Produtos"));
		FilterPanelThree.setBounds(10, 60, 294, 86);
		FilterPanelThree.add(getTxtFilterPanelThree());
		FilterPanelThree.add(getbtnFilterPanelThreeFind());
		return FilterPanelThree;
	}

	private TextField getTxtFilterPanelThree() {
		txtFilterPanelThree = new TextField();
		txtFilterPanelThree.setToolTipText("Pesquise por codigo ou Nome");
		txtFilterPanelThree.setBounds(10, 35, 184, 20);
		txtFilterPanelThree.addKeyListener(this);
		return txtFilterPanelThree;
	}

	private JButton getbtnFilterPanelThreeFind() {
		btnFilterPanelThreeFind = new JButton("Pesquisar");
		btnFilterPanelThreeFind.setBounds(195, 33, 90, 25);
		btnFilterPanelThreeFind.addActionListener(this);
		btnFilterPanelThreeFind.setActionCommand("findProduct");
		return btnFilterPanelThreeFind;
	}

	private JPanel getPaneltblPanelThree() {
		paneltblPanelThree = new JPanel(null);
		paneltblPanelThree.setBorder(new LineBorder(MainScreen.desktopPane.getBackground()));
		paneltblPanelThree.setBounds(340, 5, 422, 140);
		paneltblPanelThree.add(getScrollPanelThreeTbl());
		return paneltblPanelThree;
	}

	private JLabel getlblClientPanelThree() {
		lblClientPanelThree = new JLabel("Cliente:");
		lblClientPanelThree.setBounds(10, 10, 40, 20);
		return lblClientPanelThree;
	}

	private TextField getTxtClientPanelThree() {
		txtClientPanelThree = new TextField();
		txtClientPanelThree.setEnabled(false);
		txtClientPanelThree.setBounds(60, 10, 240, 20);
		return txtClientPanelThree;
	}

	private JLabel getLblCPFPanelThree() {
		lblCPFPanelThree = new JLabel("CPF:");
		lblCPFPanelThree.setBounds(10, 34, 40, 20);
		return lblCPFPanelThree;
	}

	private TextField getTxtCPFPanelThree() {
		txtCPFPanelThree = new TextField();
		txtCPFPanelThree.setEnabled(false);
		txtCPFPanelThree.setBounds(60, 34, 240, 20);
		return txtCPFPanelThree;
	}

	private JScrollPane getScrollPanelThreeTbl() {
		scrollPanelThreeTbl = new JScrollPane(getTblPanelThree());
		scrollPanelThreeTbl.setBounds(10, 10, 405, 120);
		return scrollPanelThreeTbl;
	}

	private Table getTblPanelThree() {
		tblPanelThree = new Table(tblProducts, 0);
		tblPanelThree.getSelectionModel().addListSelectionListener(this);
		tblPanelThree.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblPanelThree.addMouseListener(this);
		return tblPanelThree;
	}

	public JPanel getPanelFour() {
		panelFour = new JPanel(null);
		panelFour.setBorder(new LineBorder(MainScreen.desktopPane.getBackground()));
		panelFour.setBounds(10, 350, 765, 150);
		panelFour.add(getScrollTblPanelFour());
		panelFour.add(getTxtTotalPanelFour());
		panelFour.add(getLblTotalPanelFour());
		panelFour.add(getLblChooserDatePanelFour());
		panelFour.add(getChooserDatePanelFour());
		return panelFour;
	}

	private Table getTblPanelFour() {
		tblPanelFour = new Table(tblProducts, 0, new boolean[] { false, false, false, true });
		tblPanelFour.getSelectionModel().addListSelectionListener(this);
		tblPanelFour.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblPanelFour.addMouseListener(this);
		tblPanelFour.setRowHeight(25);
		return tblPanelFour;
	}

	private TextField getTxtTotalPanelFour() {
		txtTotalPanelFour = new TextField();
		txtTotalPanelFour.setEnabled(false);
		txtTotalPanelFour.setBounds(655, 5, 100, 20);
		return txtTotalPanelFour;
	}

	private JLabel getLblTotalPanelFour() {
		lblTotalPanelFour = new JLabel("Valor Total:");
		lblTotalPanelFour.setBounds(580, 5, 80, 20);
		return lblTotalPanelFour;
	}

	private JLabel getLblChooserDatePanelFour() {
		lblChooserDatePanelFour = new JLabel("Data da Venda:");
		lblChooserDatePanelFour.setBounds(10, 5, 80, 20);
		return lblChooserDatePanelFour;
	}

	private JDateChooser getChooserDatePanelFour() {
		chooserDatePanelFour = new JDateChooser(new Date());
		chooserDatePanelFour.setBounds(100, 5, 100, 20);
		return chooserDatePanelFour;
	}

	private JScrollPane getScrollTblPanelFour() {
		scrollTblPanelFour = new JScrollPane(getTblPanelFour());
		scrollTblPanelFour.setBounds(10, 30, 745, 110);
		return scrollTblPanelFour;
	}

	private JButton getBtnConcluir() {
		btnConcluir = new JButton("Concluir");
		btnConcluir.setBounds(675, 520, 100, 35);
		btnConcluir.addActionListener(this);
		btnConcluir.setActionCommand("conclude");
		return btnConcluir;
	}

	private JButton getBtnExcluir() {
		btnExcluir = new JButton("Excluir Item");
		btnExcluir.setBounds(550, 520, 100, 35);
		btnExcluir.addActionListener(this);
		btnExcluir.setActionCommand("delete");
		return btnExcluir;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			switch (e.getActionCommand()) {
			case "adicionar":
				RegistrationClientScreen rbs = new RegistrationClientScreen();
				MainScreen.desktopPane.add(rbs);
				rbs.setVisible(true);
				break;
			case "delete":
				if (tblPanelFour.getSelectedRow() >= 0) {
					tblPanelFour.getModel().removeRow(tblPanelFour.getSelectedRow());
					this.setTxtTotalPanelFour();
				} else {
					JOptionPane.showMessageDialog(this, "Favor selecionar uma linha!", "Aviso de Falha",
							JOptionPane.ERROR_MESSAGE);
				}
				break;
			case "conclude":
				tblPanelFour.getSelectionModel().clearSelection();
				Sale sale = new Sale(0, id_cliente, Float.parseFloat(txtTotalPanelFour.getText()),
						chooserDatePanelFour.getDate(), User.getInstance().getId());
				if (tblPanelFour.getRowCount() != 0) {
					boolean ret = SaleDAO.getInstance().save(sale);
					for (int i = 0; i < tblPanelFour.getRowCount(); i++) {
						int id = (int) tblPanelFour.getValueAt(i, 0);
						float valor = (float) tblPanelFour.getValueAt(i, 2);
						int quantidade = Integer.valueOf(tblPanelFour.getModel().getValueAt(i, 3).toString());

						SaleDAO.getInstance().saveList(id, valor * quantidade, quantidade, User.getInstance().getId());
					}
					if (ret) {
						JOptionPane.showMessageDialog(this, "Venda Realizada Com Sucesso!\n" + "ID da Venda: "
								+ SaleDAO.getInstance().returnSale());
						this.dispose();
					}
				} else {
					JOptionPane.showMessageDialog(this, "Selecione pelo menos um produto para venda!", "Aviso de Falha",
							JOptionPane.ERROR_MESSAGE);
				}
				break;
			case "findProduct":
				if (txtFilterPanelThree.getText().length() > 0) {
					tblPanelThree.getModel().setRowCount(0);
					ProductDAO.getInstance().getBy(txtFilterPanelThree.getText()).forEach((p) -> {
						tblPanelThree.getModel().addRow(new Object[] { p.getProduct_id(), p.getProduct_name(),
								p.getProduct_valor(), p.getProduct_valor() });
					});
				} else {
					JOptionPane.showMessageDialog(this, "Digite o nome de um produto para pesquisar!");
				}
				break;
			case "findClient":
				tblPanelTwo.getModel().setRowCount(0);
				if (txtPanelOneName.getText().length() > 1) {
					ClientDAO.getInstance().getBy(txtPanelOneName.getText()).forEach((p) -> {
						tblPanelTwo.getModel().addRow(new Object[] { p.getId(), p.getCpf(), p.getNome() });
					});
				} else {
					ClientDAO.getInstance().getBy(txtPanelOneCPF.getText()).forEach((p) -> {
						tblPanelTwo.getModel().addRow(new Object[] { p.getId(), p.getCpf(), p.getNome() });
					});
				}
				break;
			}
		} catch (NumberFormatException | ParseException ex) {
			JOptionPane.showMessageDialog(MainScreen.desktopPane.getSelectedFrame(), ex.getMessage(), "Aviso de Falha",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	protected void loadTable(String command) {
		if (command.equals("All") || command.equals("Client")) {
			tblPanelTwo.getModel().setRowCount(0);
			tblPanelTwo.getColumnModel().getColumn(0).setMaxWidth(0);
			tblPanelTwo.getColumnModel().getColumn(0).setMinWidth(0);
			tblPanelTwo.getColumnModel().getColumn(0).setPreferredWidth(0); // ID
			ClientDAO.getInstance().getAll().forEach((p) -> {
				tblPanelTwo.getModel().addRow(new Object[] { p.getId(), p.getCpf(), p.getNome() });
			});
		}
		if (command.equals("All") || command.equals("Product")) {
			tblPanelThree.getModel().setRowCount(0);
			ProductDAO.getInstance().getAll().forEach((p) -> {
				if (p.getProduct_qtd() >= 1) {
					tblPanelThree.getModel().addRow(new Object[] { p.getProduct_id(), p.getProduct_name(),
							p.getProduct_valor(), p.getProduct_qtd() });
				}
			});
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 2) {
			if (e.getSource().equals(tblPanelThree)) {
				int numeroLinha = tblPanelThree.getSelectedRow();
				int id = Integer.parseInt(tblPanelThree.getModel().getValueAt(numeroLinha, 0).toString());
				String nome = tblPanelThree.getModel().getValueAt(numeroLinha, 1).toString();
				float preco = Float.parseFloat(tblPanelThree.getModel().getValueAt(numeroLinha, 2).toString());
				int quantidade = Integer.parseInt(tblPanelThree.getModel().getValueAt(numeroLinha, 3).toString());

				boolean newRegister = true;
				for (int i = 0; i < tblPanelFour.getRowCount(); i++) {
					if (id == Integer.parseInt(tblPanelFour.getModel().getValueAt(i, 0).toString())) {
						if ((1 + Integer.parseInt(tblPanelFour.getModel().getValueAt(i, 3).toString())) <= quantidade) {
							tblPanelFour.getModel().setValueAt(
									(1 + (Integer.parseInt(tblPanelFour.getModel().getValueAt(i, 3).toString()))), i,
									3);
							setTxtTotalPanelFour();
							newRegister = false;
						} else {
							newRegister = false;
							JOptionPane.showMessageDialog(this, "Número máximo de estoque já incluido na compra!",
									"Aviso de Falha", JOptionPane.ERROR_MESSAGE);
						}
						break;
					}
				}

				if (newRegister == true) {
					tblPanelFour.getModel().addRow(new Object[] { id, nome, preco, 1 });
					setTxtTotalPanelFour();

					TableColumn column = tblPanelFour.getColumnModel().getColumn(3);
					column.setCellRenderer(new DefaultTableCellRenderer() {
						@Override
						public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
								boolean hasFocus, int row, int column) {
							Component component = super.getTableCellRendererComponent(table, value, isSelected,
									hasFocus, row, column);

							spinner.addChangeListener((ChangeEvent e) -> {

								setTxtTotalPanelFour();
							});
							if (component instanceof JLabel) {
								spinner.setValue(Integer.valueOf(((JLabel) component).getText()));
							} else if (value != null) {
								spinner.setValue(Integer.valueOf(value.toString()));
							}
							return spinner;
						}
					});
					column.setCellEditor(new SpinnerEditor(1, 1, quantidade));
				}

			} else if (e.getSource().equals(tblPanelTwo)) {
				int numeroLinha = tblPanelTwo.getSelectedRow();
				id_cliente = Integer.parseInt(tblPanelTwo.getModel().getValueAt(numeroLinha, 0).toString());
				String cpf = tblPanelTwo.getModel().getValueAt(numeroLinha, 1).toString();
				String nome = tblPanelTwo.getModel().getValueAt(numeroLinha, 2).toString();
				txtClientPanelThree.setText(nome);
				txtCPFPanelThree.setText(cpf);
			} else if (e.getSource().equals(tblPanelFour)) {
				tblPanelFour.clearSelection();
			}
		}
	}

	private void setTxtTotalPanelFour() {
		float total = 0;
		float preco = 0;
		int quantidade = 0;

		for (int i = 0; i < tblPanelFour.getModel().getRowCount(); i++) {
			preco = Float.parseFloat(tblPanelFour.getModel().getValueAt(i, 2).toString());
			quantidade = Integer.parseInt(tblPanelFour.getModel().getValueAt(i, 3).toString());
			total = total + (preco * quantidade);
		}
		txtTotalPanelFour.setText(String.valueOf(total == 0 ? "" : total));
	}

	@Override
	public void keyTyped(KeyEvent e) {
		if (e.getSource().equals(txtPanelOneName)) {
			if (txtPanelOneName.getText().length() == 0) {
				loadTable("Client");
			}
		}
		if (e.getSource().equals(txtPanelOneCPF)) {
			if (txtPanelOneCPF.getText().replaceAll("\\.", "").replaceAll("\\-", "").replaceAll(" ", "")
					.length() == 0) {
				loadTable("Client");
			}
		}
		if (e.getSource().equals(txtFilterPanelThree)) {
			if (txtFilterPanelThree.getText().length() == 0) {
				loadTable("Product");
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if ((e.getKeyCode() == Event.ENTER) && e.getSource().equals(txtFilterPanelThree)) {
			actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "findProduct"));
		} else if ((e.getKeyCode() == Event.ENTER) && e.getSource().equals(txtPanelOneCPF)
				|| e.getSource().equals(txtPanelOneName)) {
			actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "findClient"));
		}
	}
}
