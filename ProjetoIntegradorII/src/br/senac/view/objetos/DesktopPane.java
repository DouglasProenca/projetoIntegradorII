package objetos;

import java.awt.Color;
import java.awt.Graphics;
import java.io.IOException;

import objetos.PropertiesSystem.Propriedade;

public class DesktopPane extends javax.swing.JDesktopPane {

    public DesktopPane() {
        super();
    }

    @Override
    public void paintComponent(Graphics y) {

        try {
            String cores = Propriedade.getColor();
            if (cores == null) {
                cores = "63,100,129";
            }
            String cor[] = cores.split(",");
            int r = Integer.parseInt(cor[0]);
            int g = Integer.parseInt(cor[1]);
            int b = Integer.parseInt(cor[2]);

            super.paintComponent(y);
            y.setColor(new Color(r, g, b));
            y.fillRect(0, 0, getWidth(), getHeight());
        } catch (IOException e) {

        }
    }
};
