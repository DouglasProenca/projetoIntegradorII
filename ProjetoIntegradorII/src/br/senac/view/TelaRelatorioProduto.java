package br.senac.view;

import br.senac.geral.menu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

/**
 *
 * @author Douglas
 */
public class TelaRelatorioProduto extends JFrame implements ActionListener {
    
    public TelaRelatorioProduto(){
        super("Relatório Produto");
        initialize();
    }
    
    private final JTextField txtPesquisa = new JTextField();
    private final JButton btnNovo = new JButton("Novo");
    private final JButton btnDeletar = new JButton("Excluir");
    private final JButton btnExportar = new JButton("Exportar");
    private final JButton btnExcluir = new JButton("Excluir");
    private final JButton btnPesquisar = new JButton("Pesquisar");
    
    private void initialize(){
        menu Menu = new menu();
        setJMenuBar(Menu);
        setSize(820, 512);
    }
    

    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
}
