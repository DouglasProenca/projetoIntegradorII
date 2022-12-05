package br.senac.objects;

import com.toedter.calendar.JDateChooser;
import java.io.InputStream;
import java.sql.Connection;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

/**
 *
 * @author Douglas
 */
public class JasperManager extends Thread {

    public JasperPrint gerarManagetmentReport(Object[] params) {
        HashMap parametros = new HashMap();
        Connection conn = ConnectionManager.getInstance().getConexao();

        JasperPrint impressao = null;
        InputStream jasperFile = ClassLoader.getSystemResourceAsStream("jasper/Rel_Managent_Report_Geral.jasper");

        parametros.put("cabecalho", "resources/CallCenter.jpg");
        parametros.put("data1", ((JDateChooser) params[1]).getDate());
        parametros.put("data2", ((JDateChooser) params[3]).getDate());
        parametros.put("SUBREPORT_DIR", "jasper/");
        
        try {
            impressao = JasperFillManager.fillReport(jasperFile, parametros, conn);
        } catch (JRException ex) {
            Logger.getLogger(JasperManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return impressao;
    }

    public JasperPrint gerarAnalyticalReport(Object[] params) {
        HashMap parametros = new HashMap();
        Connection conn = ConnectionManager.getInstance().getConexao();

        JasperPrint impressao = null;
        InputStream jasperFile = ClassLoader.getSystemResourceAsStream("jasper/Rel_Analytical_Report_Geral.jasper");

        parametros.put("data1", ((JDateChooser) params[1]).getDate());
        parametros.put("data2", ((JDateChooser) params[3]).getDate());
        try {
            impressao = JasperFillManager.fillReport(jasperFile, parametros, conn);
        } catch (JRException ex) {
            Logger.getLogger(JasperManager.class.getName()).log(Level.SEVERE, null, ex);
        }

        return impressao;
    }

    public JasperPrint gerarSyntheticReport(Object[] params) {
        HashMap parametros = new HashMap();
        Connection conn = ConnectionManager.getInstance().getConexao();

        JasperPrint impressao = null;
        InputStream jasperFile = ClassLoader.getSystemResourceAsStream("jasper/Rel_Synthetic_Report_Geral.jasper");

        parametros.put("data1", ((JDateChooser) params[1]).getDate());
        parametros.put("data2", ((JDateChooser) params[3]).getDate());
        try {
            impressao = JasperFillManager.fillReport(jasperFile, parametros, conn);
        } catch (JRException ex) {
            Logger.getLogger(JasperManager.class.getName()).log(Level.SEVERE, null, ex);
        }

        return impressao;
    }
}