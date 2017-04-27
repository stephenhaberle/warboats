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
 * Creates a destroyer boat object to be placed on a board
 *
 * @author clo006
 */
public class Destroyer extends Boat {

    /**
     * Instantiates a destroyer boat object to be placed on a playing board
     *
     * @param xStart Start x coordinate of the gridpane that the boat will be
     * placed on
     * @param yStart Start y coordinate of the gridpane that the boat will be
     * placed on
     * @param xEnd End x coordinate of the gridpane that the boat will be placed
     * on
     * @param yEnd End y coordinate of the gridpane that the boat will be placed
     * on
     * @param curBoard The board (player/opponent) that the ship will be a part
     * of
     */
    public Destroyer(int xStart, int yStart, int xEnd, int yEnd, Board curBoard) {
        super(xStart, yStart, xEnd, yEnd, curBoard);
        this.setSize(3);
        this.placeBoat(this);
    }

}
