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

        for (int i = 0; i < width; i++) {
            markerArray.add(new ArrayList<Marker>());
            for (int j = 0; j < height; j++) {
                markerArray.get(i).add(new Marker(i, j));
            }
        }

    }

    @Override
    public String toString() {
        String board = "";
        String[] letterArray = {"A ", "B ", "C ", "D ", "E ", "F ", "G ", "H ", "I ", "J "};
        board += "  12345678910";
        board += "\n";
        for (int i = 0; i < 10; i++) {
            board += letterArray[i];

            for (int j = 0; j < 10; j++) {

                board += markerArray.get(i).get(j).toString();
            }
            board += "\n";
        }
        return board;
    }

    public ArrayList<ArrayList<Marker>> getBoard() {
        return markerArray;
    }

}
