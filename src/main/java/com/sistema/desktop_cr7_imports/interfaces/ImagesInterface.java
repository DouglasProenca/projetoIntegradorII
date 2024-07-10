package com.sistema.desktop_cr7_imports.interfaces;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import com.sistema.desktop_cr7_imports.DesktopCr7ImportsApplication;
import com.sistema.desktop_cr7_imports.enums.Images;

public interface ImagesInterface {

	/**
	 * 
	 * @return image 
	 */
	public ImageIcon getImage();
	
	default BufferedImage readImage(String urlImage) {
		try {
			return ImageIO.read(DesktopCr7ImportsApplication.class.getResourceAsStream(urlImage));
		} catch (IOException e) {
			Logger.getLogger(Images.class.getName()).log(Level.SEVERE, null, e);
		}
		return null;
	}
}
