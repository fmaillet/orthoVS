
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.Date;
import java.util.LinkedList;
import java.util.Properties;




/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Fred
 */
public class MySQLClass {
    private static final String DATABASE_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DATABASE_URL = "jdbc:mysql://fredericmaillet.fr/fmaillet_professionnels?autoReconnect=true";
    private static final String USERNAME = "fmaillet_fredo";
    private static final String PASSWORD = "mastercog";
    private static final String MAX_POOL = "250";
    
    // init connection object
    public static Connection connection;
    // init properties object
    private Properties properties;
    
    // create properties
    private Properties getProperties() {
        if (properties == null) {
            properties = new Properties();
            properties.setProperty("user", USERNAME);
            properties.setProperty("password", PASSWORD);
            properties.setProperty("MaxPooledStatements", MAX_POOL);
        }
        return properties;
    }
    
    // connect database
    public Connection connect() {
        if (connection != null) {
            try {
                if (! connection.isValid(120)) {
                    disconnect () ;
                }
                UserInfo.journal.addJournal("Connection still alive.") ;
            }    catch (Exception e) { disconnect () ; }
        }
        if (connection == null) {
            try {
                Class.forName(DATABASE_DRIVER);
                DriverManager.setLoginTimeout(5);
                connection = DriverManager.getConnection(DATABASE_URL, getProperties());
                if (connection != null) connection.setAutoCommit(true);
            } catch (ClassNotFoundException | SQLException e) {
                //e.printStackTrace();
            }
            UserInfo.journal.addJournal("Connection.") ;
        }
        
        return connection;
    }
    // disconnect database
    public void disconnect() {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
            } catch (SQLException e) {
                //e.printStackTrace();
            }
            UserInfo.journal.addJournal("Déconnection.") ;
        }
    }
    
    private Statement transmission ;
    private ResultSet leResultat ;
    
    public static boolean getJeton (ResultSet r) { //Pour orthoVS c'est le secod bit
        // on prend le jeton 
        try {
            byte jeton = r.getByte("JETON") ;
            jeton = (byte) (jeton | 0x02) ;
            r.updateByte("JETON", jeton);
            
        } catch (SQLException e) { return false ; }
        return true ;
    }

    //On rend le jeton...
    public void rendreJeton() {

        if (connect () != null) {
            try {
                transmission = connection.createStatement (ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE) ;
                leResultat = transmission.executeQuery ("select ADELI, JETON from Pro where ADELI = " + OrthoVS.user.adeli) ;
                if (leResultat.next()) {
                    // On rend le jeton
                    byte jeton = leResultat.getByte("JETON") ;
                    jeton = (byte) (jeton & 0xFD) ;
                    leResultat.updateByte("JETON", jeton);
                    
                    //On met à jour
                    leResultat.updateRow () ;
                }
            } catch (Exception e) { }
            
        }
    }
    
    //on met à jour le journal dans la base
    public void saveJournal () {
        SaveJournalThrd sj = new SaveJournalThrd () ;
        Thread t = new Thread (sj) ; t.start () ;
    }
    
    //Sauvegarde des données dans la base
    public void saveAllDatas () {
        if (UserInfo.modifiedResultatsMonkey) {
            SaveMonkThrd rg = new SaveMonkThrd () ;
        }
        if (UserInfo.modifiedResultatsFeature) {
            SaveFeatThrd rg = new SaveFeatThrd () ;
        }
        if (UserInfo.modifiedResultatsPolygons) {
            SavePolyThrd rg = new SavePolyThrd () ;
        }
        if (UserInfo.modifiedResultatsShepard) {
            SaveShepThrd rg = new SaveShepThrd () ;
        }
        if (UserInfo.modifiedResultatsSymetry) {
            SaveSymetryThrd rg = new SaveSymetryThrd () ;
        }
    }
    
    //Test lire les datats
    public void loadAllPatientDatas () {
        //On informe
        //UserInfo.journal.addJournal("Loading previous results") ;
        long debut = System.currentTimeMillis () ;
        //On va chercher les données
        if (connect () != null) {
            try {
                String updateSQL = "SELECT VS_MONK, VS_FEAT, VS_POLY, VS_SHEP, VS_SYM from Patients WHERE ID = " + UserInfo.currentPatient ;
                PreparedStatement pstmt = connection.prepareStatement(updateSQL) ;
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    InputStream input ;
                    //Résultats Monkey Ladder
                    try {
                        input = rs.getBinaryStream("VS_MONK") ;
                        if (input.available() > 0) {
                            ObjectInput in = new ObjectInputStream(input);
                            UserInfo.resultatsMonkey = (LinkedList) in.readObject() ;
                        }
                    } catch (Exception e) {UserInfo.journal.addJournal("loading MonkeyLadder : " + e.toString()) ;}
                    //Résultats Feature
                    try {
                        input = rs.getBinaryStream("VS_FEAT") ;
                        if (input.available() > 0) {
                            ObjectInput in = new ObjectInputStream(input);
                            UserInfo.resultatsFeature = (LinkedList) in.readObject() ;
                        }
                    } catch (Exception e) {UserInfo.journal.addJournal("loading Feature : " + e.toString()) ;}
                    //Résultats Polygones
                    try {
                        input = rs.getBinaryStream("VS_POLY") ;
                        if (input.available() > 0) {
                            ObjectInput in = new ObjectInputStream(input);
                            UserInfo.resultatsPolygons = (LinkedList) in.readObject() ;
                        }
                    } catch (Exception e) {UserInfo.journal.addJournal("loading Polygons : " + e.toString()) ;}
                    //Résultats Shepard
                    try {
                        input = rs.getBinaryStream("VS_SHEP") ;
                        if (input.available() > 0) {
                            ObjectInput in = new ObjectInputStream(input);
                            UserInfo.resultatsShepard = (LinkedList) in.readObject() ;
                        }
                    } catch (Exception e) {UserInfo.journal.addJournal("loading Shepard : " + e.toString()) ;}
                    //Résultats Symetry
                    try {
                        input = rs.getBinaryStream("VS_SYM") ;
                        if (input.available() > 0) {
                            ObjectInput in = new ObjectInputStream(input);
                            UserInfo.resultatsSymetry = (LinkedList) in.readObject() ;
                        }
                    } catch (Exception e) {UserInfo.journal.addJournal("loading Symetry : " + e.toString()) ;}
                }
                pstmt.close () ;
            } catch (Exception e) {UserInfo.journal.addJournal("loading: " + e.toString()) ;}
        }
        //Si données vides il faut créer les structures
        if (UserInfo.resultatsMonkey == null) UserInfo.resultatsMonkey  = new LinkedList<Session> () ;
        UserInfo.modifiedResultatsMonkey = false ;
        if (UserInfo.resultatsFeature == null) UserInfo.resultatsFeature  = new LinkedList<Session> () ;
        UserInfo.modifiedResultatsFeature = false ;
        if (UserInfo.resultatsPolygons == null) UserInfo.resultatsPolygons  = new LinkedList<Session> () ;
        UserInfo.modifiedResultatsPolygons = false ;
        if (UserInfo.resultatsShepard == null) UserInfo.resultatsShepard  = new LinkedList<Session> () ;
        UserInfo.modifiedResultatsShepard = false ;
        if (UserInfo.resultatsSymetry == null) UserInfo.resultatsSymetry  = new LinkedList<Session> () ;
        UserInfo.modifiedResultatsSymetry = false ;
        //On informe
        UserInfo.journal.addJournal ("Loading previous results (" + 
                String.valueOf(System.currentTimeMillis()-debut ) + " ms)");
    }
    
    public void loadPatientsList () {
        //Durée du transfert ?
        long debut = System.currentTimeMillis () ;
        //Date ?
        java.text.SimpleDateFormat sdf ;
        sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
        Date d ;
        OrthoVS.user.clearListePatients() ;
        //connect () ;
        if (connect () != null) {
            try {
                transmission = connection.createStatement (ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE) ;
                leResultat = transmission.executeQuery ("select ID, NOM, PRENOM, DN, SEX, ACTIF from Patients where ADELI = " + OrthoVS.user.adeli) ;
                while (leResultat.next()) {
                    //On lit le patient dans la base
                    int pID = leResultat.getInt("ID") ;
                    String pNom    = leResultat.getString("NOM") ;
                    String pPrenom = leResultat.getString("PRENOM") ;
                    String dn = leResultat.getString("DN") ;
                    Boolean actif = leResultat.getBoolean("ACTIF") ;
                    int sex = leResultat.getInt("SEX") ;
                    if (dn == null) dn = "1980-01-01" ;
                        d = sdf.parse(dn);
                    //On rajoute le patient en local
                    OrthoVS.user.addPatients(pID, pNom, pPrenom, d, sex, actif);
                }
            } catch (SQLException | ParseException e) {UserInfo.journal.addJournal(e.toString()) ;}
            UserInfo.journal.addJournal("Base Patients chargée (" + 
                String.valueOf(System.currentTimeMillis()-debut ) + " ms)");
        }
        else UserInfo.journal.addJournal("Base Patients pas chargée !") ;
    }
    
    public int addNewPatient (String pNom, String pPrenom, int sex, Date pDN) {
        int resultID = 0 ;
        //connect () ;
        //Ajouter dans la base
        if (connect () != null) {
            try {
                //Reset max unique id ?
                /*int maxID = 0 ;
                Statement s = connection.createStatement();
                s.execute("SELECT MAX(ID) FROM Patients");
                ResultSet rs = s.getResultSet();
                if (rs.next()) {
                    maxID = rs.getInt(1);
                }
                System.out.println (maxID) ;*/

                //Place the add query
                String query = "insert into Patients (ADELI, NOM, PRENOM, DN, SEX, ACTIF)"
                    + " values (?, ?, ?, ?, ?, ?)";
                PreparedStatement pStmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                pStmt.setInt   (1, Integer.parseUnsignedInt(OrthoVS.user.adeli)) ;
                pStmt.setString(2, pNom);
                pStmt.setString(3, pPrenom);
                if (pDN != null) pStmt.setDate  (4, new java.sql.Date(pDN.getTime()));
                else pStmt.setDate  (4, null) ;
                pStmt.setInt (5, sex) ;
                pStmt.setBoolean (6, true) ;
                pStmt.execute() ;
                ResultSet rs = pStmt.getGeneratedKeys();
                if (rs.next()){
                    resultID=rs.getInt(1);
                }
                pStmt.close () ;
                
            } catch (Exception e) {UserInfo.journal.addJournal(e.toString()) ; return 0 ; }
        }
        //On sauve des données vides
        OrthoVS.user.currentPatient = resultID ;
        UserInfo.disposeAllResultats () ;
        SaveMonkThrd rg = new SaveMonkThrd () ;
        
        //Tout s'est bien passé
        return resultID ;
    }
    
    public void deletePatient (int selectedID) {
        //connect () ;
        if (connect () != null) {
            try {
                String query = "delete from Patients where ID = ?" ;
                PreparedStatement pStmt = connection.prepareStatement(query);
                pStmt.setInt(1, selectedID);
                pStmt.execute () ;
                pStmt.close () ;
                //System.out.println (r) ;
            } catch (Exception e) {UserInfo.journal.addJournal(e.toString()) ; }
        }
    }
    
    public void updatePatient (int selectedID, String newNom, String newPrenom, int newSex, Date newDN) {
        //System.out.println (selectedID + ": "+ newNom + " "+ newPrenom) ;
        //connect () ;
        if (connect () != null) {
            try {
                String query = "update Patients set NOM = ?, PRENOM = ?, SEX = ?, DN = ? where ID = ?" ;
                PreparedStatement pStmt = connection.prepareStatement(query);
                pStmt.setString(1, newNom);
                pStmt.setString(2, newPrenom);
                pStmt.setInt(3, newSex);
                if (newDN != null) pStmt.setDate  (4, new java.sql.Date(newDN.getTime()));
                else pStmt.setDate  (4, null) ;
                pStmt.setInt(5, selectedID);
                pStmt.executeUpdate () ;
                pStmt.close () ;
                UserInfo.journal.addJournal("Patient "+selectedID+" updated") ;
            } catch (Exception e) {UserInfo.journal.addJournal(e.toString()) ; }
        }
    }
}//Fin de la classe MySQLClass



