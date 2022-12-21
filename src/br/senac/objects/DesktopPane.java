package br.senac.objects;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JDesktopPane;

public class DesktopPane extends JDesktopPane {

    public DesktopPane(Dimension dimension) {
        super.setPreferredSize(dimension);
    }

    @Override
    public void paintComponent(Graphics y) {
        PropertiesSystem ps = new PropertiesSystem();
        String cores = ps.getColor();
        cores = cores == null ? "63,100,129" : cores;
        Color color = Utils.convertColor(cores);
        super.paintComponent(y);
        y.setColor(color);
        y.fillRect(0, 0, getWidth(), getHeight());
    }

};
