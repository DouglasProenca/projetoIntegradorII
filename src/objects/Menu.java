package objects;

import java.awt.event.ActionListener;
import javax.swing.Icon;
import javax.swing.JMenu;

@SuppressWarnings("serial")
public abstract class Menu extends JMenu implements ActionListener {

    protected Menu(String title, Icon image) {
        super(title);
        super.setIcon(image);
    }
}
