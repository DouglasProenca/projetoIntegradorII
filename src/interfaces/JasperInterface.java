package interfaces;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;

public interface JasperInterface {

	/**
	 * 
	 * @param params
	 * @return JasperPrint
	 * @throws JRException
	 */
	public JasperPrint getReport(Object[] params) throws JRException;
}
