/* *****************************************
* CSCI205 - Software Engineering and Design
* Spring 2017 - Final Project
*
* Name: Christian Ouellette, Keller Chambers, Stephen Haberle, Peyton Rumachik
* Date: Apr 12, 2017
* Time: 10:39:24 AM
*
* Project: warboats
* Package: warboats
* File: Board
* Description:
*
* ****************************************
 */
package warboats;

import java.util.ArrayList;

/**
 *
 * @author clo006
 */
public class Board {

    private ArrayList<ArrayList<Marker>> markerArray;

    public Board() {
        //Board length and width
        int height = 10; //letters
        int width = 10; //numbers

        markerArray = new ArrayList<ArrayList<Marker>>();

        for (int x = 0; x < width; x++) {
            markerArray.add(new ArrayList<Marker>());
            for (int y = 0; y < height; y++) {
                markerArray.get(x).add(new Marker(x, y));
            }
        }

    }

    @Override
    public String toString() {
        String board = "";
        String[] letterArray = {"A ", "B ", "C ", "D ", "E ", "F ", "G ", "H ", "I ", "J "};
        board += "  1 2 3 4 5 6 7 8 9 10";
        board += "\n";
        for (int i = 0; i < 10; i++) {
            board += letterArray[i];

            for (int j = 0; j < 10; j++) {

                board += markerArray.get(i).get(j).toString() + " ";
            }
            board += "\n";
        }
        return board;
    }

    /**
     * Check if chosen tile on board has a ship on it, if so, then hit, else
     * miss.
     *
     * @param x x coordinate
     * @param y y coordinate
     * @return boolean if hit or not
     */
    public boolean checkHit(int x, int y) {
        Marker tile = markerArray.get(x - 1).get(y - 1);

        if (tile.isShipOn()) {
            //CHANGE MARKER TO HIT COLOR
            return true;
        }
        else {
            //CHANGE MARKER TO MISS COLOR
            return false;
        }
    }

    /**
     * Based on reply from opponent, update opponent's board with hit or miss
     * marker at specified coordinates
     *
     * @param x
     * @param y
     */
    public void hitMiss(boolean isHit, Coordinates shot) {
        Marker tile = markerArray.get(shot.x - 1).get(shot.y - 1);

        if (isHit) {
            System.out.println("HIT");
            tile.setConsoleIndicator("H");
        }
        else {
            System.out.println("MISS, EAT A DICk");
            tile.setConsoleIndicator("M");
        }
    }

    public ArrayList<ArrayList<Marker>> getBoard() {
        return markerArray;
    }

}
