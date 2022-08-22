package br.senac.objects;

import java.awt.Image;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *
 * @author Douglas
 */
public abstract class images {

    private static images uniqueInstance;

    private images() {
    }

    public static synchronized images getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new images() {
            };
        }
        return uniqueInstance;
    }

    public ImageIcon imagemDatabase() {
        Image img = null;
        try {
            img = ImageIO.read(getClass().getResource("/resources/BancoDados-icon.png"));
        } catch (IOException ex) {
            Logger.getLogger(images.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new ImageIcon(img);
    }

    public ImageIcon imagemPrincipal() {
        Image img = null;
        try {
            img = ImageIO.read(getClass().getResource("/resources/System-computer-icon.png"));
        } catch (IOException ex) {
            Logger.getLogger(images.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new ImageIcon(img);
    }

    public ImageIcon imagemSair() {
        Image img = null;
        try {
            img = ImageIO.read(getClass().getResource("/resources/Log-Out-icon.png"));
        } catch (IOException ex) {
            Logger.getLogger(images.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new ImageIcon(img);
    }

    public ImageIcon imagemBlocoNotas() {
        Image img = null;
        try {
            img = ImageIO.read(getClass().getResource("/resources/Notepad-Bloc-notes-icon.png"));
        } catch (IOException ex) {
            Logger.getLogger(images.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new ImageIcon(img);
    }

    public ImageIcon imagemCalculadora() {
        Image img = null;
        try {
            img = ImageIO.read(getClass().getResource("/resources/Calculator-icon.png"));
        } catch (IOException ex) {
            Logger.getLogger(images.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new ImageIcon(img);
    }

    public ImageIcon imagemMenu() {
        Image img = null;
        try {
            img = ImageIO.read(getClass().getResource("/resources/Menu-icon.png"));
        } catch (IOException ex) {
            Logger.getLogger(images.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new ImageIcon(img);
    }

    public ImageIcon imagemReport() {
        Image img = null;
        try {
            img = ImageIO.read(getClass().getResource("/resources/chart-icon.png"));
        } catch (IOException ex) {
            Logger.getLogger(images.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new ImageIcon(img);
    }

    public ImageIcon imagemHelp() {
        Image img = null;
        try {
            img = ImageIO.read(getClass().getResource("/resources/Info-icon.png"));
        } catch (IOException ex) {
            Logger.getLogger(images.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new ImageIcon(img);
    }

    public ImageIcon imagemVersion() {
        Image img = null;
        try {
            img = ImageIO.read(getClass().getResource("/resources/Apps-preferences-system-windows-actions-icon.png"));
        } catch (IOException ex) {
            Logger.getLogger(images.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new ImageIcon(img);
    }

    public ImageIcon imagemSite() {
        Image img = null;
        try {
            img = ImageIO.read(getClass().getResource("/resources/Categories-applications-internet-icon.png"));
        } catch (IOException ex) {
            Logger.getLogger(images.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new ImageIcon(img);
    }

    public ImageIcon imagemCalendar() {
        Image img = null;
        try {
            img = ImageIO.read(getClass().getResource("/resources/Calendar-icon.png"));
        } catch (IOException ex) {
            Logger.getLogger(images.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new ImageIcon(img);
    }

    public ImageIcon imagemLookAndFeel() {
        Image img = null;
        try {
            img = ImageIO.read(getClass().getResource("/resources/Calendar-icon.png"));
        } catch (IOException ex) {
            Logger.getLogger(images.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new ImageIcon(img);
    }

    public ImageIcon imagemProduct() {
        Image img = null;
        try {
            img = ImageIO.read(getClass().getResource("/resources/product-icon.png"));
        } catch (IOException ex) {
            Logger.getLogger(images.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new ImageIcon(img);
    }

    public ImageIcon imagemBrand() {
        Image img = null;
        try {
            img = ImageIO.read(getClass().getResource("/resources/Apple-Store-Tshirt-Red-icon.png"));
        } catch (IOException ex) {
            Logger.getLogger(images.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new ImageIcon(img);
    }

    public ImageIcon imagemManagement() {
        Image img = null;
        try {
            img = ImageIO.read(getClass().getResource("/resources/Finance-Bill-icon.png"));
        } catch (IOException ex) {
            Logger.getLogger(images.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new ImageIcon(img);
    }

    public ImageIcon imagemMail() {
        Image img = null;
        try {
            img = ImageIO.read(getClass().getResource("/resources/Mail-icon.png"));
        } catch (IOException ex) {
            Logger.getLogger(images.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new ImageIcon(img);
    }

    public ImageIcon imagemSale() {
        Image img = null;
        try {
            img = ImageIO.read(getClass().getResource("/resources/Sales-by-payment-method-icon.png"));
        } catch (IOException ex) {
            Logger.getLogger(images.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new ImageIcon(img);
    }

    public ImageIcon imagemAdministrator() {
        Image img = null;
        try {
            img = ImageIO.read(getClass().getResource("/resources/Administrator-icon.png"));
        } catch (IOException ex) {
            Logger.getLogger(images.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new ImageIcon(img);
    }

    public ImageIcon imagemClose() {
        Image img = null;
        try {
            img = ImageIO.read(getClass().getResource("/resources/close-icon.png"));
        } catch (IOException ex) {
            Logger.getLogger(images.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new ImageIcon(img);
    }

    public ImageIcon imagemCheck() {
        Image img = null;
        try {
            img = ImageIO.read(getClass().getResource("/resources/check-1-icon.png"));
        } catch (IOException ex) {
            Logger.getLogger(images.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new ImageIcon(img);
    }

    public ImageIcon imagemRefresh() {
        Image img = null;
        try {
            img = ImageIO.read(getClass().getResource("/resources/Button-Refresh-icon.png"));
        } catch (IOException ex) {
            Logger.getLogger(images.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new ImageIcon(img);
    }

    public ImageIcon conectionSucess() {
        Image img = null;
        try {
            img = ImageIO.read(getClass().getResource("/resources/Database-accept-icon.png"));
        } catch (IOException ex) {
            Logger.getLogger(images.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new ImageIcon(img);
    }

    public ImageIcon conectionError() {
        Image img = null;
        try {
            img = ImageIO.read(getClass().getResource("/resources/Database-erro-icon.png"));
        } catch (IOException ex) {
            Logger.getLogger(images.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new ImageIcon(img);
    }

    public ImageIcon backup() {
        Image img = null;
        try {
            img = ImageIO.read(getClass().getResource("/resources/Backup-icon.png"));
        } catch (IOException ex) {
            Logger.getLogger(images.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new ImageIcon(img);
    }

    public ImageIcon category() {
        Image img = null;
        try {
            img = ImageIO.read(getClass().getResource("/resources/sql-join-right-icon.png"));
        } catch (IOException ex) {
            Logger.getLogger(images.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new ImageIcon(img);
    }
}
