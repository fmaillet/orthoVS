
import java.io.Serializable;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Fred
 */
public class Score implements Serializable { //Un résultat individuel (= un flash)

    int level ; // monkeyLadder : empan proposé
                // feature : pareils initial (0 ou 1)
    int reponse  ;  // monkeyLadder : bon jusqu'à...
                    // feature : 0 si nok, 1 si ok
    int presentation ;  // monkeyLadder : temps de présentation
                        // feature : nbre d'items affichés
    long tr_i, tr_f ;   // monkeyLadder : temps de réponse initial et final
                        // feature : tr_f : tps de réponse (bonne ou mauvaise)
}
