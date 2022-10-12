package br.senac.objects;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.util.EventObject;
import javax.swing.AbstractCellEditor;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

/**
 *
 * @author Douglas
 */
public  class SpinnerEditor extends AbstractCellEditor implements TableCellEditor {

        final SpinnerNumberInt spinner = new SpinnerNumberInt();

        public SpinnerEditor() {

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
    
