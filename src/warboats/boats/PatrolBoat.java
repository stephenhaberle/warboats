/* *****************************************
* CSCI205 - Software Engineering and Design
* Spring 2017 - Final Project
*
* Name: Christian Ouellette, Keller Chambers, Stephen Haberle, Peyton Rumachik
* Date: Apr 12, 2017
* Time: 5:47:05 PM
*
* Project: warboats
* Package: warboats.boats
* File: PatrolBoat
* Description: Specifically represents the PT boat type
*
* ****************************************
 */
package warboats.boats;

import warboats.Board;

/**
 *
 * @author clo006
 */
public class PatrolBoat extends Boat {

    public PatrolBoat(int xStart, int yStart, int xEnd, int yEnd, Board curBoard) {
        super(xStart, yStart, xEnd, yEnd, curBoard);
        this.setSize(2);
        this.placeBoat(this);
    }

}
