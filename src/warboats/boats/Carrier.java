/* *****************************************
* CSCI205 - Software Engineering and Design
* Spring 2017 - Final Project
*
* Name: Christian Ouellette, Keller Chambers, Stephen Haberle, Peyton Rumachik
* Date: Apr 12, 2017
* Time: 5:47:10 PM
*
* Project: warboats
* Package: warboats.boats
* File: Carrier
* Description:
*
* ****************************************
 */
package warboats.boats;

import warboats.Board;

/**
 *
 * @author clo006
 */
public class Carrier extends Boat {

    public Carrier(int xStart, int yStart, int xEnd, int yEnd, Board curBoard) {
        super(xStart, yStart, xEnd, yEnd, curBoard);
        this.setSize(5);
        //carrier is type 5
        this.setType(5);
        this.placeBoat();
    }

}
