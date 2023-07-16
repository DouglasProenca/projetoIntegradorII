package objects;

import view.BrandReportScreen;
import view.CategoryReportScreen;
import view.ProductReportScreen;
import view.ReportScreen;
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

@SuppressWarnings("serial")
public class MenuReport extends Menu {

	private JMenuItem category;
	private ReportScreen reportScreen;
	private JMenuItem product;
	private JMenuItem syntheticRep;
	private JMenu report;
	private JMenuItem marca;
	private JMenuItem analyticalRep;

	protected MenuReport() {
		super("Relatório", Images.REPORT.getImage());
		this.initComponents();
	}

	private void initComponents() {
		this.setMnemonic('R');
		this.add(getBrand());
		this.add(getCategory());
		this.add(getProduct());
		this.add(getMenuReport());
	}

	private JMenu getMenuReport() {
		report = new JMenu("Relatórios");
		report.setIcon(Images.MANAGEMENT.getImage());
		report.add(getAnalyticalRep());
		report.add(getSyntheticRep());
		report.add(new JSeparator());
		report.add(getManagementRep());
		return report;
	}

	private JMenuItem getCategory() {
		category = new JMenuItem("Categoria", Images.CATEGORY.getImage());
		category.addActionListener(this);
		category.setActionCommand("category");
		return category;
	}

	private JMenuItem getSyntheticRep() {
		syntheticRep = new JMenuItem("Relatório Sintetico");
		syntheticRep.addActionListener(this);
		syntheticRep.setActionCommand("syntheticRep");
		return syntheticRep;
	}

	private JMenuItem getAnalyticalRep() {
		analyticalRep = new JMenuItem("Relatório Analitico");
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
		product = new JMenuItem("Produto", Images.PRODUCT.getImage());
		product.addActionListener(this);
		product.setActionCommand("product");
		return product;
	}

	private JMenuItem getBrand() {
		marca = new JMenuItem("Marca", Images.BRAND.getImage());
		marca.addActionListener(this);
		marca.setActionCommand("brand");
		return marca;
	}

	private Date getMesAnt() {
		LocalDateTime data = LocalDateTime.now();
		LocalDateTime ultimoDiaDoMesAnterior = data.minusMonths(1).with(TemporalAdjusters.lastDayOfMonth());
		return Date.from(ultimoDiaDoMesAnterior.atZone(ZoneId.systemDefault()).toInstant());
	}

	private Object[] getParams() {
		JDateChooser[] dtChooser = new JDateChooser[] { new JDateChooser(getMesAnt()), new JDateChooser(new Date()) };
		Object[] params = { "Escolha a data inicial:\n", dtChooser[0], "Escolha a data Final:\n", dtChooser[1] };
		return params;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "brand":
			BrandReportScreen br = new BrandReportScreen();
			br.setVisible(true);
			break;
		case "product":
			ProductReportScreen pr = new ProductReportScreen();
			pr.setVisible(true);
			break;
		case "managementRep":
			reportScreen = new ReportScreen("Relatório Gerencial", getParams());
			reportScreen.setVisible(true);
			break;
		case "analyticalRep":
			Object vetor[] = getParams();
			if (JOptionPane.showConfirmDialog(null, vetor, "Relatório Analitíco", JOptionPane.PLAIN_MESSAGE) == 0) {
				reportScreen = new ReportScreen("Relatório Analitíco", vetor);
				reportScreen.setVisible(true);
			}
			break;
		case "syntheticRep":
			Object vet[] = getParams();
			if (JOptionPane.showConfirmDialog(null, vet, "Relatório Sintetico", JOptionPane.PLAIN_MESSAGE) == 0) {
				reportScreen = new ReportScreen("Relatório Sintetico", vet);
				reportScreen.setVisible(true);
			}
			break;
		case "category":
			CategoryReportScreen ct = new CategoryReportScreen();
			ct.setVisible(true);
			break;
		}
	}
}
