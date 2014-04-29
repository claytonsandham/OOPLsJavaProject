/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package chess;
import java.util.ArrayList;
/**
 *
 * @author SANDHAMCR1
 */

public class Event {
    String name;

    ArrayList<String> players;
    ArrayList<String> games;
    public Event(String name, String white, String black, String game)
    {
        this.name = name;
        this.players = new ArrayList();
        this.players.add(white);
        this.players.add(black);
        
        this.games = new ArrayList();
        this.games.add(game);
    }
    public void addPlayer(String player)
    {
        this.players.add(player);
    }
    public void addGame(String game)
    {
        this.games.add(game);
    }
}
