package br.senac.objects;

import br.senac.view.BrandReportScreen;
import br.senac.view.CategoryReportScreen;
import br.senac.view.MainScreen;
import br.senac.view.ProductReportScreen;
import br.senac.view.ReportScreen;
import com.toedter.calendar.JDateChooser;
import java.awt.event.ActionEvent;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;
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
    private JMenuItem category;
    private ReportScreen reportScreen;

    private JMenuReport() {
        super("Relatório", images.getInstance().imagemReport());
        initComponents();
    }

    private void initComponents() {
        this.setMnemonic('R');
        this.add(getBrand());
        this.add(getCategory());
        this.add(getProduct());
        this.add(getMenuReport());
    }

    private JMenu getMenuReport() {
        JMenu report = new JMenu("Relatórios");
        report.setIcon(images.getInstance().imagemManagement());
        report.add(getAnalyticalRep());
        report.add(getSyntheticRep());
        report.add(new JSeparator());
        report.add(getManagementRep());
        return report;
    }

    private JMenuItem getCategory() {
        category = new JMenuItem("Categoria", images.getInstance().category());
        category.addActionListener(this);
        category.setActionCommand("category");
        return category;
    }

    private JMenuItem getSyntheticRep() {
        JMenuItem syntheticRep = new JMenuItem("Relatório Sintetico");
        syntheticRep.addActionListener(this);
        syntheticRep.setActionCommand("syntheticRep");
        return syntheticRep;
    }

    private JMenuItem getAnalyticalRep() {
        JMenuItem analyticalRep = new JMenuItem("Relatório Analitico");
        analyticalRep.addActionListener(this);
        analyticalRep.setActionCommand("analyticalRep");
        return analyticalRep;
    }

    private JMenuItem getManagementRep() {
        JMenuItem managementRep = new JMenuItem("Relatório Gerencial");
        managementRep.addActionListener(this);
        managementRep.setActionCommand("managementRep");
        return managementRep;
    }

    private JMenuItem getProduct() {
        JMenuItem product = new JMenuItem("Produto", images.getInstance().imagemProduct());
        product.addActionListener(this);
        product.setActionCommand("product");
        return product;
    }

    private JMenuItem getBrand() {
        JMenuItem marca = new JMenuItem("Marca", images.getInstance().imagemBrand());
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

    private Object[] getParams() {
        LocalDateTime data = LocalDateTime.now();
        LocalDateTime ultimoDiaDoMesAnterior = data.minusMonths(1).with(TemporalAdjusters.lastDayOfMonth());
        Date mes_ant = Date.from(ultimoDiaDoMesAnterior.atZone(ZoneId.systemDefault()).toInstant());
        JDateChooser jd = new JDateChooser(mes_ant);
        JDateChooser jdf = new JDateChooser(new Date());
        String message = "Escolha a data inicial:\n";
        String message2 = "Escolha a data Final:\n";
        Object[] params = {message, jd, message2, jdf};
        return params;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "brand":
                BrandReportScreen br = BrandReportScreen.getInstance();
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
                int resposta = JOptionPane.showConfirmDialog(null, getParams(), "Relatório Gerencial", JOptionPane.PLAIN_MESSAGE);
                if (resposta == 0) {
                    reportScreen = new ReportScreen("Relatório Gerencial", getParams());
                    MainScreen.desktopPane.add(reportScreen);
                    MainScreen.jToolBar.add(reportScreen.getDesktopIcon());
                    reportScreen.setVisible(true);
                    MainScreen.centralizaForm(reportScreen);
                    reportScreen.addInternalFrameListener(this);
                }
                break;
            case "analyticalRep":
                int resposta2 = JOptionPane.showConfirmDialog(null, getParams(), "Relatório Analitíco", JOptionPane.PLAIN_MESSAGE);
                if (resposta2 == 0) {
                    reportScreen = new ReportScreen("Relatório Analitíco", getParams());
                    MainScreen.desktopPane.add(reportScreen);
                    MainScreen.jToolBar.add(reportScreen.getDesktopIcon());
                    reportScreen.setVisible(true);
                    MainScreen.centralizaForm(reportScreen);
                    reportScreen.addInternalFrameListener(this);
                }
                break;
            case "syntheticRep":
                int resposta3 = JOptionPane.showConfirmDialog(null, getParams(), "Relatório Sintetico", JOptionPane.PLAIN_MESSAGE);
                if (resposta3 == 0) {
                    reportScreen = new ReportScreen("Relatório Sintetico", getParams());
                    MainScreen.desktopPane.add(reportScreen);
                    MainScreen.jToolBar.add(reportScreen.getDesktopIcon());
                    reportScreen.setVisible(true);
                    MainScreen.centralizaForm(reportScreen);
                    reportScreen.addInternalFrameListener(this);
                }
                break;
            case "category":
                CategoryReportScreen ct = new CategoryReportScreen();
                MainScreen.desktopPane.add(ct);
                MainScreen.jToolBar.add(ct.getDesktopIcon());
                ct.setVisible(true);
                MainScreen.centralizaForm(ct);
                ct.addInternalFrameListener(this);
                break;
        }
    }
}
