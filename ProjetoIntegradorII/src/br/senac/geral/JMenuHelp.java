package br.senac.geral;

import java.awt.event.KeyEvent;
import javax.swing.JMenu;

/**
 *
 * @author Douglas
 */
public class JMenuHelp extends JMenu {

    private static JMenu uniqueInstance;

    public JMenuHelp() {
        super("Ajuda");
        this.setIcon(images.imagemHelp());
        //this.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_M, 0));
        this.setMnemonic('A');
    }

    public static synchronized JMenu getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new JMenuHelp();
        }
        return uniqueInstance;
    }
}
