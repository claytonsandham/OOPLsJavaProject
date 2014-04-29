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
public class Player implements Serializable{
    String id;
    ArrayList<String> events;
    public Player(String eco, String event)
    {
        this.id = eco;
        this.events = new ArrayList();
        this.events.add(event);
    }
    public void addEvent(String event)
    {
        this.events.add(event);
    }
}
