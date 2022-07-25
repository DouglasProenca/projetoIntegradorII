/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.geral;

import javax.swing.JMenu;

/**
 *
 * @author Douglas
 */
public class JMenuReport extends JMenu {

    private static JMenu uniqueInstance;

    public JMenuReport() {
        super("Relatório");
        this.setIcon(images.imagemReport());
        this.setMnemonic('R');
    }

    public static synchronized JMenu getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new JMenuReport();
        }
        return uniqueInstance;
    }
}
