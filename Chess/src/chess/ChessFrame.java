package chess;

import java.awt.Color;
import java.awt.Point;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class ChessFrame extends JFrame 
{
    Random rnd = new Random();
    ChessPanel panel = new ChessPanel();
    
    public ChessFrame() 
    {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);        
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
