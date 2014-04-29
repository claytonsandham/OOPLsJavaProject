/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package chess;

import java.awt.BasicStroke;
import java.awt.Color;
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
        
        public Ball(Point position_, ChessPanel.Type nodeType_, String id_)
        {
            position = position_;
            nodeType = nodeType_;
            id = id_;
            
            switch(nodeType)
            {
                case PLAYER:
                    color = Color.RED;
                    break;
                case EVENT:
                    color = Color.BLACK;
                    break;
                case GAME:
                    color = Color.GREEN;
                    break;
                case OPENING:
                    color = Color.BLUE;
                    break;
            }
        }
       
        public void draw(Graphics g)
        {
            Graphics2D g2d = (Graphics2D)g;
            g.setColor(color);
            g2d.setStroke(new BasicStroke(lineWidth));
            g.drawOval(position.x - (diameter/2),position.y - (diameter/2),diameter,diameter);
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
            stop.x += normalX * ((diameter+lineWidth)/2);
            stop.y += normalY * ((diameter+lineWidth)/2);
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
