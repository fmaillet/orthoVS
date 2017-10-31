
import java.io.BufferedInputStream;
import java.io.IOException;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Fred
 */
public class SoundClips extends Thread {
    public SoundClips (int t) {
        son = t ;
    }
    
    @Override
    public void run () {
        
        if (! OrthoVS.fen.sndMenu.isSelected())
                return ;
        switch (son) {
            case 0 :
                fichier = "Ressources/Pling.wav" ;
                break ;
            case 1 :
                fichier = "Ressources/pindrop.wav" ;
                break ;
            case 2 :
                fichier = "Ressources/Applause2.wav" ; //applaudissements léger
                break ;
            case 3 :
                fichier = "Ressources/Applause1.wav" ; //applaudissements fournis
                break ;
            case 4 :
                fichier = "Ressources/positive-beeps.wav" ; // Good beep
                break ;
            case 5 :
                fichier = "Ressources/negative-beeps.wav" ; // Bad beep
                break ;
            default :
                fichier = "Pling.wav" ;
        }
        try {
            
            clip = AudioSystem.getClip();
        
            clip.open(AudioSystem.getAudioInputStream(new BufferedInputStream(getClass().getResourceAsStream(fichier))));
            clip.start();
            /*try {
                Thread.sleep(6000);
            } catch (Exception e) {}
            if (clip.isOpen()) {
             clip.close();
            }*/
            
            clip.addLineListener( new LineListener() {
                @Override
                public void update(LineEvent evt) {
                    if (evt.getType() == LineEvent.Type.STOP) {
                        evt.getLine().close();
                    }
                }

                
            });
            
        }    catch (LineUnavailableException | UnsupportedAudioFileException | IOException e)
        {
            //System.out.println (fichier) ;    
            //e.printStackTrace(System.out);
        }
        
    }
    
    int son ;
    String fichier ;
    Clip clip ;
}
