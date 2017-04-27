/* *****************************************
* CSCI205 - Software Engineering and Design
* Spring 2017 - Final Project
*
* Name: Christian Ouellette, Keller Chambers, Stephen Haberle, Peyton Rumachik
* Date: Apr 10, 2017
* Time: 12:30:14 PM
*
* Project: warboats
* Package: warboats.network
* File: Coordinates
* Description: Class used to send provided coordinates between client and server.
*
* ****************************************
 */
package warboats.network;

/**
 * Class that creates a Coordinate object that represents how simple coordinates
 * are stored
 *
 * @author clo006
 */
public class Coordinates {

    public String message;
    public int x;
    public int y;

    /**
     * Basic constructor used when coordinates are not given, but later assigned
     */
    public Coordinates() {
    }

    /**
     * Constructor for Coordinates object when given coordinates
     *
     * @param x x coordinate (columns)
     * @param y y coordinate (rows)
     */
    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

}
