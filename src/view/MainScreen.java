package view;

import objects.ConnectionManager;
import objects.MenuBar;
import objects.ThreadCustom;
import objects.DesktopPane;
import objects.InternalFrame;
import objects.images;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JToolBar;

@SuppressWarnings("serial")
public class MainScreen extends JFrame implements KeyListener, WindowStateListener {

	public static DesktopPane desktopPane;
	public static JToolBar jToolBar;
	protected static MenuBar menubar = new MenuBar();
	private final LoginScreen loginScreen = new LoginScreen();
	private final DatabaseConnectionScreen bd = new DatabaseConnectionScreen(false, false);

	public MainScreen() {
		super("CR7 Imports");
		this.initComponents();
	}

	private void initComponents() {
		this.addWindowStateListener(this);
		this.setMinimumSize(new Dimension(800, 500));
		this.setExtendedState(MAXIMIZED_BOTH);
		this.setLocationRelativeTo(null);
		this.addKeyListener(this);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.add(getDesktopPane(), BorderLayout.CENTER);
		this.add(getJToolBar(), BorderLayout.PAGE_END);
		this.setIconImage(getIcone().getImage());
		this.setJMenuBar(menubar);
		this.getFirst();
	}

	private ImageIcon getIcone() {
		return images.getInstance().imagemPrincipal();
	}

	private ThreadCustom getFirst() {
		ThreadCustom t = new ThreadCustom(() -> {
			ThreadCustom.sleepThread(800);
			if (ConnectionManager.getInstance().getConexao() == null) {
				bd.setVisible(true);
			} else {
				loginScreen.setVisible(true);
			}
		});
		t.start();
		return t;
	}

	private JDesktopPane getDesktopPane() {
		desktopPane = new DesktopPane(new Dimension(this.getSize().width, this.getSize().height - 40));
		return desktopPane;
	}

	private JToolBar getJToolBar() {
		jToolBar = new JToolBar();
		jToolBar.setPreferredSize(new Dimension(this.getSize().width, 40));
		jToolBar.setFloatable(false);
		jToolBar.setRollover(false);
		return jToolBar;
	}

	public static void removeForms() {
		jToolBar.removeAll();
		int qtd = desktopPane.getComponentCount();
		for (int i = 0; i < qtd; i++) {
			if (desktopPane.getComponent(i) instanceof InternalFrame) {
				InternalFrame frame = (InternalFrame) desktopPane.getComponent(i);
				frame.dispose();
				i = -1;
				qtd = desktopPane.getComponentCount();
			}
		}
	}

	private ThreadCustom addIconsToolbar(Component comp[]) {
		ThreadCustom t = new ThreadCustom(() -> {
			ThreadCustom.sleepThread(500);
			for (int i = 0; i < comp.length; i++) {
				jToolBar.add(comp[i]);
			}
			jToolBar.validate();
		});
		return t;
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			System.exit(0);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void windowStateChanged(WindowEvent e) {
		for (int i = 0; i < desktopPane.getComponentCount(); i++) {
			if (desktopPane.getComponent(i) instanceof InternalFrame) {
				InternalFrame frame = (InternalFrame) desktopPane.getComponent(i);
				Dimension jInternalFrameSize = frame.getSize();

				if (!(e.getOldState() == 6 && e.getNewState() == 7 || e.getOldState() == 7 && e.getNewState() == 6)) {
					if (e.getNewState() == MainScreen.MAXIMIZED_BOTH) {
						frame.setLocation((1300 - jInternalFrameSize.width) / 2, (656 - jInternalFrameSize.height) / 2);
					} else {
						frame.setLocation(400 - jInternalFrameSize.width / 2, (400 - jInternalFrameSize.height) / 2);
					}
				}
			}
		}
		Thread t = this.addIconsToolbar(jToolBar.getComponents());
		t.start();
	}
}