//Classe thread de sauvegarde du journal
class SaveJournalThrd implements Runnable {
    @Override
    public void run() {
        if (OrthoVS.mySQLConnection.connect () != null) {
            try { 
                String updateSQL = "UPDATE Pro SET JNAL_VS = CONCAT(JOURNAL, ?) WHERE ADELI = " + OrthoVS.user.adeli ;
                PreparedStatement pstmt = OrthoVS.mySQLConnection.connection.prepareStatement(updateSQL) ;
                pstmt.setString(1, OrthoVS.user.journal.jTexte.getText());
                pstmt.executeUpdate() ;
                pstmt.close () ;
            } catch (Exception e) { System.out.println (e.toString() );}
        }
    }
    
}

//Classe thread de sauvegarde des données Monkey Ladder
class SaveMonkThrd implements Runnable {
    
    Thread t ;

    SaveMonkThrd () {
        t = new Thread (this, "savingMonkey") ;
        t.start ( ) ;
        //On force l'attente de la fin de la sauvegarde
        //try { t.join();} catch (InterruptedException e) {}
    }
    
    @Override
    public void run() {
        //On informe
        //UserInfo.journal.addJournal("Saving ResultatsRG.") ;
        long debut = System.currentTimeMillis();
        //connect () ;
        if (OrthoVS.mySQLConnection.connect () != null) {
            try { 
                String updateSQL = "UPDATE Patients SET VS_MONK = ? WHERE ID = " + OrthoVS.user.currentPatient ;
                PreparedStatement pstmt = OrthoVS.mySQLConnection.connection.prepareStatement(updateSQL) ;
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(bos);
                oos.writeObject(UserInfo.resultatsMonkey);
                byte[] bytes = bos.toByteArray();
                InputStream input = new ByteArrayInputStream(bytes) ;
                
                pstmt.setBinaryStream(1, input);
                pstmt.executeUpdate() ;
                pstmt.close () ;
                //On ralenti le thread pour tester une comm lente
                //Thread.sleep (10000) ;
                UserInfo.modifiedResultatsMonkey = false ;
                
            } catch (Exception e) {UserInfo.journal.addJournal(e.toString()) ;}
        }
        UserInfo.journal.addJournal ("Saving ResultatsMonk (" + 
                String.valueOf(System.currentTimeMillis()-debut ) + " ms)");
    }
    
}

