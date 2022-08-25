package br.senac.view;

import br.senac.controller.ClientDAO;
import br.senac.controller.ProductDAO;
import br.senac.objects.InternalFrame;
import com.toedter.calendar.JDateChooser;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.ParseException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author Douglas
 */
public class SaleScreen extends InternalFrame {

    private final JPanel clientFilter = new ClientFilter(); //1º esqueda
    private final JPanel clientTable = new ClientTable(); //1º direita
    private final JPanel saleTable = new SaleTable(); //2º Painel
    private final JPanel salesInfo = new SalesInfo(); // 3º Painel
    private JButton btnConcluir;
    private JButton btnExcluir;

    public SaleScreen() {
        super("Nova Venda", false, true, false, true, 800, 600);
        initComponents();
    }

    private void initComponents() {
        this.setLayout(null);
        this.add(clientFilter);
        this.add(clientTable);
        this.add(saleTable);
        this.add(salesInfo);
        this.add(getBtnConcluir());
        this.add(getBtnExcluir());
    }

    private class ClientFilter extends JPanel implements ActionListener {

        private JLabel lblFilterClient;
        private JLabel lblClientFilterNome;
        private JTextField txtClientFilterNome;
        private JLabel lblClientFilterCPF;
        private JTextField txtClientFilterCPF;
        private JButton btnClientFilterFind;
        private JButton btnAdicionar;

        public ClientFilter() {
            this.setLayout(null);
            this.setBorder(new LineBorder(MainScreen.desktopPane.getBackground()));
            this.setBounds(10, 10, 310, 150);
            this.add(getLblFilterClient());
            this.add(getLblClientFilterNome());
            this.add(getTxtClientFilterNome());
            this.add(getLblClientFilterCPF());
            this.add(getTxtClientFilterCPF());
            this.add(getBtnClientFilterFind());
            this.add(getBtnAdicionar());
        }

        private JLabel getLblFilterClient() {
            lblFilterClient = new JLabel("Filtrar Clientes");
            lblFilterClient.setBounds(10, 10, 150, 20);
            return lblFilterClient;
        }

        private JLabel getLblClientFilterNome() {
            lblClientFilterNome = new JLabel("Nome:");
            lblClientFilterNome.setBounds(10, 40, 40, 20);
            return lblClientFilterNome;
        }

        private JTextField getTxtClientFilterNome() {
            txtClientFilterNome = new JTextField();
            txtClientFilterNome.setBounds(50, 40, 250, 20);
            return txtClientFilterNome;
        }

        private JLabel getLblClientFilterCPF() {
            lblClientFilterCPF = new JLabel("CPF:");
            lblClientFilterCPF.setBounds(10, 70, 40, 20);
            return lblClientFilterCPF;
        }

        private JTextField getTxtClientFilterCPF() {
            try {
                txtClientFilterCPF = new JFormattedTextField(new MaskFormatter("###.###.###-##"));
            } catch (ParseException ex) {
                Logger.getLogger(SaleScreen.class.getName()).log(Level.SEVERE, null, ex);
            }
            txtClientFilterCPF.setBounds(50, 70, 250, 20);
            return txtClientFilterCPF;
        }

        private JButton getBtnClientFilterFind() {
            btnClientFilterFind = new JButton("Pesquisar");
            btnClientFilterFind.setBounds(220, 100, 80, 25);
            return btnClientFilterFind;
        }

