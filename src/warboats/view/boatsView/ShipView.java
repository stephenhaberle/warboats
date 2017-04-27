/* *****************************************
* CSCI205 - Software Engineering and Design
* Spring 2017 - Final Project
*
* Name: Christian Ouellette, Keller Chambers, Stephen Haberle, Peyton Rumachik
* Date: Apr 17, 2017
* Time: 9:11:54 PM
*
* Project: warboats
* Package: warboats.view.boatsView
* File: ShipView
* Description: Generic view for allowing a boat object to be represented graphically
*
* ****************************************
 */
package warboats.view.boatsView;

import java.util.ArrayList;
import javafx.scene.image.ImageView;
import warboats.model.Marker;

/**
 * Stores the nodes that the ship occupies and the type of ship that it is
 *
 * @author StephenHaberle
 */
public class ShipView extends ImageView {

    public ArrayList<Marker> nodes;
    private int shipType;
    public ImageView image;

    /**
     * Basic constructor for the ShipView
     */
    public ShipView() {

    }

    /**
     * Getter for the shipType attribute
     *
     * @return int representing the type of ship: 5 - Carrier, 4 - Battleship, 3
     * - Destroyer, 2 - Submarine, 1 - Patrol Boat
     */
    public int getShipType() {
        return shipType;
    }

    /**
     * Getter for the shipType attribute
     *
     * @param shipType int representing the type of ship: 5 - Carrier, 4 -
     * Battleship, 3 - Destroyer, 2 - Submarine, 1 - Patrol Boat
     */
    public void setShipType(int shipType) {
        this.shipType = shipType;
    }

}
