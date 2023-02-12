package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

import objects.images;

@SuppressWarnings("serial")
public class IntroScreen extends JDialog {

	private static JFrame frame;
	private static JLabel lblIcon;

	public IntroScreen() {
		super(getFrame());
		setSize(300, 300);
		setLocationRelativeTo(null);
		setUndecorated(true);
		setBackground(new Color(0f, 0f, 0f, 0f));
		add(getLblIcon(), BorderLayout.CENTER);
		getIntro().start();
	}

	private static JFrame getFrame() {
		frame = new JFrame();
		frame.setSize(300, 300);
		frame.setUndecorated(true);
		frame.setBackground(new Color(0f, 0f, 0f, 0f));
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		return frame;
	}

	private static JLabel getLblIcon() {
		lblIcon = new JLabel(images.getInstance().intro());
		lblIcon.setPreferredSize(new Dimension(300, 300));
		return lblIcon;
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
