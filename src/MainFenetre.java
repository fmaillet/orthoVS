/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.Color;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.ListIterator;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSeparator;
import static javax.swing.SwingConstants.LEFT;
import javax.swing.SwingUtilities;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartMouseEvent;
import org.jfree.chart.ChartMouseListener;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.entity.XYItemEntity;
import org.jfree.chart.labels.XYToolTipGenerator;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;



/**
 *
 * @author Fred
 */
public class MainFenetre extends JFrame implements ActionListener, MouseMotionListener, WindowListener, ChartMouseListener  {
    
    static public Image tinyTrophy ;
    
    public MainFenetre () {
        setTitle ("orthoVisuo-Spatial ("+OrthoVS.user.getSoftVersion()+") - MODE DEMONSTRATION (NON CONNECTE)");
        //setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);  setSize(1080, 720);
        addWindowListener (this) ;
        
        getContentPane().setBackground(Color.white);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(0,204 , 204));
        
        //Le patient
        jPatient = new JButton ("(Démo)") ;
        jPatient.setBounds(20, 20, 500, 40);
        jPatient.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jPatient.setForeground(Color.CYAN);
        jPatient.setBorderPainted(false);
        jPatient.setBackground(Color.WHITE);
        jPatient.setFocusPainted (false) ;
        jPatient.setOpaque(false);
        jPatient.setMargin(new Insets(0, 0, 0, 0));
        jPatient.setHorizontalAlignment(LEFT);
        jPatient.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        jPatient.setToolTipText("Cliquez pour afficher la synthèse") ;
        
        //Image du trophé
        tinyTrophy = getToolkit().getImage(getClass().getResource("/Ressources/trophy-small.png"));
        
        //Bouton PDF
        Image imgPDF = getToolkit().getImage(getClass().getResource("Ressources/pdf-icon.png"));
        jPDF = new JButton (new ImageIcon(imgPDF)) ;
        jPDF.setBounds(1020, 20, 32, 32);
        jPDF.setBorderPainted(false);
        jPDF.setContentAreaFilled(false);
        jPDF.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        jPDF.setToolTipText("Cliquez pour imprimer la synthèse") ;
        
        //On charge les images dont on aura besoin
        UserInfo.iconTime = new ImageIcon ( getToolkit().getImage(getClass().getResource("Ressources/sablier.png")) );
        UserInfo.iconMonkey = new ImageIcon ( getToolkit().getImage(getClass().getResource("Ressources/monkey-ladder.png")) );
        UserInfo.iconFeature = new ImageIcon ( getToolkit().getImage(getClass().getResource("Ressources/feature2.png")) );
        UserInfo.iconShepard = new ImageIcon ( getToolkit().getImage(getClass().getResource("Ressources/shepard.png")) );
        UserInfo.iconButterfly = new ImageIcon ( getToolkit().getImage(getClass().getResource("Ressources/butterfly.png")) );
        UserInfo.iconRotation2D = new ImageIcon ( getToolkit().getImage(getClass().getResource("Ressources/rotation2D.png")) );
        
        //Icone
        Image img = getToolkit().getImage(getClass().getResource("Ressources/Shepard/1/1A.png"));
        setIconImage(img);
        //setModalExclusionType(ModalExclusionType.NO_EXCLUDE);
        setVisible(true) ;
                
        //Menu
        barreMenus = new JMenuBar () ;
        setJMenuBar (barreMenus) ;
        
        //Entrée Menus "Fichiers"
        fileMenu = new JMenu ("Fichier") ;
        barreMenus.add(fileMenu) ;
        //Menu Patients
        patientsMenu = new JMenu ("Patients") ;
        barreMenus.add(patientsMenu) ;
        patientsMenu.setEnabled(false);
        exeMenu = new JMenu ("Activités") ;
        barreMenus.add(exeMenu) ;
        helpMenu = new JMenu ("Aide") ;
        barreMenus.add(helpMenu) ;
        
        //Entrée menus Fichier
        connectMenu = new JMenu ("Serveur Internet") ;
        fileMenu.add(connectMenu) ;
        //Séparateur
        eyeTrackMenu = new JMenu ("Eye Tracker") ;
        fileMenu.add (eyeTrackMenu) ;
        eyeTrackMenu.setToolTipText("eyeTracker non détecté");
        
        //eyetrack menu
        eyeTrackDiag = new JMenuItem ("Diagnostic") ;
        eyeTrackMenu.add (eyeTrackDiag) ;
        eyeTrackDiag.setEnabled(false);
        eyeTrackDiag.setToolTipText("eyeTracker non détecté");
        eyeTrackCalibration = new JMenuItem ("Calibration") ;
        eyeTrackMenu.add (eyeTrackCalibration) ;
        eyeTrackCalibration.setEnabled(false);
        eyeTrackCalibration.setToolTipText("eyeTracker non détecté");
                
        comMenu = new JMenuItem ("Connexion au serveur") ;
        connectMenu.add(comMenu) ;
        comMenu.addActionListener(this);
        comMenu.setEnabled(false);
        //Déconnexion
        dcomMenu = new JMenuItem ("Déconnexion du serveur") ;
        connectMenu.add(dcomMenu) ;
        dcomMenu.addActionListener(this);
        dcomMenu.setEnabled(false);
        //Edition des donénes du professionnel
        connectMenu.add (new JSeparator()) ;
        editProMenu = new JMenuItem ("Fiche Professionnel") ;
        connectMenu.add(editProMenu) ;
        editProMenu.addActionListener(this);
        editProMenu.setEnabled(false);
        
        //Tableau de bord + patients
        syntheseMenu = new JRadioButtonMenuItem ("Synthèse") ;
        exeMenu.add(syntheseMenu) ;
        syntheseMenu.setEnabled(false);
        syntheseMenu.addActionListener(this);
        exeMenu.add (new JSeparator()) ;
        //Fiches patients
        fichesPatientsMenu = new JMenuItem ("Fiches Patients") ;
        patientsMenu.add(fichesPatientsMenu) ;
        fichesPatientsMenu.addActionListener(this);
        fichesPatientsMenu.setEnabled(true);
        
        //Séparateur
        fileMenu.add (new JSeparator()) ;
        quitMenu = new JMenuItem ("Quitter") ;
        fileMenu.add(quitMenu) ;
        quitMenu.addActionListener(this);

