
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.ListIterator;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import org.apache.commons.math3.stat.regression.SimpleRegression;
import org.jfree.data.xy.XYSeries;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author fmail
 */
public class Synthese extends Thread {
    
    public Synthese () {
        setName ("Synthese") ;
        datesOfSeances = new ArrayList () ;
        kit = new HTMLEditorKit();
        doc = new HTMLDocument();
        PanelSynthese.jTextRG.setEditorKit(kit);
        PanelSynthese.jTextRG.setDocument(doc);
    }
    
    int nbSession = 0 ;
    int nbSeances = 0 ;
    double[][] meansOfRGSessions, meansOfRGSeances ;
    public static ArrayList datesOfSeances ;
    HTMLEditorKit kit ;
    HTMLDocument doc ;
    SimpleRegression regression ;
    
    @Override
    public void run () {
        StringBuilder text = new StringBuilder ();
        text.append ("<b>Synthèse Mémoire Visuo-Spatiale :</b><br>") ;
        //On initialise les graphes
        //OrthoVS.fen.serieBAD = new XYSeries("% report");
        //OrthoVS.fen.xySeriesCollectionBAD.removeAllSeries();
  
        
        //text.append ("<br><br><i>test</i>") ;
        addText (text.toString()) ;
        
    }
    
    void addText (String s) {
        //StyledDocument doc = PanelSynthese.jTextRG.getStyledDocument();
        try {
            kit.insertHTML(doc, doc.getLength()-14, s, 0, 0, null );

            
        } catch (Exception e) {}
        
        
    }
    
    
    
    
    
}
