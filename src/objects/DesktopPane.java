package objects;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JDesktopPane;


public class DesktopPane extends JDesktopPane {

	private static final long serialVersionUID = 1L;
	private PopUpMenu popUpMenu;

	public DesktopPane(Dimension dimension) {
		super.setPreferredSize(dimension);
		this.setComponentPopupMenu(getPopUpMenu());
	}

	public PopUpMenu getPopUpMenu() {
		if (popUpMenu == null) {
			popUpMenu = new PopUpMenu();
		}
		return popUpMenu;
	}

	@Override
	public void paintComponent(Graphics y) {
		PropertiesSystem ps = new PropertiesSystem();
		super.paintComponent(y);
		y.setColor(getColor(ps));
		y.fillRect(0, 0, getWidth(), getHeight());
	}

	private Color getColor(PropertiesSystem ps) {
		return Utils.convertColor(getColorString(ps));
	}

	private String getColorString(PropertiesSystem ps) {
		return ps.getColor() == null ? "63,100,129" : ps.getColor();
	}
};
