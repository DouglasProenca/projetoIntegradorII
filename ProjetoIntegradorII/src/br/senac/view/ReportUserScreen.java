package br.senac.view;

import br.senac.objects.InternalFrame;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Douglas
 */
public class ReportUserScreen extends InternalFrame {

    private JButton btnNew;
    private JButton btnEdit;
    private JButton btnRemove;
    private JPanel panelNorth;
    private final String colunas[] = {"ID", "Nome", "Email", "Senha Email", "Data"};
    private DefaultTableModel dm;
    private JTable tblResultado;
    private JScrollPane scroll;

    public ReportUserScreen() {
        super("Cadastro Usuarios", false, true, true, true, 707, 400);
        initComponents();
    }

    private void initComponents() {
        this.add(BorderLayout.CENTER, getScrollPane());
        this.add(BorderLayout.NORTH, getPanelNorth());
    }

    private JPanel getPanelNorth() {
        panelNorth = new JPanel(new FlowLayout(10, 10, 10));
        panelNorth.add(getBtnNew());
        panelNorth.add(getBtnEdit());
        panelNorth.add(getBtnRemove());
        return panelNorth;
    }

    private JButton getBtnNew() {
        btnNew = new JButton("Novo");
        btnNew.addActionListener(this);
        btnNew.setActionCommand("new");
        btnNew.setPreferredSize(new Dimension(100, 30));
        return btnNew;
    }

    private JButton getBtnEdit() {
        btnEdit = new JButton("Editar");
        btnEdit.addActionListener(this);
        btnEdit.setActionCommand("edit");
        btnEdit.setPreferredSize(new Dimension(100, 30));
        btnEdit.setEnabled(false);
        return btnEdit;
    }

    private JButton getBtnRemove() {
        btnRemove = new JButton("Remover");
        btnRemove.addActionListener(this);
        btnRemove.setActionCommand("remove");
        btnRemove.setPreferredSize(new Dimension(100, 30));
        btnRemove.setEnabled(false);
        return btnRemove;
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

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "new":
                RegisterUserScreen rbs = new RegisterUserScreen(true);
                getParent().add(rbs);
                rbs.setVisible(true);
                MainScreen.centralizaForm(rbs);
                break;
            case "edit":
                RegisterUserScreen rbsE = new RegisterUserScreen(false);
                getParent().add(rbsE);
                rbsE.setVisible(true);
                MainScreen.centralizaForm(rbsE);
                break;
            case "remove":
                break;
        }
    }

    @Override
    protected void loadTable() {

    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            boolean rowsAreSelected = tblResultado.getSelectedRowCount() > 0;
            btnRemove.setEnabled(rowsAreSelected);
            btnEdit.setEnabled(rowsAreSelected);
        }
    }

}
