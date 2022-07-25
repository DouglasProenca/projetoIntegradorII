package objetos;

import java.awt.Dimension;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatLightLaf;

import view.TelaPrincipal;

public class LookAndFeel extends InternalFrame implements ItemListener {

	private JPanel painel = new JPanel(null);
	private JComboBox<String> comboLAF = new JComboBox<String>();

	public LookAndFeel() {
		super("Look And Feel", false, true, false, true,324, 232);
		initComponents();
		setLayout(null);
	}

	private void initComponents() {
		painel.setSize(290, 170);
		painel.setLocation(10, 10);

		comboLAF.setPreferredSize(new Dimension(150, 26));
		for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
			comboLAF.addItem(info.getName());
		}
		comboLAF.addItem(new FlatDarculaLaf().getName());
		comboLAF.addItem(new FlatLightLaf().getName());
		comboLAF.setSelectedItem(UIManager.getLookAndFeel().getName());
       
		
		painel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
		painel.add(comboLAF);
		comboLAF.setBounds(70, 70, 150, 26);
		comboLAF.addItemListener(this);
		getContentPane().add(painel);
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {
			String LAFSelected = (String) e.getItem();
			PropertiesSystem ps = new PropertiesSystem();
			ps.changeLookAndFeel(LAFSelected);
			SwingUtilities.updateComponentTreeUI(TelaPrincipal.dkpaneFundoTela);
			JOptionPane.showMessageDialog(TelaPrincipal.dkpaneFundoTela,
					"O Sistema ser� fechado para atualiza��o de configura��es!");
			System.exit(0);
		}
	}
	
	public static void initLookAndFeel() {
		try {
			String myLAF = PropertiesSystem.Propriedade.getLookAndFeel();
			if (myLAF == null || myLAF.isEmpty()) {
				PropertiesSystem.Propriedade.setLookAndFeel(UIManager.getLookAndFeel().getName());
			} else {

				for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
					if (myLAF.equalsIgnoreCase(info.getName())) {
						UIManager.setLookAndFeel(info.getClassName());
						break;
					}
					if(myLAF.equals("FlatLaf Darcula")) {
						UIManager.setLookAndFeel(new FlatDarculaLaf());
					} else if(myLAF.equals("FlatLaf Light")) {
						UIManager.setLookAndFeel(new FlatLightLaf());
					}
					
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} 
	}
}
