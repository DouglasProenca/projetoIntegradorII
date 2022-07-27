package objetos;

import br.senac.view.CalendarScreen;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.WindowStateListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JColorChooser;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

import view.TelaBackup;
import view.TelaPrincipal;
import view.TelaRelatorio;
import view.TelaSobre;

/**
 *
 * @author Douglas
 */
public class Menu extends JMenuBar implements ActionListener, InternalFrameListener,WindowStateListener {

	public Menu() {
		super();
		initialize();
	}

	private final JMenu opcoes = new JMenu("Menu");
	private final JMenu banco = new JMenu("Banco de Dados");
	private final JMenu relatorio = new JMenu("Relatorio");
	private final JMenuItem rel = new JMenuItem("Relatorio Camisas");
	private final JMenuItem itemLookAndFeel = new JMenuItem("Look and Feel");
	private final JMenuItem itemFundodeTela = new JMenuItem("Fundo de Tela");
	private final JMenuItem itemConexaoBD = new JMenuItem("JDBC");
	private final JMenuItem itemBackup = new JMenuItem("Backup");

	private void initialize() {
		opcoes.setIcon(new ImageIcon(getClass().getResource("/resources/Menu-icon.png")));
		add(opcoes);
		banco.addActionListener(this);
		banco.setIcon(new ImageIcon(getClass().getResource("/Resources/BancoDados-icon.png")));
		opcoes.add(banco);
		itemConexaoBD.addActionListener(this);
		itemConexaoBD.setActionCommand("itemConexaoBD");
		banco.add(itemConexaoBD);
		
		itemBackup.addActionListener(this);
		itemBackup.setActionCommand("backup");
		banco.add(itemBackup);

		itemLookAndFeel.addActionListener(this);
		itemLookAndFeel.setActionCommand("LookAndFeel");

		itemFundodeTela.addActionListener(this);
		itemFundodeTela.setActionCommand("FundoTela");

		
		rel.addActionListener(this);
		rel.setActionCommand("rel");
		
		relatorio.add(rel);
		opcoes.add(relatorio);
		sobre.addActionListener(this);
		sobre.setActionCommand("Sobre");
		opcoes.add(sobre);
		opcoes.add(sep);
		sair.addActionListener(this);
		sair.setActionCommand("Sair");
		opcoes.add(sair);
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

	@Override
	public void internalFrameOpened(InternalFrameEvent e) {

	}

	@Override
	public void internalFrameClosing(InternalFrameEvent e) {

	}

	@Override
	public void internalFrameClosed(InternalFrameEvent e) {
		JInternalFrame frame = (JInternalFrame) e.getSource();
		frame.setClosable(true);
	}

	@Override
	public void internalFrameIconified(InternalFrameEvent e) {
		JInternalFrame frame = (JInternalFrame) e.getSource();
		frame.setIconifiable(false);
		TelaPrincipal.tbBarraIcone.add(frame.getDesktopIcon());
	}

	@Override
	public void internalFrameDeiconified(InternalFrameEvent e) {
		JInternalFrame frame = (JInternalFrame) e.getSource();
		TelaPrincipal.tbBarraIcone.remove(frame.getDesktopIcon());
		frame.setIconifiable(true);
		TelaPrincipal.dkpaneFundoTela.add(frame);
	}

	@Override
	public void internalFrameActivated(InternalFrameEvent e) {
		JInternalFrame frame = (JInternalFrame) e.getSource();
		TelaPrincipal.tbBarraIcone.add(frame.getDesktopIcon());
	}

	@Override
	public void internalFrameDeactivated(InternalFrameEvent e) {
		JInternalFrame frame = (JInternalFrame) e.getSource();
		TelaPrincipal.tbBarraIcone.add(frame.getDesktopIcon());
	}

	@Override
	public void windowStateChanged(WindowEvent e) {
		
	}
}