        //Entrée menu Exercices
        monkeyLadderMenu = new JRadioButtonMenuItem ("Echelle de Singe") ;
        exeMenu.add(monkeyLadderMenu) ;
        monkeyLadderMenu.addActionListener(this);
        associatedPairslMenu = new JRadioButtonMenuItem ("Paires associées") ;
        exeMenu.add(associatedPairslMenu) ;
        associatedPairslMenu.setEnabled(false);
        associatedPairslMenu.addActionListener(this);
        empanSpatialMenu = new JRadioButtonMenuItem ("Empan Spatial") ;
        exeMenu.add(empanSpatialMenu) ;
        empanSpatialMenu.addActionListener(this);
        empanSpatialMenu.setEnabled(false);
        exeMenu.add (new JSeparator()) ;
        
        spatialSearchMenu = new JRadioButtonMenuItem ("Spatial search") ;
        exeMenu.add(spatialSearchMenu) ;
        spatialSearchMenu.addActionListener(this);
        spatialSearchMenu.setEnabled(false);
        symetryMenu = new JRadioButtonMenuItem ("Symétrie du papillon") ;
        exeMenu.add(symetryMenu) ;
        symetryMenu.addActionListener(this);
        symetryMenu.setEnabled(true);
        
        exeMenu.add (new JSeparator()) ;
        rotation2DMenu = new JRadioButtonMenuItem ("Rotations 2D") ;
        exeMenu.add(rotation2DMenu) ;
        rotation2DMenu.addActionListener(this);
        shepardMenu = new JRadioButtonMenuItem ("Rotations 3D") ;
        exeMenu.add(shepardMenu) ;
        shepardMenu.addActionListener(this);
        shepardMenu.setEnabled(true);
        exeMenu.add (new JSeparator()) ;
        featureMenu = new JRadioButtonMenuItem ("Discrimination") ;
        exeMenu.add(featureMenu) ;
        featureMenu.addActionListener(this);
        featureMenu.setEnabled(true);
        polygonsMenu = new JRadioButtonMenuItem ("Polygones") ;
        exeMenu.add(polygonsMenu) ;
        polygonsMenu.addActionListener(this);
        polygonsMenu.setEnabled(true);
        exeMenu.add (new JSeparator()) ;
        sndMenu = new JCheckBoxMenuItem ("Effets sonores") ;
        exeMenu.add(sndMenu) ;
        sndMenu.setSelected (true) ;
        
        //On groupe les entrées
        ButtonGroup group = new ButtonGroup();
        group.add (syntheseMenu) ;
        group.add (monkeyLadderMenu) ;
        group.add (associatedPairslMenu) ;
        group.add (empanSpatialMenu) ;
        group.add (spatialSearchMenu) ;
        group.add (symetryMenu) ;
        group.add (rotation2DMenu) ;
        group.add (shepardMenu) ;
        group.add (featureMenu) ;
        group.add (polygonsMenu) ;
        
        //Entrée menu aide
        hMenu = new JMenuItem ("Journal de bord") ;
        helpMenu.add(hMenu) ;
        hMenu.addActionListener(this);
        helpMenu.add (new JSeparator()) ;
        aboutMenu = new JMenuItem ("A Propos") ;
        helpMenu.add(aboutMenu) ;
        aboutMenu.addActionListener(this);
        
        //ON affiche la synthèse par défaut ?
        reInit () ;
        
