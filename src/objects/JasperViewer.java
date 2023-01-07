package objects;

import java.util.Locale;
import java.util.ResourceBundle;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.swing.JRViewer;
import net.sf.jasperreports.swing.JRViewerToolbar;
import net.sf.jasperreports.view.JRSaveContributor;
import net.sf.jasperreports.view.save.JRDocxSaveContributor;
import net.sf.jasperreports.view.save.JRPdfSaveContributor;
import net.sf.jasperreports.view.save.JRRtfSaveContributor;
import net.sf.jasperreports.view.save.JRSingleSheetXlsSaveContributor;

@SuppressWarnings("serial")
public class JasperViewer extends JRViewer {

    public JasperViewer(JasperPrint jasperPrint) {
        super(jasperPrint);
    }
 
    @Override
    protected JRViewerToolbar createToolbar() {
        JasperToolBar toolbar = new JasperToolBar(super.viewerContext);
        
        Locale locale = viewerContext.getLocale();
        ResourceBundle resBundle = viewerContext.getResourceBundle();
        JRPdfSaveContributor pdf = new JRPdfSaveContributor(locale, resBundle);
        JRRtfSaveContributor rtf = new JRRtfSaveContributor(locale, resBundle);
        JRSingleSheetXlsSaveContributor xls = new JRSingleSheetXlsSaveContributor(locale, resBundle);
        JRDocxSaveContributor docx = new JRDocxSaveContributor(locale, resBundle);
        toolbar.setSaveContributors(new JRSaveContributor[]{pdf, rtf, xls, docx});
        return toolbar;
    }

}
