package br.senac.geral;

import java.awt.Dimension;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 *
 * @author Douglas
 */
public class LookAndFeel extends JFrame implements ItemListener {

    private final JComboBox<String> comboLAF = new JComboBox<>();
    private final JPanel painel = new JPanel(null);

    public LookAndFeel() {
        super("Look And Feel");
        setLayout(null);
        initialize();
    }

    private void initialize() {
        setSize(324, 232);
        setResizable(false);

        painel.setSize(290, 170);
        painel.setLocation(10, 10);

        painel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        comboLAF.setBounds(70, 70, 150, 26);
        comboLAF.addItemListener(this);

        comboLAF.setPreferredSize(new Dimension(150, 26));
        for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
            comboLAF.addItem(info.getName());
        }
        comboLAF.setSelectedItem(UIManager.getLookAndFeel().getName());

        painel.add(comboLAF);
        getContentPane().add(painel);
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            String LAFSelected = (String) e.getItem();
            System.out.println(LAFSelected);
            //PropertiesSystem ps = new PropertiesSystem();
            //ps.changeLookAndFeel(LAFSelected);
            SwingUtilities.updateComponentTreeUI(LookAndFeel.this);
            //JOptionPane.showMessageDialog(LookAndFeel.this,
            //"O Sistema será fechado para atualização de configurações!");
            //System.exit(0);
        }
    }
}
