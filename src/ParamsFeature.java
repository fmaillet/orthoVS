
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import static java.awt.event.KeyEvent.VK_ESCAPE;
import java.awt.event.KeyListener;
import java.util.Date;
import java.util.LinkedList;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.util.Random;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import static java.lang.Thread.sleep;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Fred
 */
public class ParamsFeature extends javax.swing.JPanel {

    /**
     * Creates new form ParamsSaccade
     */
    public ParamsFeature() {
        initComponents();
        if (OrthoVS.user.nom != null) {
            jGridSize.setEnabled (true) ;
            jDurée.setEnabled (true) ;
            jDurée.setValue (2) ;
            //jLabelGrid.setEnabled (true) ;
            //jGridSize.setSelectedIndex(1);
        }
        else {
            jGridSize.setEnabled (false) ;
            //jLabelGrid.setEnabled (false) ;
            jGridSize.setSelectedIndex(1);
            jDurée.setEnabled (false) ;
            jDurée.setValue (1) ;
        }
            
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
        jLevel = new javax.swing.JSpinner();
        jGridSize = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        jLabelGrid = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel8 = new javax.swing.JLabel();
        jDifficulty = new javax.swing.JSpinner();

        setBackground(new java.awt.Color(255, 255, 255));
        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(431, 513));

        jTitle.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jTitle.setForeground(new java.awt.Color(102, 102, 102));
        jTitle.setText("Discrimination :");

        jDurée.setModel(new javax.swing.SpinnerNumberModel(2, 1, 15, 1));
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
        jLabel7.setText("Niveau initial :");

        jLevel.setModel(new javax.swing.SpinnerNumberModel(2, 2, 20, 1));
        jLevel.setOpaque(false);

        jGridSize.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " 3 x 3", " 4 x 4", " 5 x 5" }));
        jGridSize.setSelectedIndex(1);
        jGridSize.setToolTipText("Taille de la grille");

        jLabel12.setText("(1 à 15 minutes)");
        jLabel12.setEnabled(false);

        jLabelGrid.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabelGrid.setText("Taille Grille :");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("Durée de la série :");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setText("Difficulté :");

        jDifficulty.setModel(new javax.swing.SpinnerNumberModel(1, 1, 3, 1));
        jDifficulty.setOpaque(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jManualStart)
                .addGap(26, 26, 26)
                .addComponent(jAutoStar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jTitle)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator2, javax.swing.GroupLayout.DEFAULT_SIZE, 411, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabelGrid)
                                            .addComponent(jLabel5))
                                        .addGap(47, 47, 47)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jDurée, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jGridSize, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel12))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel7)
                                            .addComponent(jLabel8))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jDifficulty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLevel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator3))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator4)))
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
                    .addComponent(jLabelGrid))
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jDurée, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addGap(24, 24, 24)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLevel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jDifficulty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jManualStart)
                    .addComponent(jAutoStar))
                .addContainerGap(170, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jManualStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jManualStartActionPerformed
        int size = (Integer) jGridSize.getSelectedIndex() +3 ;
        int level   = (Integer) jLevel.getValue() ;
        int durée =  (Integer) jDurée.getValue() ;
        //on lance l'activité
        LaunchFeature l = new LaunchFeature (this, size, durée, level, (Integer) jDifficulty.getValue()) ;
    }//GEN-LAST:event_jManualStartActionPerformed

    private void jAutoStarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAutoStarActionPerformed
        /*int n = (Integer) spinNbCycles.getValue();
        int sp   = Integer.parseInt((String) jSpeed.getSelectedItem());
        //on lance l'activité
        LaunchFixation test = new LaunchFixation ( n, sp, jManuel.isSelected(), true ) ;
        test.start () ;*/
    }//GEN-LAST:event_jAutoStarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jAutoStar;
    static javax.swing.JSpinner jDifficulty;
    public static javax.swing.JSpinner jDurée;
    private javax.swing.JComboBox<String> jGridSize;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel5;
    public static javax.swing.JLabel jLabel7;
    public static javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabelGrid;
    static javax.swing.JSpinner jLevel;
    private javax.swing.JButton jManualStart;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    public static javax.swing.JLabel jTitle;
    // End of variables declaration//GEN-END:variables
}

class LaunchFeature extends Thread implements ActionListener, KeyListener {
    
    Thread t ;    
    JPanel p ;
    int size, durée, level, difficulty ;
    JProgressBar progressBar ;
    JButton jTime, ok, nok ;
    JLabel jScore, jClipart ;
    
    DoubleCadre grilleL, grilleR ;
    Random rand ;
    boolean pareils, nextGrid, escPressed ;
    Score score ;
    static SoundClips snd ;
    long tempsDebut ;
    
