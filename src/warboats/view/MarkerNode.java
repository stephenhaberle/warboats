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
* Description: MarkerNode object is a visual represetation of an individual tile in the gridpane
*
* ****************************************
 */
package warboats.view;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import warboats.model.Marker;

/**
 * The MarkerNode stores the coordinates, type of ship, and whether it is hit or
 * not. Allows for the color of the tile to be changed. See Marker.java
 *
 * @author StephenHaberle
 */
public class MarkerNode extends Circle {

    private Marker marker;

    /**
     * Constructor for the MarkerNode object.
     *
     * @param marker a marker object to be visualized
     */
    public MarkerNode(Marker marker) {
        super(15, Color.TRANSPARENT); // MAKE THIS CLEAR. WILL BE USED AS HIT MARKER.
        this.marker = marker;
    }

    /**
     * Getter for marker object encapsulated in the MarkerNode object
     *
     * @return Marker object representing the information present in that
     * specific tile of the gridpane
     */
    public Marker getMarker() {
        return marker;
    }

}
