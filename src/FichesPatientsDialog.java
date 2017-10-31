
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.awt.event.KeyEvent.VK_DOWN;
import static java.awt.event.KeyEvent.VK_UP;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.ListIterator;
import java.util.Properties;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Fred
 */
public class FichesPatientsDialog extends java.awt.Dialog {

    /**
     * Creates new form FichesPatientsDialog
     */
    DefaultTableModel dm ;
    UtilDateModel model ;
    private int selectedID = 0 ;
    private JDatePickerImpl datePicker ;
            
    public FichesPatientsDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        //On formate un peu le tableau
        jTable.getColumnModel().getColumn(2).setPreferredWidth(20);
        jTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        //add elemnts
        //DefaultTableModel tableModel = (DefaultTableModel) patientsJPanel.jTable.getModel();
        jTable.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 13));
        jTable.setRowSelectionAllowed(true);
        //On cache la colonne id
        TableColumnModel tcm = jTable.getColumnModel();
        tcm.removeColumn( tcm.getColumn(3) );
        
        //Create date picker
        model = new UtilDateModel();
        Properties p = new Properties();
        p.put("text.today", "Aujourd'hui");
        p.put("text.month", "Mois");
        p.put("text.year", "Année");
        JDatePanelImpl datePanel = new JDatePanelImpl(model, p );
        datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
        datePicker.setBounds(jLastName.getLocation().x, jDN.getLocation().y-5, jLastName.getSize().width, jDN.getSize().height+10);
        //datePicker.setBounds(550, 200, 50, 20);
        datePicker.setVisible(true);
        datePicker.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                //System.out.println(ae.toString() +"\n"+ ae.getActionCommand()) ;
                //Calcul de l'âge...
                Calendar tdCal = Calendar.getInstance();
                Date dn = (Date) model.getValue();
                //System.out.println (dn) ;
                if (dn != null) {
                    Calendar dnCal = Calendar.getInstance();
                    dnCal.setTime(dn);
                    int age = tdCal.get(Calendar.YEAR) - dnCal.get(Calendar.YEAR) ;
                    if ( tdCal.get(Calendar.MONTH) < dnCal.get(Calendar.MONTH) )
                        age -- ;
                    jAge.setText ("(" + String.valueOf (age) +" ans)" ) ;
                }
                else
                    jAge.setText ("") ;
            }
        });
        add (datePicker) ;
        //On rempli le tableau
        majTableau ( 0 ) ;
        //On les classe
        sort () ;
        /*On ajoute un double click listener
        jTable.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent me) {
                if (me.getClickCount() == 2) {
                    System.out.println ("double click");  
                }
            }
        });*/
    }
    
    private void majTableau (int idSelected) {
        
        dm = (DefaultTableModel) jTable.getModel () ;
        dm.setRowCount(0);
        //Liste des patients
        ListIterator<Patient> lit = UserInfo.listePatients.listIterator() ;
        Patient pP ;
        while (lit.hasNext()) {
            pP = lit.next( ) ;
            dm.addRow(new Object[] { pP.nom, pP.prenom, pP.getAge(), pP.id } );
            /*if (pP.id == idSelected) {
                jTable.setRowSelectionInterval(jTable.getRowCount()-1, jTable.getRowCount()-1);
                jTableMouseClicked (null) ;
            }*/
        }
        //Select ?
        if (idSelected != 0) {
            int i = 0;
            while ((int) dm.getValueAt(i, 3) != idSelected) i++ ;
            int r = jTable.getRowSorter().convertRowIndexToView(i) ;
            jTable.setRowSelectionInterval(r, r);
            jTableMouseReleased (null) ;
        }
        else {
            jLastName.setText("");
            jFirstName.setText("");
            model.setValue (null) ;
            jAge.setText("(âge)") ;
            jPatientName.setText("(aucun)") ;
            selectedID = 0 ;
        }
    }
    
    class DateLabelFormatter extends AbstractFormatter {

        private String datePattern = "dd-MMM-yyyy";
        private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);


        @Override
        public Object stringToValue(String text) throws ParseException {
            return dateFormatter.parseObject(text);
        }

        @Override
        public String valueToString(Object value) throws ParseException {
            if (value != null) {
                Calendar cal = (Calendar) value;
                return dateFormatter.format(cal.getTime());
            }
            return "";
        }
}
    
    private void sort () {
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(dm) ;
        jTable.setRowSorter(sorter);
        sorter.toggleSortOrder(0);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFermer = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPatientName = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jDN = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLastName = new javax.swing.JTextField();
        jFirstName = new javax.swing.JTextField();
        jActif = new javax.swing.JRadioButton();
        jInactifs = new javax.swing.JRadioButton();
        jSeparator2 = new javax.swing.JSeparator();
        jAge = new javax.swing.JLabel();
        jCreate = new javax.swing.JButton();
        jDelete = new javax.swing.JButton();
        jSex = new javax.swing.JComboBox<>();

        setBackground(java.awt.Color.cyan);
        setMinimumSize(new java.awt.Dimension(654, 563));
        setResizable(false);
        setSize(new java.awt.Dimension(654, 563));
        setTitle("orthoEVA : fiches patients...");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });

        jFermer.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jFermer.setText("Fermer");
        jFermer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFermerActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Sélection patient :");

        jPatientName.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jPatientName.setForeground(new java.awt.Color(102, 102, 102));
        jPatientName.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jPatientName.setText("(aucun)");
        jPatientName.setDoubleBuffered(true);
        jPatientName.setFocusable(false);
        jPatientName.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jPatientName.setMaximumSize(new java.awt.Dimension(411, 40));
        jPatientName.setMinimumSize(new java.awt.Dimension(411, 40));
        jPatientName.setPreferredSize(new java.awt.Dimension(411, 40));
        jPatientName.setRequestFocusEnabled(false);
        jPatientName.setVerifyInputWhenFocusTarget(false);

        jTable.setBackground(new java.awt.Color(204, 255, 255));
        jTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nom", "Prénom", "Age", "id"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable.setFocusTraversalPolicyProvider(true);
        jTable.getTableHeader().setReorderingAllowed(false);
        jTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTableMouseReleased(evt);
            }
        });
        jTable.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jTablePropertyChange(evt);
            }
        });
        jTable.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTableKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTableKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jTable);

        jLabel2.setText("Nom :");

        jLabel3.setText("Prénom :");

        jDN.setText("Né(e) le :");

        jLastName.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jLastNamePropertyChange(evt);
            }
        });

        jActif.setBackground(java.awt.Color.cyan);
        jActif.setSelected(true);
        jActif.setText("Patients actifs");
        jActif.setActionCommand("");
        jActif.setFocusPainted(false);
        jActif.setFocusable(false);
        jActif.setRolloverEnabled(false);
        jActif.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jActifActionPerformed(evt);
            }
        });

        jInactifs.setBackground(java.awt.Color.cyan);
        jInactifs.setText("Patients inactifs");
        jInactifs.setActionCommand("");
        jInactifs.setEnabled(false);
        jInactifs.setFocusPainted(false);
        jInactifs.setFocusable(false);
        jInactifs.setRolloverEnabled(false);
        jInactifs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jInactifsActionPerformed(evt);
            }
        });

        jAge.setText("(âge)");

        jCreate.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jCreate.setText("Nouveau");
        jCreate.setToolTipText("Créer un nouveau patient");
        jCreate.setMaximumSize(new java.awt.Dimension(73, 23));
        jCreate.setMinimumSize(new java.awt.Dimension(73, 23));
        jCreate.setPreferredSize(new java.awt.Dimension(73, 23));
        jCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCreateActionPerformed(evt);
            }
        });

        jDelete.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jDelete.setText("Supprimer");
        jDelete.setToolTipText("Supprimer cette fiche");
        jDelete.setEnabled(false);
        jDelete.setMaximumSize(new java.awt.Dimension(73, 23));
        jDelete.setMinimumSize(new java.awt.Dimension(73, 23));
        jDelete.setPreferredSize(new java.awt.Dimension(73, 23));
        jDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jDeleteActionPerformed(evt);
            }
        });

        jSex.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Genre ?", "Garçon", "Fille" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPatientName, javax.swing.GroupLayout.PREFERRED_SIZE, 411, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jActif)
                                .addGap(37, 37, 37)
                                .addComponent(jInactifs)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(83, 83, 83)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jAge, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jSex, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLastName, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jDN)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel2)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jDelete, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jFermer, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jCreate, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(43, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jPatientName, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLastName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jDN)
                        .addGap(11, 11, 11)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jAge, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSex, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jCreate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jFermer, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 438, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jActif)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jInactifs)
                        .addComponent(jDelete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(22, Short.MAX_VALUE))
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
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        //Check for changes...
        if (selectedID != 0) {
            Patient pP = (Patient) OrthoVS.user.listePatients.get(jTable.getRowSorter().convertRowIndexToModel(jTable.getSelectedRow())) ;
            Date newDate = (Date) model.getValue() ;
            Calendar dnCal = Calendar.getInstance();
            dnCal.setTime(newDate);
            dnCal.set(Calendar.HOUR, 0);
            dnCal.set(Calendar.HOUR_OF_DAY, 0);
            dnCal.set(Calendar.MINUTE, 0);
            dnCal.set(Calendar.SECOND, 0);
            dnCal.set(Calendar.MILLISECOND, 0);
            
            if (! pP.nom.equals(jLastName.getText()) || ! pP.prenom.equals(jFirstName.getText()) || (! pP.dn.equals (dnCal.getTime()) || (pP.sex != jSex.getSelectedIndex())) ) {
                OrthoVS.mySQLConnection.updatePatient(selectedID, jLastName.getText(), jFirstName.getText(), jSex.getSelectedIndex(), newDate);
                OrthoVS.mySQLConnection.loadPatientsList();
                majTableau ( selectedID ) ;
            }
        }
        //Select or close ?
        if ( ! jFermer.getText().equalsIgnoreCase("Fermer")) {
            //S'il existe un patient actif et des données à sauver on attend...
            if (UserInfo.currentPatient != 0) while (UserInfo.areThereModifiedResults () ) {
                try { Thread.sleep (100) ;} catch (Exception e) {}
            }
            OrthoVS.user.activatePatient(selectedID);
        }
        else UserInfo.currentPatient = 0 ;
        setVisible(false);
        dispose();
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_jFermerActionPerformed

    private void jTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableMouseClicked
        if (evt.getClickCount() == 2)
            jFermerActionPerformed (null) ;
    }//GEN-LAST:event_jTableMouseClicked

    private void jTableMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableMouseReleased
        Patient pP = (Patient) OrthoVS.user.listePatients.get(jTable.getRowSorter().convertRowIndexToModel(jTable.getSelectedRow())) ;
        Calendar cal = Calendar.getInstance();
        cal.setTime(pP.dn);
        //System.out.println (jTable.getSelectedRow() + " -> " + jTable.getRowSorter().convertRowIndexToModel(jTable.getSelectedRow())) ; 
        //System.out.println (jTable.getSelectedRow() + " " + rowIdx + " "+ jTable.getModel().getValueAt(jTable.convertRowIndexToView(jTable.getSelectedRow()), 0)) ;
        String lName = (String) jTable.getModel().getValueAt(jTable.getRowSorter().convertRowIndexToModel(jTable.getSelectedRow()), 0) + " " +
                    jTable.getModel().getValueAt(jTable.getRowSorter().convertRowIndexToModel(jTable.getSelectedRow()), 1) ;
        jPatientName.setText(lName);
        jLastName.setText ((String) jTable.getModel().getValueAt(jTable.getRowSorter().convertRowIndexToModel(jTable.getSelectedRow()), 0)) ;
        jFirstName.setText ((String) jTable.getModel().getValueAt(jTable.getRowSorter().convertRowIndexToModel(jTable.getSelectedRow()), 1)) ;
        model.setDate(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
        model.setSelected(true);
        //Affichage de l'âge
        jAge.setText ("(" + String.valueOf (pP.getAge()) +" ans)" ) ;
        jSex.setSelectedIndex(pP.sex);
        //On ne peut plus créer
        jCreate.setEnabled(false);
        jDelete.setEnabled(true);
        jFermer.setText("Sélectionner");
        selectedID = (int) jTable.getModel().getValueAt(jTable.getRowSorter().convertRowIndexToModel(jTable.getSelectedRow()), 3) ;
    }//GEN-LAST:event_jTableMouseReleased

    private void jTablePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jTablePropertyChange
        
    }//GEN-LAST:event_jTablePropertyChange

    private void jTableKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTableKeyPressed
        
    }//GEN-LAST:event_jTableKeyPressed

    private void jTableKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTableKeyReleased
        int code = evt.getKeyCode () ;
        if (code == VK_UP | code == VK_DOWN) jTableMouseClicked(null) ;
    }//GEN-LAST:event_jTableKeyReleased

    private void jActifActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jActifActionPerformed
            jActif.setSelected (true) ;
            jInactifs.setSelected(false);
    }//GEN-LAST:event_jActifActionPerformed

    private void jInactifsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jInactifsActionPerformed
            jActif.setSelected (false) ;
            jInactifs.setSelected(true);
    }//GEN-LAST:event_jInactifsActionPerformed

    private void jCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCreateActionPerformed
        
        int rID ;
        
        //Date de naissance
        Date date = (Date) model.getValue();
        int sex = jSex.getSelectedIndex() ;
        //Pas de champs vides :
        if (date == null || jLastName.getText().isEmpty() || jFirstName.getText().isEmpty() || jSex.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Attention ! Vous devez compléter :\n nom, prénom, date de naissance et genre !\n\n Création annulée !",
                    "Création d'un patient :", JOptionPane.INFORMATION_MESSAGE);
        }
        else if ( (rID = OrthoVS.mySQLConnection.addNewPatient(jLastName.getText(), jFirstName.getText(), sex, date)) == 0)
            JOptionPane.showMessageDialog(this, "Erreur ! Création dans la base serveur impossible !", "Création d'un patient :", JOptionPane.ERROR_MESSAGE);
        else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            jCreate.setEnabled(false);
            OrthoVS.mySQLConnection.loadPatientsList();
            majTableau ( rID ) ;
            this.setCursor(Cursor.getDefaultCursor());
            jFermerActionPerformed (null) ;
        }
    }//GEN-LAST:event_jCreateActionPerformed

    private void jLastNamePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jLastNamePropertyChange
        
    }//GEN-LAST:event_jLastNamePropertyChange

    private void jDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jDeleteActionPerformed
        String n = jLastName.getText() + " " + jLastName.getText() ;
        int r = JOptionPane.showConfirmDialog (this, "Voulez-vous supprimer cette fiche définitivement ?" + "\n("  + n +")\n ",
            "Fiches patients :", JOptionPane.OK_CANCEL_OPTION) ;
        if (r == JOptionPane.OK_OPTION) {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            OrthoVS.mySQLConnection.deletePatient(selectedID) ;
            OrthoVS.mySQLConnection.loadPatientsList();
            majTableau ( 0 ) ;
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_jDeleteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton jActif;
    private javax.swing.JLabel jAge;
    private javax.swing.JButton jCreate;
    private javax.swing.JLabel jDN;
    private javax.swing.JButton jDelete;
    private javax.swing.JButton jFermer;
    private javax.swing.JTextField jFirstName;
    private javax.swing.JRadioButton jInactifs;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField jLastName;
    private javax.swing.JLabel jPatientName;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JComboBox<String> jSex;
    public static javax.swing.JTable jTable;
    // End of variables declaration//GEN-END:variables
}
