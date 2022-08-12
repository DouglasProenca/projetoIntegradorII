package br.senac.objects;

import br.senac.view.BrandReportScreen;
import br.senac.view.MainScreen;
import br.senac.view.ProductReportScreen;
import br.senac.view.ReportScreen;
import com.toedter.calendar.JDateChooser;
import java.awt.event.ActionEvent;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;

/**
 *
 * @author Douglas
 */
public class JMenuReport extends Menu {

    private static JMenu uniqueInstance;

    public JMenuReport() {
        super("Relatório", images.imagemReport());
        initComponents();
    }

    private void initComponents() {
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
                JDateChooser jd = new JDateChooser();
                JDateChooser jdf = new JDateChooser();
                String message = "Escolha a data inicial:\n";
                String message2 = "Escolha a data Final:\n";
                Object[] params = {message, jd, message2, jdf};
                JOptionPane.showConfirmDialog(null, params, "Relatorio Gerencial", JOptionPane.PLAIN_MESSAGE);
                InternalFrame rs = ReportScreen.getInstance("Relatorio Gerencial", params);
                MainScreen.desktopPane.add(rs);
                MainScreen.jToolBar.add(rs.getDesktopIcon());
                rs.setVisible(true);
                MainScreen.centralizaForm(rs);
                rs.addInternalFrameListener(this);
                break;
            case "analyticalRep":
                break;
            case "syntheticRep":
                break;
        }
    }
}
