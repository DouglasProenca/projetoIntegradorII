package objetos;

import com.toedter.calendar.JCalendar;

public class Calendar extends InternalFrame {

	public Calendar() {
		super("Calendário", false, true, true, true,438, 298);
		initComponents();
	}

	private void initComponents() {
		getContentPane().add(new JCalendar());
		pack();
	}
}
