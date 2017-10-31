// classe fournissant des fonctions de lecture au clavier
// d'apres "Exercices en Java" de Claude Delannoy
// http://www.editions-eyrolles.com/

import java.io.* ;

public class Clavier
{
    public static String readString ()   // lecture d'une chaine
    {
        String ligne_lue = null ;
        
        try
        {
            InputStreamReader lecteur = new InputStreamReader (System.in) ;
            
            BufferedReader entree = new BufferedReader (lecteur) ;
            
            ligne_lue = entree.readLine() ;
            System.out.println ("io") ;
        }
        catch (IOException err)
        {
            System.exit(0) ; 
        }
        return ligne_lue ;
    }
    
    public static char readChar ()   // lecture d'un char
    {
        char c = 0 ;   // valeur a lire
        
        try
        {
            String ligne_lue = readString() ;
            c = ligne_lue.charAt(0) ;
        }
        catch (IndexOutOfBoundsException err)
        {
            System.out.println ("*** Erreur de donnee ***") ;
            System.exit(0) ;
        }
        return c ;
    }
    
    public static float readFloat ()   // lecture d'un float
    {
        float x = 0 ;   // valeur a lire
        
        try
        {
            String ligne_lue = readString() ;
            x = Float.parseFloat(ligne_lue) ;
        }
        catch (NumberFormatException err)
        {
            System.out.println ("*** Erreur de donnee ***") ;
            System.exit(0) ;
        }
        return x ;
    }
    
    public static double readDouble ()   // lecture d'un double
    {
        double x = 0 ;   // valeur a lire
        try
        {
            String ligne_lue = readString() ;
            x = Double.parseDouble(ligne_lue) ;
        }
        catch (NumberFormatException err)
        {
            System.out.println ("*** Erreur de donnee ***") ;
            System.exit(0) ;
        }
        return x ;
    }
    public static int readInt ()         // lecture d'un int
    {
        int n = 0 ;   // valeur a lire
        try
        {
            String ligne_lue = readString() ;
            n = Integer.parseInt(ligne_lue) ;
        }
        catch (NumberFormatException err)
        {
            System.out.println ("*** Erreur de donnee ***") ;
            System.exit(0) ;
        }
        return n ;
    }
}


