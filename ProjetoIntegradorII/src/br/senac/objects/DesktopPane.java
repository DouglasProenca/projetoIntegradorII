package br.senac.objects;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

public class DesktopPane extends javax.swing.JDesktopPane {

    public DesktopPane(Dimension dimension) {
        super();
        this.setPreferredSize(dimension);
    }

    @Override
    public void paintComponent(Graphics y) {
            PropertiesSystem ps = new PropertiesSystem();
            String cores = ps.getColor();
            cores = cores == null ? "63,100,129" : cores;
            String cor[] = cores.split(",");
            int r = Integer.parseInt(cor[0]);
            int g = Integer.parseInt(cor[1]);
            int b = Integer.parseInt(cor[2]);

            super.paintComponent(y);
            y.setColor(new Color(r, g, b));
            y.fillRect(0, 0, getWidth(), getHeight());
    }
};
