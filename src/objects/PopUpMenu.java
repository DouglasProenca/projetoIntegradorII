package objects;

import javax.swing.JPopupMenu;


public class PopUpMenu extends JPopupMenu {

	private static final long serialVersionUID = 1L;
	private static final MainMenu menu = new MainMenu();
	private static final MenuReport report = new MenuReport();
	private static final MenuHelp help = new MenuHelp();

	public PopUpMenu() {
		this.add(menu);
		this.add(report);
		this.add(help);
	}
	
	public static void lockMenu(boolean type) {
		menu.setEnabled(type);
		report.setEnabled(type);
		help.setEnabled(type);
	}
}
