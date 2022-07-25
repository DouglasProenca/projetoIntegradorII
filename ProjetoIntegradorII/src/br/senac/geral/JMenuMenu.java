/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.geral;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JSeparator;

/**
 *
 * @author Douglas
 */
public class JMenuMenu extends JMenu implements ActionListener {

    private static JMenu uniqueInstance;

    public JMenuMenu() {
        super("Menu");
        this.setIcon(images.imagemMenu());
        this.setMnemonic('M');
        this.add(getNotes());
        this.add(getCalculator());
        this.add(new JSeparator());
        this.add(getExit());
    }

    private JMenuItem getNotes() {
        JMenuItem notes = new JMenuItem("Bloco de Notas", images.imagemBlocoNotas());
        notes.addActionListener(this);
        notes.setActionCommand("notes");
        return notes;
    }

    private JMenuItem getCalculator() {
        JMenuItem calculator = new JMenuItem("Calculadora", images.imagemCalculadora());
        calculator.addActionListener(this);
        calculator.setActionCommand("calculator");
        return calculator;
    }

    private JMenuItem getExit() {
        JMenuItem exit = new JMenuItem("Sair", images.imagemSair());
        exit.addActionListener(this);
        exit.setActionCommand("exit");
        return exit;
    }

    public static synchronized JMenu getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new JMenuMenu();
        }
        return uniqueInstance;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getActionCommand().equals("calculator")) {
                Runtime.getRuntime().exec("C:\\windows\\System32\\calc.exe");
            } else if (e.getActionCommand().equals("exit")) {
                System.exit(0);
            } else if(e.getActionCommand().equals("notes")){
                Runtime.getRuntime().exec("C:\\windows\\System32\\notepad.exe");
            }
        } catch (IOException ex) {
            Logger.getLogger(JMenuMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