class SaveSymetryThrd implements Runnable {
    
    Thread t ;

    SaveSymetryThrd () {
        t = new Thread (this, "savingSymetry") ;
        t.start ( ) ;
        //On force l'attente de la fin de la sauvegarde
        //try { t.join();} catch (InterruptedException e) {}
    }
    
    @Override
    public void run() {
        //On informe
        //UserInfo.journal.addJournal("Saving ResultatsRG.") ;
        long debut = System.currentTimeMillis();
        //connect () ;
        if (OrthoVS.mySQLConnection.connect () != null) {
            try { 
                String updateSQL = "UPDATE Patients SET VS_SYM = ? WHERE ID = " + OrthoVS.user.currentPatient ;
                PreparedStatement pstmt = OrthoVS.mySQLConnection.connection.prepareStatement(updateSQL) ;
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(bos);
                oos.writeObject(UserInfo.resultatsSymetry);
                byte[] bytes = bos.toByteArray();
                InputStream input = new ByteArrayInputStream(bytes) ;
                
                pstmt.setBinaryStream(1, input);
                pstmt.executeUpdate() ;
                pstmt.close () ;
                //On ralenti le thread pour tester une comm lente
                //Thread.sleep (10000) ;
                UserInfo.modifiedResultatsSymetry = false ;
                
            } catch (Exception e) {UserInfo.journal.addJournal(e.toString()) ;}
        }
        UserInfo.journal.addJournal ("Saving ResultatsSym (" + 
                String.valueOf(System.currentTimeMillis()-debut ) + " ms)");
    }
    
}

