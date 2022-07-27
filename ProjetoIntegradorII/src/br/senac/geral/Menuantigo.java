package br.senac.geral;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;

/**
 *
 * @author Douglas
 */
public class Menuantigo extends JMenu implements ActionListener {

    //public Menu() {
      //  super();
      //  initialize();
   // }

    private final JMenu opcoes = new JMenu("Opções");
    private final JMenuItem tp = new JMenuItem("Tela Inicial", new ImageIcon(getClass().getResource("/resources/Home-icon.png")));
    private final JMenuItem banco = new JMenuItem("Banco de Dados", new ImageIcon(getClass().getResource("/resources/BancoDados-icon.png")));
    private final JMenuItem calc = new JMenuItem("Calculadora", new ImageIcon(getClass().getResource("/resources/Calculator-icon.png")));
    private final JMenuItem cale = new JMenuItem("Calendario", new ImageIcon(getClass().getResource("/resources/Calendar-icon.png")));
    private final JMenuItem bloco = new JMenuItem("Bloco de notas", new ImageIcon(getClass().getResource("/resources/Notepad-Bloc-notes-icon.png")));
    private final JMenuItem lf = new JMenuItem("Look And Feel", new ImageIcon(getClass().getResource("/resources/Cor.png")));
    private final JSeparator sep = new JSeparator();
    private final JMenuItem sair = new JMenuItem("sair", new ImageIcon(getClass().getResource("/resources/Log-Out-icon.png")));

    private void initialize() {
        opcoes.setIcon(new ImageIcon(getClass().getResource("/resources/Settings-icon.png")));
        add(opcoes);
        tp.addActionListener(this);
        tp.setActionCommand("TelaInicial");
        opcoes.add(tp);
        banco.addActionListener(this);
        banco.setActionCommand("Sair");
        opcoes.add(banco);
        bloco.addActionListener(this);
        bloco.setActionCommand("BlocoNotas");
        opcoes.add(bloco);
        calc.addActionListener(this);
        calc.setActionCommand("Calculadora");
        opcoes.add(calc);
        cale.addActionListener(this);
        cale.setActionCommand("Sair");
        opcoes.add(cale);
        lf.addActionListener(this);
        lf.setActionCommand("LookAndFeel");
        opcoes.add(lf);
        opcoes.add(sep);
        sair.addActionListener(this);
        sair.setActionCommand("Sair");
        opcoes.add(sair);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getActionCommand().equals("Sair")) {
                System.exit(0);
            } else if (e.getActionCommand().equals("Calculadora")) {
                Runtime.getRuntime().exec("C:\\windows\\System32\\calc.exe");
            } else if (e.getActionCommand().equals("BlocoNotas")) {
                Runtime.getRuntime().exec("C:\\windows\\System32\\notepad.exe");
            } else if (e.getActionCommand().equals("TelaInicial")) {
               // TelaPrincipal telaInicial = new TelaPrincipal();
               // telaInicial.setLocationRelativeTo(null);
               // telaInicial.setVisible(true);
                //pensar em lógica para fechar a tela anterior
            } else if (e.getActionCommand().equals("LookAndFeel")) {
                LookAndFeel lf = new LookAndFeel();
                lf.setLocationRelativeTo(null);
                lf.setVisible(true);
            }
        } catch (IOException ex) {
            //Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
