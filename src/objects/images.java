package objects;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public abstract class images {

    private static images uniqueInstance;

    private images() {
    }

    public static synchronized images getInstance() {
        return uniqueInstance == null ? uniqueInstance = new images() {
        } : uniqueInstance;
    }

    public ImageIcon imagemDatabase() {
        return new ImageIcon(readImg("/resources/BancoDados-icon.png"));
    }

    public ImageIcon imagemPrincipal() {
        return new ImageIcon(readImg("/resources/System-computer-icon.png"));
    }

    public ImageIcon imagemSair() {
        return new ImageIcon(readImg("/resources/Log-Out-icon.png"));
    }

    public ImageIcon imagemBlocoNotas() {
        return new ImageIcon(readImg("/resources/Notepad-Bloc-notes-icon.png"));
    }

    public ImageIcon imagemCalculadora() {
        return new ImageIcon(readImg("/resources/Calculator-icon.png"));
    }

    public ImageIcon imagemMenu() {
        return new ImageIcon(readImg("/resources/Menu-icon.png"));
    }

    public ImageIcon imagemReport() {
        return new ImageIcon(readImg("/resources/chart-icon.png"));
    }

    public ImageIcon imagemHelp() {
        return new ImageIcon(readImg("/resources/Info-icon.png"));
    }

    public ImageIcon imagemVersion() {
        return new ImageIcon(readImg("/resources/Apps-preferences-system-windows-actions-icon.png"));
    }

    public ImageIcon imagemSite() {
        return new ImageIcon(readImg("/resources/Categories-applications-internet-icon.png"));
    }

    public ImageIcon imagemCalendar() {
        return new ImageIcon(readImg("/resources/Calendar-icon.png"));
    }

    public ImageIcon imagemLookAndFeel() {
        return new ImageIcon(readImg("/resources/Calendar-icon.png"));
    }

    public ImageIcon imagemProduct() {
        return new ImageIcon(readImg("/resources/product-icon.png"));
    }

    public ImageIcon imagemBrand() {
        return new ImageIcon(readImg("/resources/Apple-Store-Tshirt-Red-icon.png"));
    }

    public ImageIcon imagemManagement() {
        return new ImageIcon(readImg("/resources/Finance-Bill-icon.png"));
    }

    public ImageIcon imagemMail() {
        return new ImageIcon(readImg("/resources/Mail-icon.png"));
    }

    public ImageIcon imagemSale() {
        return new ImageIcon(readImg("/resources/Sales-by-payment-method-icon.png"));
    }

    public ImageIcon imagemAdministrator() {
        return new ImageIcon(readImg("/resources/Administrator-icon.png"));
    }

    public ImageIcon imagemClose() {
        return new ImageIcon(readImg("/resources/close-icon.png"));
    }

    public ImageIcon imagemCheck() {
        return new ImageIcon(readImg("/resources/check-1-icon.png"));
    }

    public ImageIcon imagemRefresh() {
        return new ImageIcon(readImg("/resources/Button-Refresh-icon.png"));
    }

    public ImageIcon conectionSucess() {
        return new ImageIcon(readImg("/resources/Database-accept-icon.png"));
    }

    public ImageIcon conectionError() {
        return new ImageIcon(readImg("/resources/Database-erro-icon.png"));
    }

    public ImageIcon backup() {
        return new ImageIcon(readImg("/resources/Backup-icon.png"));
    }

    public ImageIcon category() {
        return new ImageIcon(readImg("/resources/sql-join-right-icon.png"));
    }

    private BufferedImage readImg(String adress) {
        try {
            return ImageIO.read(getClass().getResource(adress));
        } catch (IOException ex) {
            Logger.getLogger(images.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
