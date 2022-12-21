package br.senac.objects;

import com.toedter.calendar.JDateChooser;
import java.io.InputStream;
import java.sql.Connection;
import java.util.HashMap;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

/**
 *
 * @author Douglas
 */
public class JasperManager extends Thread {

    public JasperPrint gerarManagetmentReport(Object[] params) throws JRException {
        HashMap parametros = new HashMap();
        Connection conn = ConnectionManager.getInstance().getConexao();

        InputStream jasperFile = ClassLoader.getSystemResourceAsStream("jasper/Rel_Managent_Report_Geral.jasper");

        parametros.put("data1", ((JDateChooser) params[1]).getDate());
        parametros.put("data2", ((JDateChooser) params[3]).getDate());
        parametros.put("SUBREPORT_DIR", "jasper/");

        return JasperFillManager.fillReport(jasperFile, parametros, conn);
    }

    public JasperPrint gerarAnalyticalReport(Object[] params) throws JRException {
        HashMap parametros = new HashMap();
        Connection conn = ConnectionManager.getInstance().getConexao();

        InputStream jasperFile = ClassLoader.getSystemResourceAsStream("jasper/Rel_Analytical_Report_Geral.jasper");

        parametros.put("data1", ((JDateChooser) params[1]).getDate());
        parametros.put("data2", ((JDateChooser) params[3]).getDate());

        return JasperFillManager.fillReport(jasperFile, parametros, conn);
    }

    public JasperPrint gerarSyntheticReport(Object[] params) throws JRException {
        HashMap parametros = new HashMap();
        Connection conn = ConnectionManager.getInstance().getConexao();

        InputStream jasperFile = ClassLoader.getSystemResourceAsStream("jasper/Rel_Synthetic_Report_Geral.jasper");

        parametros.put("data1", ((JDateChooser) params[1]).getDate());
        parametros.put("data2", ((JDateChooser) params[3]).getDate());

        return JasperFillManager.fillReport(jasperFile, parametros, conn);
    }
}