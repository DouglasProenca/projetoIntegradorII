package br.senac.view;

import br.senac.objects.InternalFrame;
import java.text.ParseException;
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
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author Douglas
 */
public class SaleScreen extends InternalFrame {

    private JPanel clientFilter;
    private JPanel clientTable;
    private JPanel productFilter;
    private JPanel productTable;
    private JPanel saleTable;
    private JPanel saleInfo;
    private JTable tblSale;
    private JTable tblProduct;
    private JTable tblclient;
    private JLabel lblFilterClient;
    private JLabel lblClientFilterNome;
    private JTextField txtClientFilterNome;
    private JLabel lblClientFilterCPF;
    private JTextField txtClientFilterCPF;
    private JButton btnClientFilterFind;
    private final String colunasClientFilterFind[] = {"CPF", "Nome"};
    private JTable tblClientClientFilterFind;
    private DefaultTableModel dm;
    private JScrollPane scrollClientFilterFind;

    public SaleScreen() {
        super("Nova Venda", false, true, false, true, 800, 500);
        initComponents();
    }

    private void initComponents() {
        this.setLayout(null);
        this.add(getClientFilter());
        this.add(getClientTable());
    }

    private JPanel getClientFilter() {
        clientFilter = new JPanel(null);
        clientFilter.setBorder(new LineBorder(MainScreen.desktopPane.getBackground()));
        clientFilter.setBounds(10, 10, 310, 150);
        clientFilter.add(getLblFilterClient());
        clientFilter.add(getLblClientFilterNome());
        clientFilter.add(getTxtClientFilterNome());
        clientFilter.add(getLblClientFilterCPF());
        clientFilter.add(getTxtClientFilterCPF());
        clientFilter.add(getBtnClientFilterFind());
        return clientFilter;
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

    private JPanel getClientTable() {
        clientTable = new JPanel(null);
        clientTable.setBorder(new LineBorder(MainScreen.desktopPane.getBackground()));
        clientTable.setBounds(350, 10, 425, 150);
        clientTable.add(getScrollClientFilterFind());
        return clientTable;
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

    private JTable getTblClientClientFilterFind() {
        tblClientClientFilterFind = new JTable(getDm(colunasClientFilterFind));
        tblClientClientFilterFind.getSelectionModel().addListSelectionListener(this);
        tblClientClientFilterFind.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        return tblClientClientFilterFind;
    }

    private JScrollPane getScrollClientFilterFind() {
        scrollClientFilterFind = new JScrollPane(getTblClientClientFilterFind());
        scrollClientFilterFind.setBounds(10, 10, 405, 100);
        return scrollClientFilterFind;
    }

    private JPanel getProductFilter() {
        productFilter = new JPanel(null);
        return productFilter;
    }

    private JPanel getProductTable() {
        productTable = new JPanel(null);
        return productTable;
    }

    private JPanel getSaleTable() {
        saleTable = new JPanel(null);
        return saleTable;
    }

    private JPanel getSaleInfo() {
        saleInfo = new JPanel(null);
        return saleInfo;
    }
}