/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
import warboats.view.MarkerNode;
import warboats.view.WarboatsView;

/**
 *
 * @author pcr008
 */
public class SendShotController {

    private WarboatsView theView;
    private WarboatsModel theModel;
    private WarboatsController theCtrl;
    private GridPane target;

    public SendShotController(WarboatsView theView, WarboatsModel theModel,
                              WarboatsController theCtrl) {
        this.theModel = theModel;
        this.theView = theView;

        this.target = theView.getOpponentBoard();

        target.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    MarkerNode chosenMarker = (MarkerNode) event.getPickResult().getIntersectedNode();
                    int x = GridPane.getColumnIndex(chosenMarker);
                    int y = GridPane.getRowIndex(chosenMarker);
                    if (theModel.isPlayerReady()) {
                        if (WarboatsModel.isPlayerTurn() && WarboatsModel.isOpponentReady()) {
                            try {
                                theModel.sendPlayerMove(x, y);
                            } catch (Exception e) {
                                System.out.println(
                                        "sending move failed. unsupported exception");
                            }
                            Marker tile = WarboatsModel.getOpponentBoard().getBoard().get(
                                    x - 1).get(y - 1);
                            if (tile.isHit()) {
                                chosenMarker.setFill(Color.RED);
                            }
                            else {
                                chosenMarker.setFill(Color.WHITE);
                            }
                        }
                        else if (!WarboatsModel.isPlayerTurn() && WarboatsModel.isOpponentReady()) {
                            Alert a = new Alert(AlertType.INFORMATION);
                            a.setTitle("Waiting for opponent");
                            a.setHeaderText("WAIT YOUR TURN");
                            a.setContentText("It is your opponent's move");
                            a.showAndWait();
                        }
                        else if (!WarboatsModel.isOpponentReady()) {
                            System.out.println("OPPONENT ISNT READY");
                            Alert a = new Alert(AlertType.INFORMATION);

                            a.setTitle("Waiting for opponent");
                            a.setHeaderText("Opponent is still placing ships");
                            a.showAndWait();
                        }
                    }
                    else if (!theModel.isPlayerReady()) {
                        //change back to 5 when not testing
                        if (theModel.getNavy().size() == 1) {
                            Alert a = new Alert(AlertType.INFORMATION);
                            a.setTitle("Cannot Begin Game");
                            a.setHeaderText("Ready up!");
                            a.setContentText(
                                    "You have not yet locked in your fleet configuration");
                            a.showAndWait();
                        }
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
                    System.out.println("invalid object clicked");
                }
            }
        });
    }
}
