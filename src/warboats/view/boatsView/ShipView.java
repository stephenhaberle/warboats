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

import javafx.scene.image.ImageView;
import warboats.boats.Boat;

/**
 *
 * @author StephenHaberle
 */
public class ShipView extends ImageView {

    private Boat model;
    private int shipType;
    private ImageView image;

    public ShipView() {

    }

    public int getShipType() {
        return shipType;
    }

    public void setShipType(int shipType) {
        this.shipType = shipType;
    }

    public Boat getModel() {
        return model;
    }

    public void setModel(Boat model) {
        this.model = model;
    }

    public ImageView getBoatImage() {
        return image;
    }

    public void setBoatImage(ImageView image) {
        this.image = image;
    }

}
