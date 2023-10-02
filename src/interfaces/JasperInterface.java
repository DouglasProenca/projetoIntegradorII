package interfaces;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;

public interface JasperInterface {

	public JasperPrint getReport(Object[] params) throws JRException;
}