//Classe thread de sauvegarde des données Feature
class SaveFeatThrd implements Runnable {
    
    Thread t ;

    SaveFeatThrd () {
        t = new Thread (this, "savingFeature") ;
        t.start ( ) ;
        //On force l'attente de la fin de la sauvegarde
        //try { t.join();} catch (InterruptedException e) {}
    }
    
    @Override
    public void run() {
        //On informe
        //UserInfo.journal.addJournal("Saving ResultatsRG.") ;
        long debut = System.currentTimeMillis();
        //connect () ;
        if (OrthoVS.mySQLConnection.connect () != null) {
            try { 
                String updateSQL = "UPDATE Patients SET VS_FEAT = ? WHERE ID = " + OrthoVS.user.currentPatient ;
                PreparedStatement pstmt = OrthoVS.mySQLConnection.connection.prepareStatement(updateSQL) ;
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(bos);
                oos.writeObject(UserInfo.resultatsFeature);
                byte[] bytes = bos.toByteArray();
                InputStream input = new ByteArrayInputStream(bytes) ;
                
                pstmt.setBinaryStream(1, input);
                pstmt.executeUpdate() ;
                pstmt.close () ;
                //On ralenti le thread pour tester une comm lente
                //Thread.sleep (10000) ;
                UserInfo.modifiedResultatsFeature = false ;
                
            } catch (Exception e) {UserInfo.journal.addJournal(e.toString()) ;}
        }
        UserInfo.journal.addJournal ("Saving ResultatsFeature (" + 
                String.valueOf(System.currentTimeMillis()-debut ) + " ms)");
    }
    
}

