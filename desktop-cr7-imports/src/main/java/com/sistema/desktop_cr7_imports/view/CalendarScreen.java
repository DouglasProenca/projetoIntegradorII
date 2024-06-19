package com.sistema.desktop_cr7_imports.view;


import com.sistema.desktop_cr7_imports.objects.InternalFrame;
import com.toedter.calendar.JCalendar;

public class CalendarScreen extends InternalFrame {

	private static final long serialVersionUID = 1L;

	public CalendarScreen() {
        super("Calendário", false, true, true, true,438, 298);
        this.initComponents();
    }

    private void initComponents() {
        this.getContentPane().add(new JCalendar());
        super.pack();
    }
}
