package chess;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class ChessFrame extends JFrame 
{
    Random rnd = new Random();
    ChessPanel panel = new ChessPanel();
    JFrame frame = this;
    
    public ChessFrame() 
    {
        JMenuBar menubar = new JMenuBar();

        JMenu fileCategory = new JMenu("File");
        
        JMenuItem saveMenuItem = new JMenuItem("Save");
        saveMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChessData.Save("ChessDataFile.txt");
            }
        });
        fileCategory.add(saveMenuItem);
        
        JMenuItem restoreMenuItem = new JMenuItem("Restore");
        restoreMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               
            }
        });
        fileCategory.add(restoreMenuItem);
        
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        fileCategory.add(exitMenuItem);
        
        menubar.add(fileCategory);
        
        JMenu insertCategory = new JMenu("Insert");
        

        
        JMenuItem gameMenuItem = new JMenuItem("Game");
        gameMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                
                String event = (String)JOptionPane.showInputDialog(frame,
                "Enter the event the game belongs to",
                "Enter Event:",
                JOptionPane.PLAIN_MESSAGE,
                null,
                null,
                "World Championship 1st");

                String site = (String)JOptionPane.showInputDialog(frame,
                "Enter the location of the game",
                "Enter Site:",
                JOptionPane.PLAIN_MESSAGE,
                null,
                null,
                "USA");
                
                String date = (String)JOptionPane.showInputDialog(frame,
                "Enter the date of the game",
                "Enter Date:",
                JOptionPane.PLAIN_MESSAGE,
                null,
                null,
                "YYYY.MM.DD");
                
                String round = (String)JOptionPane.showInputDialog(frame,
                "Enter the round number",
                "Enter Round:",
                JOptionPane.PLAIN_MESSAGE,
                null,
                null,
                "1");
                
                String whiteLastName = (String)JOptionPane.showInputDialog(frame,
                "Enter white's last name",
                "Enter White Last Name:",
                JOptionPane.PLAIN_MESSAGE,
                null,
                null,
                "Last");
                
                String whiteFirstName = (String)JOptionPane.showInputDialog(frame,
                "Enter white's first name and middle initial",
                "Enter White First Name and Middle Initial:",
                JOptionPane.PLAIN_MESSAGE,
                null,
                null,
                "First M.");
                
                String blackLastName = (String)JOptionPane.showInputDialog(frame,
                "Enter black's last name",
                "Enter Black Last Name:",
                JOptionPane.PLAIN_MESSAGE,
                null,
                null,
                "Last");
                
                String blackFirstName = (String)JOptionPane.showInputDialog(frame,
                "Enter black's first name and middle initial",
                "Enter Black First Name and Middle Initial:",
                JOptionPane.PLAIN_MESSAGE,
                null,
                null,
                "First M.");
                
                String result = (String)JOptionPane.showInputDialog(frame,
                "Enter game result",
                "Enter Result:",
                JOptionPane.PLAIN_MESSAGE,
                null,
                null,
                "?-?");
                
                String eco = (String)JOptionPane.showInputDialog(frame,
                "Enter ECO",
                "Enter ECO:",
                JOptionPane.PLAIN_MESSAGE,
                null,
                null,
                "A11");
                
                ChessData.instance().addGame(event, site, date, round, whiteLastName + ", " + whiteFirstName, blackLastName + ", " + blackFirstName, result, eco);

                
            }
        });
        insertCategory.add(gameMenuItem);
        
        menubar.add(insertCategory);
        
        setJMenuBar(menubar);

        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
               
        setBounds(50,50,java.awt.Toolkit.getDefaultToolkit().getScreenSize().width-100,java.awt.Toolkit.getDefaultToolkit().getScreenSize().height-100);
        getContentPane().add(panel);
        /*
        addComponentListener(new ComponentAdapter() 
        {
            public void componentResized(ComponentEvent e) 
            {
                // This is only called when the user releases the mouse button.
                
                float r = rnd.nextFloat();
                float g = rnd.nextFloat();
                float b = rnd.nextFloat();
                Color c1 = new Color(r, g, b);
                float r2 = rnd.nextFloat();
                float g2 = rnd.nextFloat();
                float b2 = rnd.nextFloat();
                Color c2 = new Color(r2, g2, b2);
                cgPanel.setColors(c1, c2);
                        
            }
        });
        */
    }
}
