/* *****************************************
* CSCI205 - Software Engineering and Design
* Spring 2017 - Final Project
*
* Name: Christian Ouellette, Keller Chambers, Stephen Haberle, Peyton Rumachik
* Date: Apr 12, 2017
* Time: 10:40:05 AM
*
* Project: warboats
* Package: warboats.model
* File: Marker
* Description: Class meant to represent tiles on the boards.
*
* ****************************************
 */
package warboats.model;

import warboats.boats.Boat;

/**
 * A representation of a tile on the board and the properties that go along with
 * it
 *
 * @author clo006
 */
public class Marker {

    private int posX; // x coordinate
    private int posY; // y coordinate
    private String consoleRepresentation = "o"; //can determine if tile has been shot at based on consoleRepresentation. H is hit.

    private boolean isHit = false;
    private boolean isShipOn = false; // boolean value to determine if a boat is placed on the tile

    //field pointing to the instance of boat placed on this marker, if one exists
    private Boat boatOn;

    /**
     * Constructor for the marker object
     *
     * @param x x coordinate of the tile
     * @param y y coordinate of the tile
     */
    public Marker(int x, int y) {
        posX = x;
        posY = y;
        consoleRepresentation = "o"; // used for text based game
    }

    @Override
    public String toString() {
        return this.consoleRepresentation;
    }

    /**
     * Getter for posX
     *
     * @return an int representing the x coordinate of the marker
     */
    public int getPosX() {
        return posX;
    }

    /**
     * Getter for posY
     *
     * @return an int representing the y coordinate of the marker
     */
    public int getPosY() {
        return posY;
    }

    /**
     * Getter for the console representation of the game
     *
     * @return a String of 'o', and 'h' to represent the status of the game
     * being played
     */
    public String getConsoleRepresentation() {
        return consoleRepresentation;
    }

    /**
     * Setter for the console representation of the game
     *
     * @param consoleRepresentation a String of 'o', 'b' and 'h' to represent
     * the status of the game being played
     */
    public void setConsoleRepresentation(String consoleRepresentation) {
        this.consoleRepresentation = consoleRepresentation;
    }

    /**
     * Getter for isShipOn
     *
     * @return a boolean value representing whether or not a ship is on the
     * given marker
     */
    public boolean isShipOn() {
        return isShipOn;
    }

    /**
     * Toggles the boolean value and changes the console representation
     * accordingly Used when placing ships.
     */
    public void toggleShipOn() {
        this.isShipOn = !(this.isShipOn);
        this.consoleRepresentation = "B";
    }

    /**
     * Setter for boatOn parameter
     *
     * @param boatType the type of boat to be set on the board
     */
    public void setBoat(Boat boatType) {
        this.boatOn = boatType;
    }

    /**
     * Getter for the boatOn parameter
     *
     * @return a Boat object representing the type of boat that is on the marker
     */
    public Boat getBoat() {
        return boatOn;
    }

    /**
     * Getter for the isHit parameter
     *
     * @return a boolean representing whether or not a ship is on the marker or
     * not
     */
    public boolean isHit() {
        return isHit;
    }

    /**
     * Setter for the isHit parameter
     *
     * @param hit a boolean value representing whether or not the ship has been
     * hit or not
     */
    public void setHit(boolean hit) {
        this.isHit = hit;
    }

}
