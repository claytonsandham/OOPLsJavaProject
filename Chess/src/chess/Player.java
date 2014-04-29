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
public class Player {
    String name;
    ArrayList<String> events;
    public Player(String name, String event)
    {
        this.name = name;
        this.events = new ArrayList();
        this.events.add(event);
    }
    public void addEvent(String event)
    {
        this.events.add(event);
    }
}
