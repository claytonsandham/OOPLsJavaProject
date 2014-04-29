/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package chess;
import java.io.Serializable;
import java.util.ArrayList;
/**
 *
 * @author SANDHAMCR1
 */
public class Game implements Serializable{
    String id;
    String event;
    String site;
    String date;
    String round;
    String white;
    String black;
    String result;
    String eco;
    ArrayList <String> players;
    Game(String name, String event, String site, 
            String date, String round, String white, 
            String black, String result, String eco)
    {
        this.id = name;
        this.event = event;
        this.site = site;
        this.date = date;
        this.round = round;
        this.white = white;
        this.black = black;
        this.result = result;
        this.eco = eco;
        this.players = new ArrayList();
        this.players.add(white);
        this.players.add(black);
    }
}
