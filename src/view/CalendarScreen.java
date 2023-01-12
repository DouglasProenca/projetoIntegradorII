package view;

import objects.InternalFrame;
import com.toedter.calendar.JCalendar;

@SuppressWarnings("serial")
public class CalendarScreen extends InternalFrame {

    public CalendarScreen() {
        super("Calendário", false, true, true, true,438, 298);
        this.initComponents();
    }

    private void initComponents() {
        this.getContentPane().add(new JCalendar());
        super.pack();
    }
}
