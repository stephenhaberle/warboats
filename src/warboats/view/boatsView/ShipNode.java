/* *****************************************
* CSCI205 - Software Engineering and Design
* Spring 2017 - Final Project
*
* Name: Christian Ouellette, Keller Chambers, Stephen Haberle, Peyton Rumachik
* Date: Apr 17, 2017
* Time: 9:11:54 PM
*
* Project: warboats
* Package: warboats.view
* File: ShipNode
* Description:
*
* ****************************************
 */
package warboats.view.boatsView;

import java.util.ArrayList;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import warboats.model.Marker;

/**
 *
 * @author StephenHaberle
 */
public class ShipNode extends Rectangle {

    private Marker marker;
    private ArrayList<TextField> initializedCoordinates;
    private int shipType;

    public ShipNode() {
        super(30, 30, Color.GREY);
        initializedCoordinates = new ArrayList<>();

    }

    public ShipNode(Marker marker) {
        super(30, 30, Color.GREY);
        this.marker = marker;
        this.marker.toggleShipOn();
        initializedCoordinates = new ArrayList<>();
    }

    public ArrayList<TextField> getInitializedCoordinates() {
        return initializedCoordinates;
    }

    public int getShipType() {
        return shipType;
    }

    public void setShipType(int shipType) {
        this.shipType = shipType;
    }

}
