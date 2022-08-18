package br.senac.objects;

import br.senac.model.Brand;
import br.senac.model.Category;
import br.senac.model.Product;
import br.senac.view.MainScreen;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
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

    private WritableFont fontbol;
    private WritableCellFormat cellFormat;

    private WritableFont getFontbol() {
        fontbol = new WritableFont(WritableFont.ARIAL, 10);
        try {
            fontbol.setBoldStyle(WritableFont.BOLD);
        } catch (WriteException ex) {
            Logger.getLogger(Excel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fontbol;
    }

    private WritableCellFormat getCellFormat() {
        cellFormat = new WritableCellFormat(getFontbol());
        try {
            cellFormat.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN);
            cellFormat.setVerticalAlignment(jxl.format.VerticalAlignment.JUSTIFY);
            jxl.format.Colour bckcolor = jxl.format.Colour.AQUA;
            cellFormat.setBackground(bckcolor);
        } catch (WriteException ex) {
            Logger.getLogger(Excel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cellFormat;
    }

    public boolean BrandExcel(File arquivo, ArrayList<Brand> lista) {
        boolean status = true;
        try {
            WritableCellFormat cellFormatt = new WritableCellFormat();
            cellFormatt.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN);

            //Instaciando a classe que gera o novo arquivo do excel
            WritableWorkbook workbook = Workbook.createWorkbook(new File(arquivo.toString() + ".xls"));

            //criando uma nova planilha
            WritableSheet sheet = workbook.createSheet("Marcas", 0);

            //col, lin
            Label labelTitulo = new Label(0, 0, "ID");
            sheet.addCell(labelTitulo);
            sheet.setColumnView(0, 12);
            labelTitulo.setCellFormat(getCellFormat());

            Label labelTitulo2 = new Label(1, 0, "Marca");
            sheet.addCell(labelTitulo2);
            sheet.setColumnView(1, 45);
            labelTitulo2.setCellFormat(getCellFormat());

            Label labelTitulo3 = new Label(2, 0, "Pais");
            sheet.addCell(labelTitulo3);
            sheet.setColumnView(2, 15);
            labelTitulo3.setCellFormat(getCellFormat());

            Label labelTitulo4 = new Label(3, 0, "Data");
            sheet.addCell(labelTitulo4);
            sheet.setColumnView(3, 11);
            labelTitulo4.setCellFormat(getCellFormat());

            Label labelTitulo5 = new Label(4, 0, "User");
            sheet.addCell(labelTitulo5);
            sheet.setColumnView(4, 10);
            labelTitulo5.setCellFormat(getCellFormat());

            int contador = 1;
            for (Brand p : lista) {

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

    public boolean CategoryExcel(File arquivo, ArrayList<Category> lista) {
        boolean status = true;
        try {

            WritableCellFormat cellFormatt = new WritableCellFormat();
            cellFormatt.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN);

            //Instaciando a classe que gera o novo arquivo do excel
            WritableWorkbook workbook = Workbook.createWorkbook(new File(arquivo.toString() + ".xls"));

            //criando uma nova planilha
            WritableSheet sheet = workbook.createSheet("Categorias", 0);

            //col, lin
            Label labelTitulo = new Label(0, 0, "ID");
            sheet.addCell(labelTitulo);
            sheet.setColumnView(0, 12);
            labelTitulo.setCellFormat(getCellFormat());

            Label labelTitulo2 = new Label(1, 0, "Categoria");
            sheet.addCell(labelTitulo2);
            sheet.setColumnView(1, 45);
            labelTitulo2.setCellFormat(getCellFormat());

            Label labelTitulo4 = new Label(2, 0, "Data");
            sheet.addCell(labelTitulo4);
            sheet.setColumnView(3, 11);
            labelTitulo4.setCellFormat(getCellFormat());

            Label labelTitulo5 = new Label(3, 0, "User");
            sheet.addCell(labelTitulo5);
            sheet.setColumnView(4, 10);
            labelTitulo5.setCellFormat(getCellFormat());

            int contador = 1;
            for (Category p : lista) {

                Label label1 = new Label(0, contador, String.valueOf(p.getId()));
                sheet.addCell(label1);
                label1.setCellFormat(cellFormatt);
                Label label2 = new Label(1, contador, p.getCategoria());
                sheet.addCell(label2);
                label2.setCellFormat(cellFormatt);
                Label label4 = new Label(2, contador, p.getDate().toString());
                sheet.addCell(label4);
                label4.setCellFormat(cellFormatt);
                Label label5 = new Label(3, contador, p.getUser());
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

    public boolean ProductExcel(File arquivo, ArrayList<Product> lista) {
        boolean status = true;
        try {

            WritableCellFormat cellFormatt = new WritableCellFormat();
            cellFormatt.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN);

            //Instaciando a classe que gera o novo arquivo do excel
            WritableWorkbook workbook = Workbook.createWorkbook(new File(arquivo.toString() + ".xls"));

            //criando uma nova planilha
            WritableSheet sheet = workbook.createSheet("Produtos", 0);

            //col, lin
            Label labelTitulo = new Label(0, 0, "ID");
            sheet.addCell(labelTitulo);
            sheet.setColumnView(0, 12);
            labelTitulo.setCellFormat(getCellFormat());

            Label labelTitulo2 = new Label(1, 0, "Produto");
            sheet.addCell(labelTitulo2);
            sheet.setColumnView(1, 45);
            labelTitulo2.setCellFormat(getCellFormat());

            Label labelTitulo3 = new Label(2, 0, "Marca");
            sheet.addCell(labelTitulo3);
            sheet.setColumnView(2, 15);
            labelTitulo3.setCellFormat(getCellFormat());

            Label labelTitulo4 = new Label(3, 0, "Categoria");
            sheet.addCell(labelTitulo4);
            sheet.setColumnView(2, 15);
            labelTitulo4.setCellFormat(getCellFormat());

            Label labelTitulo5 = new Label(4, 0, "Valor");
            sheet.addCell(labelTitulo5);
            sheet.setColumnView(3, 11);
            labelTitulo5.setCellFormat(getCellFormat());

            Label labelTitulo6 = new Label(5, 0, "Quantidade");
            sheet.addCell(labelTitulo6);
            sheet.setColumnView(4, 10);
            labelTitulo6.setCellFormat(getCellFormat());

            Label labelTitulo7 = new Label(6, 0, "Data");
            sheet.addCell(labelTitulo7);
            sheet.setColumnView(4, 10);
            labelTitulo7.setCellFormat(getCellFormat());

            Label labelTitulo8 = new Label(7, 0, "Usuario");
            sheet.addCell(labelTitulo8);
            sheet.setColumnView(4, 10);
            labelTitulo8.setCellFormat(getCellFormat());

            int contador = 1;
            for (Product p : lista) {

                Label label1 = new Label(0, contador, String.valueOf(p.getId()));
                sheet.addCell(label1);
                label1.setCellFormat(cellFormatt);
                Label label2 = new Label(1, contador, p.getNome());
                sheet.addCell(label2);
                label2.setCellFormat(cellFormatt);
                Label label3 = new Label(2, contador, p.getMarca());
                sheet.addCell(label3);
                label3.setCellFormat(cellFormatt);
                Label label4 = new Label(3, contador, p.getCategoria());
                sheet.addCell(label4);
                label4.setCellFormat(cellFormatt);
                Label label5 = new Label(4, contador, String.valueOf(p.getValor()));
                sheet.addCell(label5);
                label5.setCellFormat(cellFormatt);
                Label label6 = new Label(5, contador, String.valueOf(p.getQuantidade()));
                sheet.addCell(label6);
                label6.setCellFormat(cellFormatt);
                Label label7 = new Label(6, contador, p.getDate().toString());
                sheet.addCell(label7);
                label7.setCellFormat(cellFormatt);
                Label label8 = new Label(7, contador, p.getUser());
                sheet.addCell(label8);
                label8.setCellFormat(cellFormatt);

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

    public static ArrayList<Product> importProduct(File arquivo) {
        ArrayList<Product> productList = new ArrayList<>();
        try {
            Workbook workbook = Workbook.getWorkbook(arquivo);
            Sheet sheet = workbook.getSheet(0);

            int linhas = sheet.getRows();

            for (int z = 1; z < linhas; z++) {

                Cell ca = sheet.getCell(0, z);
                Cell cb = sheet.getCell(1, z);
                Cell cc = sheet.getCell(2, z);
                Cell cd = sheet.getCell(3, z);
                Cell ce = sheet.getCell(4, z);

                Product p = new Product(ca.getContents(), Float.parseFloat(cb.getContents()),
                        Integer.parseInt(cc.getContents()), ce.getContents(), 0, cd.getContents(), null, null, null);

                productList.add(p);
            }
            workbook.close();
        } catch (IOException | BiffException ex) {
            JOptionPane.showMessageDialog(MainScreen.desktopPane.getSelectedFrame(), ex.getMessage(),
                    "Aviso de Falha", JOptionPane.ERROR_MESSAGE);
        }
        return productList;
    }

    public static ArrayList<Brand> importBrand(File arquivo) {
        ArrayList<Brand> brandList = new ArrayList<>();
        try {
            Workbook workbook = Workbook.getWorkbook(arquivo);
            Sheet sheet = workbook.getSheet(0);

            int linhas = sheet.getRows();

            for (int z = 1; z < linhas; z++) {

                Cell ca = sheet.getCell(0, z);
                Cell cb = sheet.getCell(1, z);

                Brand p = new Brand(0, ca.getContents(), cb.getContents(), null, null);

                brandList.add(p);
            }
            workbook.close();
        } catch (IOException | BiffException ex) {
            JOptionPane.showMessageDialog(MainScreen.desktopPane.getSelectedFrame(), ex.getMessage(),
                    "Aviso de Falha", JOptionPane.ERROR_MESSAGE);
        }
        return brandList;
    }

    public static ArrayList<Category> importCategory(File arquivo) {
        ArrayList<Category> categoryList = new ArrayList<>();
        try {
            Workbook workbook = Workbook.getWorkbook(arquivo);
            Sheet sheet = workbook.getSheet(0);

            int linhas = sheet.getRows();

            for (int z = 1; z < linhas; z++) {

                Cell ca = sheet.getCell(0, z);

                Category p = new Category(ca.getContents(), 0, null, null, null, null);

                categoryList.add(p);
            }
            workbook.close();
        } catch (IOException | BiffException ex) {
            JOptionPane.showMessageDialog(MainScreen.desktopPane.getSelectedFrame(), ex.getMessage(),
                    "Aviso de Falha", JOptionPane.ERROR_MESSAGE);
        }
        return categoryList;
    }
}
