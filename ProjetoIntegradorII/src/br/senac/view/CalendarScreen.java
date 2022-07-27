package br.senac.view;

import com.toedter.calendar.JCalendar;
import java.awt.Dimension;
import javax.swing.JInternalFrame;

public class CalendarScreen extends JInternalFrame {

    public CalendarScreen() {
        super("Calendario", false, true, true, true);
        initComponents();
    }

    private void initComponents() {
        this.setSize(new Dimension(438, 298));
        this.add(new JCalendar());
        pack();
    }
}
