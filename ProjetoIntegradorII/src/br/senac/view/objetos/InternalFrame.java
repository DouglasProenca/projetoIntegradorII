package objetos;

import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;

public class InternalFrame extends JInternalFrame {

	public InternalFrame(String titulo, boolean resizable, boolean closabe, boolean maximizable, boolean iconifiable,
			int width, int height) {
		super(titulo, resizable, closabe, maximizable, iconifiable);
		setFrameIcon(new ImageIcon(this.getClass().getResource("/Resources/System-computer-icon.png")));
		setSize(width, height);
	}

}
