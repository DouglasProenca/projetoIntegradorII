package com.sistema.desktop_cr7_imports;

import javax.swing.SwingUtilities;

import com.sistema.desktop_cr7_imports.controller.IntroScreen;
import com.sistema.desktop_cr7_imports.controller.LookAndFeelScreen;


public class DesktopCr7ImportsApplication {

	public static void main(String[] args) {
		Runnable r = () -> {
            LookAndFeelScreen.initLookAndFeel();
            IntroScreen introScreen = new IntroScreen();
            introScreen.setVisible(true);
        }; 
        SwingUtilities.invokeLater(r);
	}

}
