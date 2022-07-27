package objetos;

import br.senac.view.CalendarScreen;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JColorChooser;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JSeparator;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

import view.TelaPrincipal;
import view.TelaSobre;

/**
 *
 * @author Douglas
 */
public class MenuPopup extends JPopupMenu implements ActionListener, InternalFrameListener {

	public MenuPopup() {
		super();
		initialize();
	}

	private final JMenu banco = new JMenu("Banco de Dados");
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

	private void initialize() {
		banco.addActionListener(this);
		banco.setIcon(new ImageIcon(getClass().getResource("/Resources/BancoDados-icon.png")));
		add(banco);
		itemConexaoBD.addActionListener(this);
		itemConexaoBD.setActionCommand("itemConexaoBD");
		banco.add(itemConexaoBD);
		bloco.addActionListener(this);
		bloco.setActionCommand("BlocoNotas");
		add(bloco);
		calc.addActionListener(this);
		calc.setActionCommand("Calculadora");
		add(calc);
		cale.addActionListener(this);
		cale.setActionCommand("calendario");
		add(cale);

		itemLookAndFeel.addActionListener(this);
		itemLookAndFeel.setActionCommand("LookAndFeel");

		itemFundodeTela.addActionListener(this);
		itemFundodeTela.setActionCommand("FundoTela");

		lf.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/Look-n-feel-icon.png")));
		lf.add(itemFundodeTela);
		lf.add(itemLookAndFeel);
		add(lf);
		sobre.addActionListener(this);
		sobre.setActionCommand("Sobre");
		add(sobre);
		add(sep);
		sair.addActionListener(this);
		sair.setActionCommand("Sair");
		add(sair);
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
				CalendarScreen calendario = new CalendarScreen();
				TelaPrincipal.dkpaneFundoTela.add(calendario);
				TelaPrincipal.tbBarraIcone.add(calendario.getDesktopIcon());
				calendario.setVisible(true);
				calendario.addInternalFrameListener(this);
				TelaPrincipal.centralizaForm(calendario);
			}
		} catch (IOException ex) {
			Logger.getLogger(MenuPopup.class.getName()).log(Level.SEVERE, null, ex);
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
}
