package objects;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JDesktopPane;

@SuppressWarnings("serial")
public class DesktopPane extends JDesktopPane {

    public DesktopPane(Dimension dimension) {
        super.setPreferredSize(dimension);
    }

    @Override
    public void paintComponent(Graphics y) {
        PropertiesSystem ps = new PropertiesSystem();
        Color color = Utils.convertColor(getColor(ps));
        super.paintComponent(y);
        y.setColor(color);
        y.fillRect(0, 0, getWidth(), getHeight());
    }
    
    private String getColor(PropertiesSystem ps) {
    	return ps.getColor() == null ? "63,100,129" : ps.getColor();
    }
};
