package objects;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class TextField extends JTextField implements KeyListener {

	private String type = "All";

	public TextField(String type) {
		super.addKeyListener(this);
		this.type = type;
	}

	public TextField() {

	}

	public TextField(String type, String text) {
		super(text);
		super.addKeyListener(this);
		this.type = type;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		String caracteres = "0987654321.";
		switch (type) {
		case "Number":
			if (!caracteres.contains(e.getKeyChar() + "")) {
				e.consume();// aciona esse propriedade para eliminar a ação do evento
			}
			break;
		case "Letter":
			if (caracteres.contains(e.getKeyChar() + "")) {
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