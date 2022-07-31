package br.senac.view;

import br.senac.view.objetos.InternalFrame;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class BrandReportScreen extends InternalFrame implements ActionListener {

    private final JLabel lblNome = new JLabel("Nome:");
    private final String colunas[] = {"ID", "Time", "Liga", "Quantidade", "Valor", "Data"};
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
        super("Relatorio Marcas", true, true, true, true,707, 400);
        initComponents();
    }
    
    private JTable getTblResultado(){
        tblResultado = new JTable(getDm());
        return tblResultado;
    }
    
    private DefaultTableModel getDm(){
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
            btnExcluir = new JButton("Excluir    ");
            btnExcluir.setAlignmentX(Component.CENTER_ALIGNMENT);
            btnExcluir.setPreferredSize(new Dimension(100, 30));
        }
        return btnExcluir;
    }

    private JButton getBtnIncluir() {
        if (btnIncluir == null) {
            btnIncluir = new JButton("Incluir     ");
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
        this.add(BorderLayout.NORTH, getPanelNorth());
        this.add(BorderLayout.LINE_END, getPanelWest());
        this.add(BorderLayout.CENTER, getScrollPane());

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        /*if(e.getActionCommand().equals("Incluir")) {
			TelaCadastroCamisa telaCadastroCamisa = new TelaCadastroCamisa();
			getParent().add(telaCadastroCamisa);
			// Exibir o JFrame
			telaCadastroCamisa.setVisible(true);
			TelaPrincipal.centralizaForm(telaCadastroCamisa);
		}*/

    }
}
