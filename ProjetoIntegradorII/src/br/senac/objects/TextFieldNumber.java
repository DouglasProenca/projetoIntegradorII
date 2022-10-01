/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.objects;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JTextField;

/**
 *
 * @author Douglas
 */
public class TextFieldNumber extends JTextField implements KeyListener {

    public TextFieldNumber() {
        super();
        this.addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        String caracteres = "0987654321.";
        if (!caracteres.contains(e.getKeyChar() + "")) {
            e.consume();//aciona esse propriedade para eliminar a ação do evento
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
