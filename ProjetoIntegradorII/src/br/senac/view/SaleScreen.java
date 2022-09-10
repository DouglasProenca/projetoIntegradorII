package br.senac.view;

import br.senac.controller.ClientDAO;
import br.senac.controller.ProductDAO;
import br.senac.controller.SaleDAO;
import br.senac.model.Sale;
import br.senac.model.User;
import br.senac.objects.InternalFrame;
import com.toedter.calendar.JDateChooser;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author Douglas
 */
public class SaleScreen extends InternalFrame {

    // 1º Painel
    private JPanel panelOne;
    private JLabel lblPanelOneTitle;
    private JButton btnPanelOneAdd;
    private JLabel lblPanelOneName;
    private JTextField txtPanelOneName;
    private JLabel lblPanelOneCPF;
    private JTextField txtPanelOneCPF;
    private JButton btnPanelOneFind;
    // 2º Painel
    private JPanel panelTwo;
    private final String columnsTblPanelTwo[] = {"ID", "CPF", "Nome"};
    private JScrollPane scrollPanelTwoClientTable;
    private JTable tblPanelTwo;
    // 3º Painel
    private JPanel panelThree;
    private JLabel lblClientPanelThree;
    private JLabel lblCPFPanelThree;
    private JTextField txtClientPanelThree;
    private JTextField txtCPFPanelThree;
    private JPanel FilterPanelThree;
    private JTextField txtFilterPanelThree;
    private JButton btnFilterPanelThreeFind;
    private JPanel paneltblPanelThree;
    private JScrollPane scrollPanelThreeTbl;
    private JTable tblPanelThree;
    // 4º Painel
    private JPanel panelFour;
    private JDateChooser chooserDatePanelFour;
    private JLabel lblChooserDatePanelFour;
    private JLabel lblTotalPanelFour;
    private JTextField txtTotalPanelFour;
    private JScrollPane scrollTblPanelFour;
    private JTable tblPanelFour;
    // Internal Frame
    private JButton btnConcluir;
    private JButton btnExcluir;
    private DefaultTableModel dm;
    private final String tblProducts[] = {"ID", "Nome", "Preço", "quantidade"};
    private float total = 0;
    private int id_cliente;

    public SaleScreen() {
        super("Nova Venda", false, true, false, true, 800, 600);
        initComponents();
    }

    private void initComponents() {
        this.setLayout(null);
        this.add(getPanelOne());
        this.add(getPanelTwo());
        this.add(getPanelThree());
        this.add(getPanelFour());
        this.add(getBtnConcluir());
        this.add(getBtnExcluir());
        this.loadTable();
    }

