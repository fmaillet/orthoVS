
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import static java.awt.event.KeyEvent.VK_ESCAPE;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import static java.lang.Thread.sleep;
import java.util.Date;
import java.util.LinkedList;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.util.Random;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Fred
 */
public class ParamsRotation2D extends javax.swing.JPanel {

    /**
     * Creates new form ParamsSaccade
     */
    JLabel icone ;
        
    public ParamsRotation2D(JLabel icone) {
        initComponents();
        this.icone = icone ;
        if (OrthoVS.user.nom != null) {
            jGridSize.setEnabled (true) ;
            jAmplitude.setEnabled (true) ;
            jDurée.setEnabled (true) ;
            //jDurée.setValue (1) ;
            jGridSize.setSelectedIndex(6);
            jAmplitude.setSelectedIndex(5);
            //jLabelGrid.setEnabled (true) ;
            //jGridSize.setSelectedIndex(1);
        }
        else {
            jGridSize.setSelectedIndex(1);
            jGridSize.setEnabled (false) ;
            jAmplitude.setEnabled (false) ;
            //jLabelGrid.setEnabled (false) ;
            jGridSize.setSelectedIndex(1);
            jAmplitude.setSelectedIndex(1);
            jDurée.setEnabled (false) ;
            jDurée.setValue (1) ;
        }
        addComponentListener ( new ComponentAdapter () {
            public void componentShown ( ComponentEvent e )
            {
                icone.setLocation( OrthoVS.fen.getContentPane ().getWidth()-300, 40);
            }

            public void componentHidden ( ComponentEvent e )
            {
                //icone.setVisible(false);
            }
        } );    
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTitle = new javax.swing.JLabel();
        jDurée = new javax.swing.JSpinner();
        jSeparator2 = new javax.swing.JSeparator();
        jManualStart = new javax.swing.JButton();
        jAutoStar = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel7 = new javax.swing.JLabel();
        jTrophy = new javax.swing.JSpinner();
        jGridSize = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        jLabelGrid = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel8 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabelGrid1 = new javax.swing.JLabel();
        jAmplitude = new javax.swing.JComboBox<>();

        setBackground(new java.awt.Color(255, 255, 255));
        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(431, 513));

        jTitle.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jTitle.setForeground(new java.awt.Color(102, 102, 102));
        jTitle.setText("Rotation 2D");

        jDurée.setModel(new javax.swing.SpinnerNumberModel(3, 1, 15, 1));
        jDurée.setToolTipText("Durée de la série (en mn)");
        jDurée.setOpaque(false);
        jDurée.setPreferredSize(new java.awt.Dimension(35, 20));
        jDurée.setValue(1);

        jManualStart.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jManualStart.setText("Démarrer");
        jManualStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jManualStartActionPerformed(evt);
            }
        });

        jAutoStar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jAutoStar.setText("Progression auto");
        jAutoStar.setEnabled(false);
        jAutoStar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAutoStarActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setText("Un trophé pour ");

        jTrophy.setModel(new javax.swing.SpinnerNumberModel(6, 2, 10, 1));
        jTrophy.setOpaque(false);

        jGridSize.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "50", "60", "70", "80", "90", "100", "110", "120", "130", "140", "150" }));
        jGridSize.setSelectedIndex(4);
        jGridSize.setToolTipText("Taille de la grille");

        jLabel12.setText("(1 à 15 minutes)");
        jLabel12.setEnabled(false);

        jLabelGrid.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabelGrid.setText("Taille Item :");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("Durée de la série :");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setText("items");

        jLabel13.setText("(pixels)");
        jLabel13.setEnabled(false);

        jLabelGrid1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabelGrid1.setText("Amplitude angulaire :");

        jAmplitude.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Aucune", "0 à 45°", "0 à 90°", "0 à 180°", "0 à 270°", "0 à 360°" }));
        jAmplitude.setToolTipText("Taille de la grille");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jTitle)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator2)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelGrid)
                                    .addComponent(jLabelGrid1))
                                .addGap(25, 25, 25)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jGridSize, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel13))
                                    .addComponent(jAmplitude, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addComponent(jManualStart)
                                .addGap(26, 26, 26)
                                .addComponent(jAutoStar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jSeparator3)
                            .addComponent(jSeparator4)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jTrophy, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel8))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addGap(47, 47, 47)
                                        .addComponent(jDurée, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel12)))
                                .addGap(0, 88, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTitle)
                .addGap(36, 36, 36)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jGridSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelGrid)
                    .addComponent(jLabel13))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelGrid1)
                    .addComponent(jAmplitude, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(48, 48, 48)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jDurée, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addGap(24, 24, 24)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jTrophy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(21, 21, 21)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jManualStart)
                    .addComponent(jAutoStar))
                .addContainerGap(124, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jManualStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jManualStartActionPerformed
        int itemSize = (Integer) 50 + jGridSize.getSelectedIndex() * 10 ;
        int degres = jAmplitude.getSelectedIndex() ;
        int nbGrilles   = (Integer) jTrophy.getValue() ;
        int durée =  (Integer) jDurée.getValue() ;
        //on lance l'activité
        OrthoVS.fen.enableMenuBar(false);
        //OrthoVS.fen.setExtendedState(OrthoVS.fen.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        //OrthoVS.fen.repaint () ;
        
        LaunchRotation2D l = new LaunchRotation2D (this, itemSize, degres, durée, nbGrilles, icone) ;
    }//GEN-LAST:event_jManualStartActionPerformed

    private void jAutoStarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAutoStarActionPerformed
        /*int n = (Integer) spinNbCycles.getValue();
        int sp   = Integer.parseInt((String) jSpeed.getSelectedItem());
        //on lance l'activité
        LaunchFixation test = new LaunchFixation ( n, sp, jManuel.isSelected(), true ) ;
        test.start () ;*/
    }//GEN-LAST:event_jAutoStarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> jAmplitude;
    private javax.swing.JButton jAutoStar;
    public static javax.swing.JSpinner jDurée;
    private javax.swing.JComboBox<String> jGridSize;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel5;
    public static javax.swing.JLabel jLabel7;
    public static javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabelGrid;
    private javax.swing.JLabel jLabelGrid1;
    private javax.swing.JButton jManualStart;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    public static javax.swing.JLabel jTitle;
    public static javax.swing.JSpinner jTrophy;
    // End of variables declaration//GEN-END:variables
}

