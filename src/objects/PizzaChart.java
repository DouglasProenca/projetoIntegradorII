package objects;

import java.awt.Color;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;

import net.sf.jasperreports.engine.JRChart;
import net.sf.jasperreports.engine.JRChartCustomizer;
import net.sf.jasperreports.engine.JRPropertiesMap;

public class PizzaChart implements JRChartCustomizer {

	private boolean linkVisible = false;
	private boolean legendVisible = false;
	private Color shadowPaint = new Color(255, 255, 255);

	@Override
	public void customize(JFreeChart chart, JRChart jasperChart) {
		JRPropertiesMap pm = jasperChart.getPropertiesMap();

		if (pm != null) {
			if (pm.getProperty("linkVisible") != null) {
				linkVisible = Boolean.parseBoolean(pm.getProperty("linkVisible"));
			}
			if (pm.getProperty("legendVisible") != null) {
				legendVisible = Boolean.parseBoolean(pm.getProperty("legendVisible"));
			}
			if (pm.getProperty("shadowPaint") != null) {
				shadowPaint = Utils.convertColor(pm.getProperty("shadowPaint"));
			}
		}

		PiePlot plot = (PiePlot) chart.getPlot();
		plot.setLabelLinksVisible(linkVisible);
		plot.setShadowPaint(shadowPaint); // Sombra do Gráfico
		plot.setLabelBackgroundPaint(new Color(255, 255, 255));
		plot.setLabelOutlinePaint(new Color(255, 255, 255));
		plot.setLabelShadowPaint(new Color(255, 255, 255));

		chart.getLegend().setVisible(legendVisible); // Tirar Legenda
		chart.getLegend().setBorder(0, 0, 0, 0); // seta tamanho das bordas na legenda do gráfico

	}
}
