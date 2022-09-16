package br.senac.view;

import br.senac.controller.CategoryDAO;
import br.senac.objects.Excel;
import br.senac.model.Category;
import br.senac.objects.InternalFrame;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class CategoryReportScreen extends InternalFrame implements ListSelectionListener, KeyListener {

    private final JLabel lblNome = new JLabel("Nome:");
    private final String colunas[] = {"ID", "Categoria", "Data", "Usuário"};
    private JPanel panelNorth;
    private JPanel panelWest;
    private JTextField txtPesquisa;
    private JButton btnPesquisa;
    private JButton btnExcluir;
    private JButton btnExportar;
    private JButton btnIncluir;
    private DefaultTableModel dm;
    private JTable tblResultado;
    private JScrollPane scroll;
    private static InternalFrame uniqueInstance;
    private final CategoryDAO dao = CategoryDAO.getInstance();
    private final Excel excel = new Excel();

    public static synchronized InternalFrame getInstance() {
        if (uniqueInstance == null) uniqueInstance = new CategoryReportScreen();
        return uniqueInstance;
    }

    public CategoryReportScreen() {
        super("Relatório Categorias", true, true, true, true, 707, 400);
        initComponents();
    }

    private JTable getTblResultado() {
        tblResultado = new JTable(getDm());
        tblResultado.getSelectionModel().addListSelectionListener(this);
        tblResultado.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblResultado.addMouseListener(this);
        return tblResultado;
    }

    private DefaultTableModel getDm() {
        dm = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int rowIndex, int mColIndex) {
                return false;
            }
        };
        return dm;
    }

    private JScrollPane getScrollPane() {
        scroll = new JScrollPane(getTblResultado());
        return scroll;
    }

    private JButton getBtnPesquisa() {
        if (btnPesquisa == null) {
            btnPesquisa = new JButton("Pesquisar");
            btnPesquisa.addActionListener(this);
            btnPesquisa.setActionCommand("find");
            btnPesquisa.setPreferredSize(new Dimension(100, 30));
        }
        return btnPesquisa;
    }

    private JTextField getTxtPesquisa() {
        if (txtPesquisa == null) {
            txtPesquisa = new JTextField();
            txtPesquisa.setPreferredSize(new Dimension(200, 30));
            txtPesquisa.addKeyListener(this);
            txtPesquisa.setToolTipText("Procure por categoria ou digite Refresh, R e tecle enter para atualizar a tabela.");
        }
        return txtPesquisa;
    }

    private JButton getbtnExportar() {
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
        if (btnExcluir == null) {
            btnExcluir = new JButton("Excluir");
            btnExcluir.setAlignmentX(Component.CENTER_ALIGNMENT);
            btnExcluir.setPreferredSize(new Dimension(100, 30));
            btnExcluir.addActionListener(this);
            btnExcluir.setActionCommand("excluir");
            btnExcluir.setEnabled(false);
        }
        return btnExcluir;
    }

    private JButton getBtnIncluir() {
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
        return panelNorth;
    }

    private void initComponents() {
        this.add(BorderLayout.CENTER, getScrollPane());
        this.add(BorderLayout.NORTH, getPanelNorth());
        this.add(BorderLayout.EAST, getPanelWest());
        this.loadTable();
    }

    @Override
    protected void loadTable() {
        DefaultTableModel modelo = (DefaultTableModel) tblResultado.getModel();
        modelo.setRowCount(0);
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MMM/yyyy"); //você pode usar outras máscaras
        dao.getAll().forEach((p) -> {
            modelo.addRow(new Object[]{p.getId(), p.getCategoria(), sdf1.format(p.getDate()), p.getUser()});
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "excluir":
                try {
                    int numeroLinha = tblResultado.getSelectedRow();
                    int id = Integer.parseInt(tblResultado.getModel().getValueAt(numeroLinha, 0).toString());
                    if (dao.delete(id)) {
                        JOptionPane.showMessageDialog(this, "Categoria excluída com sucesso!");
                    } else {
                        JOptionPane.showMessageDialog(this, "Falha ao excluir Categoria!");
                    }
                    this.loadTable();
                } catch (HeadlessException | NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage(),
                            "Aviso de Falha", JOptionPane.ERROR_MESSAGE);
                }
                break;
            case "Incluir":
                RegistrationCategoryScreen rbs = new RegistrationCategoryScreen();
                getParent().add(rbs);
                rbs.setVisible(true);
                break;
            case "Exportar":
                JFileChooser fc = new JFileChooser();
                fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
                int choice = fc.showSaveDialog(null);
                if (choice != 1) excel.CategoryExcel(fc.getSelectedFile(), dao.getAll());
                break;
            case "find":
                DefaultTableModel modelo = (DefaultTableModel) tblResultado.getModel();
                modelo.setRowCount(0);
                SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MMM/yyyy"); //você pode usar outras máscaras
                dao.getBy(txtPesquisa.getText()).forEach((p) -> {
                    modelo.addRow(new Object[]{p.getId(), p.getCategoria(), sdf1.format(p.getDate()), p.getUser()});
                });
                break;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2) {
            int numeroLinha = tblResultado.getSelectedRow();

            int id = Integer.parseInt(tblResultado.getModel().getValueAt(numeroLinha, 0).toString());
            String category = tblResultado.getModel().getValueAt(numeroLinha, 1).toString();
            String user = tblResultado.getModel().getValueAt(numeroLinha, 3).toString();

            Category brand = new Category(category, id, null, null, null, user);

            RegistrationCategoryScreen rbs = new RegistrationCategoryScreen(brand);
            getParent().add(rbs);
            rbs.setVisible(true);
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            boolean rowsAreSelected = tblResultado.getSelectedRowCount() > 0;
            btnExcluir.setEnabled(rowsAreSelected);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == Event.ENTER) {
            if (txtPesquisa.getText().toLowerCase().equals("refresh") || txtPesquisa.getText().toLowerCase().equals("r")) {
                loadTable();
            } else {
                ActionEvent z = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "find");
                actionPerformed(z);
            }
        }
    }
}
