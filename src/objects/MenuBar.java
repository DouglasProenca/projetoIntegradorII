package objects;

import javax.swing.JMenuBar;


public class MenuBar extends JMenuBar {

	private static final long serialVersionUID = 1L;
	private MainMenu menu;
    private MenuReport report;
    private MenuHelp help;

    public MenuBar() {
        this.initComponents();
    }
    
    private MainMenu getMenu() {
    	if(menu == null) {
    		menu = new MainMenu();
    	}
    	return menu;
    }
    
    private MenuReport getReport() {
    	if(report == null) {
    		report = new MenuReport();
    	}
    	return report;
    }
    
    private MenuHelp getHelp() {
    	if(help == null) {
    		help = new MenuHelp();
    	}
    	return help;
    }

    private void initComponents() {
        this.add(getMenu());
        this.add(getReport());
        this.add(getHelp());
    }
}