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

public class ChessFrame extends JFrame 
{
    Random rnd = new Random();
    ChessPanel panel = new ChessPanel();
    
    public ChessFrame() 
    {
        JMenuBar menubar = new JMenuBar();

        JMenu fileCategory = new JMenu("File");
        
        JMenuItem saveMenuItem = new JMenuItem("Save");
        saveMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
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
