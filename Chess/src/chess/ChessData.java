/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package chess;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author SANDHAMCR1
 */
public class ChessData {
   //SINGLETON:
   private static ChessData instance = null;
   protected ChessData() {
      // Exists only to defeat instantiation.
       this.Players = new HashMap<>();
       this.Events = new HashMap<>();
       this.Games = new HashMap<>();
       this.Openings = new HashMap<>();
       this.ECOs = new HashMap<>();
   }
   public static ChessData instance() {
      if(instance == null) {
         instance = new ChessData();
      }
      return instance;
   }
   //END SINGLETON
   //Code Here:
    Map<String, Player> Players;
    Map<String, Event> Events;
    Map<String, Game> Games;
    Map<String, Opening> Openings;
    Map<String, String> ECOs;
    
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
                this.Players.put(white, new Player(black, event));
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
}