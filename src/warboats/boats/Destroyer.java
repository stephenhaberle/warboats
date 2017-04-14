/* *****************************************
* CSCI205 - Software Engineering and Design
* Spring 2017 - Final Project
*
* Name: Christian Ouellette, Keller Chambers, Stephen Haberle, Peyton Rumachik
* Date: Apr 12, 2017
* Time: 5:46:58 PM
*
* Project: warboats
* Package: warboats.boats
* File: Destroyer
* Description: Specifically represents the destroyer boat type
*
* ****************************************
 */
package warboats.boats;

import warboats.model.Board;

/**
 *
 * @author clo006
 */
public class Destroyer extends Boat {

    public Destroyer(int xStart, int yStart, int xEnd, int yEnd, Board curBoard) {
        super(xStart, yStart, xEnd, yEnd, curBoard);
        this.setSize(3);
        this.placeBoat(this);
    }

}
