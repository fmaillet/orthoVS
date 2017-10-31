
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Fred
 */
public class DialogJournal extends java.awt.Dialog {

    /**
     * Creates new form DialogJournal
     */
    public DialogJournal(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }
    
    
    public void addJournal (String evt) {
        LocalTime hour = ZonedDateTime.now().toLocalTime().truncatedTo(ChronoUnit.SECONDS);
        jTexte.append(hour + ": " + evt + "\n");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTexte = new javax.swing.JTextArea();
        jFermer = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setBackground(java.awt.Color.cyan);
        setResizable(false);
        setTitle("Journal...");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });

        jTexte.setEditable(false);
        jTexte.setColumns(20);
        jTexte.setRows(5);
        jScrollPane1.setViewportView(jTexte);

        jFermer.setLabel("Fermer");
        jFermer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFermerActionPerformed(evt);
            }
        });

        jLabel1.setText("Journal des événements :");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(162, 162, 162)
                .addComponent(jFermer, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(169, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jFermer)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Closes the dialog
     */
    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
        setVisible(false);
        dispose();
    }//GEN-LAST:event_closeDialog

    private void jFermerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFermerActionPerformed
        setVisible(false);
        dispose();
    }//GEN-LAST:event_jFermerActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        
        //jTexte.append("hello\n");
    }//GEN-LAST:event_formWindowActivated

    /**
     * @param args the command line arguments
     */
    


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jFermer;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTextArea jTexte;
    // End of variables declaration//GEN-END:variables
}