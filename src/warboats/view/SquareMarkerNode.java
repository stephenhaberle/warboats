/* *****************************************
* CSCI205 - Software Engineering and Design
* Spring 2017 - Final Project
*
* Name: Christian Ouellette, Keller Chambers, Stephen Haberle, Peyton Rumachik
* Date: Apr 17, 2017
* Time: 9:10:33 PM
*
* Project: warboats
* Package: warboats.view
* File: MarkerNode
* Description:
*
* ****************************************
 */
package warboats.view;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import warboats.model.Marker;

/**
 * For use on player's own board.
 *
 * @author khc009
 */
public class SquareMarkerNode extends Rectangle {

    private Marker marker;

    public SquareMarkerNode(Marker marker) {
        super(38, 38, Color.ORANGE); // MAKE THIS ORANGE TO VIEW CIRCLES
        this.marker = marker;
    }

    public Marker getMarker() {
        return marker;
    }

}
