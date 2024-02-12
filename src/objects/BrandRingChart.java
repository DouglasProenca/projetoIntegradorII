package objects;

import java.awt.Dimension;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.RingPlot;
import org.jfree.data.general.DefaultPieDataset;

public class BrandRingChart extends ChartPanel {


	private static final long serialVersionUID = 1L;

	public BrandRingChart() {
		super(criarChart());
		this.setPreferredSize(new Dimension(40, 40));
	}

	private static JFreeChart criarChart() {
		JFreeChart chart = ChartFactory.createRingChart("", createDataset(), true, false, false);
		chart.setBackgroundPaint(null);
		chart.getLegend().setVisible(false);

		RingPlot plot = (RingPlot) chart.getPlot();
		plot.setCircular(true);
		plot.setSectionOutlinesVisible(false);
		plot.setOutlineVisible(false);
		plot.setSeparatorsVisible(false);
		plot.setSectionDepth(0.4);
		plot.setBackgroundPaint(null);
		plot.setShadowPaint(null);
		plot.setLabelBackgroundPaint(null);
		plot.setLabelOutlinePaint(null);
		plot.setLabelShadowPaint(null);

		return chart;
	}

	private static DefaultPieDataset createDataset() {
		Connection conexao = ConnectionManager.getInstance().getConexao();
		HashMap<String, Double> dados = new HashMap<String, Double>();

		StringBuilder query = new StringBuilder();
		query.append("select p.paisNome ");
		query.append(", cast(count(m.pais) as float) / (select cast(count(p1.pais) as float) from rc_marca p1) * 100 as porcentagem ");
		query.append("from rc_marca m ");
		query.append("inner join rc_pais p ");
		query.append("on p.paisId = m.pais ");
		query.append(" group by p.paisNome ");
		query.append(" order by cast(count(m.pais) as float) / (select cast(count(p1.pais) as float) from rc_marca p1) * 100 desc ");

		try {
			PreparedStatement instrucaoSQL = conexao.prepareStatement(query.toString());
			ResultSet rs = instrucaoSQL.executeQuery();
			while (rs.next()) {
				dados.put(rs.getString("paisNome"), rs.getDouble("porcentagem"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		DefaultPieDataset dataset = new DefaultPieDataset();

		dados.forEach((key, value) -> dataset.setValue(key, value));

		return dataset;
	}
}
