package objects;

import com.toedter.calendar.JDateChooser;

import interfaces.JasperInterface;

import java.io.InputStream;
import java.sql.Connection;
import java.util.HashMap;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

public enum JasperManager implements JasperInterface {

	MANAGETMENT {

		@Override
		public JasperPrint getReport(Object[] params) throws JRException {
			HashMap<String, Object> parametros = new HashMap<String, Object>();
			Connection conn = ConnectionManager.getInstance().getConexao();

			InputStream jasperFile = ClassLoader.getSystemResourceAsStream("jasper/Rel_Managent_Report_Geral.jasper");

			parametros.put("SUBREPORT_DIR", "jasper/");

			return JasperFillManager.fillReport(jasperFile, parametros, conn);
		}

	},
	ANAlYTICAL {
		@Override
		public JasperPrint getReport(Object[] params) throws JRException {
			HashMap<String, Object> parametros = new HashMap<String, Object>();
			Connection conn = ConnectionManager.getInstance().getConexao();

			InputStream jasperFile = ClassLoader.getSystemResourceAsStream("jasper/Rel_Analytical_Report_Geral.jasper");

			parametros.put("data1", ((JDateChooser) params[1]).getDate());
			parametros.put("data2", ((JDateChooser) params[3]).getDate());

			return JasperFillManager.fillReport(jasperFile, parametros, conn);
		}
	},
	SYNTHETIC {
		@Override
		public JasperPrint getReport(Object[] params) throws JRException {
			HashMap<String, Object> parametros = new HashMap<String, Object>();
			Connection conn = ConnectionManager.getInstance().getConexao();

			InputStream jasperFile = ClassLoader.getSystemResourceAsStream("jasper/Rel_Synthetic_Report_Geral.jasper");

			parametros.put("data1", ((JDateChooser) params[1]).getDate());
			parametros.put("data2", ((JDateChooser) params[3]).getDate());

			return JasperFillManager.fillReport(jasperFile, parametros, conn);
		}
	};
}