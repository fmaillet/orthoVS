
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.print.PrinterJob;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.LosslessFactory;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.printing.PDFPageable;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author fmail
 */
public class GeneratePDF extends Thread {
    public GeneratePDF () {
        setName ("GeneratePDF") ;
    }
    
    @Override
    public void run () {
        try {
            SimpleDateFormat df = new SimpleDateFormat("dd MMM yyyy");
            Date today = new Date () ;
            String fileName = "Rapport orthoVS "+OrthoVS.fen.jPatient.getText()+".pdf";
            PDDocument doc = new PDDocument();
            PDPage page = new PDPage(PDRectangle.A4);
            doc.addPage(page);
            PDPageContentStream content = new PDPageContentStream(doc, page);
            
            //Image img = OrthoVS.fen.chartPanelBAD.createImage(100, 100) ;
            //BufferedImage buffered = (BufferedImage) img ;
            
            //Le professionnel...
            content.beginText();
            float fontSize = 14;
            float leading = 1.5f * fontSize;
            content.setFont(PDType1Font.HELVETICA_BOLD, fontSize);
            content.setLeading(leading) ;
            content.newLineAtOffset(50, 777);
            content.showText (OrthoVS.user.titre + " "  + OrthoVS.user.nom + " " + OrthoVS.user.prenom + ", " + OrthoVS.user.activite);
            content.newLine();
            fontSize = 11;
            leading = 1.5f * fontSize;
            content.setLeading(leading) ;
            content.setFont(PDType1Font.HELVETICA, fontSize);
            content.showText (OrthoVS.user.adr1 + " - " + OrthoVS.user.adr2 + "  " + OrthoVS.user.cp + "  " + OrthoVS.user.ville) ;
            content.newLine();
            content.showText ("Tel: " + OrthoVS.user.tel1) ;
            content.endText();
            //Ligne
            content.moveTo(35,727);
            content.lineTo(page.getMediaBox().getWidth()-50, 727);
            content.moveTo(45,805);
            content.lineTo(45, 680);
            content.moveTo(35,795);
            content.lineTo(150, 795);
            content.stroke() ;
            
            //Titre
            content.beginText();
            fontSize = 16;
            leading = 1.5f * fontSize;
            content.setLeading(leading) ;
            content.setFont(PDType1Font.HELVETICA_BOLD, fontSize);
            content.setNonStrokingColor(Color.DARK_GRAY);
            content.newLineAtOffset(50, 705);
            content.showText ("Synthèse OrthoVisuoSpatial : " + OrthoVS.fen.jPatient.getText() );
            fontSize = 10;
            leading = 1.5f * fontSize;
            content.setLeading(leading) ;
            content.newLine();
            content.setFont(PDType1Font.HELVETICA_OBLIQUE, fontSize);
            content.showText (df.format(today)) ;
            content.endText();
            
            
            //Sous-titre
            content.beginText();
            content.setFont(PDType1Font.HELVETICA_BOLD, 12);
            content.setNonStrokingColor(Color.BLACK);
            content.newLineAtOffset(50, 640);
            content.showText ("Synthèse Mémoire VisuoSpatiale :");
            content.endText();
            
            //Graphique
            BufferedImage bufferedImage = OrthoVS.fen.chartPanelBAD.getChart().createBufferedImage(OrthoVS.fen.chartPanelBAD.getWidth(),
                OrthoVS.fen.chartPanelBAD.getHeight(), BufferedImage.TYPE_INT_RGB, null);
            PDImageXObject ximage = LosslessFactory.createFromImage (doc, bufferedImage);
            content.drawImage( ximage , 375, 540, 175, 146); // rapport 1.2 entre la hauteur et la largeur du graphique
            
            //Texte...
            String analyse = PanelSynthese.jTextRG.getDocument().getText(0, PanelSynthese.jTextRG.getDocument().getLength()) ;
            analyse = analyse.replace("\n", "").replace("\r", "");
            //analyse = analyse.substring (36) ;
            //Split text in lines
            PDFont pdfFont = PDType1Font.HELVETICA;
            
            float width = 310 ;
            ArrayList<String> lines = new ArrayList<String>();
            int lastSpace = -1;
            while (analyse.length() > 0) {
                int spaceIndex = analyse.indexOf(' ', lastSpace + 1);
                if (spaceIndex < 0)
                    spaceIndex = analyse.length();
                String subString = analyse.substring(0, spaceIndex);
                float size = fontSize * pdfFont.getStringWidth(subString) / 1000;
                //System.out.printf("'%s' - %f of %f\n", subString, size, width);
                if (size > width)
                {
                    if (lastSpace < 0)
                        lastSpace = spaceIndex;
                    subString = analyse.substring(0, lastSpace);
                    lines.add(subString);
                    analyse = analyse.substring(lastSpace).trim();
                    //System.out.printf("'%s' is line\n", subString);
                    lastSpace = -1;
                }
                else if (spaceIndex == analyse.length())
                {
                    lines.add(analyse);
                    //System.out.printf("'%s' is line\n", analyse);
                    analyse = "";
                }
                else
                {
                    lastSpace = spaceIndex;
                }
            }
            
            //Writing text to pdf
            fontSize = 10;
            leading = 1.5f * fontSize;
            content.beginText();
            content.setFont(PDType1Font.HELVETICA, fontSize);
            content.setLeading(leading) ;
            content.newLineAtOffset(50, 620);
            
            for (String line: lines) {
                content.showText(line);
                content.newLine();
            }
            //content.endText();
            
            //Note finale
            content.newLine();
            content.setFont(PDType1Font.HELVETICA_OBLIQUE, 8) ;
            content.setNonStrokingColor(Color.RED);
            //content.newLineAtOffset(50, 500);
            content.showText("(Synthèse incomplète en l'état actuel du programme : " + OrthoVS.user.getSoftVersion() + ")");
            content.endText();

            content.close();
            //On imprime ou on affiche
            if (! OrthoVS.onLine ) {
                doc.save(fileName);
                File myFile = new File(fileName);
                Desktop.getDesktop().open(myFile);
            }
            else {
                PrinterJob job = PrinterJob.getPrinterJob();
                job.setPageable(new PDFPageable(doc));
                job.print();
            }
            
            
            
            //On ferme le doc
            
            doc.close();
            
        } catch (Exception e) { OrthoVS.user.journal.addJournal ( e.toString() ); }
    }
}
