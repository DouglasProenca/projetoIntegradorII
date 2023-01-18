package objects;

import view.MainScreen;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import jxl.Sheet;
import jxl.Workbook;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.VerticalAlignment;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

/**
 *
 * @author Douglas
 */
public class Excel {

    private WritableCellFormat cellFormat;

    private WritableFont getFontbol(boolean bold) {
        return new WritableFont(WritableFont.ARIAL, 10, bold ? WritableFont.BOLD : WritableFont.NO_BOLD);
    }

    private WritableCellFormat getCellFormat(boolean bold, boolean color) throws WriteException {
        cellFormat = new WritableCellFormat(getFontbol(bold));
        cellFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
        cellFormat.setVerticalAlignment(VerticalAlignment.JUSTIFY);
        cellFormat.setBackground(color ? Colour.AQUA : Colour.WHITE);
        return cellFormat;
    }

    public boolean exportExcel(File arquivo, ArrayList<?> dados, String Titulo, JTable table) {
        try {
            //Instaciando a classe que gera o novo arquivo do excel
            WritableWorkbook workbook = Workbook.createWorkbook(new File(arquivo.toString() + ".xls"));

            //criando uma nova planilha
            WritableSheet sheet = workbook.createSheet(Titulo, 0);

            //Para os Titulos
            String titulos[] = new String[table.getColumnCount()];
            //part1
            for (int i = 0; i < table.getColumnCount(); i++) {
                titulos[i] = table.getColumnName(i);
            }

            for (int i = 0; i < titulos.length; i++) {
                Label labelTitulo = new Label(i, 0, titulos[i]);
                sheet.addCell(labelTitulo);
                sheet.setColumnView(i, titulos[i].length() * 3);
                labelTitulo.setCellFormat(getCellFormat(true, true));
            }
            //Para os Dados
            for (int i = 0; i < dados.size(); i++) {
                String[] aux = dados.get(i).toString().split(";");
                for (int j = 0; j < aux.length; j++) {
                    Label label1 = new Label(j, i+1, aux[j]);
                    sheet.addCell(label1);
                }
            }

            //escrevendo o arquivo em disco
            workbook.write();

            //Fechando a IO
            workbook.close();
        } catch (IOException | WriteException ex) {
            JOptionPane.showMessageDialog(MainScreen.desktopPane.getSelectedFrame(), ex.getMessage(),
                    "Aviso de Falha", JOptionPane.ERROR_MESSAGE);
            return (false);
        }
        return (true);
    }

    public String[][] importExcel(File arquivo, JTable table) {
        String colunas[][] = null;
        try {
            Workbook workbook = Workbook.getWorkbook(arquivo);
            Sheet sheet = workbook.getSheet(0);
            colunas = new String[sheet.getRows()][table.getColumnCount()];

            for (int z = 1; z < sheet.getRows(); z++) {
                for (int i = 0; i < table.getColumnCount(); i++) {
                    colunas[z][i] = sheet.getCell(i, z).getContents();
                }
            }

            workbook.close();
        } catch (IOException | BiffException ex) {
            JOptionPane.showMessageDialog(MainScreen.desktopPane.getSelectedFrame(), ex.getMessage(),
                    "Aviso de Falha", JOptionPane.ERROR_MESSAGE);
        }
        return colunas;
    }
}
