package br.senac.view;

import javax.swing.JPanel;
import javax.swing.JTable;

/**
 *
 * @author Douglas
 */
public class SaleScreen {

    private JPanel clientFilter;
    private JPanel clientTable;
    private JPanel productFilter;
    private JPanel productTable;
    private JPanel saleTable;
    private JPanel saleInfo;
    private JTable tblSale;
    private JTable tblProduct;
    private JTable tblclient;

    public SaleScreen() {
        initComponents();
    }
    
    private void initComponents(){
       
    }
    
    private JPanel getClientFilter(){
        clientFilter = new JPanel(null);
        return clientFilter;
    }
    
    private JPanel getClientTable(){
        clientTable = new JPanel(null);
        return clientTable;
    }
}
