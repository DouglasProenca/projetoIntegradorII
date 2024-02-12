package view;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import objects.BrandRingChart;
import objects.CategoryStackedBarChart;
import objects.InternalFrame;
import objects.ProductWatterfallChart;

public class DashboardScreen extends InternalFrame {

	private static final long serialVersionUID = 1L;
	private JPanel panelBrandRingChart;
	private JPanel panelDonuts;
	private JPanel panelProductWatterfallChart;
	private JPanel panelTotalMoney;
	private JPanel panelTotalProduct;
	private JLabel defaultLabelTotal;
	private TitledBorder defaultTitledBorder;

	public DashboardScreen() {
		super("Dashboard", true, true, true, true, 707, 400);
		this.setLayout(new GridBagLayout());

		// Configura as restrições do GridBagLayout para os componentes
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 0.25; // 25% da largura para os painéis à esquerda
		gbc.weighty = 0.25; // 25% da altura para cada painel
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		add(getPanelTotalMoney(), gbc);

		gbc.gridy = 2;
		add(getPanelTotalProduct(), gbc);

		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.weightx = 0.75; // 75% da largura para os gráficos à direita
		gbc.gridheight = 2; // Os gráficos ocupam duas linhas
		add(getPanelDonuts(), gbc);

		gbc.gridx = 2;
		add(getPanelBrandRingChart(), gbc);

		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.weightx = 1; // Restaura o peso da largura para os gráficos
		gbc.gridwidth = 2; // Os gráficos ocupam duas colunas
		gbc.gridheight = 1; // A linha do gráfico de linha
		add(getPanelProductWatterfallChart(), gbc);

	}

	private JPanel getPanelBrandRingChart() {
		panelBrandRingChart = new JPanel(new GridLayout(1, 1));
		panelBrandRingChart.add(new BrandRingChart());
		return panelBrandRingChart;
	}

	private JPanel getPanelDonuts() {
		panelDonuts = new JPanel(new GridLayout(1, 1));
		panelDonuts.add(new CategoryStackedBarChart());
		return panelDonuts;
	}

	private JPanel getPanelProductWatterfallChart() {
		panelProductWatterfallChart = new JPanel(new GridLayout(1, 1));
		panelProductWatterfallChart.add(new ProductWatterfallChart());
		return panelProductWatterfallChart;
	}

	private JPanel getPanelTotalMoney() {
		panelTotalMoney = new JPanel(new GridLayout(1, 1));
		panelTotalMoney.add(getDefaultLabelTotal("R$ 1320,50","Total Valor"));
		return panelTotalMoney;
	}

	private JPanel getPanelTotalProduct() {
		panelTotalProduct = new JPanel(new GridLayout(1, 1));
		panelTotalProduct.add(getDefaultLabelTotal("23","Total Produtos"));
		return panelTotalProduct;
	}
	
	private JLabel getDefaultLabelTotal(String text,String title) {
		defaultLabelTotal = new JLabel(text);
		defaultLabelTotal.setFont(new Font("Arial", 1, 30));
		defaultLabelTotal.setBorder(getDefaultTitledBorder(title));
		defaultLabelTotal.setHorizontalAlignment(SwingConstants.CENTER);
		return defaultLabelTotal;
	}
	
	private TitledBorder getDefaultTitledBorder(String text) {
		defaultTitledBorder = new TitledBorder(text);
		defaultTitledBorder.setTitleFont(new Font("Arial", 1, 15));
		defaultTitledBorder.setTitleJustification(TitledBorder.CENTER);
		return defaultTitledBorder;
	}
}