package br.senac.view;

import br.senac.geral.menu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

/**
 *
 * @author Douglas
 */
public class TelaRelatorioProduto extends JFrame implements ActionListener {
    
    public TelaRelatorioProduto(){
        super("Relatório Produto");
        initialize();
    }
    
    private void initialize(){
        menu Menu = new menu();
        setJMenuBar(Menu);
        setSize(820, 512);
    }
    

    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
}
