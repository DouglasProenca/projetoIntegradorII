package br.senac.geral;

import br.senac.model.Marca;
import br.senac.view.MainScreen;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import jxl.Workbook;
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

    public static boolean BrandExcel(File arquivo, ArrayList<Marca> lista) {
        boolean status = true;
        try {

            WritableFont fontbol = new WritableFont(WritableFont.ARIAL, 10);
            fontbol.setBoldStyle(WritableFont.BOLD);
            WritableCellFormat cellFormat = new WritableCellFormat(fontbol);
            jxl.format.Colour bckcolor = jxl.format.Colour.AQUA;
            cellFormat.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN);
            cellFormat.setVerticalAlignment(jxl.format.VerticalAlignment.JUSTIFY);
            WritableCellFormat cellFormatt = new WritableCellFormat();
            cellFormatt.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN);

            cellFormat.setBackground(bckcolor);

            //Instaciando a classe que gera o novo arquivo do excel
            WritableWorkbook workbook = Workbook.createWorkbook(new File(arquivo.toString() + ".xls"));

            //criando uma nova planilha
            WritableSheet sheet = workbook.createSheet("Marcas", 0);

            //col, lin
            Label labelTitulo = new Label(0, 0, "ID");
            sheet.addCell(labelTitulo);
            sheet.setColumnView(0, 12);
            labelTitulo.setCellFormat(cellFormat);

            Label labelTitulo2 = new Label(1, 0, "Marca");
            sheet.addCell(labelTitulo2);
            sheet.setColumnView(1, 45);
            labelTitulo2.setCellFormat(cellFormat);

            Label labelTitulo3 = new Label(2, 0, "Pais");
            sheet.addCell(labelTitulo3);
            sheet.setColumnView(2, 15);
            labelTitulo3.setCellFormat(cellFormat);

            Label labelTitulo4 = new Label(3, 0, "Data");
            sheet.addCell(labelTitulo4);
            sheet.setColumnView(3, 11);
            labelTitulo4.setCellFormat(cellFormat);

            Label labelTitulo5 = new Label(4, 0, "User");
            sheet.addCell(labelTitulo5);
            sheet.setColumnView(4, 10);
            labelTitulo5.setCellFormat(cellFormat);

            int contador = 1;
            for (Marca p : lista) {

                Label label1 = new Label(0, contador, String.valueOf(p.getId()));
                sheet.addCell(label1);
                label1.setCellFormat(cellFormatt);
                Label label2 = new Label(1, contador, p.getMarca());
                sheet.addCell(label2);
                label2.setCellFormat(cellFormatt);
                Label label3 = new Label(2, contador, p.getPais());
                sheet.addCell(label3);
                label3.setCellFormat(cellFormatt);
                Label label4 = new Label(3, contador, p.getDate().toString());
                sheet.addCell(label4);
                label4.setCellFormat(cellFormatt);
                Label label5 = new Label(4, contador, p.getUser());
                sheet.addCell(label5);
                label5.setCellFormat(cellFormatt);

                contador++;
            }

            //escrevendo o arquivo em disco
            workbook.write();

            //Fechando a IO
            workbook.close();
        } catch (IOException | WriteException ex) {
            JOptionPane.showMessageDialog(MainScreen.desktopPane.getSelectedFrame(), ex.getMessage(),
                    "Aviso de Falha", JOptionPane.ERROR_MESSAGE);
        }

        return status;
    }
}