    ChartPanel chartPanelTEMP ;
    XYSeriesCollection xySeriesCollection ;
    XYSeries serieBAD ;
    
    LaunchFeature (JPanel p, int size, int durée, int level, int difficulty) {
        this.p = p ;
        this.size = size ;
        this.durée = durée * 60 ;
        this.level = level ;
        this.difficulty = difficulty ;
        rand = new Random () ;
        escPressed = false ;
               
        //On cache les menus
        OrthoVS.fen.enableMenuBar(false);
        OrthoVS.fen.jPatient.setVisible (false) ;
        OrthoVS.fen.chartPanelOK.setVisible(false);
        OrthoVS.fen.chartPanelBAD.setVisible(false);
        //ProgressBar
        progressBar = new JProgressBar();
        progressBar.setMaximum(this.durée);
        progressBar.setValue(0);
        OrthoVS.fen.getContentPane().add (progressBar) ;
        /*Border border = BorderFactory.createTitledBorder("Reste...");
        progressBar.setBorder(border);*/
        progressBar.setOpaque(false);
        progressBar.setForeground(Color.PINK);
        //Icon time
        jTime = new JButton ( UserInfo.iconTime ) ;
        jTime.setBorderPainted(false);
        jTime.setContentAreaFilled(false);
        OrthoVS.fen.getContentPane ().add (jTime) ;
        jTime.setVisible(true);
        //Clipart
        //Icon monkey
        jClipart = new JLabel ( UserInfo.iconFeature ) ;
        //jClipart.setBorderPainted(false);
        //jClipart.setContentAreaFilled(false);
        
        jClipart.setVisible(true);
        
        //On affiche les deux cadres
        grilleL = new DoubleCadre (size) ;
        grilleL.setBounds((OrthoVS.fen.getContentPane().getWidth()/2-(size*50))-2, (OrthoVS.fen.getContentPane().getHeight()-(size*50))/2, size*50, size*50);
        OrthoVS.fen.getContentPane().add (grilleL) ;
        grilleL.validate () ;

        grilleR = new DoubleCadre (size) ;
        grilleR.setBounds((OrthoVS.fen.getContentPane().getWidth()/2)+2, (OrthoVS.fen.getContentPane().getHeight()-(size*50))/2, size*50, size*50);
        OrthoVS.fen.getContentPane().add (grilleR) ;
        grilleR.validate () ;
        
        //Les deux boutons
        ok = new JButton ("Pareil") ;
        ok.setBackground(Color.GREEN); ok.setFont(new java.awt.Font("Tahoma", 1, 18));
        ok.setBounds(grilleL.getX(), grilleL.getY() + grilleL.getHeight() + 10, grilleL.getWidth(), 35);
        //ok.setBorder(BorderFactory.createRaisedBevelBorder());
        ok.setVisible(true); ok.addActionListener(this);
        OrthoVS.fen.getContentPane().add (ok) ;
        nok = new JButton ("Pas Pareil") ;
        nok.setBackground(Color.RED.brighter()); nok.setFont(new java.awt.Font("Tahoma", 1, 18));
        nok.setBounds(grilleR.getX(), grilleR.getY() + grilleR.getHeight() + 10, grilleR.getWidth(), 35);
        nok.setVisible(true); nok.addActionListener(this);
        OrthoVS.fen.getContentPane().add (nok) ;
                
        //Graphique
        xySeriesCollection = new XYSeriesCollection();
        JFreeChart chartBAD = ChartFactory.createXYLineChart("", "", "", xySeriesCollection,
            PlotOrientation.VERTICAL, false, false, false);
        
        chartBAD.removeLegend();
        chartPanelTEMP = new ChartPanel( chartBAD ) ;
        OrthoVS.fen.getContentPane ().add (chartPanelTEMP) ;
        XYPlot plot = (XYPlot) chartBAD.getPlot() ;
        XYLineAndShapeRenderer xyLineAndShapeRenderer = new XYLineAndShapeRenderer() ;
        xyLineAndShapeRenderer.setSeriesPaint(0, Color.YELLOW) ;
        plot.setRenderer(xyLineAndShapeRenderer);
        ValueAxis domain = plot.getDomainAxis() ; domain.setVisible(false);
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        chartPanelTEMP.setPopupMenu(null);
        chartPanelTEMP.setDomainZoomable(false);
        chartPanelTEMP.setRangeZoomable(false);
        chartBAD.setBackgroundPaint(new Color(0,204 , 204));
        
        chartPanelTEMP.setVisible(true);
        //On initialise le tracé
        serieBAD = new XYSeries("% inversions");
        xySeriesCollection.addSeries(serieBAD);
        //Le score
        jScore = new JLabel ("") ;
        OrthoVS.fen.getContentPane ().add (jScore) ;
        jScore.setFont(new java.awt.Font("Tahoma", 1, 16));
        jScore.setForeground(Color.GRAY);
        //jScore.setOpaque(false);
        jScore.setVisible(true);
        OrthoVS.fen.getContentPane ().add (jClipart) ;
        //On écoute le clavier
        OrthoVS.fen.addKeyListener (this) ;
        //On lance le thread
        t = new Thread (this, "launchFeature") ;
        t.start ( ) ;
    }
    
