package objects;

import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class CategoryStackedBarChart extends ChartPanel {
	
	private static final long serialVersionUID = 1L;

	public CategoryStackedBarChart() {
		super(criarChart());
		this.setPreferredSize(new java.awt.Dimension(560, 370));
	}
	
	private static JFreeChart criarChart() {
		DefaultCategoryDataset dataset = createDataset();

		JFreeChart chart = ChartFactory.createStackedBarChart ("", "", "", dataset, PlotOrientation.VERTICAL, true, true,
				false);

		chart.setBackgroundPaint(null);
		chart.getLegend().setVisible(false);

		CategoryPlot plot = (CategoryPlot) chart.getPlot();
		plot.setOutlineVisible(false);
		plot.setBackgroundPaint(null);
		plot.getRangeAxis().setTickLabelPaint(new Color(0, 0, 0));
		plot.getDomainAxis().setTickLabelPaint(new Color(0, 0, 0));
		plot.setDomainGridlinesVisible(false);
		plot.setRangeGridlinesVisible(false);
		plot.getDomainAxis().setTickMarksVisible(false);
		plot.getRangeAxis().setTickMarksVisible(false);
		plot.getDomainAxis().setAxisLineVisible(false);

		return chart;
	}
	
	private static DefaultCategoryDataset createDataset() {
		Connection conexao = ConnectionManager.getInstance().getConexao();
		LinkedHashMap<String, Double> dados = new LinkedHashMap<String, Double>();

		StringBuilder query = new StringBuilder();
		query.append("select f.* ");
		query.append("from ( ");
		query.append("select 1 tipo ");
		query.append("    , m.marca ");
		query.append(" 	, sum(p.valor) valor ");
		query.append(" from rc_marca m ");
		query.append(" inner join rc_produto p ");
		query.append(" 	on p.marca = m.id ");
		query.append(" group by m.marca ");
		query.append(" ");
		query.append(" union all ");
		query.append("  ");
		query.append(" select 2 tipo ");
		query.append(" 	, 'Total' ");
		query.append(" 	, sum(valor)");
		query.append(" from rc_produto p) f ");
		query.append(" order by tipo, valor asc");

		try {
			PreparedStatement instrucaoSQL = conexao.prepareStatement(query.toString());
			ResultSet rs = instrucaoSQL.executeQuery();
			while (rs.next()) {
				dados.put(rs.getString("marca"), rs.getDouble("valor"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		dados.forEach((key, value) -> dataset.addValue(value, "Bar1", key));

		return dataset;
	}
}
