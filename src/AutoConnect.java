
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Fred
 */
public class AutoConnect extends Thread {
    public AutoConnect () {
        setName ("AutoConnect") ;
    }
    
    Connection laConnection = null ;
    Statement transmission ;
    ResultSet leResultat ;
    String nom, prenom, titre, message, version, activite, dateLimite ;
    
    @Override
    public void run () {
        int n = 0 ;
        
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.util.Date dt = new java.util.Date();
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            DriverManager.setLoginTimeout(5);
            laConnection = DriverManager.getConnection ("jdbc:mysql://fredericmaillet.fr/fmaillet_professionnels", "fmaillet_fredo", "mastercog");
            if (laConnection != null) {
            transmission = laConnection.createStatement (ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE) ;
            //On cherche la macAdress
            leResultat = transmission.executeQuery ("select ADELI, PWD, JETON, LASTCONNECTION, LIMITE, NOM, PRENOM, TITRE, ACTIVITE, MSG, ADR1, ADR2, CP, VILLE, TEL1, TEL2, MAIL"
                    + " from Pro where MACADR = '"+ OrthoVS.user.macaddress+"'") ;
            
            while (leResultat.next()) {
                n++ ;
                //Check quickly if there's only one result
                if (leResultat.next()) break ;
                else leResultat.previous() ;
                //à ce niveau là on sait qu'il n'y a qu'une macAdress correspondante
                //Prendre le jeton ! si on ne peut pas, sortir
                if ( ! MySQLClass.getJeton (leResultat) ) break ;
                //On vérifie la date limite...
                dateLimite = leResultat.getString("LIMITE") ;
                sdf = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    Date d = sdf.parse(dateLimite);
                    if (! d.after(dt)) //
                        break ;
                } catch (ParseException e) {OrthoVS.user.journal.addJournal ("Auto connection : erreur sql.") ;}
                
                //Récupérer les données
                OrthoVS.user.nom = leResultat.getString("NOM") ;
                OrthoVS.user.prenom = leResultat.getString("PRENOM") ;
                OrthoVS.user.titre = leResultat.getString("TITRE") ;
                OrthoVS.user.activite = leResultat.getString("ACTIVITE") ;
                message = "" ;
                message = leResultat.getString("MSG") ;
                //Coordonnées
                OrthoVS.user.adr1 = leResultat.getString("ADR1") ;
                OrthoVS.user.adr2 = leResultat.getString("ADR2") ;
                OrthoVS.user.cp = leResultat.getString("CP") ;
                OrthoVS.user.ville = leResultat.getString("VILLE") ;
                OrthoVS.user.tel1 = leResultat.getString("TEL1") ;
                OrthoVS.user.tel2 = leResultat.getString("TEL2") ;
                OrthoVS.user.mail = leResultat.getString("MAIL") ;
                OrthoVS.user.adeli = leResultat.getString("ADELI") ;
                OrthoVS.user.code = leResultat.getString("PWD") ;
                //Mise à jour date de la dernière connection
                String currentTime = sdf.format(dt);
                leResultat.updateString("LASTCONNECTION", currentTime);
                leResultat.updateRow () ;
                
                //On met à jour la fenêtre :
                OrthoVS.fen.connected () ;
                //On charge les patients
                OrthoVS.mySQLConnection.loadPatientsList () ;
                //message journal
                OrthoVS.user.journal.addJournal ("Auto connection : ok.") ;
            }
        }//fin de la "if laconnection != null
        } catch (SQLException e) { } 

        if (OrthoVS.user.nom == null) {
            OrthoVS.fen.comMenu.setEnabled(true) ;
            OrthoVS.fen.dcomMenu.setEnabled(false) ;
            OrthoVS.user.journal.addJournal ("Auto connection : erreur unicité.") ;
        }
        //On ferme la connection
        try {
            if (laConnection != null) laConnection.close();
            if (transmission != null) transmission.close () ;
            if (leResultat != null) leResultat.close () ;
        } catch (SQLException e) {}
    }
    
    
}
