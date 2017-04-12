/* *****************************************
* CSCI205 - Software Engineering and Design
* Spring 2017 - Final Project
*
* Name: Christian Ouellette, Keller Chambers, Stephen Haberle, Peyton Rumachik
* Date: Apr 12, 2017
* Time: 1:38:29 PM
*
* Project: warboats
* Package: warboats.boats
* File: Boat
* Description:
*
* ****************************************
 */
package warboats.boats;

import java.util.ArrayList;
import warboats.Board;
import warboats.Marker;

/**
 *
 * @author clo006
 */
public class Boat {

    private Board currentBoard;
    private boolean isAlive;
    private ArrayList<Marker> positionTiles;
    private int startX;
    private int startY; //converted from letter representation
    private int endX;
    private int endY; //converted from letter representation

    public Boat(int xStart, int xEnd, int yStart, int yEnd, Board curBoard) {
        isAlive = true;
        startX = xStart - 1;
        startY = yStart - 1;
        endX = xEnd - 1;
        endY = yEnd - 1;
        currentBoard = curBoard;

        positionTiles = new ArrayList<Marker>();

        //Assign markers from board to Boat based off user input
        for (int i = startY; i <= endY; i++) {
            for (int j = startX; j <= endX; j++) {
                Marker temp = currentBoard.getBoard().get(i).get(j);
                positionTiles.add(temp);
                temp.toggleShipOn();
            }
        }
    }
}