        //On regarde ce qui se passe
        runtime = Runtime.getRuntime();
        //On écoute la souris
        addMouseMotionListener (this) ;
        //AutoConnect
        AutoConnect auto = new AutoConnect () ;
        auto.start () ;
    }
    
    public Image getShepardImage (int s, boolean s1, int ss) {
        Image img ;
        String st = "Ressources/Shepard/" + String.valueOf(s) +"/" ;
        
        if (s1) st = st + "1";
        else st = st + "2";
        
        switch (ss) {
            case 1 : st = st + "A" ; break ;
            case 2 : st = st + "B" ; break ;
            case 3 : st = st + "C" ; break ;
            case 4 : st = st + "D" ; break ;
            case 5 : st = st + "E" ;
        }
        
        st = st + ".png" ;
        img = getToolkit().getImage(getClass().getResource(st));
        return img ;
    }
    
    public void reInit () {
        getContentPane().removeAll () ;
        /*On met une image de fond
        Image img = getToolkit().getImage(getClass().getResource("Ressources/ANN Brainv2.png"));
        ImageIcon icon = new ImageIcon(img);
        JLabel lab = new JLabel (icon) ;
        lab.setBounds(90, 30, icon.getIconWidth(), icon.getIconHeight());
        getContentPane().add(lab);
        //repaint () ;*/
        //le patient..
        getContentPane ().add (jPatient) ;
        jPatient.setVisible(true);
    }
    
    //Menu de la JFrame    
    JMenuBar barreMenus ;
    JMenu fileMenu, eyeTrackMenu, patientsMenu, exeMenu , helpMenu, connectMenu ;
    JMenuItem eyeTrackDiag, eyeTrackCalibration ;
    JMenuItem comMenu, dcomMenu, editProMenu, fichesPatientsMenu, quitMenu, hMenu, aboutMenu  ;
    JRadioButtonMenuItem syntheseMenu, monkeyLadderMenu, associatedPairslMenu, empanSpatialMenu, spatialSearchMenu, symetryMenu, shepardMenu, featureMenu, polygonsMenu ;
    JRadioButtonMenuItem rotation2DMenu ;
    public static JCheckBoxMenuItem sndMenu ;
    //Nom du patient en cours
    JButton jPatient, jPDF ;
    //JPanels éléments de report global
    static public PanelSynthese panelSynthese ;
    
    public ChartPanel chartPanelOK, chartPanelBAD ;
    public XYSeriesCollection xySeriesCollectionOK, xySeriesCollectionBAD ;
    XYSeries serieOK, serieBAD, serie2, serie3, serie4, serie5, serie6 ;
    
    //Les panels de paramétrages
    ParamsMonkeyLadder monkeyLadderParam ;
    ParamsFeature featureParam ;
    ParamsPolygons polygonsParam ;
    ParamsShepard shepardParam ;
    ParamsSymetry symetryParam ;
    ParamsRotation2D rotation2DParam ;
    
    Runtime runtime ;
    
    @Override
    public void actionPerformed(ActionEvent e) {
        //Source ?
        Object source = e.getSource () ;
        if (source == comMenu) {
            //JDialogue de connection
            ConnectDialog connect = new ConnectDialog (OrthoVS.fen, true) ;
            connect.setLocationRelativeTo(null);
            connect.setVisible (true) ;
        }
        else if (source == dcomMenu) {
            //On dde confirmation
            if (JOptionPane.showConfirmDialog (null, "Voulez-vous être déconnecté du serveur et\nbasculé en mode démonstration ?\n\n(Vous pourrez toujours vous reconnecter ensuite)\n\n", "ATTENTION : déconnexion du serveur...", JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION)
                deconnect () ;
        }
        else if (source == editProMenu) {
            editProDialog edit = new editProDialog (OrthoVS.fen, true) ;
            edit.setLocationRelativeTo(null);
            edit.setVisible (true) ;
        }
        else if ( source == fichesPatientsMenu ) {
            //On sauvegarde par défaut 
            if ( OrthoVS.user.currentPatient != 0 )
                OrthoVS.mySQLConnection.saveAllDatas() ;
            FichesPatientsDialog liste = new FichesPatientsDialog (OrthoVS.fen, true) ;
            liste.setLocationRelativeTo(null);
            liste.setVisible (true) ;
        }
        //Les activités
        else if ( source == syntheseMenu || source == jPatient ) {
            syntheseMenu.setSelected(true);
            initSynthese () ;
        }
        else if ( source == monkeyLadderMenu ) initMonkeyLadder () ;
        else if ( source == featureMenu ) initFeature () ;
        else if ( source == polygonsMenu ) initPolygons () ;
        else if ( source == shepardMenu ) initShepard () ;
        else if ( source == symetryMenu ) initSymetry () ;
        else if ( source == rotation2DMenu ) initRotation2D () ;
        
        else if (source == aboutMenu) aPropos () ;
        else if (source == hMenu) { //Journal
            UserInfo.journal.setLocationRelativeTo(null);
            UserInfo.journal.setVisible(true);
        }
        else if (source == quitMenu) {
            OrthoVS.sortir () ;
        }
        else if (source == jPDF) {
            GeneratePDF t = new GeneratePDF () ;
            t.start () ;
            JOptionPane.showMessageDialog(null, "Impression du rapport en cours...", "Générer un rapport (sortie imprimante par défaut) :",  JOptionPane.INFORMATION_MESSAGE);
        }
        
        //On nettoie la mémoire
        runtime.gc();
    }

    public void aPropos () {
        
        long memory = runtime.totalMemory() - runtime.freeMemory();
        memory = memory / (1024L * 1024L) ; 
        JOptionPane.showMessageDialog(this, "orthoVS\n"+ OrthoVS.user.getSoftVersion()+"\n(Mémoire utilisée : " + memory + " Mo)"+"\n\nFrédéric Maillet - Orthoptiste (Toulouse)" , "A Propos", JOptionPane.INFORMATION_MESSAGE);
        //System.out.println(System.getProperty("os.name"));
    }
    
    public void enableMenuBar (boolean t) {
        OrthoVS.fen.fileMenu.setEnabled(t);
        OrthoVS.fen.exeMenu.setEnabled(t);
        OrthoVS.fen.helpMenu.setEnabled(t);
        if (OrthoVS.user.nom != null) OrthoVS.fen.patientsMenu.setEnabled(t);
    }
    
    void disposePreviousPanels () {
        getContentPane().removeAll() ;
        //Panel Synthse
        if (panelSynthese != null) {
            panelSynthese = null ;
            chartPanelBAD = null ;
            jPDF.setVisible(false);
        }
        //Monkey Ladder
        if (monkeyLadderParam != null) {
            monkeyLadderParam = null ;
            chartPanelOK = null ;
        }
        //Rotation2D
        if (rotation2DParam != null) {
            rotation2DParam = null ;
            //chartPanelOK = null ;
        }
        //Symetry
        if (symetryParam != null) {
            symetryParam = null ;
            //chartPanelOK = null ;
        }
        //Discrimination
        if (featureParam != null ) {
            featureParam = null ;
        }
        //Polygones
        if (polygonsParam != null ) {
            polygonsParam = null ;
        }
        //Shepard
        if (shepardParam != null ) {
            shepardParam = null ;
        }
        
        //On efface les graphes précédents
        if (serieOK != null) serieOK.clear () ;
        if (serieBAD != null) serieBAD.clear () ;
        //On remet le patient..
        getContentPane ().add (jPatient) ;
        jPatient.setVisible(true);
        //on redessine
        revalidate () ;
        repaint () ;
    }
    
    void initSynthese () {
        //Si c'est déjà la synthèse, rien à faire
        if (panelSynthese != null) return ;
        //Sinon, on efface tout
        disposePreviousPanels () ;
        //Et on affiche la synthese
        panelSynthese = new PanelSynthese () ;
        panelSynthese.jTextRG.setText("");
        panelSynthese.setBounds (15,70,410, 590) ;
        panelSynthese.jTabbedPane.setSize(400, 590);
        getContentPane ().add (panelSynthese) ;
        panelSynthese.setVisible(true) ;
        panelSynthese.revalidate();
        
        //On affiche le bouton PDF
        getContentPane ().add (jPDF) ;
        jPDF.setVisible(true);
        if (jPDF.getActionListeners().length == 0)
            jPDF.addActionListener(this);
        
        //On lance les calculs
        Synthese s = new Synthese () ;
        s.start () ;
        
        /*//le patient..
        getContentPane ().add (jPatient) ;
        jPatient.setVisible(true);*/        
        
        //Chart Mémoire
        if (xySeriesCollectionBAD != null) xySeriesCollectionBAD.removeAllSeries();
        JFreeChart chartBAD = ChartFactory.createXYLineChart("Mémoire", "Séances", "", xySeriesCollectionBAD,
            PlotOrientation.VERTICAL, false, false, false);
        chartPanelBAD = new ChartPanel( chartBAD ) ;
        getContentPane ().add (chartPanelBAD) ;
        chartPanelBAD.setBounds(430, 130, 300, 250);
        chartPanelBAD.addChartMouseListener(this); //pour le double clic qui renvoi à l'activité
        chartPanelBAD.setPopupMenu(null);
        chartPanelBAD.setVisible(true);
        
        //Formatage du tracé
        final XYPlot plot = chartBAD.getXYPlot();
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        NumberAxis domainAxis = (NumberAxis) plot.getDomainAxis();
        domainAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        XYLineAndShapeRenderer rendererXY = new XYLineAndShapeRenderer();
        rendererXY.setBaseToolTipGenerator(new CustomXYToolTipGenerator());
        plot.setRenderer(rendererXY);
    }
 
    void initMonkeyLadder () {
        //Si c'est dékjà affiché, on fait rien
        if (monkeyLadderParam != null) return ;
        //Sinon, on efface tout
        disposePreviousPanels () ;
        //Icone de l'activité
        JLabel icone = new JLabel () ;
        icone.setBounds(getContentPane ().getWidth()-250, 100, 150, 150);
        icone.setIcon(UserInfo.iconMonkey);
        getContentPane ().add (icone) ;
        icone.setVisible(true);
        //Et on affiche...
        monkeyLadderParam = new ParamsMonkeyLadder (icone) ;
        monkeyLadderParam.setBounds (getContentPane ().getWidth()/2-431/2,getContentPane ().getHeight()/2-513/2,431,513) ;
        getContentPane ().add (monkeyLadderParam) ;
        monkeyLadderParam.setVisible(true);
        
        
        //Données du graphique
        xySeriesCollectionOK = new XYSeriesCollection();
        // FreeChart...
        JFreeChart chartOK = ChartFactory.createXYLineChart("Empan Moy/Max", "", "", xySeriesCollectionOK,
            PlotOrientation.VERTICAL, true, false, false);
        chartOK.setBackgroundPaint(new Color(0,204 , 204));
        chartPanelOK = new ChartPanel( chartOK ) ;
        getContentPane ().add (chartPanelOK) ;
        chartPanelOK.setBounds(30, 90, 270, 250);
        chartPanelOK.setVisible(true);
        chartPanelOK.setPopupMenu(null);
        //Présentation du graph OK
        final XYPlot plot = chartOK.getXYPlot();
        plot.setBackgroundPaint(Color.lightGray);
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        NumberAxis domainAxis = (NumberAxis) plot.getDomainAxis();
        domainAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        XYLineAndShapeRenderer xyLineAndShapeRenderer = new XYLineAndShapeRenderer() ;
        xyLineAndShapeRenderer.setBaseToolTipGenerator(new CustomXYToolTipGenerator());
        plot.setRenderer(xyLineAndShapeRenderer);

        //On affiche les données (s'il y en a)
        computeChartsMonkey(false) ;
        //On affiche
        revalidate () ;
        repaint () ;
    }
    
    public void computeChartsMonkey (boolean b) {
        //Si vide, rien à faire
        xySeriesCollectionOK.removeAllSeries();
        if (UserInfo.resultatsMonkey.size() == 0) return ;
        //Séries
        serieOK = new XYSeries("Maximum");
        serieBAD = new XYSeries("Moyen");
        xySeriesCollectionOK.addSeries(serieOK);
        xySeriesCollectionOK.addSeries(serieBAD);
        //Donées temporaires
        Score score ;
        int max = 0 ;
        //pour chaque session...
        ListIterator<Session> it = UserInfo.resultatsMonkey.listIterator() ;
        int nSession = 1 ;
        while (it.hasNext()) {
            //On boucle sur chaque score de la session
            Session s = it.next() ;
            LinkedList<Score> l = (LinkedList<Score>) s.results ;
            ListIterator<Score> lit = l.listIterator() ;
            max = 0 ; double n = 0 ;
            double mean = 0.0 ;
            while (lit.hasNext()) {
                score = lit.next() ;
                if (score.reponse > max) max = score.reponse ;
                mean = mean + score.reponse ;
                n++;
            }
            //ON rajoute la moyenne dans le graphe
            mean = mean / n ;
            serieBAD.add(nSession, mean);
            //On rajoute le max dans le graphe
            serieOK.add(nSession, max);
            nSession++ ;
        }
        //On autorise le clic droit (suppression)
        addChartPopupDelMenu (chartPanelOK, UserInfo.resultatsMonkey) ;
    }
    
    public void computeChartsRotation2D (boolean b) {
        //Si vide, rien à faire
        xySeriesCollectionOK.removeAllSeries();
        if (UserInfo.resultatsRotation2D.size() == 0) return ;
        
        //Séries
        serieOK = new XYSeries("Vitesse");
        serieOK.setDescription("vitesse");
        xySeriesCollectionOK.addSeries(serieOK);
        //Donées temporaires
        Score score ;
        double speed = 0 ;
        
        //pour chaque session...
        ListIterator<Session> it = UserInfo.resultatsRotation2D.listIterator() ;
        int nSession = 1 ;
        while (it.hasNext()) {
            
            //On boucle sur chaque score de la session
            Session s = it.next() ;
            LinkedList<Score> l = (LinkedList<Score>) s.results ;
            score = l.getFirst() ;
            //System.out.println (score.reponse + " f: " + score.tr_f + " i: " + score.tr_i + " d: " + (score.tr_f - score.tr_i)) ;
            //System.out.println ((double) (score.tr_f - score.tr_i) / 60000 ) ;
            speed = (double) score.reponse / ((double)(score.tr_f - score.tr_i) / 60000 ) ;
            //System.out.println (speed) ;
            //ON rajoute la vitesse dans le graphe
            serieOK.add(nSession, Math.round(speed));
            
            nSession++ ;
        }
    }
    
    public void computeChartsSymetry (boolean b) {
        //Si vide, rien à faire
        xySeriesCollectionOK.removeAllSeries();
        if (UserInfo.resultatsSymetry.size() == 0) return ;
        
        //Séries
        serieOK = new XYSeries("Vitesse");
        serieOK.setDescription("vitesse");
        xySeriesCollectionOK.addSeries(serieOK);
        //Donées temporaires
        Score score ;
        double speed = 0 ;
        
        //pour chaque session...
        ListIterator<Session> it = UserInfo.resultatsSymetry.listIterator() ;
        int nSession = 1 ;
        while (it.hasNext()) {
            //On boucle sur chaque score de la session
            Session s = it.next() ;
            LinkedList<Score> l = (LinkedList<Score>) s.results ;
            score = l.getFirst() ;
            //System.out.println (score.reponse + " f: " + score.tr_f + " i: " + score.tr_i + " d: " + (score.tr_f - score.tr_i)) ;
            //System.out.println ((double) (score.tr_f - score.tr_i) / 60000 ) ;
            speed = (double) score.reponse / ((double)(score.tr_f - score.tr_i) / 60000 ) ;
            //System.out.println (speed) ;
            //ON rajoute la vitesse dans le graphe
            serieOK.add(nSession, Math.round(speed));
            nSession++ ;
        }
    }
    
    public void computeChartsFeature (boolean b) {
        //Si vide, rien à faire
        xySeriesCollectionOK.removeAllSeries();
        xySeriesCollectionBAD.removeAllSeries();
        if (UserInfo.resultatsFeature.size() == 0) return ;
        //Séries
        serieOK = new XYSeries("1");
        serieOK.setDescription("vitesse");
        xySeriesCollectionOK.addSeries(serieOK);
        serieBAD = new XYSeries("2");
        xySeriesCollectionBAD.addSeries(serieBAD);
        //Donées temporaires
        Score score ;
        Double mean = 0.0 ;
        //pour chaque session...
        ListIterator<Session> it = UserInfo.resultatsFeature.listIterator() ;
        int nSession = 1 ;
        while (it.hasNext()) {
            //On boucle sur chaque score de la session
            Session s = it.next() ;
            LinkedList<Score> l = (LinkedList<Score>) s.results ;
            ListIterator<Score> lit = l.listIterator() ;
            mean = 0.0 ; double n = 0 ;
            double scoreCumulé = 0.0 ;
            double tpsCumulé = 0.0 ;
            while (lit.hasNext()) {
                //réussite
                score = lit.next() ;
                mean = mean + score.reponse ;
                n++;
                //vitesse
                if (score.reponse == 1) scoreCumulé +=score.presentation ;
                tpsCumulé += score.tr_f ;
            }
            //% réussite
            mean = mean / n ;
            serieBAD.add(nSession, mean*100);
            //Vitesse
            serieOK.add (nSession, 60000 / (tpsCumulé/scoreCumulé)) ;
            nSession++ ;
        }
        //On autorise leclic droit (suppression)
        addChartPopupDelMenu (chartPanelOK, UserInfo.resultatsFeature) ;
        addChartPopupDelMenu (chartPanelBAD, UserInfo.resultatsFeature) ;
    }
    
    public void computeChartsPolygons (boolean b) {
        //Si vide, rien à faire
        xySeriesCollectionOK.removeAllSeries();
        xySeriesCollectionBAD.removeAllSeries();
        if (UserInfo.resultatsPolygons.size() == 0) return ;
        //Séries
        serieOK = new XYSeries("1");
        serieOK.setDescription("vitesse");
        xySeriesCollectionOK.addSeries(serieOK);
        serieBAD = new XYSeries("2");
        xySeriesCollectionBAD.addSeries(serieBAD);
        //Donées temporaires
        Score score ;
        Double mean = 0.0 ;
        //pour chaque session...
        ListIterator<Session> it = UserInfo.resultatsPolygons.listIterator() ;
        int nSession = 1 ;
        while (it.hasNext()) {
            //On boucle sur chaque score de la session
            Session s = it.next() ;
            LinkedList<Score> l = (LinkedList<Score>) s.results ;
            ListIterator<Score> lit = l.listIterator() ;
            mean = 0.0 ; double n = 0 ;
            double scoreCumulé = 0.0 ;
            double tpsCumulé = 0.0 ;
            while (lit.hasNext()) {
                //réussite
                score = lit.next() ;
                mean = mean + score.reponse ;
                n++;
                //vitesse
                if (score.reponse == 1) scoreCumulé +=score.presentation ;
                tpsCumulé += score.tr_f ;
            }
            //% réussite
            mean = mean / n ;
            serieBAD.add(nSession, mean*100);
            //Vitesse
            serieOK.add (nSession, 60000 / (tpsCumulé/scoreCumulé)) ;
            nSession++ ;
        }
        //On autorise leclic droit (suppression)
        addChartPopupDelMenu (chartPanelOK, UserInfo.resultatsPolygons) ;
        addChartPopupDelMenu (chartPanelBAD, UserInfo.resultatsPolygons) ;
    }
    
    public void computeChartsShepard (boolean b) {
        //Si vide, rien à faire
        xySeriesCollectionOK.removeAllSeries();
        xySeriesCollectionBAD.removeAllSeries();
        if (UserInfo.resultatsShepard.size() == 0) return ;
        //Séries
        serieOK = new XYSeries("1");
        serieOK.setDescription("vitesse");
        xySeriesCollectionOK.addSeries(serieOK);
        serieBAD = new XYSeries("2");
        xySeriesCollectionBAD.addSeries(serieBAD);
        //Donées temporaires
        Score score ;
        Double mean = 0.0 ;
        //pour chaque session...
        ListIterator<Session> it = UserInfo.resultatsShepard.listIterator() ;
        int nSession = 1 ;
        while (it.hasNext()) {
            //On boucle sur chaque score de la session
            Session s = it.next() ;
            LinkedList<Score> l = (LinkedList<Score>) s.results ;
            ListIterator<Score> lit = l.listIterator() ;
            mean = 0.0 ; double n = 0 ;
            double scoreCumulé = 0.0 ;
            double tpsCumulé = 0.0 ;
            while (lit.hasNext()) {
                //réussite
                score = lit.next() ;
                mean = mean + score.reponse ;
                n++;
                //vitesse
                if (score.reponse == 1) scoreCumulé +=score.presentation ;
                tpsCumulé += score.tr_f ;
            }
            //% réussite
            mean = mean / n ;
            serieBAD.add(nSession, mean*100);
            //Vitesse
            serieOK.add (nSession, 60000 / (tpsCumulé/scoreCumulé)) ;
            nSession++ ;
        }
        //On autorise leclic droit (suppression)
        addChartPopupDelMenu (chartPanelOK, UserInfo.resultatsShepard) ;
        addChartPopupDelMenu (chartPanelBAD, UserInfo.resultatsShepard) ;
    }
    
    void initFeature () {
        
        //Si c'est dékjà affiché, on fait rien
        if (featureParam != null) return ;
        //Sinon, on efface tout
        disposePreviousPanels () ;
        //Et on affiche...
        featureParam = new ParamsFeature () ;
        featureParam.setBounds (getContentPane ().getWidth()/2-431/2,getContentPane ().getHeight()/2-513/2,431,513) ;
        getContentPane ().add (featureParam) ;
        featureParam.setVisible(true);
        //Données du graphique
        xySeriesCollectionOK = new XYSeriesCollection();
        xySeriesCollectionBAD = new XYSeriesCollection();
        
        // Graphique du HAUT
        JFreeChart chartOK = ChartFactory.createXYLineChart("Vitesse (items/mn)", "", "", xySeriesCollectionOK,
            PlotOrientation.VERTICAL, false, false, false);
        chartOK.setBackgroundPaint(new Color(0,204 , 204));
        chartPanelOK = new ChartPanel( chartOK ) ;
        getContentPane ().add (chartPanelOK) ;
        chartPanelOK.setBounds(30, 90, 270, 250);
        chartPanelOK.setVisible(true);
        chartPanelOK.setPopupMenu(null);
        //Présentation du graph OK
        final XYPlot plot = chartOK.getXYPlot();
        plot.setBackgroundPaint(Color.lightGray);
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        NumberAxis domainAxis = (NumberAxis) plot.getDomainAxis();
        domainAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        XYLineAndShapeRenderer xyLineAndShapeRenderer = new XYLineAndShapeRenderer() ;
        xyLineAndShapeRenderer.setSeriesPaint(0, Color.GREEN.darker()) ;
        xyLineAndShapeRenderer.setBaseToolTipGenerator(new CustomXYToolTipGenerator());
        plot.setRenderer(xyLineAndShapeRenderer);
        // Graphique du BAS (% réussite)
        JFreeChart chartBAD = ChartFactory.createXYLineChart("Réussite (%)", "", "", xySeriesCollectionBAD,
            PlotOrientation.VERTICAL, false, false, false);
        chartBAD.setBackgroundPaint(new Color(0,204 , 204));
        chartPanelBAD = new ChartPanel( chartBAD ) ;
        getContentPane ().add (chartPanelBAD) ;
        chartPanelBAD.setBounds(30, 355, 270, 170);
        chartPanelBAD.setVisible(true);
        chartPanelBAD.setPopupMenu(null);
        //Présentation du graph BAS (% réussite)
        final XYPlot plot2 = chartBAD.getXYPlot();
        plot2.setBackgroundPaint(Color.lightGray);
        NumberAxis rangeAxis2 = (NumberAxis) plot2.getRangeAxis();
        rangeAxis2.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        NumberAxis domainAxis2 = (NumberAxis) plot2.getDomainAxis();
        domainAxis2.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        XYLineAndShapeRenderer xyLineAndShapeRenderer2 = new XYLineAndShapeRenderer() ;
        xyLineAndShapeRenderer2.setSeriesPaint(0, Color.BLUE) ;
        xyLineAndShapeRenderer2.setBaseToolTipGenerator(new CustomXYToolTipGenerator());
        plot2.setRenderer(xyLineAndShapeRenderer2);
        
        //S'il y a des données on les affiche
        computeChartsFeature(false) ;
        //On affiche
        revalidate () ;
        repaint () ;
    }
    
    void initSymetry () {
        //Si c'est dékjà affiché, on fait rien
        if (symetryParam != null) return ;
        //Sinon, on efface tout
        disposePreviousPanels () ;
        //Icone de l'activité
        JLabel icone = new JLabel () ;
        icone.setBounds(getContentPane ().getWidth()-250, 100, 150, 150);
        icone.setIcon(UserInfo.iconButterfly);
        getContentPane ().add (icone) ;
        icone.setVisible(true);
        //Et on affiche...
        symetryParam = new ParamsSymetry (icone) ;
        symetryParam.setBounds (getContentPane ().getWidth()/2-431/2,getContentPane ().getHeight()/2-513/2,431,513) ;
        getContentPane ().add (symetryParam) ;
        symetryParam.setVisible(true);
        
        //Données du graphique
        xySeriesCollectionOK = new XYSeriesCollection();
        // FreeChart...
        JFreeChart chartOK = ChartFactory.createXYLineChart("Vitesse (grilles/mn)", "", "", xySeriesCollectionOK,
            PlotOrientation.VERTICAL, true, false, false);
        chartOK.setBackgroundPaint(new Color(0,204 , 204));
        chartPanelOK = new ChartPanel( chartOK ) ;
        getContentPane ().add (chartPanelOK) ;
        chartPanelOK.setBounds(30, 90, 270, 250);
        chartPanelOK.setVisible(true);
        chartPanelOK.setPopupMenu(null);
        //Présentation du graph OK
        final XYPlot plot = chartOK.getXYPlot();
        plot.setBackgroundPaint(Color.lightGray);
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        NumberAxis domainAxis = (NumberAxis) plot.getDomainAxis();
        domainAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        XYLineAndShapeRenderer xyLineAndShapeRenderer = new XYLineAndShapeRenderer() ;
        xyLineAndShapeRenderer.setBaseToolTipGenerator(new CustomXYToolTipGenerator());
        plot.setRenderer(xyLineAndShapeRenderer);
        
        //On affiche les données (s'il y en a)
        computeChartsSymetry(false) ;
        
        //On affiche
        revalidate () ;
        repaint () ;
    }
    
    void initRotation2D () {
        //Si c'est dékjà affiché, on fait rien
        if (rotation2DParam != null) return ;
        //Sinon, on efface tout
        disposePreviousPanels () ;
        //Icone de l'activité
        JLabel icone = new JLabel () ;
        icone.setBounds(getContentPane ().getWidth()-300, 40, 283, 276);
        icone.setIcon(UserInfo.iconRotation2D);
        getContentPane ().add (icone) ;
        icone.setVisible(true);
        //Et on affiche...
        rotation2DParam = new ParamsRotation2D (icone) ;
        rotation2DParam.setBounds (getContentPane ().getWidth()/2-431/2,getContentPane ().getHeight()/2-513/2,431,513) ;
        getContentPane ().add (rotation2DParam) ;
        rotation2DParam.setVisible(true);
        
        //Données du graphique
        xySeriesCollectionOK = new XYSeriesCollection();
        // FreeChart...
        JFreeChart chartOK = ChartFactory.createXYLineChart("Vitesse (items/mn)", "", "", xySeriesCollectionOK,
            PlotOrientation.VERTICAL, true, false, false);
        chartOK.setBackgroundPaint(new Color(0,204 , 204));
        chartPanelOK = new ChartPanel( chartOK ) ;
        getContentPane ().add (chartPanelOK) ;
        chartPanelOK.setBounds(30, 90, 270, 250);
        chartPanelOK.setVisible(true);
        chartPanelOK.setPopupMenu(null);
        
        //Présentation du graph OK
        final XYPlot plot = chartOK.getXYPlot();
        plot.setBackgroundPaint(Color.lightGray);
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        NumberAxis domainAxis = (NumberAxis) plot.getDomainAxis();
        domainAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        XYLineAndShapeRenderer xyLineAndShapeRenderer = new XYLineAndShapeRenderer() ;
        xyLineAndShapeRenderer.setBaseToolTipGenerator(new CustomXYToolTipGenerator());
        plot.setRenderer(xyLineAndShapeRenderer);
        
        //On affiche les données (s'il y en a)
        computeChartsRotation2D(false) ;
        
        //On affiche
        revalidate () ;
        repaint () ;
    }
    
    void initPolygons () {
        
        //Si c'est déjà affiché, on fait rien
        if (polygonsParam != null) return ;
        //Sinon, on efface tout
        disposePreviousPanels () ;
        //Et on affiche...
        polygonsParam = new ParamsPolygons () ;
        polygonsParam.setBounds (getContentPane ().getWidth()/2-431/2,getContentPane ().getHeight()/2-513/2,431,513) ;
        getContentPane ().add (polygonsParam) ;
        polygonsParam.setVisible(true);
        //Données du graphique
        xySeriesCollectionOK = new XYSeriesCollection();
        xySeriesCollectionBAD = new XYSeriesCollection();
        
        // Graphique du HAUT
        JFreeChart chartOK = ChartFactory.createXYLineChart("Vitesse (items/mn)", "", "", xySeriesCollectionOK,
            PlotOrientation.VERTICAL, false, false, false);
        chartOK.setBackgroundPaint(new Color(0,204 , 204));
        chartPanelOK = new ChartPanel( chartOK ) ;
        getContentPane ().add (chartPanelOK) ;
        chartPanelOK.setBounds(30, 90, 270, 250);
        chartPanelOK.setVisible(true);
        chartPanelOK.setPopupMenu(null);
        //Présentation du graph OK
        final XYPlot plot = chartOK.getXYPlot();
        plot.setBackgroundPaint(Color.lightGray);
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        NumberAxis domainAxis = (NumberAxis) plot.getDomainAxis();
        domainAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        XYLineAndShapeRenderer xyLineAndShapeRenderer = new XYLineAndShapeRenderer() ;
        xyLineAndShapeRenderer.setSeriesPaint(0, Color.GREEN.darker()) ;
        xyLineAndShapeRenderer.setBaseToolTipGenerator(new CustomXYToolTipGenerator());
        plot.setRenderer(xyLineAndShapeRenderer);
        // Graphique du BAS (% réussite)
        JFreeChart chartBAD = ChartFactory.createXYLineChart("Réussite (%)", "", "", xySeriesCollectionBAD,
            PlotOrientation.VERTICAL, false, false, false);
        chartBAD.setBackgroundPaint(new Color(0,204 , 204));
        chartPanelBAD = new ChartPanel( chartBAD ) ;
        getContentPane ().add (chartPanelBAD) ;
        chartPanelBAD.setBounds(30, 355, 270, 170);
        chartPanelBAD.setVisible(true);
        chartPanelBAD.setPopupMenu(null);
        //Présentation du graph BAS (% réussite)
        final XYPlot plot2 = chartBAD.getXYPlot();
        plot2.setBackgroundPaint(Color.lightGray);
        NumberAxis rangeAxis2 = (NumberAxis) plot2.getRangeAxis();
        rangeAxis2.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        NumberAxis domainAxis2 = (NumberAxis) plot2.getDomainAxis();
        domainAxis2.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        XYLineAndShapeRenderer xyLineAndShapeRenderer2 = new XYLineAndShapeRenderer() ;
        xyLineAndShapeRenderer2.setSeriesPaint(0, Color.BLUE) ;
        xyLineAndShapeRenderer2.setBaseToolTipGenerator(new CustomXYToolTipGenerator());
        plot2.setRenderer(xyLineAndShapeRenderer2);
        
        //S'il y a des données on les affiche
        computeChartsPolygons(false) ;
        //On affiche
        revalidate () ;
        repaint () ;
    }
    
    void initShepard () {
        
        //Si c'est dékjà affiché, on fait rien
        if (shepardParam != null) return ;
        //Sinon, on efface tout
        disposePreviousPanels () ;
        //Icone de l'activité
        JLabel icone = new JLabel () ;
        icone.setBounds(getContentPane ().getWidth()-250, 100, 150, 150);
        icone.setIcon(new ImageIcon ( getShepardImage(5, true, 1)));
        getContentPane ().add (icone) ;
        icone.setVisible(true);
        //Et on affiche...
        shepardParam = new ParamsShepard (icone) ;
        shepardParam.setBounds (getContentPane ().getWidth()/2-431/2,getContentPane ().getHeight()/2-513/2,431,513) ;
        getContentPane ().add (shepardParam) ;
        shepardParam.setVisible(true);
        //Données du graphique
        xySeriesCollectionOK = new XYSeriesCollection();
        xySeriesCollectionBAD = new XYSeriesCollection();
        
        // Graphique du HAUT
        JFreeChart chartOK = ChartFactory.createXYLineChart("Vitesse (items/mn)", "", "", xySeriesCollectionOK,
            PlotOrientation.VERTICAL, false, false, false);
        chartOK.setBackgroundPaint(new Color(0,204 , 204));
        chartPanelOK = new ChartPanel( chartOK ) ;
        getContentPane ().add (chartPanelOK) ;
        chartPanelOK.setBounds(30, 90, 270, 250);
        chartPanelOK.setVisible(true);
        chartPanelOK.setPopupMenu(null);
        //Présentation du graph OK
        final XYPlot plot = chartOK.getXYPlot();
        plot.setBackgroundPaint(Color.lightGray);
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        NumberAxis domainAxis = (NumberAxis) plot.getDomainAxis();
        domainAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        XYLineAndShapeRenderer xyLineAndShapeRenderer = new XYLineAndShapeRenderer() ;
        xyLineAndShapeRenderer.setSeriesPaint(0, Color.GREEN.darker()) ;
        xyLineAndShapeRenderer.setBaseToolTipGenerator(new CustomXYToolTipGenerator());
        plot.setRenderer(xyLineAndShapeRenderer);
        // Graphique du BAS (% réussite)
        JFreeChart chartBAD = ChartFactory.createXYLineChart("Réussite (%)", "", "", xySeriesCollectionBAD,
            PlotOrientation.VERTICAL, false, false, false);
        chartBAD.setBackgroundPaint(new Color(0,204 , 204));
        chartPanelBAD = new ChartPanel( chartBAD ) ;
        getContentPane ().add (chartPanelBAD) ;
        chartPanelBAD.setBounds(30, 355, 270, 170);
        chartPanelBAD.setVisible(true);
        chartPanelBAD.setPopupMenu(null);
        //Présentation du graph BAS (% réussite)
        final XYPlot plot2 = chartBAD.getXYPlot();
        plot2.setBackgroundPaint(Color.lightGray);
        NumberAxis rangeAxis2 = (NumberAxis) plot2.getRangeAxis();
        rangeAxis2.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        NumberAxis domainAxis2 = (NumberAxis) plot2.getDomainAxis();
        domainAxis2.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        XYLineAndShapeRenderer xyLineAndShapeRenderer2 = new XYLineAndShapeRenderer() ;
        xyLineAndShapeRenderer2.setSeriesPaint(0, Color.BLUE) ;
        xyLineAndShapeRenderer2.setBaseToolTipGenerator(new CustomXYToolTipGenerator());
        plot2.setRenderer(xyLineAndShapeRenderer2);
        
        //S'il y a des données on les affiche
        computeChartsShepard(false) ;
        //On affiche
        revalidate () ;
        repaint () ;
    }
    
    public void connected () {
        //On débloque les items
       
        //Titre de la fenêtre
        setTitle ("orthoVisuo-Spatial ("+OrthoVS.user.getSoftVersion()+") - Connecté au serveur : " + OrthoVS.user.titre + " " +OrthoVS.user.nom.toUpperCase() + " " + OrthoVS.user.prenom ) ;
        //Menu disabled
        comMenu.setEnabled(false);
        dcomMenu.setEnabled(true);
        editProMenu.setEnabled(true);
        syntheseMenu.setEnabled(true);
        patientsMenu.setEnabled(true);
        //On autorise le clic sur le nom comme racourci
        if (jPatient.getActionListeners().length == 0)
            jPatient.addActionListener(this);
    }
    
    public void deconnect () {
        //Plus d'utilisateur
        OrthoVS.user.nom = null ;
        UserInfo.currentPatient = 0 ;
        jPatient.setText("(Démo)");
        //Modif titre de la fenêtre
        setTitle ("orthoVisuo-Spatial ("+OrthoVS.user.getSoftVersion()+") - MODE DEMONSTRATION (NON CONNECTE)");
        //Menu disabled
        comMenu.setEnabled(true);
        dcomMenu.setEnabled(false);
        editProMenu.setEnabled(false);
        syntheseMenu.setEnabled(false);
        patientsMenu.setEnabled(false);
        //On bloque les items
        
        //On supprime les données courantes
        
        OrthoVS.user.disposeAllResultats () ;
        //On rend le jeton...
        OrthoVS.mySQLConnection.rendreJeton () ;
        //Clear patients
        OrthoVS.user.clearListePatients();
        jPatient.removeActionListener(this);
        jPDF.removeActionListener(this);
    }
    
    static int clickedSerie = -1 ;
    void addChartPopupDelMenu (ChartPanel chart, LinkedList d) {
        LinkedList datas = d ;
        JPopupMenu popup = new JPopupMenu();
        ActionListener popupMenuListener = new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                //quelle activité ?
                //On confirme ?
                if (getDateDatasValue (datas, clickedSerie) ) {
                    if (monkeyLadderMenu.isSelected()) { computeChartsMonkey (false) ; UserInfo.modifiedResultatsMonkey = true ; }
                    else if (featureMenu.isSelected()) { computeChartsFeature (false) ;  UserInfo.modifiedResultatsFeature = true ; }
                }
                        
            }
        };
        JMenuItem item;
        popup.add(item = new JMenuItem("Supprimer"));
        item.addActionListener(popupMenuListener);
        chart.setPopupMenu(null);
        chart.addChartMouseListener(new ChartMouseListener() {
            @Override
            public void chartMouseClicked(ChartMouseEvent cme) {
                if (SwingUtilities.isRightMouseButton(cme.getTrigger()) && cme.getEntity() instanceof XYItemEntity) {
                    XYItemEntity entity = (XYItemEntity) cme.getEntity();
                    //XYDataset dataset = entity.getDataset();
                    clickedSerie = entity.getItem();
                    popup.show (chart, cme.getTrigger().getX(), cme.getTrigger().getY()) ;
                }
            }

            @Override
            public void chartMouseMoved(ChartMouseEvent cme) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
            
        } ) ;
    }
    
    boolean getDateDatasValue (LinkedList d, int idx) {
        int n = 0;
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        ListIterator<Session> it = d.listIterator() ;
        while (it.hasNext()) {
            Session s = it.next() ;
            if (n == idx) {
                int reply = JOptionPane.showConfirmDialog(OrthoVS.fen, "ATTENTION !\n Vous allez supprimer la série n°" + String.valueOf(clickedSerie+1)
                        + " du " + dateFormat.format(s.date) + "\n\nCette action est irréversible ! Etes-vous certain de vouloir supprimer cette série ?\n\n", "Supprimer une série :", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
                if (reply == JOptionPane.YES_OPTION) {
                    it.remove();
                    return true ;
                }
            }
            n++ ;
        }
        return false ;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        //setCursor(Cursor.getDefaultCursor());
        
    }  

    @Override
    public void windowOpened(WindowEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowClosing(WindowEvent e) {
        OrthoVS.sortir() ;
    }

    @Override
    public void windowClosed(WindowEvent e) {
        
    }

    @Override
    public void windowIconified(WindowEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowActivated(WindowEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void chartMouseClicked(ChartMouseEvent cme) {
        
        if (cme.getTrigger().getClickCount()==2 && cme.getChart() == chartPanelBAD.getChart()) {
            ActionEvent e = new ActionEvent (monkeyLadderMenu, ActionEvent.ACTION_PERFORMED, "") ;
            actionPerformed (e) ;
        }
            
    }

    @Override
    public void chartMouseMoved(ChartMouseEvent cme) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

class CustomXYToolTipGenerator implements XYToolTipGenerator {
    public CustomXYToolTipGenerator() {
        
    }

    @Override
    public String generateToolTip(XYDataset x_dataset, int x_row, int x_column) {
        //return x_dataset.getSeriesKey(x_row) + ": (" + String.valueOf(x_dataset.getXValue(x_row, x_column)) + ", " + String.valueOf(x_dataset.getYValue(x_row, x_column)) + ")";
        XYSeriesCollection xd = ( XYSeriesCollection) x_dataset ;
        if (xd.getSeries(x_row).getDescription() != null) {
            if (xd.getSeries(x_row).getDescription().contentEquals("datesOfSeances") ) 
                return "<html>"+ (String) Synthese.datesOfSeances.get(x_column) +"<br>"+ String.format("%.1f", (double)x_dataset.getYValue(x_row, x_column))+ " %</html>";
            else if (xd.getSeries(x_row).getDescription().contentEquals("vitesse") ) 
                return String.format("%.1f", (double)x_dataset.getYValue(x_row, x_column));
            else
                return String.format("%.1f", (double)x_dataset.getYValue(x_row, x_column))+ " %";
        }
        else
            return String.format("%.1f", (double)x_dataset.getYValue(x_row, x_column))+ " %";
        
    }

    
}
