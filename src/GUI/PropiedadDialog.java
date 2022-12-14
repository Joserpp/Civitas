package GUI;

import civitas.CasillaCalle;
import civitas.Jugador;

import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.DefaultListModel;

/**
 *
 * @author joserpp
 */
public class PropiedadDialog extends javax.swing.JDialog {

    private int propiedadElegida;
    
    public int getPropiedadElegida(){
    
        return propiedadElegida;
    }
    
    public PropiedadDialog(java.awt.Frame parent, Jugador jugador) {
        super(parent, true);
        initComponents();
        
        propiedadElegida = -1;
        
        DefaultListModel propiedades = new DefaultListModel<>();
        
        ArrayList<CasillaCalle> opciones = jugador.getPropiedades();
        
        for(CasillaCalle c : opciones){
        
            propiedades.addElement(c.getNombre());
        } // se completan los datos
        
        ListaPropiedades.setModel(propiedades); // se le dice a la lista cuáles 
                                                 // cuales son las gestiones
                                                 
        setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PROPIEDADES = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        ListaPropiedades = new javax.swing.JList<>();
        realizar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        PROPIEDADES.setBackground(new java.awt.Color(255, 51, 51));
        PROPIEDADES.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        PROPIEDADES.setForeground(new java.awt.Color(0, 0, 0));
        PROPIEDADES.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        PROPIEDADES.setText("PROPIEDADES");

        ListaPropiedades.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        ListaPropiedades.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ListaPropiedadesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(ListaPropiedades);

        realizar.setText("REALIZAR");
        realizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                realizarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1))
                    .addComponent(PROPIEDADES, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addComponent(realizar)
                .addContainerGap(74, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PROPIEDADES, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(realizar)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ListaPropiedadesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ListaPropiedadesMouseClicked
        propiedadElegida = ListaPropiedades.getSelectedIndex();
        dispose();
    }//GEN-LAST:event_ListaPropiedadesMouseClicked

    private void realizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_realizarActionPerformed
        
    }//GEN-LAST:event_realizarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList<String> ListaPropiedades;
    private javax.swing.JLabel PROPIEDADES;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton realizar;
    // End of variables declaration//GEN-END:variables
}
