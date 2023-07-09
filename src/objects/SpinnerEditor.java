package objects;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.util.EventObject;
import javax.swing.AbstractCellEditor;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

@SuppressWarnings("serial")
public class SpinnerEditor extends AbstractCellEditor implements TableCellEditor {

    private SpinnerNumberInt spinner = new SpinnerNumberInt();

    public SpinnerEditor() {

    }
    
    public SpinnerEditor(int value, int minimum, int maximum) {
    	spinner = new SpinnerNumberInt(value,minimum, maximum);
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected,
            int row, int column) {
        spinner.setValue(value);
        return spinner;
    }

    @Override
    public boolean isCellEditable(EventObject evt) {
        if (evt instanceof MouseEvent) {
            return ((MouseEvent) evt).getClickCount() >= 2;
        }
        return true;
    }

    @Override
    public Object getCellEditorValue() {
        return spinner.getValue();
    }
    
    
}
