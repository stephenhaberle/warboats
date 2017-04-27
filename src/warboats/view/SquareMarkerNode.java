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
* Description: SquareMarkerNode object is a visual represetation of an individual tile in the gridpane
*
* ****************************************
 */
package warboats.view;

import javafx.scene.image.ImageView;
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
    public ImageView image;

    /**
     *
     * @param marker a marker object to be visualized
     */
    public SquareMarkerNode(Marker marker) {
        super(38, 38, Color.TRANSPARENT); // MAKE THIS ORANGE TO VIEW SQUARES
        this.marker = marker;
        this.setMouseTransparent(true);
    }

    /**
     * Getter for marker object encapsulated in the SquareMarkerNode object
     *
     * @return Marker object representing the information present in that
     * specific tile of the gridpane
     */
    public Marker getMarker() {
        return marker;
    }

}
