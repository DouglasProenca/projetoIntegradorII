package objects;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;

import net.sf.jasperreports.engine.JRChart;
import net.sf.jasperreports.engine.JRChartCustomizer;
import net.sf.jasperreports.engine.JRPropertiesMap;

public class TimeSeriesChart implements JRChartCustomizer {

	private boolean rangeGridlinesVisible = true;
	private boolean domainGridlinesVisible = true;
	private boolean horTickMarksVisible = true;
	private boolean vertTickMarksVisible = true;

	@Override
	public void customize(JFreeChart chart, JRChart jasperChart) {
		JRPropertiesMap pm = jasperChart.getPropertiesMap();

		if (pm != null) {
			if (pm.getProperty("rangeGridlinesVisible") != null) {
				rangeGridlinesVisible = Boolean.parseBoolean(pm.getProperty("rangeGridlinesVisible"));
			}
			if (pm.getProperty("domainGridlinesVisible") != null) {
				domainGridlinesVisible = Boolean.parseBoolean(pm.getProperty("domainGridlinesVisible"));
			}
			if (pm.getProperty("horTickMarksVisible") != null) {
				horTickMarksVisible = Boolean.parseBoolean(pm.getProperty("horTickMarksVisible"));
			}
			if (pm.getProperty("vertTickMarksVisible") != null) {
				vertTickMarksVisible = Boolean.parseBoolean(pm.getProperty("vertTickMarksVisible"));
			}
		}

		XYPlot plot = (XYPlot) chart.getPlot();
		plot.setRangeGridlinesVisible(rangeGridlinesVisible);
		plot.setDomainGridlinesVisible(domainGridlinesVisible);
		plot.getRangeAxis().setTickMarksVisible(horTickMarksVisible);
		plot.getDomainAxis().setTickMarksVisible(vertTickMarksVisible);
	}

}
