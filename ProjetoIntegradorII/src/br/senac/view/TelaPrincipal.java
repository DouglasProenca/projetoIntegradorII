package br.senac.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Douglas
 */
public class TelaPrincipal extends JFrame implements ActionListener, KeyListener {

    public TelaPrincipal() {
        super("Tela Principal");
        initialize();
        setLocationRelativeTo(null);
        this.setLayout(null);
    }

    private final JButton btnRelatorioCliente = new JButton("Relatório Cliente", new javax.swing.ImageIcon(getClass().getResource("/resources/Users-icon.png")));
    private final JButton btnRelatorioProduto = new JButton("Relatório Produto", new javax.swing.ImageIcon(getClass().getResource("/resources/product-icon.png")));
    private final JButton btnRelatorioVendas = new JButton("Relatório Vendas", new javax.swing.ImageIcon(getClass().getResource("/resources/relat.png")));
    private final JLabel lbltitulo = new JLabel("CR7 Imports");
    private final JPanel titulo = new JPanel(null);
    private final JPanel painel = new JPanel(null);

    private void initialize() {
        setSize(615, 486);
        setResizable(false);
        this.setLayout(null);
        addKeyListener(this);
        setFocusable(true);

        lbltitulo.setBounds(80, 10, 200, 20);
        lbltitulo.setFont(new Font("Lucida Fax", Font.BOLD, 18));
        titulo.add(lbltitulo);

        titulo.setSize(285, 40);
        titulo.setLocation(140, 20);
        titulo.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        getContentPane().add(titulo);

        btnRelatorioCliente.setBounds(50, 50, 210, 80);
        btnRelatorioCliente.addActionListener(this);
        btnRelatorioCliente.setActionCommand("Cliente");
        painel.add(btnRelatorioCliente);

        btnRelatorioProduto.setBounds(310, 50, 210, 80);
        btnRelatorioProduto.addActionListener(this);
        btnRelatorioProduto.setActionCommand("Produto");
        painel.add(btnRelatorioProduto);

        btnRelatorioVendas.setBounds(180, 180, 210, 80);
        btnRelatorioVendas.addActionListener(this);
        btnRelatorioVendas.setActionCommand("Vendas");
        painel.add(btnRelatorioVendas);

        painel.setLayout(null);
        painel.setBorder(javax.swing.BorderFactory.createTitledBorder("Opções"));
        painel.setSize(573, 300);
        painel.setLocation(10, 80);
        painel.setBackground(new Color(205, 205, 254));
        getContentPane().add(painel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Cliente")) {

        } else if (e.getActionCommand().equals("Vendas")) {

        } else if (e.getActionCommand().equals("Produto")) {

        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            System.exit(0);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            new TelaPrincipal().setVisible(true);
        });
    }
}
