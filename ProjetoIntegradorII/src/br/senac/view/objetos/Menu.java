package br.senac.view.objetos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 *
 * @author Douglas
 */
public class Menu extends JMenuBar implements ActionListener {

	private final JMenu opcoes = new JMenu("Menu");
	private final JMenu banco = new JMenu("Banco de Dados");
	private final JMenu relatorio = new JMenu("Relatorio");
	private final JMenuItem rel = new JMenuItem("Relatorio Camisas");
	private final JMenuItem itemConexaoBD = new JMenuItem("JDBC");
	private final JMenuItem itemBackup = new JMenuItem("Backup");

	private void initialize() {
		banco.addActionListener(this);
		banco.setIcon(new ImageIcon(getClass().getResource("/Resources/BancoDados-icon.png")));
		opcoes.add(banco);
		itemConexaoBD.addActionListener(this);
		itemConexaoBD.setActionCommand("itemConexaoBD");
		banco.add(itemConexaoBD);
		
		itemBackup.addActionListener(this);
		itemBackup.setActionCommand("backup");
		banco.add(itemBackup);
		
		rel.addActionListener(this);
		rel.setActionCommand("rel");
		
		relatorio.add(rel);
		opcoes.add(relatorio);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
		
			 if (e.getActionCommand().equals("itemConexaoBD")) {
				view.TelaConexaoBD bd = new view.TelaConexaoBD();
				TelaPrincipal.dkpaneFundoTela.add(bd);
				TelaPrincipal.tbBarraIcone.add(bd.getDesktopIcon());
				bd.setVisible(true);
				TelaPrincipal.centralizaForm(bd);
				bd.addInternalFrameListener(this);
			} else if(e.getActionCommand().equals("backup")) {
				TelaBackup tbd = new TelaBackup();
				TelaPrincipal.dkpaneFundoTela.add(tbd);
				TelaPrincipal.tbBarraIcone.add(tbd.getDesktopIcon());
				tbd.setVisible(true);
				TelaPrincipal.centralizaForm(tbd);
				tbd.addInternalFrameListener(this);
			} else if (e.getActionCommand().equals("rel")) {
				TelaRelatorio tbd = new TelaRelatorio();
				TelaPrincipal.dkpaneFundoTela.add(tbd);
				TelaPrincipal.tbBarraIcone.add(tbd.getDesktopIcon());
				tbd.setVisible(true);
				TelaPrincipal.centralizaForm(tbd);
				tbd.addInternalFrameListener(this);
			}
		} catch (IOException ex) {
			Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
		}
	}



}
