

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Fred
 */
public class InputRep extends Thread {
    public InputRep () {
        
    }
    
    @Override
    public void run () {
        Clavier.readString() ;
    }
    
}
