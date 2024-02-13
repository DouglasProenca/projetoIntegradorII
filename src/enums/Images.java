package enums;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import interfaces.ImagesInterface;

public enum Images implements ImagesInterface {

	BACKUP {

		@Override
		public ImageIcon getImage() {
			try {
				return new ImageIcon(ImageIO.read(getClass().getResource("/resources/Backup-icon.png")));
			} catch (IOException e) {
				Logger.getLogger(Images.class.getName()).log(Level.SEVERE, null, e);
			}
			return null;
		}
	},
	
	BRAND {
		
		@Override
		public ImageIcon getImage() {
			try {
				return new ImageIcon(ImageIO.read(getClass().getResource("/resources/Apple-Store-Tshirt-Red-icon.png")));
			} catch (IOException e) {
				Logger.getLogger(Images.class.getName()).log(Level.SEVERE, null, e);
			}
			return null;
		}
	},
	
	CALCULATOR {

		@Override
		public ImageIcon getImage() {
			try {
				return new ImageIcon(ImageIO.read(getClass().getResource("/resources/Calculator-icon.png")));
			} catch (IOException e) {
				Logger.getLogger(Images.class.getName()).log(Level.SEVERE, null, e);
			}
			return null;
		}
	},

	CALENDAR {

		@Override
		public ImageIcon getImage() {
			try {
				return new ImageIcon(ImageIO.read(getClass().getResource("/resources/Calendar-icon.png")));
			} catch (IOException e) {
				Logger.getLogger(Images.class.getName()).log(Level.SEVERE, null, e);
			}
			return null;
		}
	},

	CATEGORY {

		@Override
		public ImageIcon getImage() {
			try {
				return new ImageIcon(ImageIO.read(getClass().getResource("/resources/sql-join-right-icon.png")));
			} catch (IOException e) {
				Logger.getLogger(Images.class.getName()).log(Level.SEVERE, null, e);
			}
			return null;
		}

	},
	
	CHART {

		@Override
		public ImageIcon getImage() {
			try {
				return new ImageIcon(ImageIO.read(getClass().getResource("/resources/Designcontest-Ecommerce-Business-Pie-chart.24.png")));
			} catch (IOException e) {
				Logger.getLogger(Images.class.getName()).log(Level.SEVERE, null, e);
			}
			return null;
		}

	},

	CHECK {

		@Override
		public ImageIcon getImage() {
			try {
				return new ImageIcon(ImageIO.read(getClass().getResource("/resources/check-1-icon.png")));
			} catch (IOException e) {
				Logger.getLogger(Images.class.getName()).log(Level.SEVERE, null, e);
			}
			return null;
		}
	},

	CLOSE {

		@Override
		public ImageIcon getImage() {
			try {
				return new ImageIcon(ImageIO.read(getClass().getResource("/resources/close-icon.png")));
			} catch (IOException e) {
				Logger.getLogger(Images.class.getName()).log(Level.SEVERE, null, e);
			}
			return null;
		}

	},

	CONECTION_SUCESS {

		@Override
		public ImageIcon getImage() {
			try {
				return new ImageIcon(ImageIO.read(getClass().getResource("/resources/Database-accept-icon.png")));
			} catch (IOException e) {
				Logger.getLogger(Images.class.getName()).log(Level.SEVERE, null, e);
			}
			return null;
		}
	},

	CONECTION_UNSUCESS {

		@Override
		public ImageIcon getImage() {
			try {
				return new ImageIcon(ImageIO.read(getClass().getResource("/resources/Database-erro-icon.png")));
			} catch (IOException e) {
				Logger.getLogger(Images.class.getName()).log(Level.SEVERE, null, e);
			}
			return null;
		}
	},

	DATABASE {

		@Override
		public ImageIcon getImage() {
			try {
				return new ImageIcon(ImageIO.read(getClass().getResource("/resources/BancoDados-icon.png")));
			} catch (IOException e) {
				Logger.getLogger(Images.class.getName()).log(Level.SEVERE, null, e);
			}
			return null;
		}
	},

	EXIT {

		@Override
		public ImageIcon getImage() {
			try {
				return new ImageIcon(ImageIO.read(getClass().getResource("/resources/Log-Out-icon.png")));
			} catch (IOException e) {
				Logger.getLogger(Images.class.getName()).log(Level.SEVERE, null, e);
			}
			return null;
		}

	},

	INFO {

		@Override
		public ImageIcon getImage() {
			try {
				return new ImageIcon(ImageIO.read(getClass().getResource("/resources/Info-icon.png")));
			} catch (IOException e) {
				Logger.getLogger(Images.class.getName()).log(Level.SEVERE, null, e);
			}
			return null;
		}
	},

	INTRO {

		@Override
		public ImageIcon getImage() {
			try {
				return new ImageIcon(ImageIO.read(getClass().getResource("/resources/intro.png")));
			} catch (IOException e) {
				Logger.getLogger(Images.class.getName()).log(Level.SEVERE, null, e);
			}
			return null;
		}
	},

