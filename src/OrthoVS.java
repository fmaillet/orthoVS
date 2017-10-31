
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Date;
import javax.jnlp.BasicService;
import javax.jnlp.ServiceManager;
import javax.jnlp.UnavailableServiceException;




/**
 *
 * @author Fred
 */
public class OrthoVS {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Valeurs par défaut
        user = new UserInfo () ;
        mySQLConnection = new MySQLClass () ;
        // fenetre principale
        fen = new MainFenetre () ;
        fen.setVisible(true) ;
        fen.setResizable(false);
        
        try {
            BasicService basicService = (BasicService) ServiceManager.lookup("javax.jnlp.BasicService");
            OrthoVS.user.journal.addJournal ( "JNLP available"  );
            if (! basicService.isOffline())
                onLine = true ;
        } catch (UnavailableServiceException e) {OrthoVS.user.journal.addJournal ( "JNLP unavailable"  );}
        if (onLine) OrthoVS.user.journal.addJournal ( "Working online"  );
        else OrthoVS.user.journal.addJournal ( "Working offline"  );
        //Java Version
        OrthoVS.user.journal.addJournal ( "Java version " + System.getProperty("java.version") ) ;
    }
    
    static public UserInfo user ;
    static public MainFenetre fen ;
    static public MySQLClass mySQLConnection ;
    static public boolean onLine = false ;
    
    static Connection laConnection = null ;
    static Statement transmission ;
    static ResultSet leResultat ;
    
    public static void sortir () {
        fen.setEnabled(false);
        //On sauvegarde s'il y a un patient et des donénes
        if ( user.currentPatient != 0 )
            mySQLConnection.saveAllDatas() ;
        //On sauve le journal
        mySQLConnection.saveJournal() ;
        //On cache la fenêtre  
        fen.setVisible (false) ;
        //si connecté 
        if (OrthoVS.user.nom != null ) OrthoVS.mySQLConnection.rendreJeton() ;
        //Fin du programme
        System.exit(0);
    }
    
    public static Date getCurrentDateZeroTime () {
        Calendar dnCal = Calendar.getInstance();
        dnCal.set(Calendar.HOUR, 0);
        dnCal.set(Calendar.HOUR_OF_DAY, 0);
        dnCal.set(Calendar.MINUTE, 0);
        dnCal.set(Calendar.SECOND, 0);
        dnCal.set(Calendar.MILLISECOND, 0);
        return dnCal.getTime() ;
    }
    public static Date setDateZeroTime (Date d) {
        Calendar dnCal = Calendar.getInstance();
        dnCal.setTime (d) ;
        dnCal.set(Calendar.HOUR, 0);
        dnCal.set(Calendar.HOUR_OF_DAY, 0);
        dnCal.set(Calendar.MINUTE, 0);
        dnCal.set(Calendar.SECOND, 0);
        dnCal.set(Calendar.MILLISECOND, 0);
        return dnCal.getTime() ;
    }
}
