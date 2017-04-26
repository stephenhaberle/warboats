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
* Description: Specifically represents the carrier boat type
*
* ****************************************
 */
package warboats.boats;

import warboats.model.Board;

/**
 * Creates a carrier boat object to be placed on a board
 *
 * @author clo006
 */
public class Carrier extends Boat {

    /**
     * Instantiates a carrier boat object to be placed on a playing board
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
    public Carrier(int xStart, int yStart, int xEnd, int yEnd, Board curBoard) {
        super(xStart, yStart, xEnd, yEnd, curBoard);
        this.setSize(5);
        this.placeBoat(this);
    }

}
