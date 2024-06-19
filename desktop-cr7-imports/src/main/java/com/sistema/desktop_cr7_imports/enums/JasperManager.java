package com.sistema.desktop_cr7_imports.enums;

import com.sistema.desktop_cr7_imports.DesktopCr7ImportsApplication;
import com.sistema.desktop_cr7_imports.interfaces.JasperInterface;
import com.sistema.desktop_cr7_imports.objects.ConnectionManager;
import com.toedter.calendar.JDateChooser;


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

			parametros.put("SUBREPORT_DIR", DesktopCr7ImportsApplication.class.getResource("/jasper/").toString());

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