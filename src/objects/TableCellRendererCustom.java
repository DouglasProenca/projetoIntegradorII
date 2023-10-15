package objects;

import java.awt.Component;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.event.ChangeEvent;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

public class TableCellRendererCustom extends DefaultTableCellRenderer implements TableCellRenderer {
	
	private static final long serialVersionUID = 1L;
	private Object object;
	
	public TableCellRendererCustom(Object object) {
		this.object = object;
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		if(object instanceof JCheckBox) {
			JCheckBox checkBox = (JCheckBox) object;
            checkBox.setSelected((Boolean) value);
            return checkBox;
		}
		if(object instanceof SpinnerNumberInt) {
			SpinnerNumberInt spinner = (SpinnerNumberInt) object;
			Component component = super.getTableCellRendererComponent(table, value, isSelected,
					hasFocus, row, column);

			spinner.addChangeListener((ChangeEvent e) -> {

				//setTxtTotalPanelFour();
			});
			if (component instanceof JLabel) {
				spinner.setValue(Integer.valueOf(((JLabel) component).getText()));
			} else if (value != null) {
				spinner.setValue(Integer.valueOf(value.toString()));
			}
			return spinner;
		}
		return null;
	}

}
