package br.senac.objects;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JDesktopPane;

public class DesktopPane extends JDesktopPane {

    public DesktopPane(Dimension dimension) {
        super();
        this.setPreferredSize(dimension);
    }

    @Override
    public void paintComponent(Graphics y) {
        PropertiesSystem ps = new PropertiesSystem();
        String cores = ps.getColor();
        cores = cores == null ? "63,100,129" : cores;
        Color color = convertColor(cores);
        super.paintComponent(y);
        y.setColor(color);
        y.fillRect(0, 0, getWidth(), getHeight());
    }

    private Color convertColor(String cor) {
        String corArray[] = cor.split(",");
        int r = Integer.parseInt(corArray[0]);
        int g = Integer.parseInt(corArray[1]);
        int b = Integer.parseInt(corArray[2]);
        Color color = new Color(r, g, b);
        return color;
    }
};
