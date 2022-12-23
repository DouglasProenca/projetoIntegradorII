package objects;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class TextField extends JTextField implements KeyListener {

	private boolean number = false;

	public TextField(boolean number) {
		super.addKeyListener(this);
		this.number = number;
	}
	
	public TextField(boolean number,String text) {
		super(text);
		super.addKeyListener(this);
		this.number = number;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		if (number) {
			String caracteres = "0987654321.";
			if (!caracteres.contains(e.getKeyChar() + "")) {
				e.consume();// aciona esse propriedade para eliminar a ação do evento
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}
}