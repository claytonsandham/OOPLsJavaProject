/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package chess;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

/**
 *
 * @author WALTERSGE1
 */
public class Ball
    {
        public ChessPanel.Type nodeType;
        public String id;
        
        public int lineWidth = 3;
        public int diameter = 100;       
        public Point position;
        public Color color;
        public String firstName;
        public String lastName;
        public String eogString;
        
        public Ball(Point position_, ChessPanel.Type nodeType_, String id_)
        {
            position = position_;
            nodeType = nodeType_;
            id = id_;
            
            switch(nodeType)
            {
                case PLAYER:
                    color = Color.RED;
                    firstName = id;
                    lastName = "";
                    String larger;
                    if(firstName.length() >= lastName.length())
                    {
                        larger = firstName;
                    }
                    else
                    {
                        larger = lastName;
                    }
                    if(larger.length() > 13)
                    {
                        int temp = larger.length() - 13;
                        diameter += (temp * 7);
                    }
                    break;
                case EVENT:
                    color = Color.BLACK;
                    eogString = id;
                    if(eogString.length() > 13)
                    {
                        int temp = eogString.length() - 13;
                        diameter += (temp * 7);
                    }
                    break;
                case GAME:
                    color = Color.GREEN;
                    eogString = id;
                    if(eogString.length() > 13)
                    {
                        int temp = eogString.length() - 13;
                        diameter += (temp * 7);
                    }
                    break;
                case OPENING:
                    color = Color.BLUE;
                    eogString = id;
                    if(eogString.length() > 13)
                    {
                        int temp = eogString.length() - 13;
                        diameter += (temp * 7);
                    }
                    break;
            }
            

 
            
        }
       
        public void draw(Graphics g)
        {
            Graphics2D g2d = (Graphics2D)g;
            g.setColor(color);
            g2d.setStroke(new BasicStroke(lineWidth));
            g.drawOval(position.x - (diameter/2),position.y - (diameter/2),diameter,diameter);
            
            g.setFont(new Font("Monospaced", Font.PLAIN, 12));
            switch(nodeType)
            {
                case PLAYER:
                    g.drawString(firstName, (position.x - (int)(3.5* firstName.length())), position.y-2);
                    g.drawString(lastName, (position.x - (int)(3.5* lastName.length())), position.y+9);
                    break;
                case EVENT:
                    g.drawString(eogString, (position.x - (int)(3.5* eogString.length())), position.y+4);
                    break;
                case GAME:
                    g.drawString(eogString, (position.x - (int)(3.5* eogString.length())), position.y+4);
                    break;
                case OPENING:
                    g.drawString(eogString, (position.x - (int)(3.5* eogString.length())), position.y+4);
                    break;
            }
            
        }
        
        public void drawLineToSecondBall(Graphics g, Ball other, Color lineColor)
        {
            Graphics2D g2d = (Graphics2D)g;
            g.setColor(lineColor);
            g2d.setStroke(new BasicStroke(lineWidth));
            Point start = new Point(position.x,position.y);
            Point stop = new Point(other.position.x,other.position.y);
            double distance = start.distance(stop);
            double normalX = start.x - stop.x;
            double normalY = start.y - stop.y;
            normalX /= distance;
            normalY /= distance;
            start.x -= normalX * ((diameter+lineWidth)/2);
            start.y -= normalY * ((diameter+lineWidth)/2);
            stop.x += normalX * ((other.diameter+lineWidth)/2);
            stop.y += normalY * ((other.diameter+lineWidth)/2);
            g.drawLine(start.x,start.y,stop.x,stop.y);
        }
        
        public Boolean collision(Point point)
        {
            double distance = point.distance(position);
            if(distance < ((lineWidth+diameter) / 2))
            {
                return true;
            }
            else
            {
                return false;
            }
        }
    }
