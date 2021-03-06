package chess;
import static com.sun.java.accessibility.util.AWTEventMonitor.addWindowListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author SANDHAMCR1
 */
public class Chess {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("WindowClosed");
                ChessData.Save("chess.data");
            }
        });

        //sets the initial data table.
        File gameData = new File("WCC.pgn");
        File ecoNames = new File("eco.txt");
        try {
            ParseECOs(ecoNames);
            ParseGames(gameData);
            ChessData.Save("chess.data");

        } catch (IOException ex) {
            Logger.getLogger(Chess.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //read in the old data?
        
        
        //display the menu -> move into drawing logic
        java.awt.EventQueue.invokeLater(new Runnable() 
        {
            public void run() 
            {
                ChessFrame chessFrame = new ChessFrame();
                chessFrame.setVisible(true);
                chessFrame.setResizable(false);
            }
        });


    }

    public Chess() {
    }

    public static void ParseGames(File fin) throws IOException 
    {
	FileInputStream fis = new FileInputStream(fin);
	//Construct BufferedReader from InputStreamReader
	BufferedReader br = new BufferedReader(new InputStreamReader(fis));
 
	String line = null;
        
        String event = null;
        String date = null;
        String round = null;
        String white = null;
        String black = null;
        String eco = null;
        String result = null;
        String location = null;
        
	while ((line = br.readLine()) != null) {
            if(line.contains("Event "))
            {
                line = line.replace("[Event \"", "");
                line = line.replace("\"]", "");
                line = line.trim();
                
                event = line;
                
                //System.out.println(line);
                
                while(!line.contains("Site"))
                {
                    line = br.readLine();
                }
                
                line = line.replace("[Site \"", "");
                line = line.replace("\"]", "");
                line = line.trim();
                location = line;

                //System.out.println(line);
                
                while(!line.contains("Date"))
                {
                    line = br.readLine();
                }
                
                line = line.replace("[Date \"", "");
                line = line.replace("\"]", "");
                line = line.trim();
                date = line;

                //System.out.println(line);
                
                while(!line.contains("Round"))
                {
                    line = br.readLine();
                }
                
                line = line.replace("[Round \"", "");
                line = line.replace("\"]", "");
                line = line.trim();
                round = line;

                //System.out.println(line);
                
                while(!line.contains("White"))
                {
                    line = br.readLine();
                }
                
                line = line.replace("[White \"", "");
                line = line.replace("\"]", "");
                line = line.trim();
                white = line;

                //System.out.println(line);
                
                while(!line.contains("Black"))
                {
                    line = br.readLine();
                }
                
                line = line.replace("[Black \"", "");
                line = line.replace("\"]", "");
                line = line.trim();
                black = line;

                //System.out.println(line);
                
                while(!line.contains("Result"))
                {
                    line = br.readLine();
                }
                
                line = line.replace("[Result \"", "");
                line = line.replace("\"]", "");
                line = line.trim();
                result = line;

                //System.out.println(line);
                
                while(!line.contains("ECO"))
                {
                    line = br.readLine();
                }
                
                line = line.replace("[ECO \"", "");
                line = line.replace("\"]", "");
                line = line.trim();
                eco = line;
                
                ChessData.instance().addGame(event, location, date, round, white, black, result, eco);
            }
            else
            {
                
            }
              
	}
 
	br.close();
    }
    
    public static void ParseECOs(File fin) throws IOException 
    {
	FileInputStream fis = new FileInputStream(fin);
	//Construct BufferedReader from InputStreamReader
	BufferedReader br = new BufferedReader(new InputStreamReader(fis));
 
	String line = null;
        
        String eco =  null;
        String name = null;
        
	while ((line = br.readLine()) != null) {
            eco = line.substring(0,2);
            //skip the whitespace beforethe "name"
            name = line.substring(4, line.length());
            
            ChessData.instance().addECO(eco, name);
            line = br.readLine();
            //JPN skips the line of "moves" - as it is unneccesary data
	}
 
	br.close();
    }
 
 
 }



