
import java.util.Calendar;
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
public class Patient {
    int id ;
    String nom , prenom ;
    Date dn ;
    int sex = 0 ;
    Boolean actif ;
    
    

    public int getAge() {
        //Today
        //Date td = Calendar.getInstance().getTime();
        Calendar tdCal = Calendar.getInstance();
        //date of Birth
        Calendar dnCal = Calendar.getInstance();
        dnCal.setTime(dn);
        
        //System.out.println ( tdCal.get(Calendar.YEAR) +" "+ dnCal.get(Calendar.YEAR));
        int age = tdCal.get(Calendar.YEAR) - dnCal.get(Calendar.YEAR) ;
        if ( tdCal.get(Calendar.MONTH) < dnCal.get(Calendar.MONTH) )
            age -- ;
        return age ;
    }


}
