
package chess;
import java.util.HashMap;
import java.util.Map;

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
    
}
