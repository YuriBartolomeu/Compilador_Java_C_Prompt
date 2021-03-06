/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

//import static analizadores.AnalisadorLexico.listaSaidas;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import tokens.Out;

/**
 *
 * @author Dell-User
 */
public class JPanelAnalex extends javax.swing.JPanel {

    /**
     * Creates new form JPanelAnalex
     */
    public JPanelAnalex() {
        initComponents();
        DefaultTableModel modelo = (DefaultTableModel) AnalexTable.getModel();
        AnalexTable.setRowSorter(new TableRowSorter(modelo));
    }
    
   /* public static void carregarTabelaAnalex() {
        //Se não for o administrador vai carregar as duas tabelas do utilizador
        DefaultTableModel modelo = (DefaultTableModel) AnalexTable.getModel();

        //Para nao duplicar
        modelo.setNumRows(0);

        //Adicionando cada elemento na jTable
        for (Out saida : listaSaidas) {
            //Colocando na jTable
            modelo.addRow(new Object[]{
                saida.getLinha(),
                saida.getLexema(),
                saida.getToken()
            });

        }
    }*/

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        AnalexTable = new javax.swing.JTable();

        setBackground(new java.awt.Color(255, 255, 255));

        AnalexTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Linha", "Lexema", "Token"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(AnalexTable);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 970, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JTable AnalexTable;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
