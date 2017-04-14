/* *****************************************
* CSCI205 - Software Engineering and Design
* Spring 2017 - Final Project
*
* Name: Christian Ouellette, Keller Chambers, Stephen Haberle, Peyton Rumachik
* Date: Apr 12, 2017
* Time: 10:40:05 AM
*
* Project: warboats
* Package: warboats
* File: Marker
* Description: Class meant to represent tiles on the boards. 
*
* ****************************************
 */
package warboats;

import warboats.boats.Boat;

/**
 *
 * @author clo006
 */
public class Marker {

    private int posX;
    private int posY;
    private String color = "o"; //can determine if tile has been shot at based on color

    //simple boolean value to determine if a boat is placed on the tile
    private boolean isShipOn = false;

    //field pointing to the instance of boat placed on this marker, if one exists
    private Boat boatOn;

    public Marker(int x, int y) {
        posX = x;
        posY = y;
        color = "o";
    }

    @Override
    public String toString() {
        return this.color;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isShipOn() {
        return isShipOn;
    }

    public void toggleShipOn() {
        this.isShipOn = !(this.isShipOn);
        this.color = "B";
    }

    public void setBoat(Boat boatType) {
        this.boatOn = boatType;
    }

    public Boat getBoat() {
        return boatOn;
    }

}