//Classe thread de sauvegarde des données Polygones
class SavePolyThrd implements Runnable {
    
    Thread t ;

    SavePolyThrd () {
        t = new Thread (this, "savingPolygones") ;
        t.start ( ) ;
        //On force l'attente de la fin de la sauvegarde
        //try { t.join();} catch (InterruptedException e) {}
    }
    
    @Override
    public void run() {
        //On informe
        //UserInfo.journal.addJournal("Saving ResultatsRG.") ;
        long debut = System.currentTimeMillis();
        //connect () ;
        if (OrthoVS.mySQLConnection.connect () != null) {
            try { 
                String updateSQL = "UPDATE Patients SET VS_POLY = ? WHERE ID = " + OrthoVS.user.currentPatient ;
                PreparedStatement pstmt = OrthoVS.mySQLConnection.connection.prepareStatement(updateSQL) ;
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(bos);
                oos.writeObject(UserInfo.resultatsPolygons);
                byte[] bytes = bos.toByteArray();
                InputStream input = new ByteArrayInputStream(bytes) ;
                
                pstmt.setBinaryStream(1, input);
                pstmt.executeUpdate() ;
                pstmt.close () ;
                //On ralenti le thread pour tester une comm lente
                //Thread.sleep (10000) ;
                UserInfo.modifiedResultatsPolygons = false ;
                
            } catch (Exception e) {UserInfo.journal.addJournal(e.toString()) ;}
        }
        UserInfo.journal.addJournal ("Saving ResultatsPolygons (" + 
                String.valueOf(System.currentTimeMillis()-debut ) + " ms)");
    }
    
}

//Classe thread de sauvegarde des données Shepard
class SaveShepThrd implements Runnable {
    
    Thread t ;

    SaveShepThrd () {
        t = new Thread (this, "savingShepard") ;
        t.start ( ) ;
        //On force l'attente de la fin de la sauvegarde
        //try { t.join();} catch (InterruptedException e) {}
    }
    
    @Override
    public void run() {
        //On informe
        //UserInfo.journal.addJournal("Saving ResultatsRG.") ;
        long debut = System.currentTimeMillis();
        //connect () ;
        if (OrthoVS.mySQLConnection.connect () != null) {
            try { 
                String updateSQL = "UPDATE Patients SET VS_SHEP = ? WHERE ID = " + OrthoVS.user.currentPatient ;
                PreparedStatement pstmt = OrthoVS.mySQLConnection.connection.prepareStatement(updateSQL) ;
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(bos);
                oos.writeObject(UserInfo.resultatsShepard);
                byte[] bytes = bos.toByteArray();
                InputStream input = new ByteArrayInputStream(bytes) ;
                
                pstmt.setBinaryStream(1, input);
                pstmt.executeUpdate() ;
                pstmt.close () ;
                //On ralenti le thread pour tester une comm lente
                //Thread.sleep (10000) ;
                UserInfo.modifiedResultatsShepard = false ;
                
            } catch (Exception e) {UserInfo.journal.addJournal(e.toString()) ;}
        }
        UserInfo.journal.addJournal ("Saving ResultatsShepard (" + 
                String.valueOf(System.currentTimeMillis()-debut ) + " ms)");
    }
    
}