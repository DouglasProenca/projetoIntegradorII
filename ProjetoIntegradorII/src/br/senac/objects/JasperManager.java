package br.senac.objects;

import com.toedter.calendar.JDateChooser;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
public class JasperManager {

    public static JasperPrint gerarManagetmentReport(Object[] params) {
        HashMap parametros = new HashMap();
        Connection conn = ConnectionManager.getConexao();

        InputStream jasperFile = null;
        JasperPrint impressao = null;
        try {
            jasperFile = new FileInputStream("src/jasper/Rel_Managent_Report_Geral.jasper");

            parametros.put("cabecalho", "resources/CallCenter.jpg");
            parametros.put("data1", ((JDateChooser)params[1]).getDate());
            parametros.put("data2", ((JDateChooser)params[3]).getDate());
            

            impressao = JasperFillManager.fillReport(jasperFile, parametros, conn);
        } catch (JRException | FileNotFoundException ex) {
            Logger.getLogger(JasperManager.class.getName()).log(Level.SEVERE, null, ex);
        }

        return impressao;
    }
}
