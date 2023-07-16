package view;

import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static java.nio.file.StandardOpenOption.CREATE;
import static java.nio.file.StandardOpenOption.APPEND;

import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.DialogTypeSelection;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import objects.Images;
import objects.InternalFrame;

@SuppressWarnings("serial")
public class ReportNotesScreen extends InternalFrame {

	private JMenuBar menubar;
	private JMenu menuArquivo;
	private JTextArea textArea;
	private JMenuItem MenuItemOpen;
	private JMenuItem MenuItemSave;
	private JMenuItem MenuItemPrint;
	private JScrollPane scroll;
	
	public ReportNotesScreen() {
		super("Bloco de Notas", true, true, true, true, 707, 400);
		this.setJMenuBar(getMenubar());
		this.add(getScrollPane());
	}
	
	private JScrollPane getScrollPane() {
		scroll = new JScrollPane(getTextArea());
		return scroll;
	}

	private JMenuBar getMenubar() {
		menubar = new JMenuBar();
		menubar.add(getMenuArquivo());
		return menubar;
	}

	private JMenu getMenuArquivo() {
		menuArquivo = new JMenu("Arquivo");
		menuArquivo.setIcon(Images.NOTES.getImage());
		menuArquivo.add(getMenuItemOpen());
		menuArquivo.add(getMenuItemSave());
		menuArquivo.add(getMenuItemPrint());
		return menuArquivo;
	}

	private JTextArea getTextArea() {
		textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		return textArea;
	}
 
	private JMenuItem getMenuItemOpen() {
		MenuItemOpen = new JMenuItem("Abrir");
		MenuItemOpen.addActionListener(this);
		MenuItemOpen.setActionCommand("open");
		return MenuItemOpen;
	}

	private JMenuItem getMenuItemSave() {
		MenuItemSave = new JMenuItem("Salvar");
		MenuItemSave.addActionListener(this);
		MenuItemSave.setActionCommand("save");
		return MenuItemSave;
	}

	private JMenuItem getMenuItemPrint() {
		MenuItemPrint = new JMenuItem("Imprimir");
		MenuItemPrint.addActionListener(this);
		MenuItemPrint.setActionCommand("print");
		return MenuItemPrint;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			switch (e.getActionCommand()) {
			case "open":
				JFileChooser fcOpen = new JFileChooser();
				if (fcOpen.showOpenDialog(this) == JFileChooser.FILES_ONLY) {
					String dados = new String(
							Files.readAllBytes(new File(fcOpen.getSelectedFile().toString()).toPath()));
					textArea.setText(dados);

				}
				break;
			case "save":
				JFileChooser fcSave = new JFileChooser();
				if (fcSave.showSaveDialog(this) == JFileChooser.FILES_ONLY) {

					byte[] content = textArea.getText().getBytes();
					Files.write(Paths.get(fcSave.getSelectedFile().toString() + ".txt"), content, CREATE, APPEND);
				}
				break;
			default:
				final PrintRequestAttributeSet attributes = new HashPrintRequestAttributeSet();
				attributes.add(DialogTypeSelection.NATIVE);
				attributes.add(new sun.print.DialogOwner(new JFrame()));
				PrinterJob printJob = PrinterJob.getPrinterJob();

				Printable b = new Printable() {

					@Override
					public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {

						if (pageIndex > 0) {
							return NO_SUCH_PAGE;
						}

						Graphics2D g2d = (Graphics2D) graphics;
						g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
						
						g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

				        int x = 30;
				        int y = 50;			 

				        FontMetrics fontMetrics = g2d.getFontMetrics();
				        String[] lines = getLines(textArea.getText(), fontMetrics, 535);
				
				        for (String line : lines) {
				            g2d.drawString(line, x, y);
				            y += fontMetrics.getHeight();
				        }		
						
						return PAGE_EXISTS;
					}
				};
				printJob.setPrintable(b);
				if (printJob.printDialog(attributes)) {
					printJob.print();
				}
			}
		} catch (IOException | PrinterException e1) {
			e1.printStackTrace();
		}
	}
	
	 private String[] getLines(String text, FontMetrics fontMetrics, int maxWidth) {
	        String[] words = text.split("\\s+");
	        StringBuilder currentLine = new StringBuilder();
	        StringBuilder nextLine = new StringBuilder();
	        int currentWidth = 0;
	        
	        for (String word : words) {
	            int wordWidth = fontMetrics.stringWidth(word + " ");
	            
	            if (currentWidth + wordWidth <= maxWidth) {
	                currentLine.append(word).append(" ");
	                currentWidth += wordWidth;
	            } else {
	                nextLine.append(currentLine.toString().trim()).append("\n");
	                currentLine.setLength(0);
	                currentLine.append(word).append(" ");
	                currentWidth = wordWidth;
	            }
	        }
	        
	        if (currentLine.length() > 0) {
	            nextLine.append(currentLine.toString().trim());
	        }
	        
	        return nextLine.toString().split("\n");
	    }
}