    @Override
    public void run () {
        //On prépare les données
        LinkedList<Score> results = new LinkedList<Score> () ;
        
        //On cache ce qu'il faut cacher
        p.setVisible (false);
        
        //La progressBar
        progressBar.setBounds(grilleL.getX(), grilleL.getY()-50, grilleR.getWidth() + grilleL.getWidth() + 4, 20);
        jTime.setBounds(grilleL.getX() - 35 , grilleL.getY()-56, 32, 32);
        chartPanelTEMP.setBounds(grilleR.getX() + grilleR.getWidth() + 30, nok.getY() + - 80, 250, 120);
        jScore.setBounds(grilleR.getX() + grilleR.getWidth() + 95 , grilleR.getY() + grilleR.getHeight() - 163 , 200, 20);
        jClipart.setBounds(grilleR.getX() + grilleR.getWidth() + 65, nok.getY() - 390, 184, 300);
        //OrthoVS.fen.chartPanelBAD.setOpaque(false);
        //On redessine
        OrthoVS.fen.repaint () ;
        
        //On boucle sur la série
        boolean notFin = true ;
        long tempsDebut = System.currentTimeMillis();
        int currentLevel = this.level - 1 ;
        double scoreCumulé = 0 ;
        long tpsCumulé = 0 ;
        double nCycle = 0 ;
        
        //On démarre le timer
        DrawTimer timerThrd = new DrawTimer (progressBar, tempsDebut, this.durée) ;
        
        //On démarre
        do {
            OrthoVS.fen.requestFocus();
            score = new Score () ;
            //On initialise les grilles
            if ( currentLevel < (size * (size - 1) ) ) currentLevel++ ;
            if (currentLevel == 1) currentLevel++ ;
            randomInit(currentLevel );
            score.presentation = currentLevel ;
            
            //On attend la saisie de l'utilisateur
            do {
                try { sleep ( 150 ) ;} catch (Exception e) {}
                if (escPressed) break ;
            } while (! nextGrid) ;
            
            //Si appui sur ESC
            if (escPressed) break ;

            //On sauve le score
            results.add(score); 
            
            //C'est la fin ?
            float seconds = (System.currentTimeMillis() - tempsDebut) / 1000F;
            //progressBar.setValue((int) seconds);
            if (seconds > durée) notFin = false ;
            nCycle++ ;
            //On calcule le score et la vitesse
            tpsCumulé += score.tr_f ;
            if (score.reponse == 1) scoreCumulé +=score.presentation ;
            
            //On met à jour le graphique et le label score
            if (scoreCumulé != 0.0) {
                serieBAD.add (nCycle, 60000 / (tpsCumulé/scoreCumulé) ) ;
                //System.out.print (score.level + ",") ;
                //System.out.println ((System.currentTimeMillis() - tempsDebut) / scoreCumulé) ;
                jScore.setText( String.format("%.1f", 60000 / (tpsCumulé/scoreCumulé)) + " items/mn" );
            }
            //On laisse un peu la réponse...
            try { sleep ( 200 ) ;} catch (Exception e) {}
        } while (notFin) ;
        //System.out.println (")") ;
        if (results.size() > 0) {
            Session session = new Session () ;
            //ajouter la date
            session.date = new Date () ;
            //affecter les résultats
            session.gridSize = this.size ;
            session.results = results ;
            //On ajoute à l'ensemble des résultats
            UserInfo.resultatsFeature.add(session);
            UserInfo.modifiedResultatsFeature = true ;
        }
        
        //On réaffiche les paramètres
        OrthoVS.fen.getContentPane().remove(grilleL);
        OrthoVS.fen.getContentPane().remove(grilleR);
        OrthoVS.fen.getContentPane().remove(ok);
        OrthoVS.fen.getContentPane().remove(nok);
        OrthoVS.fen.getContentPane().remove(progressBar);
        OrthoVS.fen.getContentPane().remove(jTime);
        OrthoVS.fen.getContentPane().remove(jClipart);
        OrthoVS.fen.getContentPane().remove(jScore);
        OrthoVS.fen.getContentPane().remove(chartPanelTEMP);
        OrthoVS.fen.enableMenuBar(true);
        OrthoVS.fen.jPatient.setVisible (true) ;
        OrthoVS.fen.chartPanelOK.setVisible(true);
        OrthoVS.fen.chartPanelBAD.setVisible(true);
        OrthoVS.fen.computeChartsFeature(true);
        p.setVisible (true) ;
        OrthoVS.fen.repaint () ;
    }
    
