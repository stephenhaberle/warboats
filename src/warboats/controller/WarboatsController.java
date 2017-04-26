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
* File: WarboatsController
* Description: Main controller for the Warboats game
*
* ****************************************
 */
package warboats.controller;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import warboats.model.WarboatsModel;
import warboats.utility.ValueUpdateUtility;
import warboats.view.WarboatsView;

/**
 * Sets up the main controller and sub-controllers for the game and updates the
 * shot statistics as well as gameplay logic
 *
 * @author clo006
 */
public class WarboatsController {

    private WarboatsView theView;
    private WarboatsModel theModel;
    private ImageView source;
    private GridPane target;
    private final DragDropController dragCtrl;
    private final SendShotController shotCtrl;

    /**
     * Instantiates the main controller for the game and prepares threads for
     * updating the statistics
     *
     * @param theModel Model object of the game
     * @param theView View object of the game
     */
    public WarboatsController(WarboatsModel theModel, WarboatsView theView) {
        this.theModel = theModel;
        this.theView = theView;

        //setup sub-controllers
        this.dragCtrl = new DragDropController(this.theView, this.theModel, this);
        this.shotCtrl = new SendShotController(this.theView, this.theModel, this);

        theView.getBeginGame().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                if (theModel.getNavy().size() == 5) {
                    dragCtrl.getTarget().setMouseTransparent(true);
                    theModel.togglePlay();
                    theModel.signalBeginGame();

                    //setup threads to handle sending messages to the user
                    Thread notifyBegin = new Thread(new notifyBeginGame());
                    notifyBegin.setDaemon(true); // Does not prevent the JVM from exiting when the program finishes but the thread is still running
                    notifyBegin.start();
                    Thread notifyTurn = new Thread(new notifyTurn());
                    notifyTurn.setDaemon(true);
                    notifyTurn.start();
                }
                else {
                    Alert a = new Alert(AlertType.ERROR);
                    a.setTitle("Cannot Begin Game");
                    a.setHeaderText("Unplaced ships remain");
                    a.setContentText(
                            "Not all ships placed. Please drag all ships onto \nyour board and position them to begin game");
                    a.showAndWait();
                }
            }
        });

        Thread updateLabelsThread = new Thread(new updateStatsTask());
        updateLabelsThread.setDaemon(true);
        updateLabelsThread.start();
    }

    /**
     * Notifier class to let user know whether they 1) have won, 2) have lost,
     * or 3) it is their turn
     */
    class notifyTurn extends Task<Void> {

        protected Void call() throws Exception {
            boolean inTurn = false;
            while (true) {

                //user has lost
                if (WarboatsModel.isLost()) {
                    Platform.runLater(new Runnable() {
                        public void run() {
                            Alert a = new Alert(AlertType.INFORMATION);
                            a.setTitle("LOSER");
                            a.setHeaderText("You've been outplayed, fool");
                            a.setContentText("Hang your head in shame"); //demoralize the user
                            a.showAndWait();
                        }
                    });
                    break;
                }

                //user has won
                else if (WarboatsModel.isWon()) {
                    Platform.runLater(new Runnable() {
                        public void run() {
                            Alert a = new Alert(AlertType.INFORMATION);
                            a.setTitle("WINNER");
                            a.setHeaderText("You have won the battle!");
                            a.setContentText("Congratulations Commander");
                            a.showAndWait();
                        }
                    });
                    break;
                }

                //it is the user's turn
                else if (WarboatsModel.isOpponentReady() && WarboatsModel.isPlayerReady() && WarboatsModel.playerTurn && !inTurn) {
                    Platform.runLater(new Runnable() {
                        public void run() {
                            Alert a = new Alert(AlertType.INFORMATION);
                            a.setTitle("Your Turn");
                            a.setHeaderText("Make your move");
                            a.showAndWait();
                        }
                    });
                    inTurn = true;
                }

                //it is not the user's turn
                else if (!WarboatsModel.isPlayerTurn()) {
                    inTurn = false;
                }

                //check occasionally to reduce overhead
                Thread.sleep(100);
            }
            return null;
        }
    }

    /**
     * Notifies the user when the opponent has placed their ships and is ready
     */
    class notifyBeginGame extends Task<Void> {

        protected Void call() throws Exception {
            while (true) {

                //send message if opponent is ready
                if (WarboatsModel.isOpponentReady()) {
                    Platform.runLater(new Runnable() {
                        public void run() {
                            Alert a = new Alert(AlertType.INFORMATION);
                            a.setTitle("Begin");
                            a.setHeaderText("Opponent has confirmed fleet");
                            a.setContentText("Prepare for battle!");
                            a.showAndWait();
                        }
                    });
                    break;
                }

                //check occasionally to reduce overhead
                Thread.sleep(100);
            }
            return null;
        }
    }

    /**
     * Updates the shot statistics until the game is over. Implements the
     * utility class to update the values
     */
    class updateStatsTask extends Task<Void> {

        protected Void call() throws Exception {
            while (true) {
                Platform.runLater(new Runnable() {
                    public void run() {
                        ValueUpdateUtility.updateStatsLabels(theModel, theView);
                    }
                });

                //Continuous updating causes too much overhead. Updates every half a second.
                Thread.sleep(500);

                if (theModel.isLost() || theModel.isWon()) {
                    break;
                }
            }
            return null;
        }
    }
}