        private JButton getBtnAdicionar() {
            btnAdicionar = new JButton("Adicionar");
            btnAdicionar.setBounds(10, 100, 100, 25);
            btnAdicionar.addActionListener(this);
            btnAdicionar.setActionCommand("adicionar");
            return btnAdicionar;
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
            }
        }
    }

    private class ClientTable extends JPanel {

        private JScrollPane scrollClientFilterFind;
        private JTable tblClientClientFilterFind;
        private final String colunasClientFilterFind[] = {"CPF", "Nome"};
        private DefaultTableModel dm;

        public ClientTable() {
            this.setLayout(null);
            this.setBorder(new LineBorder(MainScreen.desktopPane.getBackground()));
            this.setBounds(350, 10, 425, 150);
            this.add(getScrollClientFilterFind());
            this.loadTable();
        }

        private JTable getTblClientClientFilterFind() {
            tblClientClientFilterFind = new JTable(getDm(colunasClientFilterFind));
            tblClientClientFilterFind.addMouseListener(new MouseAdapter() {
                SaleTable tb = new SaleTable();
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (e.getClickCount() == 2) {
                        int numeroLinha = tblClientClientFilterFind.getSelectedRow();

                        //int id = Integer.parseInt(tblClientClientFilterFind.getModel().getValueAt(numeroLinha, 0).toString());
                        String cpf = tblClientClientFilterFind.getModel().getValueAt(numeroLinha, 0).toString();
                        String nome = tblClientClientFilterFind.getModel().getValueAt(numeroLinha, 1).toString();
                        tb.setNome(nome);
                        tb.setCPF(cpf);
                    }

                }
            });
            tblClientClientFilterFind.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            return tblClientClientFilterFind;
        }

        private JScrollPane getScrollClientFilterFind() {
            scrollClientFilterFind = new JScrollPane(getTblClientClientFilterFind());
            scrollClientFilterFind.setBounds(10, 10, 405, 130);
            return scrollClientFilterFind;
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

        protected void loadTable() {
            DefaultTableModel modelo = (DefaultTableModel) tblClientClientFilterFind.getModel();
            modelo.setRowCount(0);
            ClientDAO.getInstance().getAll().forEach((p) -> {
                modelo.addRow(new Object[]{p.getCpf(), p.getNome()});
            });
        }
    }

    private class SaleTable extends JPanel implements ListSelectionListener {

        private JPanel productFilter;
        private JPanel productTable;
        private JTable tblProduct;
        private final String colunasClientFilterFind[] = {"ID", "Nome", "Preço", "quantidade"};
        private DefaultTableModel dm;
        private JScrollPane scroll;
        private JTextField txtCliente;
        private JTextField txtCPF;
        private JLabel lblCliente;
        private JLabel lblCPF;
        private JTextField txtProduto;
        private JButton btnPesquisar;

        public SaleTable() {
            this.setLayout(null);
            this.setBorder(new LineBorder(MainScreen.desktopPane.getBackground()));
            this.setBounds(10, 175, 765, 150);
            this.add(getProductTable());
            this.add(getLblCliente());
            this.add(getTxtCliente());
            this.add(getlblCPF());
            this.add(getTxtCPF());
            this.add(getProductFilter());
            this.loadTable();
        }

        private JPanel getProductFilter() {
            productFilter = new JPanel(null);
            productFilter.setBorder(new TitledBorder(new LineBorder(MainScreen.desktopPane.getBackground()), "Filtrar Produtos"));
            productFilter.setBounds(10, 60, 294, 86);
            productFilter.add(getTxtProduto());
            productFilter.add(getBtnPesquisar());
            return productFilter;
        }

        private JTextField getTxtProduto() {
            txtProduto = new JTextField();
            txtProduto.setToolTipText("Pesquise por codigo ou Nome");
            txtProduto.setBounds(10, 35, 184, 20);
            return txtProduto;
        }

        private JButton getBtnPesquisar() {
            btnPesquisar = new JButton("Pesquisar");
            btnPesquisar.setBounds(200, 33, 80, 25);
            return btnPesquisar;
        }

        private JPanel getProductTable() {
            productTable = new JPanel(null);
            productTable.setBorder(new LineBorder(MainScreen.desktopPane.getBackground()));
            productTable.setBounds(340, 5, 422, 140);
            productTable.add(getScroll());
            return productTable;
        }

        private JLabel getLblCliente() {
            lblCliente = new JLabel("Cliente:");
            lblCliente.setBounds(10, 10, 40, 20);
            return lblCliente;
        }

        private JTextField getTxtCliente() {
            txtCliente = new JTextField();
            txtCliente.setEnabled(false);
            txtCliente.setBounds(60, 10, 240, 20);
            return txtCliente;
        }

        private JLabel getlblCPF() {
            lblCPF = new JLabel("CPF:");
            lblCPF.setBounds(10, 34, 40, 20);
            return lblCPF;
        }

        private JTextField getTxtCPF() {
            txtCPF = new JTextField();
            txtCPF.setEnabled(false);
            txtCPF.setBounds(60, 34, 240, 20);
            return txtCPF;
        }

        private JScrollPane getScroll() {
            scroll = new JScrollPane(getTblProduct());
            scroll.setBounds(10, 10, 405, 120);
            return scroll;
        }

        private JTable getTblProduct() {
            tblProduct = new JTable(getDm(colunasClientFilterFind));
            tblProduct.getSelectionModel().addListSelectionListener(this);
            tblProduct.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            return tblProduct;
        }

        public void setNome(String nome) {
            txtCliente.setText(nome);
        }

        public void setCPF(String cpf) {
            txtCPF.setText(cpf);
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

        protected void loadTable() {
            DefaultTableModel modelo = (DefaultTableModel) tblProduct.getModel();
            modelo.setRowCount(0);
            ProductDAO.getInstance().getAll().forEach((p) -> {
                modelo.addRow(new Object[]{p.getId(), p.getNome(), p.getValor(),
                    p.getQuantidade()});
            });
        }

        @Override
        public void valueChanged(ListSelectionEvent e) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }

    private class SalesInfo extends JPanel implements ListSelectionListener {

        private JTable table;
        private final String colunas[] = {"ID", "Nome", "Quantidade", "Preço"};
        private DefaultTableModel dm;
        private JScrollPane scrollSaleTable;
        private JTextField txtTotal;
        private JLabel lblTotal;
        private JLabel lblDataVenda;
        private JDateChooser chooserDate;

        public SalesInfo() {
            this.setLayout(null);
            this.setBorder(new LineBorder(MainScreen.desktopPane.getBackground()));
            this.setBounds(10, 350, 765, 150);
            this.add(getScroll());
            this.add(getTxtTotal());
            this.add(getLblTotal());
            this.add(getLblDataVenda());
            this.add(getChooserDate());
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

        private JScrollPane getScroll() {
            scrollSaleTable = new JScrollPane(getTblSale());
            scrollSaleTable.setBounds(10, 30, 745, 110);
            return scrollSaleTable;
        }

        private JTable getTblSale() {
            table = new JTable(getDm(colunas));
            table.getSelectionModel().addListSelectionListener(this);
            table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            return table;
        }

        private JTextField getTxtTotal() {
            txtTotal = new JTextField();
            txtTotal.setEnabled(false);
            txtTotal.setBounds(655, 5, 100, 20);
            return txtTotal;
        }

        private JLabel getLblTotal() {
            lblTotal = new JLabel("Valor Total:");
            lblTotal.setBounds(580, 5, 80, 20);
            return lblTotal;
        }

        private JLabel getLblDataVenda() {
            lblDataVenda = new JLabel("Data da Venda:");
            lblDataVenda.setBounds(10, 5, 80, 20);
            return lblDataVenda;
        }

        private JDateChooser getChooserDate() {
            chooserDate = new JDateChooser(new Date());
            chooserDate.setBounds(100, 5, 100, 20);
            return chooserDate;
        }

        @Override
        public void valueChanged(ListSelectionEvent e) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

    }

    private JButton getBtnConcluir() {
        btnConcluir = new JButton("Concluir");
        btnConcluir.setBounds(675, 520, 100, 35);
        return btnConcluir;
    }

    private JButton getBtnExcluir() {
        btnExcluir = new JButton("Excluir Item");
        btnExcluir.setBounds(550, 520, 100, 35);
        return btnExcluir;
    }
}
