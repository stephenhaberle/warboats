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
* File: ShipView
* Description:
*
* ****************************************
 */
package warboats.view.boatsView;

import java.util.ArrayList;
import javafx.scene.image.ImageView;
import warboats.model.Marker;

/**
 *
 * @author StephenHaberle
 */
public class ShipView extends ImageView {

    public ArrayList<Marker> nodes;
    private int shipType;
    public ImageView image;

    public ShipView() {

    }

    public int getShipType() {
        return shipType;
    }

    public void setShipType(int shipType) {
        this.shipType = shipType;
    }

}
