package br.senac.view;

import br.senac.objects.InternalFrame;
import com.toedter.calendar.JCalendar;

public class CalendarScreen extends InternalFrame {

    public CalendarScreen() {
        super("Calendário", false, true, true, true,438, 298);
        this.initComponents();
    }

    private void initComponents() {
        super.getContentPane().add(new JCalendar());
        super.pack();
    }
}
