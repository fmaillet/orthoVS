
import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Fred
 */
public class Session implements Serializable { //l'ensemble des résultats de la session
    public Date date ;
    public int gridSize ;
    LinkedList<Score> results ;
}
