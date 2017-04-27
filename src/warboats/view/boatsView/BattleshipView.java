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
* File: BattleshipView
* Description: Specific view to allow the Battleship object to be represented graphically
*
* ****************************************
 */
package warboats.view.boatsView;

import warboats.boats.Battleship;

/**
 * Specific view to allow the Battleship object to be represented graphically
 *
 * @author clo006
 */
public class BattleshipView extends ShipView {

    private Battleship modelBattleship;

    /**
     * Constructor for BattleshipView that sets the ship type
     */
    public BattleshipView() {
        this.setShipType(4);
    }

    /**
     * Getter for the modelBattleship attribute
     *
     * @return Battleship object that contains all of the data
     */
    public Battleship getModelBattleship() {
        return modelBattleship;
    }

    /**
     * Setter for the modelBattleship object
     *
     * @param modelBattleship Battleship object that contains all of the data
     */
    public void setModelBattleship(Battleship modelBattleship) {
        this.modelBattleship = modelBattleship;
    }

}
