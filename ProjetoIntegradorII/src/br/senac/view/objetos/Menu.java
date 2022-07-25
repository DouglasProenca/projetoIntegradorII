package objetos;

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
	private final JMenuItem calc = new JMenuItem("Calculadora",
			new ImageIcon(getClass().getResource("/resources/Calculator-icon.png")));
	private final JMenuItem cale = new JMenuItem("Calendario",
			new ImageIcon(getClass().getResource("/resources/Calendar-icon.png")));
	private final JMenuItem bloco = new JMenuItem("Bloco de notas",
			new ImageIcon(getClass().getResource("/resources/Notepad-Bloc-notes-icon.png")));
	private final JMenu lf = new JMenu("Look And Feel");
	private final JMenuItem sobre = new JMenuItem("Sobre",
			(new ImageIcon(getClass().getResource("/Resources/Info-icon.png"))));
	private final JSeparator sep = new JSeparator();
	private final JMenuItem sair = new JMenuItem("sair",
			new ImageIcon(getClass().getResource("/resources/Log-Out-icon.png")));
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
		
		bloco.addActionListener(this);
		bloco.setActionCommand("BlocoNotas");
		opcoes.add(bloco);
		calc.addActionListener(this);
		calc.setActionCommand("Calculadora");
		opcoes.add(calc);
		cale.addActionListener(this);
		cale.setActionCommand("calendario");
		opcoes.add(cale);

		itemLookAndFeel.addActionListener(this);
		itemLookAndFeel.setActionCommand("LookAndFeel");

		itemFundodeTela.addActionListener(this);
		itemFundodeTela.setActionCommand("FundoTela");

		lf.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/Look-n-feel-icon.png")));
		lf.add(itemFundodeTela);
		lf.add(itemLookAndFeel);
		opcoes.add(lf);
		
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
			if (e.getActionCommand().equals("Sair")) {
				System.exit(0);
			} else if (e.getActionCommand().equals("Calculadora")) {
				Runtime.getRuntime().exec("C:\\windows\\System32\\calc.exe");
			} else if (e.getActionCommand().equals("BlocoNotas")) {
				Runtime.getRuntime().exec("C:\\windows\\System32\\notepad.exe");
			} else if (e.getActionCommand().equals("LookAndFeel")) {
				objetos.LookAndFeel lookAndFeel = new objetos.LookAndFeel();
				TelaPrincipal.dkpaneFundoTela.add(lookAndFeel);
				TelaPrincipal.tbBarraIcone.add(lookAndFeel.getDesktopIcon());
				lookAndFeel.setVisible(true);
				lookAndFeel.addInternalFrameListener(this);
				TelaPrincipal.centralizaForm(lookAndFeel);
			} else if (e.getActionCommand().equals("Sobre")) {
				TelaSobre ts = new TelaSobre("Douglas");
				TelaPrincipal.dkpaneFundoTela.add(ts);
				TelaPrincipal.tbBarraIcone.add(ts.getDesktopIcon());
				ts.setVisible(true);
				TelaPrincipal.centralizaForm(ts);
				ts.addInternalFrameListener(this);
			} else if (e.getActionCommand().equals("FundoTela")) {
				Color newColor = JColorChooser.showDialog(TelaPrincipal.dkpaneFundoTela, "Cor de Fundo",
						new Color(60, 96, 124));
				TelaPrincipal.dkpaneFundoTela.setBackground(newColor);
				PropertiesSystem ps = new PropertiesSystem();
				int r = newColor.getRed();
				int g = newColor.getGreen();
				int b = newColor.getBlue();
				String rgb = r + "," + g + "," + b;
				ps.changeColor(rgb);
			} else if (e.getActionCommand().equals("itemConexaoBD")) {
				view.TelaConexaoBD bd = new view.TelaConexaoBD();
				TelaPrincipal.dkpaneFundoTela.add(bd);
				TelaPrincipal.tbBarraIcone.add(bd.getDesktopIcon());
				bd.setVisible(true);
				TelaPrincipal.centralizaForm(bd);
				bd.addInternalFrameListener(this);
			} else if (e.getActionCommand().equals("calendario")) {
				Calendar calendario = new Calendar();
				TelaPrincipal.dkpaneFundoTela.add(calendario);
				TelaPrincipal.tbBarraIcone.add(calendario.getDesktopIcon());
				calendario.setVisible(true);
				calendario.addInternalFrameListener(this);
				TelaPrincipal.centralizaForm(calendario);
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
