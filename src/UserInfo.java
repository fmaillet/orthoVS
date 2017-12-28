
import java.awt.Image;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.ListIterator;
import javax.swing.Icon;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Fred
 */
public class UserInfo {
    public UserInfo () {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	//get current date time with Date()
	Date date = new Date();
	
        //Dialogue Journal
        journal = new DialogJournal (OrthoVS.fen, true) ;
        journal.addJournal("===========================");
        journal.addJournal("Démarrage : " + dateFormat.format(date));
        //Pas de connection au départ
        nom = null ;
        currentPatient = 0 ;
        //Initialisation des listes de résultats  
        resultatsMonkey  = new LinkedList<Session> () ;
        resultatsFeature = new LinkedList<Session> () ;
        resultatsPolygons = new LinkedList<Session> () ;
        resultatsShepard = new LinkedList<Session> () ;
        //On récupère la macAdress pour la connection
        try {
            InetAddress ip = InetAddress.getLocalHost();
            NetworkInterface network = NetworkInterface.getByInetAddress(ip);
            byte[] mac = network.getHardwareAddress();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < mac.length; i++) {
                sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
            }
            macaddress = sb.toString();
        } catch (Exception e) {}
    }
    
    //Images par défaut
    static public Icon iconTime, iconMonkey, iconFeature, iconShepard, iconButterfly;
    
    String nom, prenom, titre, message, activite, adeli, code, mail ;
    String adr1, adr2, cp, ville, tel1, tel2 ;
    
    static public LinkedList resultatsMonkey ; //Résultats "MonkeyLadder"
    static public boolean    modifiedResultatsMonkey = false ;
    static public LinkedList resultatsFeature ; //Résultats "Discrimination"
    static public boolean    modifiedResultatsFeature = false ;
    static public LinkedList resultatsPolygons ; //Résultats "Discrimination"
    static public boolean    modifiedResultatsPolygons = false ;
    static public LinkedList resultatsShepard ; //Résultats "Rotation Shepard"
    static public boolean    modifiedResultatsShepard = false ;
    
    static public LinkedList listePatients ;
    static public int currentPatient ;
    
    static public String macaddress = null ;
    
    //Variables finales
    static final int softVersion = 8 ;
    public static DialogJournal journal ;
    
    public String getSoftVersion () {
        return "v.0.8.0beta du 30/12/2017" ;
    }
   
    public static void disposeAllResultats () {
        resultatsMonkey.clear();
        modifiedResultatsMonkey = false ;
        
        resultatsFeature.clear () ;
        modifiedResultatsFeature = false ;
        
        resultatsPolygons.clear () ;
        modifiedResultatsPolygons = false ;
        
        resultatsShepard.clear () ;
        modifiedResultatsShepard = false ;
    }
    
    static public boolean areThereModifiedResults () {
        return modifiedResultatsMonkey && modifiedResultatsFeature && modifiedResultatsPolygons  && modifiedResultatsShepard ;
    }
    
    public void clearListePatients () {
        if (listePatients != null) listePatients.clear();
        listePatients = new LinkedList<Patient>() ;
    }
    
    public void addPatients (int id, String n, String nn, Date d, int sex, Boolean a) {
        Patient p = new Patient () ;
        p.id = id ;
        p.nom = n ;
        p.prenom = nn ;
        p.dn = d ;
        p.sex = sex ;
        p.actif = a ;
        listePatients.add (p) ;
    }
    
    public void activatePatient (int id) {
        //On informe
        UserInfo.journal.addJournal("Patient actif : " + id) ;
        UserInfo.currentPatient = id ;
        ListIterator<Patient> lit = listePatients.listIterator() ;
        Patient pP ;
        while (lit.hasNext()) {
            pP = lit.next( ) ;
            if (pP.id == id) {
                OrthoVS.fen.jPatient.setText(pP.nom + " " + pP.prenom); break ;
            }
        }
        //On efface les ancienens donénes
        disposeAllResultats () ;
        
        //On récupère les données de ce patient...
        OrthoVS.mySQLConnection.loadAllPatientDatas() ;
        
        //On met à jour le graphe adéquat
        if (OrthoVS.fen.monkeyLadderMenu.isSelected ())
            OrthoVS.fen.computeChartsMonkey(false) ;
        else if (OrthoVS.fen.featureMenu.isSelected ())
            OrthoVS.fen.computeChartsFeature(false) ;
        else if (OrthoVS.fen.polygonsMenu.isSelected ())
            OrthoVS.fen.computeChartsPolygons(false) ;
        else if (OrthoVS.fen.shepardMenu.isSelected ())
            OrthoVS.fen.computeChartsShepard(false) ;
    }
}

