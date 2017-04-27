/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package warboats.utility;

import warboats.model.WarboatsModel;
import warboats.view.WarboatsView;

/**
 *
 * @author clo006
 */
public final class ValueUpdateUtility {

    public static void updateStatsLabels(WarboatsModel theModel,
                                         WarboatsView theView) {
        int temp;
        temp = theModel.getOpponentBoard().getNumHits();
        theView.getHitsLabel().textProperty().setValue(String.format("%d", temp));

        temp = theModel.getOpponentBoard().getNumMisses();
        theView.getMissesLabel().textProperty().setValue(String.format("%d",
                                                                       temp));
        temp = theModel.getShipsRemaining();
        theView.getShipRemainingLabel().textProperty().setValue(String.format(
                "%d", temp));

    }
}
