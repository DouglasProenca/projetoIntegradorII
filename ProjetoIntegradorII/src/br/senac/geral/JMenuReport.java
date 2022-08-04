package br.senac.geral;

import br.senac.view.BrandReportScreen;
import br.senac.view.MainScreen;
import br.senac.view.ProductReportScreen;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

/**
 *
 * @author Douglas
 */
public class JMenuReport extends JMenu implements ActionListener, InternalFrameListener {

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
                BrandReportScreen br = new BrandReportScreen();
                MainScreen.desktopPane.add(br);
                MainScreen.jToolBar.add(br.getDesktopIcon());
                br.setVisible(true);
                MainScreen.centralizaForm(br);
                br.addInternalFrameListener(this);
                break;
            case "product":
                ProductReportScreen pr = new ProductReportScreen();
                MainScreen.desktopPane.add(pr);
                MainScreen.jToolBar.add(pr.getDesktopIcon());
                pr.setVisible(true);
                MainScreen.centralizaForm(pr);
                pr.addInternalFrameListener(this);
                break;
            case "managementRep":
                break;
            case "analyticalRep":
                break;
            case "syntheticRep":
                break;
        }
    }

    @Override
    public void internalFrameOpened(InternalFrameEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void internalFrameClosing(InternalFrameEvent e) {
        JInternalFrame frame = (JInternalFrame) e.getSource();
        frame.setClosable(true);
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
        MainScreen.jToolBar.add(frame.getDesktopIcon());
    }

    @Override
    public void internalFrameDeiconified(InternalFrameEvent e) {
        JInternalFrame frame = (JInternalFrame) e.getSource();
        MainScreen.jToolBar.remove(frame.getDesktopIcon());
        frame.setIconifiable(true);
        MainScreen.desktopPane.add(frame);
    }

    @Override
    public void internalFrameActivated(InternalFrameEvent e) {
        JInternalFrame frame = (JInternalFrame) e.getSource();
        MainScreen.jToolBar.add(frame.getDesktopIcon());
    }

    @Override
    public void internalFrameDeactivated(InternalFrameEvent e) {
        JInternalFrame frame = (JInternalFrame) e.getSource();
        MainScreen.jToolBar.add(frame.getDesktopIcon());
    }
}