class LaunchRotation2D extends Thread implements ActionListener, KeyListener {
    
    Thread t ;    
    JPanel p ;
    int PANEL_SIZE = 300 ;
    int itemSize, durée, amplitude ;
    static int nbGrillesForTrophy ;
    JLabel jIconRotation2D ;
    
    static boolean notFin = true ;
    
    //Eléments affichés
    CadreSimple cadre ;
    JButton jNormal, jMiroir ;
    
    //Paramètres
    Random rand = new Random () ;
    boolean escPressed, nextItem ;
    Score score ;
    
    //Trphés
    static JLabel trophy[] ;
    static int trophyNumber = 0 ;
    static int threeCount = 0 ;
    static SoundClips snd ;
    
    //Progression de jeu
    static public boolean newGame = false ;
    JButton jTime ;
    JProgressBar progressBar ;
    
    LaunchRotation2D (JPanel p, int itemSize, int amplitude, int durée, int nbGrilles, JLabel icon) {
        this.p = p ;
        this.itemSize = itemSize ;
        this.amplitude = amplitude ;
        this.durée = durée * 60 ;
        this.nbGrillesForTrophy = nbGrilles ;
        this.jIconRotation2D = icon ;
        
        escPressed = false ;
        
        //On cache les menus
        //OrthoVS.fen.enableMenuBar(false);
        OrthoVS.fen.setExtendedState(OrthoVS.fen.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        OrthoVS.fen.jPatient.setVisible (false) ;
        OrthoVS.fen.rotation2DParam.setVisible(false);
        OrthoVS.fen.chartPanelOK.setVisible(false);
        OrthoVS.fen.validate () ;
        //OrthoVS.fen.chartPanelOK.setVisible(false);
        
        //Cadre
        cadre = new CadreSimple (PANEL_SIZE, itemSize, amplitude) ;
        cadre.setLocation((OrthoVS.fen.getContentPane().getWidth()/2-(PANEL_SIZE)/2), (OrthoVS.fen.getContentPane().getHeight()/2-(PANEL_SIZE)));
        OrthoVS.fen.getContentPane().add (cadre) ;
        cadre.validate () ;
        //Les deux boutons
        jNormal = new JButton ("Normal") ;
        jNormal.setBackground(Color.GREEN); jNormal.setFont(new java.awt.Font("Tahoma", 1, 18));
        jNormal.setBounds(cadre.getX(), cadre.getY() + cadre.getHeight() + 10, cadre.getWidth()/2-2, 35);
        jNormal.setVisible(true); jNormal.addActionListener(this);
        OrthoVS.fen.getContentPane().add (jNormal) ;
        jMiroir = new JButton ("Miroir") ;
        jMiroir.setBackground(Color.RED.brighter()); jMiroir.setFont(new java.awt.Font("Tahoma", 1, 18));
        jMiroir.setBounds(jNormal.getX()+jNormal.getWidth()+4, cadre.getY() + cadre.getHeight() + 10, cadre.getWidth()/2-2, 35);
        jMiroir.setVisible(true); jMiroir.addActionListener(this);
        OrthoVS.fen.getContentPane().add (jMiroir) ;

        //Icon rotation2D
        this.jIconRotation2D.setLocation( OrthoVS.fen.getContentPane ().getWidth()-300, 40) ;
        //Icon time
        jTime = new JButton ( UserInfo.iconTime ) ;
        jTime.setBorderPainted(false);
        jTime.setContentAreaFilled(false);
        jTime.setBounds(cadre.getX() - 35 , cadre.getY()-56, 32, 32);
        OrthoVS.fen.getContentPane ().add (jTime) ;
        jTime.setVisible(true);
        //ProgressBar
        progressBar = new JProgressBar();
        progressBar.setMaximum(this.durée);
        progressBar.setValue(0);
        progressBar.setBounds(cadre.getX(), cadre.getY()-50, cadre.getWidth(), 20);
        OrthoVS.fen.getContentPane().add (progressBar) ;
        /*Border border = BorderFactory.createTitledBorder("Reste...");
        progressBar.setBorder(border);*/
        progressBar.setOpaque(false);
        progressBar.setForeground(Color.PINK);
        
        
        //On écoute le clavier
        OrthoVS.fen.addKeyListener (this) ;
        
        //On lance le thread
        t = new Thread (this, "launchSymetry") ;
        t.start ( ) ;
    }
    
    @Override
    public void run () {
        
        //Create trophy
        trophy = new JLabel[5] ;
        for (int i=0; i<5; i++) {
            trophy[i] = new JLabel() ;
            trophy[i].setIcon(new ImageIcon(MainFenetre.tinyTrophy));
            trophy[i].setBounds(20, 100 + 85*i, 64,64) ;
            OrthoVS.fen.getContentPane().add(trophy[i]) ;
            trophy[i].setEnabled(false);
        }
        trophyNumber = 0 ;
        
        //On redessine
        OrthoVS.fen.repaint () ;
        //originalGrid.requestFocusInWindow(); //necessary for ESC
               
        notFin = true ;  
        //Score
        score = new Score () ;
        score.level = itemSize ;
        score.reponse = 0 ;
        //Début
        long tempsDebut = score.tr_i = System.currentTimeMillis();
        //On lance le timer
        DrawTimer timerThrd = new DrawTimer (progressBar, tempsDebut, this.durée) ;
        //On boucle
        do {
            OrthoVS.fen.requestFocus();
            cadre.resetValue();
            nextItem = false ;
                        
            //On attend la saisie de l'utilisateur
            do {
                try { sleep ( 150 ) ;} catch (Exception e) {}
                if (escPressed) break ;
            } while (! nextItem) ;
            
            //Si appui sur ESC
            if (escPressed) break ;
            
            //C'est la fin ?
            float seconds = (System.currentTimeMillis() - tempsDebut) / 1000F;
            //progressBar.setValue((int) seconds);
            if (seconds > durée) notFin = false ;
            //On laisse un peu la réponse...
            try { sleep ( 100 ) ;} catch (Exception e) {}
        } while (notFin) ;
        //temps de fin
        score.tr_f = System.currentTimeMillis() ;
        //Arrêt de la barre de progression
        timerThrd.interrupt();
        
        //On supprime les trucs inutiles
        OrthoVS.fen.getContentPane().remove(cadre);
        OrthoVS.fen.getContentPane().remove(jNormal);
        OrthoVS.fen.getContentPane().remove(jMiroir);
        OrthoVS.fen.getContentPane().remove(progressBar);
        OrthoVS.fen.getContentPane().remove(jTime);
        //OrthoVS.fen.getContentPane().remove(jButterfly);
        for (int i=0; i<trophy.length; i++)
            OrthoVS.fen.getContentPane().remove(trophy[i]) ;
        
        //Calcul du score
        Session session = new Session () ;
        session.date = new Date () ;
        session.gridSize = itemSize ; ;
        LinkedList<Score> results = new LinkedList<Score> () ;
        results.add(score); 
        session.results = results ;
        //On ajoute à l'ensemble des résultats
        UserInfo.resultatsRotation2D.add(session);
        UserInfo.modifiedResultatsRotation2D = true ;
            
        //On applaudi
        snd = new SoundClips (3) ; //applauses
        snd.start () ;
        
        //gridExplode() ;
        //try { sleep ( 2500 ) ;} catch (Exception e) {}
        //On stoppe les timers
        //executor.shutdownNow() ;
        
        //OrthoVS.fen.repaint () ;
        
        OrthoVS.fen.setExtendedState(JFrame.NORMAL);
        for (int i=0; i<trophy.length; i++)
            OrthoVS.fen.getContentPane().remove(trophy[i]) ;
        OrthoVS.fen.enableMenuBar(true);
        OrthoVS.fen.jPatient.setVisible (true) ;
        OrthoVS.fen.chartPanelOK.setVisible(true);
        OrthoVS.fen.computeChartsRotation2D(true);
        p.setVisible (true) ;
        
        OrthoVS.fen.repaint () ;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton b = (JButton) e.getSource() ;
        
        //Bonne réponse ?
        if ( (b == jNormal && cadre.isOrientedNormal) || (b == jMiroir && ! cadre.isOrientedNormal)) {
            cadre.setBackground(Color.GREEN);
            score.reponse++ ;
            snd = new SoundClips (4) ; //GOOD
            snd.start () ;
            //Trophy ?
            threeCount++ ;
            if (threeCount == nbGrillesForTrophy & trophyNumber<5) {
                trophy[trophyNumber++].setEnabled(true);
                threeCount = 0 ;
            }
        }
        else {
            cadre.setBackground(Color.RED.brighter());
            snd = new SoundClips (5) ; //GOOD
            snd.start () ;
        }
        nextItem = true ;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //score.tr_f = System.currentTimeMillis() - tempsDebut ;
        int code = e.getKeyCode () ;
        if (code == VK_ESCAPE) {    //Sortir de la boucle de test, revenir à l'écran de paramétrage
            escPressed = true ;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

class CadreSimple extends JPanel {
    int itemSize, panelSize, amplitude ;
    
    boolean isOrientedNormal ;
    Random rand ;
    String item ;
    BufferedImage grid;  // declare the image
    Graphics2D g2 ;
    int angle = 0 ;
    Font f ;
    
    String items = "RPFG" ;
    
    public CadreSimple (int panelSize, int itemSize, int amplitude) {
        this.itemSize = itemSize ;
        this.panelSize = panelSize ;
        switch(amplitude) {
            case 0 : this.amplitude = 0   ; break ;
            case 1 : this.amplitude = 45  ; break ;
            case 2 : this.amplitude = 90  ; break ;
            case 3 : this.amplitude = 180 ; break ;
            case 4 : this.amplitude = 270 ; break ;
            default: this.amplitude = 380 ; break ;
        }
        this.setSize(panelSize, panelSize);
        setBackground(Color.CYAN);
        item = "A" ;
        rand = new Random () ;
        
        //Les trucs par défaut pour dessiner le caractère
        f = new Font(Font.SANS_SERIF, Font.PLAIN, itemSize);
        grid = new BufferedImage(this.getWidth(),this.getHeight(), BufferedImage.TYPE_INT_ARGB) ;
        g2 = (Graphics2D) grid.getGraphics() ;
        g2.setBackground(new Color(0, 0, 0, 0));
        g2.setFont(f);
        g2.setPaint(Color.BLACK);
        
        //On affiche
        setVisible (true) ;
        resetValue (); 
    }
    
    public void resetValue () {
        //On reforce le fond après avoir mis du vert ou du rouge
        setBackground(Color.CYAN);
        //Miroir ou pas ?        
        isOrientedNormal = rand.nextBoolean() ;
        //Choix du caractère
        int idx  = rand.nextInt(items.length()) ;
        char s = items.charAt(idx) ;
        item = String.valueOf(s) ;
        
        //On efface et dessine le caractère
        g2.clearRect(0, 0, panelSize, panelSize);
        g2.drawString(item, grid.getWidth()/2 - g2.getFontMetrics().stringWidth(item)/2, grid.getHeight()/2 + g2.getFontMetrics().getAscent() / 2);
        //Amplitude angulaire
        if (amplitude > 0)
            angle = rand.nextInt(this.amplitude) ;
        else
            angle = 0 ;
    }
    
    
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            
            //On fait tourner le caractère
            g2.rotate(Math.toRadians(angle), panelSize / 2, panelSize / 2);
            //Miroir ou pas
            if (isOrientedNormal)
                g2.drawRenderedImage(grid, null);
            else
                g2.drawImage(grid, panelSize, 0, -panelSize, panelSize, null);
            
        }
}