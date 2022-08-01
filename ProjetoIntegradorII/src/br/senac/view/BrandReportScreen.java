package br.senac.view;

import br.senac.controller.MarcaDao;
import br.senac.model.Marca;
import br.senac.view.objetos.InternalFrame;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
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

public class BrandReportScreen extends InternalFrame implements ActionListener, MouseListener, ListSelectionListener {

    private final JLabel lblNome = new JLabel("Nome:");
    private String colunas[] = {"ID", "Marca", "Pais", "Data", "Usuario"};
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

    public BrandReportScreen() {
        super("Relatorio Marcas", true, true, true, true, 707, 400);
        initComponents();
    }

    private JTable getTblResultado() {
        tblResultado = new JTable(getDm());
        tblResultado.getSelectionModel().addListSelectionListener(this);
        tblResultado.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
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
        scroll.addMouseListener(this);
        return scroll;
    }

    private JButton getBtnPesquisa() {
        if (btnPesquisa == null) {
            btnPesquisa = new JButton("Pesquisar");
            btnPesquisa.setPreferredSize(new Dimension(100, 30));
        }
        return btnPesquisa;
    }

    private JTextField getTxtPesquisa() {
        if (txtPesquisa == null) {
            txtPesquisa = new JTextField();
            txtPesquisa.setPreferredSize(new Dimension(200, 30));
        }
        return txtPesquisa;
    }

    private JButton getbtnExportar() {
        if (btnExportar == null) {
            btnExportar = new JButton("Exportar");
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
        this.CarregarJTable();
    }

    private void CarregarJTable() {
        try {
            DefaultTableModel modelo = (DefaultTableModel) tblResultado.getModel();
            modelo.setRowCount(0);
            for (Marca p : MarcaDao.todos()) {
                modelo.addRow(new Object[]{p.getId(), p.getMarca(), p.getPais(), p.getDate(), p.getUser()});
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(MainScreen.desktopPane.getSelectedFrame(), ex.getMessage(),
                    "Aviso de Falha", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "excluir":
                try {
                    int numeroLinha = tblResultado.getSelectedRow();
                    int id = Integer.parseInt(tblResultado.getModel().getValueAt(numeroLinha, 0).toString());
                    if (MarcaDao.excluirMarca(id)) {
                        JOptionPane.showMessageDialog(this, "Marca excluída com sucesso!");
                    } else {
                        JOptionPane.showMessageDialog(this, "Falha ao excluir marca!");
                    }
                    this.CarregarJTable();
                } catch (Exception ex) {
                               JOptionPane.showMessageDialog(this, ex.getMessage(),
                    "Aviso de Falha", JOptionPane.ERROR_MESSAGE);
                }
                break;
        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        e.getComponent().hasFocus();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            boolean rowsAreSelected = tblResultado.getSelectedRowCount() > 0;
            btnExcluir.setEnabled(rowsAreSelected);
        }
    }
}
