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
public class Opening {
    String eco;
    String description;
    ArrayList<String> games;
    public Opening(String eco, String description, String game)
    {
        this.eco = eco;
        this.description = description;
        this.games.add(game);
    }
    public void addGame(String game)
    {
        this.games.add(game);
    }
}