	LOOK_AND_FEEL {

		@Override
		public ImageIcon getImage() {
			try {
				return new ImageIcon(ImageIO.read(getClass().getResource("/resources/Look-n-feel-icon.png")));
			} catch (IOException e) {
				Logger.getLogger(Images.class.getName()).log(Level.SEVERE, null, e);
			}
			return null;
		}

	},

	MAIL {

		@Override
		public ImageIcon getImage() {
			try {
				return new ImageIcon(ImageIO.read(getClass().getResource("/resources/Mail-icon.png")));
			} catch (IOException e) {
				Logger.getLogger(Images.class.getName()).log(Level.SEVERE, null, e);
			}
			return null;
		}

	},
	
	MANAGEMENT {
		
		@Override
		public ImageIcon getImage() {
			try {
				return new ImageIcon(ImageIO.read(getClass().getResource("/resources/Finance-Bill-icon.png")));
			} catch (IOException e) {
				Logger.getLogger(Images.class.getName()).log(Level.SEVERE, null, e);
			}
			return null;
		}
	},

	MENU {

		@Override
		public ImageIcon getImage() {
			try {
				return new ImageIcon(ImageIO.read(getClass().getResource("/resources/Menu-icon.png")));
			} catch (IOException e) {
				Logger.getLogger(Images.class.getName()).log(Level.SEVERE, null, e);
			}
			return null;
		}
	},

	NOTES {

		@Override
		public ImageIcon getImage() {
			try {
				return new ImageIcon(ImageIO.read(getClass().getResource("/resources/Notepad-Bloc-notes-icon.png")));
			} catch (IOException e) {
				Logger.getLogger(Images.class.getName()).log(Level.SEVERE, null, e);
			}
			return null;
		}
	},

	PRODUCT {

		@Override
		public ImageIcon getImage() {
			try {
				return new ImageIcon(ImageIO.read(getClass().getResource("/resources/product-icon.png")));
			} catch (IOException e) {
				Logger.getLogger(Images.class.getName()).log(Level.SEVERE, null, e);
			}
			return null;
		}
	},

	REFRESH {

		@Override
		public ImageIcon getImage() {
			try {
				return new ImageIcon(ImageIO.read(getClass().getResource("/resources/Button-Refresh-icon.png")));
			} catch (IOException e) {
				Logger.getLogger(Images.class.getName()).log(Level.SEVERE, null, e);
			}
			return null;
		}
	},

	RELOAD {

		@Override
		public ImageIcon getImage() {
			try {
				return new ImageIcon(ImageIO.read(getClass().getResource("/resources/reload.GIF")));
			} catch (IOException e) {
				Logger.getLogger(Images.class.getName()).log(Level.SEVERE, null, e);
			}
			return null;
		}
	},
	
	REPORT {
		
		@Override
		public ImageIcon getImage() {
			try {
				return new ImageIcon(ImageIO.read(getClass().getResource("/resources/chart-icon.png")));
			} catch (IOException e) {
				Logger.getLogger(Images.class.getName()).log(Level.SEVERE, null, e);
			}
			return null;
		}
	},

	SALE {

		@Override
		public ImageIcon getImage() {
			try {
				return new ImageIcon(
						ImageIO.read(getClass().getResource("/resources/Sales-by-payment-method-icon.png")));
			} catch (IOException e) {
				Logger.getLogger(Images.class.getName()).log(Level.SEVERE, null, e);
			}
			return null;
		}
	},

	SITE {

		@Override
		public ImageIcon getImage() {
			try {
				return new ImageIcon(
						ImageIO.read(getClass().getResource("/resources/Categories-applications-internet-icon.png")));
			} catch (IOException e) {
				Logger.getLogger(Images.class.getName()).log(Level.SEVERE, null, e);
			}
			return null;
		}
	},
	
	SYSTEM {
		
		@Override
		public ImageIcon getImage() {
			try {
				return new ImageIcon(
						ImageIO.read(getClass().getResource("/resources/System-computer-icon.png")));
			} catch (IOException e) {
				Logger.getLogger(Images.class.getName()).log(Level.SEVERE, null, e);
			}
			return null;
		}
	},

	USER {

		@Override
		public ImageIcon getImage() {
			try {
				return new ImageIcon(ImageIO.read(getClass().getResource("/resources/Administrator-icon.png")));
			} catch (IOException e) {
				Logger.getLogger(Images.class.getName()).log(Level.SEVERE, null, e);
			}
			return null;
		}
	},

	VERSION {

		@Override
		public ImageIcon getImage() {
			try {
				return new ImageIcon(ImageIO
						.read(getClass().getResource("/resources/Apps-preferences-system-windows-actions-icon.png")));
			} catch (IOException e) {
				Logger.getLogger(Images.class.getName()).log(Level.SEVERE, null, e);
			}
			return null;
		}
	}

}
