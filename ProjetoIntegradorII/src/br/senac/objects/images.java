package br.senac.objects;

import javax.swing.ImageIcon;

/**
 *
 * @author Douglas
 */
public abstract class images {

    public static ImageIcon imagemDatabase() {
        return new ImageIcon(Thread.currentThread().getClass().getResource("/Resources/BancoDados-icon.png"));
    }

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

    public static ImageIcon imagemProduct() {
        return new ImageIcon(Thread.currentThread().getClass().getResource("/resources/product-icon.png"));
    }

    public static ImageIcon imagemBrand() {
        return new ImageIcon(Thread.currentThread().getClass().getResource("/resources/Apple-Store-Tshirt-Red-icon.png"));
    }

    public static ImageIcon imagemManagement() {
        return new ImageIcon(Thread.currentThread().getClass().getResource("/resources/Finance-Bill-icon.png"));
    }

    public static ImageIcon imagemMail() {
        return new ImageIcon(Thread.currentThread().getClass().getResource("/resources/Mail-icon.png"));
    }

    public static ImageIcon imagemSale() {
        return new ImageIcon(Thread.currentThread().getClass().getResource("/resources/Sales-by-payment-method-icon.png"));
    }

    public static ImageIcon imagemAdministrator() {
        return new ImageIcon(Thread.currentThread().getClass().getResource("/resources/Administrator-icon.png"));
    }

    public static ImageIcon imagemClose() {
        return new ImageIcon(Thread.currentThread().getClass().getResource("/resources/close-icon.png"));
    }

    public static ImageIcon imagemCheck() {
        return new ImageIcon(Thread.currentThread().getClass().getResource("/resources/check-1-icon.png"));
    }

    public static ImageIcon imagemRefresh() {
        return new ImageIcon(Thread.currentThread().getClass().getResource("/resources/Button-Refresh-icon.png"));
    }

    public static ImageIcon conectionSucess() {
        return new ImageIcon(Thread.currentThread().getClass().getResource("/resources/Database-accept-icon.png"));
    }

    public static ImageIcon conectionError() {
        return new ImageIcon(Thread.currentThread().getClass().getResource("/resources/Database-erro-icon.png"));
    }

    public static ImageIcon backup() {
        return new ImageIcon(Thread.currentThread().getClass().getResource("/resources/Backup-icon.png"));
    }

    public static ImageIcon category() {
        return new ImageIcon(Thread.currentThread().getClass().getResource("/resources/sql-join-right-icon.png"));
    }
}
