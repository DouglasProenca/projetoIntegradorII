package objects;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;


@SuppressWarnings("serial")
public class SpinnerNumberInt extends JSpinner implements KeyListener {

    public SpinnerNumberInt() {
        super(new SpinnerNumberModel(0, 0, 999999, 1));
         ((JSpinner.DefaultEditor) super.getEditor()).getTextField().addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        String caracteres = "0987654321";
        if (!caracteres.contains(e.getKeyChar() + "")) {
            e.consume();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}