package chess;

import java.awt.BasicStroke;
import java.awt.Color;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ChessPanel extends JPanel
{    
    Boolean first = true;
    Boolean animating = false;
    enum Type {PLAYER,EVENT,GAME,OPENING}
    
    public Ball centerBall;
    public Vector<Ball> sideBalls = new Vector<Ball>();
    
    Rectangle bounds;
    Point center;
    Point animationStart;
  
    public ChessPanel() 
    {
        this.addMouseListener(new java.awt.event.MouseAdapter() 
        {
            public void mouseClicked(java.awt.event.MouseEvent evt) 
            {
                Point temp = evt.getPoint();

                for(int i = 0; i < sideBalls.size(); ++i)
                {
                    if(sideBalls.get(i).collision(temp))
                    {
                        centerBall = sideBalls.get(i);
                        sideBalls.clear();
                        animating = true;
                        repaint();
                    }
                }
                System.out.println("mouseClicked");
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) 
            {
                repaint();
                System.out.println("mouseEntered");
            }
            public void mouseExited(java.awt.event.MouseEvent evt) 
            {
                System.out.println("mouseExited");
            }
            public void mousePressed(java.awt.event.MouseEvent evt) 
            {
                System.out.println("mousePressed");
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) 
            {
                System.out.println("mouseReleased");
            }
        });
    }
    
    public Point getBranchPoint(int branchIndex, int totalBranches, double distance, Point center)
    {
        double slice = 360 / totalBranches;
        double[] pt = {center.x, center.y + distance};
        AffineTransform.getRotateInstance(Math.toRadians(180 + (branchIndex*slice)), center.x, center.y).transform(pt, 0, pt, 0, 1);
        double newX = pt[0];
        double newY = pt[1];
        return new Point((int)newX,(int)newY);
    }
    
    public void paint(Graphics g) 
    {
        super.paint(g); 
        
        if(first)
        {
            first = false;   
            bounds = g.getClipBounds();
            center = new Point(bounds.width/2,bounds.height/2);
            
            centerBall = new Ball(center, Type.PLAYER, "asdf");
            for(int i = 0; i < 8; ++i)
            {
                sideBalls.add(new Ball(getBranchPoint(i, 8, 200, center), Type.EVENT, "asdf"));
            }
        }
        else
        {              
            g.setColor(Color.WHITE);
            g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);

            if(animating)
            {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException ex) {
                    Logger.getLogger(ChessPanel.class.getName()).log(Level.SEVERE, null, ex);
                }


                   
                int speed = 5;
                double distance = centerBall.position.distance(center);
                
                if(distance <= speed)
                {
                    animating = false;
                    centerBall.position = center;
                }
                else
                {
                     double normalX = centerBall.position.x - center.x;
                     double normalY = centerBall.position.y - center.y;
                     normalX /= distance;
                     normalY /= distance;

                     centerBall.position.x -= normalX * speed;
                     centerBall.position.y -= normalY * speed; 
                     repaint();
                }   
            }
            else
            {
                
            }
            
            centerBall.draw(g);
            for(int i = 0; i < sideBalls.size(); ++i)
            {
                sideBalls.get(i).draw(g);
                centerBall.drawLineToSecondBall(g, sideBalls.get(i), Color.BLACK);
            }
        }
        System.out.println("paint() called");

        /*
        Rectangle bounds = g.getClipBounds();
        g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
        int[] xPoints = {bounds.width/2, bounds.width, bounds.width/2, 0};
        int[] yPoints = {0, bounds.height/2, bounds.height, bounds.height/2};
        g.fillPolygon(xPoints, yPoints, 4);
        g.setColor(Color.BLACK);
        g.drawPolygon(xPoints, yPoints, 4);
        g.drawLine(0, 0, 12, 12);
        g.drawString("Hello World", 15, 15);
        g.drawRect(20, 20, 20, 20);
        g.drawOval(20, 20, 20, 20);
        System.out.println("paint() called");
        */
    }
    

}
