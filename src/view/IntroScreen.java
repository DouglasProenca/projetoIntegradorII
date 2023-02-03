package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import objects.images;

@SuppressWarnings("serial")
public class IntroScreen extends JDialog {

	private static JFrame frame;
	private static JLabel lblIcon;
	private static JPanel panel;

	public IntroScreen() {
		super(getFrame());
		setSize(300, 300);
		setLocationRelativeTo(null);
		setUndecorated(true);
		add(getPanel(), BorderLayout.CENTER);
		getIntro().start();
	}

	private static JFrame getFrame() {
		frame = new JFrame();
		frame.setSize(300, 300);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		return frame;
	}

	private static JLabel getLblIcon() {
		lblIcon = new JLabel(images.getInstance().intro());
		lblIcon.setPreferredSize(new Dimension(300, 300));
		return lblIcon;
	}

	private static JPanel getPanel() {
		panel = new JPanel();
		panel.setPreferredSize(new Dimension(300, 300));
		panel.add(getLblIcon(), BorderLayout.CENTER);
		return panel;
	}

	private Thread getIntro() {
		return new Thread(() -> {
			try {
				Thread.sleep(800);
			} catch (InterruptedException ex) {
				Logger.getLogger(MainScreen.class.getName()).log(Level.SEVERE, null, ex);
			}
			this.dispose();
			getMainScreen();
		});
	}

	public MainScreen getMainScreen() {
		MainScreen mainScreen = new MainScreen();
		mainScreen.setVisible(true);
		return mainScreen;
	}
}
