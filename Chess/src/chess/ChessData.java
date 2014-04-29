/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package chess;

import java.awt.Point;
import java.util.HashMap;
import java.util.Map;
import java.io.Serializable;
import java.io.BufferedOutputStream;
import java.io.BufferedInputStream;
import java .io.FileOutputStream;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
/**
 *
 * @author SANDHAMCR1
 */
public class ChessData implements Serializable{
   //SINGLETON:
   private static ChessData instance = null;
   protected ChessData() {
      // Exists only to defeat instantiation.
       this.Players = new HashMap<>();
       this.Events = new HashMap<>();
       this.Games = new HashMap<>();
       this.Openings = new HashMap<>();
       this.ECOs = new HashMap<>();
       this.offset = new Point(0,0);
   }
   public static ChessData instance() {
      if(instance == null) {
         instance = ChessData.Open("chess.data");
         if(instance == null)
         {
            instance = new ChessData();
         }
      }
      return instance;
   }
   //END SINGLETON
   
   //Serialization code:
   public static void Save(String fileName)
   {
       try{
           ObjectOutputStream ooStream =
                   new ObjectOutputStream(
                   new FileOutputStream(fileName));
           ooStream.writeObject(instance);
           ooStream.flush();
           ooStream.close();
       }catch(Exception exc){
           System.out.println("Save Failed");
           exc.printStackTrace();
       }
   }
   
   public static ChessData Open(String fileName)
   {
       try{
           ObjectInputStream oiStream =
                   new ObjectInputStream(
                   new FileInputStream(fileName));
           Object obj;
           obj = oiStream.readObject();
           ChessData cd;
           cd = (ChessData)obj;
           
           return cd;
       }catch(Exception exc){
           System.out.println("Open failed");
           exc.printStackTrace();
           return null;
       }
       
   }
   //End Serialization code
   //Code Here:
    Map<String, Player> Players;
    Map<String, Event> Events;
    Map<String, Game> Games;
    Map<String, Opening> Openings;
    Map<String, String> ECOs;
    Point offset;
    
    public void addGame(String event, String site, 
            String date, String round, String white, 
            String black, String result, String eco)
    {
        String gameName = event + ": " + round;
        if(!this.Games.containsKey(gameName))
        {
            this.Games. put(gameName, new Game(gameName, event, site, date, 
                    round, white, black, result, eco));
            
            if(!this.Players.containsKey(white)) //add white player if not in database;
            {
                this.Players.put(white, new Player(white, event));
            }
            else
            {
                if(!this.Players.get(white).events.contains(event))
                {
                    this.Players.get(white).addEvent(event);
                }
            }
            
            if(!this.Players.containsKey(black)) //add black player if not in database;
            {
                this.Players.put(black, new Player(black, event));
            }
            else
            {
                if(!this.Players.get(black).events.contains(event))
                {
                    this.Players.get(black).addEvent(event);
                }
            }
            
            if(!this.Events.containsKey(event)) //if event isn't in database, add it.
            {
                this.Events.put(event, new Event(event, white, black, gameName));
            }
            else
            {
                if(!this.Events.get(event).players.contains(white))
                {
                    this.Events.get(event).addPlayer(white);
                }
                if(!this.Events.get(event).players.contains(black))
                {
                    this.Events.get(event).addPlayer(black);
                }
                if(!this.Events.get(event).games.contains(gameName))
                {
                    this.Events.get(event).addGame(gameName);
                }
            }
            
            if(!this.Openings.containsKey(eco))
            {
                if(this.ECOs.containsKey(eco))
                {
                    String description = this.ECOs.get(eco);
                    this.Openings.put(eco, new Opening(eco, description, gameName));
                }
            }
            else
            {
                this.Openings.get(eco).addGame(gameName);
            }
                
        }
        
    }
    public void addECO(String eco, String name)
    {
        if(!this.ECOs.containsKey(eco))
        {
            this.ECOs.put(eco, name);
        }
    }
    public Player getPlayer(String player)
    {
        if(this.Players.containsKey(player))
        {
            return this.Players.get(player);
        }
        else return null;
    }
    public Event getEvent(String event)
    {
        if(this.Events.containsKey(event))
        {
            return this.Events.get(event);
        }
        else return null;
    }
    public Game getGame(String game)
    {
        if(this.Games.containsKey(game))
        {
            return this.Games.get(game);
        }
        else return null;
    }
    public Opening getOpening(String opening)
    {
        if(this.Openings.containsKey(opening))
        {
            return this.Openings.get(opening);
        }
        else return null;
    }
}