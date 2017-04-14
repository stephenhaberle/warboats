/* *****************************************
* CSCI205 - Software Engineering and Design
* Spring 2017 - Final Project
*
* Name: Christian Ouellette, Keller Chambers, Stephen Haberle, Peyton Rumachik
* Date: Apr 12, 2017
* Time: 5:47:20 PM
*
* Project: warboats
* Package: warboats.boats
* File: Battleship
* Description: Specifically represents the battleship boat type
*
* ****************************************
 */
package warboats.boats;

import warboats.model.Board;

/**
 *
 * @author clo006
 */
public class Battleship extends Boat {

    public Battleship(int xStart, int yStart, int xEnd, int yEnd, Board curBoard) {
        super(xStart, yStart, xEnd, yEnd, curBoard);
        this.setSize(4);
        this.placeBoat(this);
    }

}
