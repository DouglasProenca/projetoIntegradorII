package br.senac.objects;

import java.awt.event.ActionListener;
import javax.swing.Icon;
import javax.swing.JMenu;

/**
 *
 * @author Douglas
 */
public abstract class Menu extends JMenu implements ActionListener {

    protected Menu(String title, Icon image) {
        super(title);
        super.setIcon(image);
    }
}
