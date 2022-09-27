package br.senac.view;

import br.senac.objects.InternalFrame;
import br.senac.objects.PropertiesSystem;
import java.awt.Dimension;
import java.awt.event.ItemEvent;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;

public class LookAndFeelScreen extends InternalFrame {

    private JPanel painel;
    private JComboBox<String> comboLAF;

    public LookAndFeelScreen() {
        super("Look And Feel", false, true, false, true, 324, 232);
        this.initComponents();
    }

    private void initComponents() {
        this.setLayout(null);
        this.getContentPane().add(getPainel());
    }

    private JPanel getPainel() {
        painel = new JPanel(null);
        painel.setSize(290, 170);
        painel.setLocation(10, 10);
        painel.setBorder(new SoftBevelBorder(BevelBorder.RAISED));
        painel.add(getComboLAF());
        return painel;
    }

    private JComboBox<String> getComboLAF() {
        comboLAF = new JComboBox<>();
        comboLAF.setBounds(70, 70, 150, 26);
        comboLAF.setPreferredSize(new Dimension(150, 26));
        for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
            comboLAF.addItem(info.getName());
        }
        comboLAF.addItem(new FlatDarculaLaf().getName());
        comboLAF.addItem(new FlatLightLaf().getName());
        comboLAF.setSelectedItem(UIManager.getLookAndFeel().getName());
        comboLAF.addItemListener(this);
        return comboLAF;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            String LAFSelected = (String) e.getItem();
            PropertiesSystem.setLookAndFeel(LAFSelected);
            SwingUtilities.updateComponentTreeUI(MainScreen.desktopPane);
            JOptionPane.showMessageDialog(MainScreen.desktopPane,
                    "O Sistema será fechado para atualização de configurações!");
            System.exit(0);
        }
    }

    public static void initLookAndFeel() {
        try {
            String myLAF = PropertiesSystem.getLookAndFeel();
            if (myLAF == null || myLAF.isEmpty()) {
                PropertiesSystem.setLookAndFeel(UIManager.getLookAndFeel().getName());
            } else {
                for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                    if (myLAF.equalsIgnoreCase(info.getName())) {
                        UIManager.setLookAndFeel(info.getClassName());
                        break;
                    }
                    if (myLAF.equals("FlatLaf Darcula")) {
                        UIManager.setLookAndFeel(new FlatDarculaLaf());
                        break;
                    } else if (myLAF.equals("FlatLaf Light")) {
                        UIManager.setLookAndFeel(new FlatLightLaf());
                        break;
                    }
                }
            }
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException ex) {
            JOptionPane.showMessageDialog(MainScreen.desktopPane.getSelectedFrame(), ex.getMessage(),
                    "Aviso de Falha", JOptionPane.ERROR_MESSAGE);
        }
    }
}