    void randomInit (int level) {
        int c ;
        nextGrid = false ;
        grilleL.reset(); grilleR.reset();
        pareils = rand.nextBoolean() ;
        if (pareils) score.level = 1 ; else score.level = 0 ;
        int i = 0 ; int n = 0 ;
        while (i < level) {
            final int min = 0 ; final int max = this.size -1 ;
            int rX = rand.nextInt((max - min) + 1) + min;
            int rY = rand.nextInt((max - min) + 1) + min;
            if ( grilleL.j[rX][rY].code == 0 ) {
                c = grilleL.j[rX][rY].code = rand.nextInt((JLabelFeature.max - JLabelFeature.min) + 1) + JLabelFeature.min ;
                if (pareils) grilleR.j[rX][rY].code = c ;
                else if (n < this.difficulty) {
                    n++ ;
                    do {
                        grilleR.j[rX][rY].code = rand.nextInt((JLabelFeature.max - JLabelFeature.min) + 1) + JLabelFeature.min ;
                    } while (grilleR.j[rX][rY].code == c) ;
                }
                else grilleR.j[rX][rY].code = c ;
                i++ ;
            }
        }
        grilleL.repaint () ; grilleR.repaint () ;
        tempsDebut = System.currentTimeMillis();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton b = (JButton) e.getSource() ;
        if ( (b == ok && pareils) || (b == nok && ! pareils) ) {
            grilleL.setBackground(Color.GREEN);
            grilleR.setBackground(Color.GREEN);
            score.reponse = 1 ;
            snd = new SoundClips (4) ; //GOOD
            snd.start () ;
        }
        else { 
            grilleL.setBackground(Color.RED.brighter());
            grilleR.setBackground(Color.RED);
            score.reponse = 0 ;
            snd = new SoundClips (5) ; //GOOD
            snd.start () ;
        }
        this.score.tr_f = System.currentTimeMillis() - tempsDebut ;
        nextGrid = true ;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        score.tr_f = System.currentTimeMillis() - tempsDebut ;
        int code = e.getKeyCode () ;
        if (code == VK_ESCAPE) {    //Sortir de la boucle de test, revenir à l'écran de paramétrage
            escPressed = true ;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }

    
}

class DoubleCadre extends JPanel {
    
    int size, currentOrder, empan ;
    public JLabelFeature j[][] ;
    
    DoubleCadre ( int size ) {
        this.size = size ;

        setBackground(Color.CYAN);
        //setBorder(new javax.swing.border.BevelBorder(javax.swing.border.BevelBorder.RAISED));
        
        GridLayout g = new GridLayout(size, size) ;
        g.setHgap(6); g.setVgap(6);
        setLayout( g );
        setBorder(new EmptyBorder(10, 10, 10, 10) );
        //setBorder(BorderFactory.createEtchedBorder());
        j = new JLabelFeature [size][size] ;
        for (int i=0; i<size; i++)
            for (int ii=0; ii<size; ii++) {
                j[i][ii] = new JLabelFeature () ;
                //j[i][ii].setBackground(Color.red);
                //j[i][ii].setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.EtchedBorder.RAISED));
                j[i][ii].setVisible(true);
                
                add (j[i][ii]) ;
            }
        //On affiche
        setVisible (true) ;
    }
    
    void reset () {
        setBackground(Color.CYAN);
        for (int i=0; i<size; i++)
            for (int ii=0; ii<size; ii++)
                this.j[i][ii].code = 0;
    }
}

class JLabelFeature extends JLabel {
    int code = 0;
    final static public int max = 6 ;
    final static public int min = 1;
    
    public void paintComponent(Graphics g){
    super.paintComponent(g) ;
    Graphics2D g2 = (Graphics2D) g;
    g2.setColor(Color.BLUE);
    g2.setStroke(new BasicStroke(4)) ;
    switch (code) {
        case 1 : g2.drawLine(3, 3, 34, 3); g2.drawLine(3, 3, 3, 34); break ;
        case 2 : g2.drawOval(5 , 5, 30, 30); break ;
        case 3 : g2.fillOval(3 , 3, 34, 34); break ;
        case 4 : g2.drawLine(34, 3, 34, 34); g2.drawLine(3, 34, 34, 34); break ;
        case 5 : g2.drawLine(34, 3, 34, 34); g2.drawLine(3, 3, 34, 3); g2.drawLine(3, 3, 3, 34); break ;
        case 6 : g2.fillOval(10 , 10, 20, 20); break ;
    }
    
}
}