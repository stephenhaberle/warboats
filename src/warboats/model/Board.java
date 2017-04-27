/* *****************************************
* CSCI205 - Software Engineering and Design
* Spring 2017 - Final Project
*
* Name: Christian Ouellette, Keller Chambers, Stephen Haberle, Peyton Rumachik
* Date: Apr 12, 2017
* Time: 10:39:24 AM
*
* Project: warboats
* Package: warboats.model
* File: Board
* Description: Class that represents the game board by storing an ArrayList of Markers.
*
* ****************************************
 */
package warboats.model;

import java.util.ArrayList;
import warboats.boats.Boat;
import warboats.network.Coordinates;
import warboats.utility.SoundUtility;

/**
 * Creates a 2D array representing all of the tiles on the board and introduces
 * hit/miss logic
 *
 * @author clo006
 */
public class Board {

    private ArrayList<ArrayList<Marker>> markerArray;
    private int numHits = 0;
    private int numMisses = 0;

    /**
     * initializer for the Board object which is represented as a gridpane in
     * the view Represented as a 2D array of marker objects.
     *
     */
    public Board() {
        //Board length and width
        int height = 10; //letters
        int width = 10; //numbers

        markerArray = new ArrayList<ArrayList<Marker>>();

        //populate array accordingly
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

                //arrayList is setup in columns of markers but since printing
                //is row by row this must be reversed
                board += markerArray.get(j).get(i).toString() + " ";
            }
            board += "\n";
        }
        return board;
    }

    /**
     * Check if chosen tile on board has a ship on it, if so, then hit, else
     * miss.
     *
     * @param x x coordinate of the shot
     * @param y y coordinate of the shot
     * @return boolean representing if the ship was hit or not
     */
    public boolean checkHit(int x, int y) {
        Marker tile = markerArray.get(x - 1).get(y - 1);

        //check to see if a ship is on the tile
        if (tile.isShipOn()) {
            //CHANGE MARKER TO HIT COLOR
            tile.setConsoleRepresentation("H");
            Boat boat = tile.getBoat();

            //if the entire boat is destroyed, check to see if it was the last and if the game is over
            if (boat.checkSunk()) {
                WarboatsModel.checkLoss();
            }

            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Based on reply from opponent, update opponent's board with hit or miss
     * marker at specified coordinates
     *
     * @param isHit
     * @param shot
     */
    public void hitMiss(boolean isHit, Coordinates shot) {
        Marker tile = markerArray.get(shot.x - 1).get(shot.y - 1);

        if (isHit) {
            System.out.println("HIT");
            SoundUtility.hit();
            tile.setHit(true);
            this.numHits++;
            tile.setConsoleRepresentation("H");
        }
        else {
            System.out.println("MISS");
            SoundUtility.miss();
            this.numMisses++;
            tile.setConsoleRepresentation("M");
        }
    }

    /**
     * Getter for Board
     *
     * @return an ArrayList of markers representing the 2D gridpane
     */
    public ArrayList<ArrayList<Marker>> getBoard() {
        return markerArray;
    }

    /**
     * Getter for numHits
     *
     * @return an int representing the number of successful shots a player has
     * taken
     */
    public int getNumHits() {
        return numHits;
    }

    /**
     * Getter for numMisses
     *
     * @return an int representing the number of missed shots a player has taken
     */
    public int getNumMisses() {
        return numMisses;
    }

}
