package view;

import controller.UserDAO;
import model.User;
import objects.InternalFrame;
import objects.Table;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;



public class ReportUserScreen extends InternalFrame {

	private static final long serialVersionUID = 1L;
	private JButton btnNew;
    private JButton btnEdit;
    private JButton btnRemove;
    private JPanel panelNorth;
    private final String colunas[] = {"ID", "Nome", "Email", "Senha Email", "Data"};
    private Table tblResultado;
    private JScrollPane scroll;
    private int lineNumber;

    public ReportUserScreen() {
        super("Cadastro Usuarios", false, true, true, true, 707, 400);
        this.initComponents();
    }

    private void initComponents() {
        this.add(BorderLayout.CENTER, getScrollPane());
        this.add(BorderLayout.NORTH, getPanelNorth());
        this.loadTable();
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

    private Table getTblResultado() {
        tblResultado = new Table(colunas, 0);
        tblResultado.getSelectionModel().addListSelectionListener(this);
        tblResultado.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblResultado.addMouseListener(this);
        return tblResultado;
    }

    private JScrollPane getScrollPane() {
        scroll = new JScrollPane(getTblResultado());
        return scroll;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "new":
                RegisterUserScreen rbs = new RegisterUserScreen();
                this.getParent().add(rbs);
                rbs.setVisible(true);
                break;
            case "edit":
                lineNumber = tblResultado.getSelectedRow();

                int id_edit = Integer.parseInt(tblResultado.getModel().getValueAt(lineNumber, 0).toString());
                String user_name = tblResultado.getModel().getValueAt(lineNumber, 1).toString();
                String email = tblResultado.getModel().getValueAt(lineNumber, 2).toString();
                String email_pass = tblResultado.getModel().getValueAt(lineNumber, 3).toString();
                User user = new User(id_edit, email, email_pass, user_name, null, new Date());

                RegisterUserScreen rbsE = new RegisterUserScreen(user);
                this.getParent().add(rbsE);
                rbsE.setVisible(true);
                break;
            case "remove":
                lineNumber = tblResultado.getSelectedRow();
                int id = Integer.parseInt(tblResultado.getModel().getValueAt(lineNumber, 0).toString());
                if (UserDAO.getInstance().delete(id)) {
                    JOptionPane.showMessageDialog(this, "Usuário excluído com sucesso!");
                }
                this.loadTable();
                break;
        }
    }

    public void loadTable() {
    	tblResultado.getModel().setRowCount(0);
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MMM/yyyy");
        UserDAO.getInstance().getAll().forEach((p) -> {
        	tblResultado.getModel().addRow(new Object[]{p.getId(), p.getUser(), p.getMail(), p.getMailPassword(), sdf1.format(p.getDate())});
        });
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            btnRemove.setEnabled(tblResultado.getSelectedRowCount() > 0);
            btnEdit.setEnabled(tblResultado.getSelectedRowCount() > 0);
        } 
    }
}
