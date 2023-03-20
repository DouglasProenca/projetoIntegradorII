package objects;

import java.awt.Color;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.plot.CategoryPlot;

import net.sf.jasperreports.engine.JRChart;
import net.sf.jasperreports.engine.JRChartCustomizer;
import net.sf.jasperreports.engine.JRPropertiesMap;

public class BarChart implements JRChartCustomizer {

	private boolean gridLinesVisible = true;

	@Override
	public void customize(JFreeChart chart, JRChart jasperChart) {
		JRPropertiesMap pm = jasperChart.getPropertiesMap();

		if (pm != null) {
			if (pm.getProperty("gridLinesVisible") != null) {
				gridLinesVisible = Boolean.parseBoolean(pm.getProperty("gridLinesVisible"));
			}
		}

		CategoryPlot plot = (CategoryPlot) chart.getPlot();
		plot.setRangeGridlinesVisible(gridLinesVisible);
		plot.getRangeAxis().setAxisLinePaint(new Color(255, 255, 255));
		plot.getDomainAxis().setAxisLinePaint(new Color(255, 255, 255));
		plot.setRangeAxisLocation(AxisLocation.BOTTOM_OR_LEFT);
	}
}
