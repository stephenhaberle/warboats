/* *****************************************
* CSCI205 - Software Engineering and Design
* Spring 2017 - Final Project
*
* Name: Christian Ouellette, Keller Chambers, Stephen Haberle, Peyton Rumachik
* Date: Apr 17, 2017
* Time: 10:39:33 AM
*
* Project: warboats
* Package: warboats.utility
* File: ValueUpdateUtility
* Description: Updates the values stored in the statistical part of the GUI
*
* ****************************************
 */
package warboats.utility;

import warboats.model.WarboatsModel;
import warboats.view.WarboatsView;

/**
 * Updates the values stored in the statistical section of the GUI
 *
 * @author clo006
 */
public final class ValueUpdateUtility {

    /**
     * static method that can be called to update the values in the textboxes
     *
     * @param theModel the warboats model
     * @param theView the view to be updated
     */
    public static void updateStatsLabels(WarboatsModel theModel,
                                         WarboatsView theView) {
        int temp;
        //Hits
        temp = theModel.getOpponentBoard().getNumHits();
        theView.getHitsLabel().textProperty().setValue(String.format("%d", temp));

        //Misses
        temp = theModel.getOpponentBoard().getNumMisses();
        theView.getMissesLabel().textProperty().setValue(String.format("%d",
                                                                       temp));

        //Ships remaining in case the person doesnt have eyes
        temp = theModel.getShipsRemaining();
        theView.getShipRemainingLabel().textProperty().setValue(String.format(
                "%d", temp));

    }
}
