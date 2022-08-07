package br.senac.view;

import br.senac.objects.InternalFrame;
import com.toedter.calendar.JCalendar;

public class CalendarScreen extends InternalFrame {

    public CalendarScreen() {
        super("Calendario", false, true, true, true,438, 298);
        initComponents();
    }

    private void initComponents() {
        this.add(new JCalendar());
        pack();
    }
}
