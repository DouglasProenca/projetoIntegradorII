package com.sistema.desktop_cr7_imports.enums;

import javax.swing.ImageIcon;

import com.sistema.desktop_cr7_imports.interfaces.ImagesInterface;

public enum Images implements ImagesInterface {

	BACKUP {

		@Override
		public ImageIcon getImage() {
			return new ImageIcon(readImage("/images/Backup-icon.png"));
		}
	},

	BRAND {

		@Override
		public ImageIcon getImage() {
			return new ImageIcon(readImage("/imagens/Apple-Store-Tshirt-Red-icon.png"));

		}
	},

	CALCULATOR {

		@Override
		public ImageIcon getImage() {
			return new ImageIcon(readImage("/imagens/Calculator-icon.png"));

		}
	},

	CALENDAR {

		@Override
		public ImageIcon getImage() {
			return new ImageIcon(readImage("/imagens/Calendar-icon.png"));

		}
	},

	CATEGORY {

		@Override
		public ImageIcon getImage() {
			return new ImageIcon(readImage("/imagens/sql-join-right-icon.png"));
		}

	},

	CHART {

		@Override
		public ImageIcon getImage() {
			return new ImageIcon(readImage("/imagens/Designcontest-Ecommerce-Business-Pie-chart.24.png"));
		}

	},

	CHECK {

		@Override
		public ImageIcon getImage() {
			return new ImageIcon(readImage("/imagens/check-1-icon.png"));
		}
	},

	CLOSE {

		@Override
		public ImageIcon getImage() {
			return new ImageIcon(readImage("/imagens/close-icon.png"));
		}

	},

	CONECTION_SUCESS {

		@Override
		public ImageIcon getImage() {
			return new ImageIcon(readImage("/imagens/Database-accept-icon.png"));
		}
	},

	CONECTION_UNSUCESS {

		@Override
		public ImageIcon getImage() {
			return new ImageIcon(readImage("/imagens/Database-erro-icon.png"));
		}
	},

	DATABASE {

		@Override
		public ImageIcon getImage() {
			return new ImageIcon(readImage("/imagens/BancoDados-icon.png"));

		}
	},

	EXIT {

		@Override
		public ImageIcon getImage() {
			return new ImageIcon(readImage("/imagens/Log-Out-icon.png"));
		}

	},

	INFO {

		@Override
		public ImageIcon getImage() {
			return new ImageIcon(readImage("/imagens/Info-icon.png"));
		}
	},

	INTRO {

		@Override
		public ImageIcon getImage() {
			return new ImageIcon(readImage("/imagens/intro.png"));
		}
	},

	LOOK_AND_FEEL {

		@Override
		public ImageIcon getImage() {
			return new ImageIcon(readImage("/imagens/Look-n-feel-icon.png"));
		}

	},

	MAIL {

		@Override
		public ImageIcon getImage() {
			return new ImageIcon(readImage("/imagens/Mail-icon.png"));
		}

	},

	MANAGEMENT {

		@Override
		public ImageIcon getImage() {
			return new ImageIcon(readImage("/imagens/Finance-Bill-icon.png"));
		}

	},

	MENU {

		@Override
		public ImageIcon getImage() {
			return new ImageIcon(readImage("/imagens/Menu-icon.png"));
		}

	},

	NOTES {

		@Override
		public ImageIcon getImage() {
			return new ImageIcon(readImage("/imagens/Notepad-Bloc-notes-icon.png"));
		}

	},

	PRODUCT {

		@Override
		public ImageIcon getImage() {
			return new ImageIcon(readImage("/imagens/product-icon.png"));
		}

	},

	REFRESH {

		@Override
		public ImageIcon getImage() {
			return new ImageIcon(readImage("/imagens/Button-Refresh-icon.png"));
		}

	},

	RELOAD {

		@Override
		public ImageIcon getImage() {
			return new ImageIcon(readImage("/imagens/reload.GIF"));
		}

	},

	REPORT {

		@Override
		public ImageIcon getImage() {
			return new ImageIcon(readImage("/imagens/chart-icon.png"));
		}

	},

	SALE {

		@Override
		public ImageIcon getImage() {
			return new ImageIcon(readImage("/imagens/Sales-by-payment-method-icon.png"));
		}

	},

	SITE {

		@Override
		public ImageIcon getImage() {
			return new ImageIcon(readImage("/imagens/Categories-applications-internet-icon.png"));
		}

	},

	SYSTEM {

		@Override
		public ImageIcon getImage() {
			return new ImageIcon(readImage("/imagens/System-computer-icon.png"));
		}

	},

	USER {

		@Override
		public ImageIcon getImage() {
			return new ImageIcon(readImage("/imagens/Administrator-icon.png"));
		}

	},

	VERSION {

		@Override
		public ImageIcon getImage() {
			return new ImageIcon(readImage("/imagens/Apps-preferences-system-windows-actions-icon.png"));
		}
	}

}
