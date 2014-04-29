package chess;

import static chess.ChessPanel.Type.PLAYER;
import java.awt.BasicStroke;
import java.awt.Color;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ChessPanel extends JPanel
{    
    Point dragRef;
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
        java.awt.event.MouseAdapter ma = new java.awt.event.MouseAdapter() 
        {

            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) 
            {
                repaint();
                System.out.println("mouseEntered");
            }

            @Override
            public void mouseDragged(java.awt.event.MouseEvent evt) 
            {
                Point secondPoint = evt.getPoint();
                Point difference = new Point(dragRef.x - secondPoint.x,dragRef.y - secondPoint.y);
                ChessData.instance().offset.x -= difference.x;
                ChessData.instance().offset.y -= difference.y;
                dragRef = secondPoint;
                repaint();
                System.out.println(dragRef);
                System.out.println("mouseDragged"); 
            }
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) 
            {
                Point temp = evt.getPoint();
                dragRef = evt.getPoint();
                System.out.println(temp);
                for(int i = 0; i < sideBalls.size(); ++i)
                {
                    if(sideBalls.get(i).collision(evt.getPoint()))
                    {
                        ChessData.instance().offset = new Point(0,0);
                        centerBall = sideBalls.get(i);
                        sideBalls.clear();
                        animating = true;
                    }
                }
                System.out.println(dragRef);
                repaint();
                System.out.println("mousePressed");
            }
        };
        this.addMouseListener(ma);
        this.addMouseMotionListener(ma);
        
    }
    
    public void paint(Graphics g) 
    {
        super.paint(g); 
        
        if(first)
        {
            first = false;   
            bounds = g.getClipBounds();
            center = new Point(bounds.width/2,bounds.height/2);
       
            //hardcoded start
            centerBall = new Ball(center, Type.PLAYER, "Petrosian, Tigran V");
            createSideBalls(centerBall);

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
                    //animation stopped
                    createSideBalls(centerBall);
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
            if(centerBall.nodeType == PLAYER)
            {
                for(int i = 0; i < sideBalls.size()/2; ++i)
                {
  
                    sideBalls.get(i*2).draw(g);
                    centerBall.drawLineToSecondBall(g, sideBalls.get(2*i), Color.BLACK);
                    sideBalls.get((2*i)+1).draw(g);
                    sideBalls.get(i*2).drawLineToSecondBall(g, sideBalls.get((2*i)+1), Color.BLACK);
                }
            }
            else
            {
                for(int i = 0; i < sideBalls.size(); ++i)
                {
                    sideBalls.get(i).draw(g);
                    centerBall.drawLineToSecondBall(g, sideBalls.get(i), Color.BLACK);
                }
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
    
    public Point getBranchPoint(int branchIndex, int totalBranches, double distance, Point center)
    {
        double slice = 360 / totalBranches;
        double[] pt = {center.x, center.y + distance};
        AffineTransform.getRotateInstance(Math.toRadians(180 + (branchIndex*slice)), center.x, center.y).transform(pt, 0, pt, 0, 1);
        double newX = pt[0];
        double newY = pt[1];
        return new Point((int)newX,(int)newY);
    }
    
    public void createSideBalls(Ball centerBall)
    {
        switch(centerBall.nodeType)
        {
            case PLAYER:
                Player player = ChessData.instance().Players.get(centerBall.id);
                ArrayList<String> eventIDs = player.events;
                int numSideBalls = eventIDs.size();
                for(int i = 0; i < numSideBalls; ++i)
                {
                    sideBalls.add(new Ball(getBranchPoint(i, numSideBalls, 200, center), Type.EVENT, eventIDs.get(i)));
                    Event tempEvent = ChessData.instance().Events.get(eventIDs.get(i));
                    ArrayList<String> tempPlayers = tempEvent.players;
                    if(tempPlayers.get(0).equals(player.id))
                    {
                        sideBalls.add(new Ball(getBranchPoint(i, numSideBalls, 400, center), Type.PLAYER, tempPlayers.get(1)));
                    }
                    else
                    {
                        sideBalls.add(new Ball(getBranchPoint(i, numSideBalls, 400, center), Type.PLAYER, tempPlayers.get(0)));
                    }
                }
                break;
            case EVENT:
                Event event = ChessData.instance().Events.get(centerBall.id);
                ArrayList<String> gameIDs = event.games;
                ArrayList<String> playerIDs = event.players;
                int numberOfSideBalls = gameIDs.size() + 2;
                for(int i = 0; i < numberOfSideBalls; ++i)
                {
                    if(i < 2)
                    {
                        sideBalls.add(new Ball(getBranchPoint(i, numberOfSideBalls, 200 + (150 * (i+1)), center), Type.PLAYER, playerIDs.get(i)));
                    }
                    else
                    {
                        sideBalls.add(new Ball(getBranchPoint(i, numberOfSideBalls, 250, center), Type.GAME, gameIDs.get(i-2)));
                    }
                }
                break;
            case GAME:
                Game game = ChessData.instance().Games.get(centerBall.id);
                sideBalls.add(new Ball(getBranchPoint(0, 4, 250, center), Type.PLAYER, game.players.get(0)));
                sideBalls.add(new Ball(getBranchPoint(1, 4, 250, center), Type.PLAYER, game.players.get(1)));
                sideBalls.add(new Ball(getBranchPoint(2, 4, 250, center), Type.OPENING, game.eco));
                sideBalls.add(new Ball(getBranchPoint(3, 4, 250, center), Type.EVENT, game.event));
                break;
            case OPENING:
                Opening opening = ChessData.instance().Openings.get(centerBall.id);
                ArrayList<String> openingGameIDs = opening.games;
                for(int i = 0;i < openingGameIDs.size();++i)
                {
                    sideBalls.add(new Ball(getBranchPoint(i, openingGameIDs.size(), 250, center), Type.GAME, openingGameIDs.get(i)));
                }
                break;
        }

    }
}
