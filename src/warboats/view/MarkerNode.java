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
import javafx.scene.shape.Circle;
import warboats.model.Marker;

/**
 *
 * @author StephenHaberle
 */
public class MarkerNode extends Circle {

    private Marker marker;

    public MarkerNode(Marker marker) {
        super(15, Color.BLUE);
        this.marker = marker;
    }
}
