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
* File: CarrierView
* Description: Specific view to allow the Carrier object to be represented graphically
*
* ****************************************
 */
package warboats.view.boatsView;

import warboats.boats.Carrier;

/**
 * Specific view to allow the Carrier object to be represented graphically
 *
 * @author clo006
 */
public class CarrierView extends ShipView {

    private Carrier modelCarrier;

    /**
     * Constructor for CarrierView that sets the ship type
     */
    public CarrierView() {
    }

    /**
     * Getter for the modelCarrier attribute
     *
     * @return Carrier object that contains all of the data
     */
    public Carrier getModelCarrier() {
        return modelCarrier;
    }

    /**
     * Setter for the modelCarrier attribute
     *
     * @param modelCarrier Carrier object that contains all of the data
     */
    public void setModelCarrier(Carrier modelCarrier) {
        this.modelCarrier = modelCarrier;
    }

}
