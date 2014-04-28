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
public class Chess {
    Map<String, Player> Players;
    Map<String, Player> Events;
    Map<String, Player> Games;
    Map<String, Player> Openings;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }

    public Chess() {
        this.Players = new HashMap<>();
        this.Events = new HashMap<>();
        this.Games = new HashMap<>();
        this.Openings = new HashMap<>();
    }
    
}
