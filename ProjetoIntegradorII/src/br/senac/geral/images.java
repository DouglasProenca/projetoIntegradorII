package br.senac.geral;

import javax.swing.ImageIcon;

/**
 *
 * @author Douglas
 */
public class images extends Thread {

    public static ImageIcon imagemPrincipal() {
        return new ImageIcon(Thread.currentThread().getClass().getResource("/Resources/System-computer-icon.png"));
    }

    public static ImageIcon imagemSair() {
        return new ImageIcon(Thread.currentThread().getClass().getResource("/resources/Log-Out-icon.png"));
    }

    public static ImageIcon imagemBlocoNotas() {
        return new ImageIcon(Thread.currentThread().getClass().getResource("/resources/Notepad-Bloc-notes-icon.png"));
    }

    public static ImageIcon imagemCalculadora() {
        return new ImageIcon(Thread.currentThread().getClass().getResource("/resources/Calculator-icon.png"));
    }

    public static ImageIcon imagemMenu() {
        return new ImageIcon(Thread.currentThread().getClass().getResource("/resources/Menu-icon.png"));
    }

    public static ImageIcon imagemReport() {
        return new ImageIcon(Thread.currentThread().getClass().getResource("/resources/chart-icon.png"));
    }

    public static ImageIcon imagemHelp() {
        return new ImageIcon(Thread.currentThread().getClass().getResource("/resources/Info-icon.png"));
    }

    public static ImageIcon imagemVersion() {
        return new ImageIcon(Thread.currentThread().getClass().getResource("/resources/Apps-preferences-system-windows-actions-icon.png"));
    }

    public static ImageIcon imagemSite() {
        return new ImageIcon(Thread.currentThread().getClass().getResource("/resources/Categories-applications-internet-icon.png"));
    }

    public static ImageIcon imagemCalendar() {
        return new ImageIcon(Thread.currentThread().getClass().getResource("/resources/Calendar-icon.png"));
    }
    
       public static ImageIcon imagemLookAndFeel() {
        return new ImageIcon(Thread.currentThread().getClass().getResource("/resources/Calendar-icon.png"));
    }
}