    private JPanel getPanelOne() {
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

    private JTextField getTxtPanelOneName() {
        txtPanelOneName = new JTextField();
        txtPanelOneName.setBounds(50, 40, 250, 20);
        return txtPanelOneName;
    }

    private JLabel lblPanelOneCPF() {
        lblPanelOneCPF = new JLabel("CPF:");
        lblPanelOneCPF.setBounds(10, 70, 40, 20);
        return lblPanelOneCPF;
    }

    private JTextField getTxtPanelOneCPF() {
        try {
            txtPanelOneCPF = new JFormattedTextField(new MaskFormatter("###.###.###-##"));
        } catch (ParseException ex) {
            Logger.getLogger(SaleScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
        txtPanelOneCPF.setBounds(50, 70, 250, 20);
        return txtPanelOneCPF;
    }

    private JButton getBtnPanelOneFind() {
        btnPanelOneFind = new JButton("Pesquisar");
        btnPanelOneFind.setBounds(220, 100, 80, 25);
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

    private JTable getTblPanelTwo() {
        tblPanelTwo = new JTable(getDm(columnsTblPanelTwo));
        tblPanelTwo.addMouseListener(this);
        tblPanelTwo.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        return tblPanelTwo;
    }

    private JScrollPane getScrollPanelTwoClientTable() {
        scrollPanelTwoClientTable = new JScrollPane(getTblPanelTwo());
        scrollPanelTwoClientTable.setBounds(10, 10, 405, 130);
        return scrollPanelTwoClientTable;
    }

    private DefaultTableModel getDm(String[] colunas) {
        dm = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int rowIndex, int mColIndex) {
                return false;
            }
        };
        return dm;
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
        FilterPanelThree.setBorder(new TitledBorder(new LineBorder(MainScreen.desktopPane.getBackground()), "Filtrar Produtos"));
        FilterPanelThree.setBounds(10, 60, 294, 86);
        FilterPanelThree.add(getTxtFilterPanelThree());
        FilterPanelThree.add(getbtnFilterPanelThreeFind());
        return FilterPanelThree;
    }

    private JTextField getTxtFilterPanelThree() {
        txtFilterPanelThree = new JTextField();
        txtFilterPanelThree.setToolTipText("Pesquise por codigo ou Nome");
        txtFilterPanelThree.setBounds(10, 35, 184, 20);
        return txtFilterPanelThree;
    }

    private JButton getbtnFilterPanelThreeFind() {
        btnFilterPanelThreeFind = new JButton("Pesquisar");
        btnFilterPanelThreeFind.setBounds(200, 33, 80, 25);
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

    private JTextField getTxtClientPanelThree() {
        txtClientPanelThree = new JTextField();
        txtClientPanelThree.setEnabled(false);
        txtClientPanelThree.setBounds(60, 10, 240, 20);
        return txtClientPanelThree;
    }

    private JLabel getLblCPFPanelThree() {
        lblCPFPanelThree = new JLabel("CPF:");
        lblCPFPanelThree.setBounds(10, 34, 40, 20);
        return lblCPFPanelThree;
    }

    private JTextField getTxtCPFPanelThree() {
        txtCPFPanelThree = new JTextField();
        txtCPFPanelThree.setEnabled(false);
        txtCPFPanelThree.setBounds(60, 34, 240, 20);
        return txtCPFPanelThree;
    }

    private JScrollPane getScrollPanelThreeTbl() {
        scrollPanelThreeTbl = new JScrollPane(getTblPanelThree());
        scrollPanelThreeTbl.setBounds(10, 10, 405, 120);
        return scrollPanelThreeTbl;
    }

    private JTable getTblPanelThree() {
        tblPanelThree = new JTable(getDm(tblProducts));
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

    private JTable getTblPanelFour() {
        tblPanelFour = new JTable(getDm(tblProducts));
        tblPanelFour.getSelectionModel().addListSelectionListener(this);
        tblPanelFour.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        return tblPanelFour;
    }

    private JTextField getTxtTotalPanelFour() {
        txtTotalPanelFour = new JTextField();
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
        switch (e.getActionCommand()) {
            case "adicionar":
                RegistrationClientScreen rbs = new RegistrationClientScreen();
                MainScreen.desktopPane.add(rbs);
                rbs.setVisible(true);
                MainScreen.centralizaForm(rbs);
                break;
            case "delete":
                DefaultTableModel dtm = (DefaultTableModel) tblPanelFour.getModel();
                if (tblPanelFour.getSelectedRow() >= 0) {
                    float preco = Float.parseFloat(tblPanelThree.getModel().getValueAt(tblPanelFour.getSelectedRow(), 2).toString());
                    total = total - preco;
                    txtTotalPanelFour.setText(String.valueOf(total));
                    dtm.removeRow(tblPanelFour.getSelectedRow());
                } else {
                    JOptionPane.showMessageDialog(null, "Favor selecionar uma linha");
                }
                break;
            case "conclude":
                Sale sale = new Sale(0, id_cliente, Float.parseFloat(txtTotalPanelFour.getText()), chooserDatePanelFour.getDate(), User.getInstance().getId());
                if (tblPanelFour.getRowCount() != 0) {
                    boolean ret = SaleDAO.getInstance().save(sale);
                    for (int i = 0; i < tblPanelFour.getRowCount(); i++) {
                        int id = (int) tblPanelFour.getValueAt(i, 0);
                        float valor = (float) tblPanelFour.getValueAt(i, 2);
                        int quantidade = (int) tblPanelFour.getValueAt(i, 3);
                        SaleDAO.getInstance().saveList(id, valor, quantidade, User.getInstance().getId());
                    }
                    if (ret) {
                        JOptionPane.showMessageDialog(this, "Venda Realizada Com Sucesso!\n" + "ID da Venda: " + SaleDAO.getInstance().returnSale());
                        this.dispose();
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Selecione pelo menos um produto para venda!");
                }
                break;
            case "findProduct":
                if (txtFilterPanelThree.getText().length() > 0) {
                    DefaultTableModel modelo = (DefaultTableModel) tblPanelThree.getModel();
                    modelo.setRowCount(0);
                    ProductDAO.getInstance().getBy(txtFilterPanelThree.getText()).forEach((p) -> {
                        modelo.addRow(new Object[]{p.getId(), p.getNome(), p.getValor(), p.getQuantidade()});
                    });
                } else {
                    JOptionPane.showMessageDialog(this, "Digite o nome de um produto para pesquisar!");
                }
                break;
            case "findClient":
                DefaultTableModel modelo = (DefaultTableModel) tblPanelTwo.getModel();
                modelo.setRowCount(0);
                if (txtPanelOneName.getText().length() > 1) {
                    ClientDAO.getInstance().getBy(txtPanelOneName.getText()).forEach((p) -> {
                        modelo.addRow(new Object[]{p.getId(), p.getCpf(), p.getNome()});
                    });
                } else {
                    ClientDAO.getInstance().getBy(txtPanelOneCPF.getText()).forEach((p) -> {
                        modelo.addRow(new Object[]{p.getId(), p.getCpf(), p.getNome()});
                    });
                }
                break;
        }
    }

    @Override
    protected void loadTable() {
        DefaultTableModel modelo = (DefaultTableModel) tblPanelTwo.getModel();
        modelo.setRowCount(0);
        tblPanelTwo.getColumnModel().getColumn(0).setMaxWidth(0);
        tblPanelTwo.getColumnModel().getColumn(0).setMinWidth(0);
        tblPanelTwo.getColumnModel().getColumn(0).setPreferredWidth(0);  //ID
        ClientDAO.getInstance().getAll().forEach((p) -> {
            modelo.addRow(new Object[]{p.getId(), p.getCpf(), p.getNome()});
        });

        DefaultTableModel modeloTabelThree = (DefaultTableModel) tblPanelThree.getModel();
        modeloTabelThree.setRowCount(0);
        ProductDAO.getInstance().getAll().forEach((p) -> {
            if (p.getQuantidade() >= 1) {
                modeloTabelThree.addRow(new Object[]{p.getId(), p.getNome(), p.getValor(), p.getQuantidade()});
            }
        });
    }

    @Override
    public void mouseClicked(MouseEvent e
    ) {
        if (e.getClickCount() == 2) {
            if (e.getSource().equals(tblPanelThree)) {
                int numeroLinha = tblPanelThree.getSelectedRow();
                int id = Integer.parseInt(tblPanelThree.getModel().getValueAt(numeroLinha, 0).toString());
                String nome = tblPanelThree.getModel().getValueAt(numeroLinha, 1).toString();
                float preco = Float.parseFloat(tblPanelThree.getModel().getValueAt(numeroLinha, 2).toString());
                int quantidade = Integer.parseInt(tblPanelThree.getModel().getValueAt(numeroLinha, 3).toString());

                DefaultTableModel modelo = (DefaultTableModel) tblPanelFour.getModel();
                modelo.addRow(new Object[]{id, nome, preco, quantidade});
                total = total + preco;
                txtTotalPanelFour.setText(String.valueOf(total));

            } else if (e.getSource().equals(tblPanelTwo)) {
                int numeroLinha = tblPanelTwo.getSelectedRow();
                id_cliente = Integer.parseInt(tblPanelTwo.getModel().getValueAt(numeroLinha, 0).toString());
                String cpf = tblPanelTwo.getModel().getValueAt(numeroLinha, 1).toString();
                String nome = tblPanelTwo.getModel().getValueAt(numeroLinha, 2).toString();
                txtClientPanelThree.setText(nome);
                txtCPFPanelThree.setText(cpf);
            }

        }
    }
}
