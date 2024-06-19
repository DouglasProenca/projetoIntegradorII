package com.sistema.desktop_cr7_imports.view;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.sistema.desktop_cr7_imports.enums.Images;
import com.sistema.desktop_cr7_imports.objects.ThreadCustom;



public class IntroScreen extends JDialog implements Runnable {

	private static final long serialVersionUID = 1L;

	public IntroScreen() {
		super(new JFrame());
		setSize(300, 300);
		setLocationRelativeTo(null);
		setUndecorated(true);
		setBackground(new Color(0f, 0f, 0f, 0f));
		add(new JLabel(Images.INTRO.getImage()), BorderLayout.CENTER);
		getIntro().start();
	}

	private Thread getIntro() {
		return new ThreadCustom(() -> {
			ThreadCustom.sleepThread(800);
			this.run();
		});
	}

	@Override
	public void run() {
		this.dispose();
		MainScreen mainScreen = MainScreen.getInstance();
		mainScreen.setVisible(true);	
	}
}
