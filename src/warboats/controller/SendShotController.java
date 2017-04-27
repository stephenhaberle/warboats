/* *****************************************
* CSCI205 - Software Engineering and Design
* Spring 2017 - Final Project
*
* Name: Christian Ouellette, Keller Chambers, Stephen Haberle, Peyton Rumachik
* Date: Apr 14, 2017
* Time: 1:25:33 PM
*
* Project: warboats
* Package: warboats.controller
* File: SendShotController
* Description: Sub controller to handle sending shots
*
* ****************************************
 */
package warboats.controller;

import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import warboats.model.Marker;
import warboats.model.WarboatsModel;
import warboats.utility.SoundUtility;
import warboats.view.MarkerNode;
import warboats.view.WarboatsView;

/**
 * Main controller to handle selecting shots to take and sending them to the
 * opponent
 *
 * @author pcr008
 */
public class SendShotController {

    private WarboatsView theView;
    private WarboatsModel theModel;
    private WarboatsController theCtrl;
    private GridPane target;

    /**
     * Instantiates the SendShotController and handles shooting logic for the
     * user (not opponent) and updates the GUI as needed
     *
     * @param theView The view to be changed
     * @param theModel The model of the game
     * @param theCtrl The main controller that is passed in
     */
    public SendShotController(WarboatsView theView, WarboatsModel theModel,
                              WarboatsController theCtrl) {
        this.theModel = theModel;
        this.theView = theView;

        this.target = theView.getOpponentBoard();

        target.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    //extract coordinates from the selected tile in opponent's gridpane
                    MarkerNode chosenMarker = (MarkerNode) event.getPickResult().getIntersectedNode();
                    int x = GridPane.getColumnIndex(chosenMarker);
                    int y = GridPane.getRowIndex(chosenMarker);

                    //must wait until user has placed all of their own boats
                    if (theModel.isPlayerReady()) {
                        //wait until both players have placed their ships and have readied up
                        if (WarboatsModel.isPlayerTurn() && WarboatsModel.isOpponentReady()) {
                            try {
                                theModel.sendPlayerMove(x, y);
                            } catch (Exception e) {
                                System.out.println(e);
                                System.out.println(
                                        "sending move failed. unsupported exception");
                            }
                            Marker tile = WarboatsModel.getOpponentBoard().getBoard().get(
                                    x - 1).get(y - 1);

                            //set tile that was fired upon accordingly
                            if (tile.isHit()) {
                                chosenMarker.setFill(Color.RED);
                            }
                            else {
                                chosenMarker.setFill(Color.WHITE);
                            }
                        }

                        //notify user that opponent has not taken their shot yet
                        else if (!WarboatsModel.isPlayerTurn() && WarboatsModel.isOpponentReady()) {
                            SoundUtility.waitTurn();
                            Alert a = new Alert(AlertType.INFORMATION);
                            a.setTitle("Waiting for opponent");
                            a.setHeaderText("WAIT YOUR TURN");
                            a.setContentText("It is your opponent's move");
                            a.showAndWait();
                        }

                        //notify user that the opponent still needs to place their ships
                        else if (!WarboatsModel.isOpponentReady()) {
                            SoundUtility.playerNotReady(); 
                            System.out.println("OPPONENT ISNT READY");
                            Alert a = new Alert(AlertType.INFORMATION);

                            a.setTitle("Waiting for opponent");
                            a.setHeaderText("Opponent is still placing ships");
                            a.showAndWait();
                        }
                    }

                    //notify the user that they still need to place ships and confirm their locations before starting the game
                    else if (!theModel.isPlayerReady()) {

                        //User has not confirmed the placement of their ships. Must hit button
                        if (theModel.getNavy().size() == 5) {
                            Alert a = new Alert(AlertType.INFORMATION);
                            a.setTitle("Cannot Begin Game");
                            a.setHeaderText("Ready up!");
                            a.setContentText(
                                    "You have not yet locked in your fleet configuration");
                            a.showAndWait();
                        }

                        //User has not placed all of their ships
                        else {
                            Alert a = new Alert(AlertType.INFORMATION);
                            a.setTitle("Cannot Begin Game");
                            a.setHeaderText("Unplaced ships remain");
                            a.setContentText(
                                    "Not all ships placed. Please drag all ships onto \nyour board and position them to begin game");
                            a.showAndWait();
                        }
                    }
                } catch (ClassCastException e) {
                    //gridlines and random errors
                    System.out.println("invalid object clicked");
                }
            }
        });
    }
}
