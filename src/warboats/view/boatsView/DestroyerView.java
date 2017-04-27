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
* File: DestroyerView
* Description: Specific view to allow the destroyer object to be represented graphically
*
* ****************************************
 */
package warboats.view.boatsView;

/**
 * Specific view to allow the destroyer object to be represented graphically
 *
 * @author clo006
 */
public class DestroyerView extends ShipView {

    /**
     * Constructor for destroyerView that sets the ship type
     */
    public DestroyerView() {
        this.setShipType(3);
    }
}
