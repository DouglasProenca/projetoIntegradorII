package br.senac.objects;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Douglas
 */
public class TableModel extends DefaultTableModel {

    private boolean[] canEdit;

    public TableModel(String[] colunas, int rows) {
        super(colunas, rows);
        canEdit = new boolean[colunas.length];
        for(int i = 0; i < colunas.length; i++){
            canEdit[i] = false;
        }
    }

    public TableModel(String[] colunas, int rows, boolean[] canEdit) {
        super(colunas, rows);
        this.canEdit = canEdit;
    }
    
    @Override
    public boolean isCellEditable(int rowIndex, int mColIndex) {
        return canEdit[mColIndex];
    }
}
