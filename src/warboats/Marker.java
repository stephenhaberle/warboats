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
* Description:
*
* ****************************************
 */
package warboats;

/**
 *
 * @author clo006
 */
public class Marker {

    private int posX;
    private int posY;
    private String color; //can determine if tile has been shot at based on color
    private boolean shipOn = false;
    private int boatType = 0;

    //temporary tile indicator for console
    private String consoleIndicator = "o";

    public Marker(int x, int y) {
        posX = x;
        posY = y;
        color = "blue";
    }

    @Override
    public String toString() {
        return this.consoleIndicator;
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
        return shipOn;
    }

    public void toggleShipOn() {
        this.shipOn = !(this.shipOn);
        this.consoleIndicator = "B";
    }

    public void setBoatType(int boatType) {
        this.boatType = boatType;
    }

    public void setConsoleIndicator(String consoleIndicator) {
        this.consoleIndicator = consoleIndicator;
    }

}
