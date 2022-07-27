package br.senac.geral;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;

/**
 *
 * @author Douglas
 */
public class JMenuReport extends JMenu implements ActionListener {

    private static JMenu uniqueInstance;

    public JMenuReport() {
        super("Relatório");
        initComponents();
    }

    private void initComponents() {
        this.setIcon(images.imagemReport());
        this.setMnemonic('R');

        this.add(getBrand());
        this.add(getProduct());
        this.add(getMenuReport());
    }

    private JMenu getMenuReport() {
        JMenu report = new JMenu("Relatórios");
        report.setIcon(images.imagemManagement());
        report.add(getAnalyticalRep());
        report.add(getSyntheticRep());
        report.add(new JSeparator());
        report.add(getManagementRep());
        return report;
    }

    private JMenuItem getSyntheticRep() {
        JMenuItem syntheticRep = new JMenuItem("Relatorio Sintetico");
        syntheticRep.addActionListener(this);
        syntheticRep.setActionCommand("syntheticRep");
        return syntheticRep;
    }

    private JMenuItem getAnalyticalRep() {
        JMenuItem analyticalRep = new JMenuItem("Relatorio Analitico");
        analyticalRep.addActionListener(this);
        analyticalRep.setActionCommand("analyticalRep");
        return analyticalRep;
    }

    private JMenuItem getManagementRep() {
        JMenuItem managementRep = new JMenuItem("Relatorio Gerencial");
        managementRep.addActionListener(this);
        managementRep.setActionCommand("managementRep");
        return managementRep;
    }

    private JMenuItem getProduct() {
        JMenuItem product = new JMenuItem("Produto", images.imagemProduct());
        product.addActionListener(this);
        product.setActionCommand("product");
        return product;
    }

    private JMenuItem getBrand() {
        JMenuItem marca = new JMenuItem("Marca", images.imagemBrand());
        marca.addActionListener(this);
        marca.setActionCommand("brand");
        return marca;
    }

    public static synchronized JMenu getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new JMenuReport();
        }
        return uniqueInstance;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "brand":
                break;
            case "product":
                break;
            case "managementRep":
                break;
            case "analyticalRep":
                break;
            case "syntheticRep":
                break;
        }
    }
}
