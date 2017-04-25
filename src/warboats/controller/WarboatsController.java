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
* Description:
*
* ****************************************
 */
package warboats.controller;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import warboats.WarboatsGUI;
import warboats.model.WarboatsModel;
import warboats.utility.ValueUpdateUtility;
import warboats.view.WarboatsView;

/**
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
    private WarboatsGUI theMain;

    private AtomicInteger taskCount = new AtomicInteger(0);
    private ExecutorService exec = Executors.newSingleThreadExecutor(r -> {
        Thread t = new Thread(r);
        t.setDaemon(true);
        return t;
    });

    public WarboatsController(WarboatsModel theModel, WarboatsView theView,
                              WarboatsGUI theMain) {
        this.theMain = theMain;
        this.theModel = theModel;
        this.theView = theView;
        this.dragCtrl = new DragDropController(this.theView, this.theModel, this);
        this.shotCtrl = new SendShotController(this.theView, this.theModel, this);

        IntegerProperty pendingTasks = new SimpleIntegerProperty(0);

        theView.getBeginGame().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                //change back to 5
                if (theModel.getNavy().size() == 1) {
                    dragCtrl.getTarget().setMouseTransparent(true);
                    theModel.setPlayerReady(true);
                    theModel.signalBeginGame();
                    Thread notifyBegin = new Thread(new notifyBeginGame());
                    notifyBegin.setDaemon(true);
                    notifyBegin.start();

                    Task<Void> turnTask = new notifyTurn();
                    pendingTasks.set(pendingTasks.get() + 1);
                    turnTask.setOnSucceeded(taskEvent -> pendingTasks.set(
                            pendingTasks.get() - 1));
                    exec.submit(turnTask);

                    Task<Void> rematchTask = new notifyRematch();
                    pendingTasks.set(pendingTasks.get() + 1);
                    turnTask.setOnSucceeded(taskEvent -> pendingTasks.set(
                            pendingTasks.get() - 1));
                    exec.submit(rematchTask);
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

    class notifyTurn extends Task<Void> {

        final int taskNumber = taskCount.incrementAndGet();

        protected Void call() throws Exception {
            boolean inTurn = false;
            while (true) {
                if (WarboatsModel.isLost()) {
                    Platform.runLater(new Runnable() {
                        public void run() {
                            Alert a = new Alert(AlertType.INFORMATION);
                            a.setTitle("LOSER");
                            a.setHeaderText("You've been outplayed, fool");
                            a.setContentText("Hang your head in shame");
                            a.showAndWait();
                        }
                    });
                    break;
                }
                else if (WarboatsModel.isWon()) {
                    Platform.runLater(new Runnable() {
                        public void run() {
                            Alert a = new Alert(AlertType.INFORMATION);
                            a.setTitle("WINNER");
                            a.setHeaderText("You have won the battle!");
                            a.setContentText("Congratulations Commander");
                            a.showAndWait();

                            a = new Alert(AlertType.CONFIRMATION);
                            a.setTitle("Rematch?");
                            a.setHeaderText("Do you want to play again?");
                            a.showAndWait();

                            System.out.println(a.getResult().getText());
                        }
                    });

                    break;
                }
                else if (WarboatsModel.isOpponentReady() && WarboatsModel.isPlayerReady() && WarboatsModel.playerTurn && !inTurn) {
                    /*
                    Platform.runLater(new Runnable() {
                        public void run() {
                            Alert a = new Alert(AlertType.INFORMATION);
                            a.setTitle("Your Turn");
                            a.setHeaderText("Make your move");
                            a.showAndWait();
                        }
                    });
                     */
                    inTurn = true;
                }
                else if (!WarboatsModel.isPlayerTurn()) {
                    inTurn = false;
                }

                Thread.sleep(100);
            }
            return null;
        }
    }

    class notifyRematch extends Task<Void> {

        protected Void call() throws Exception {
            while (true) {
                System.out.println("IN NEXT TASK");
                if (!WarboatsModel.isRematch()) {
                    break;
                }
                else if (WarboatsModel.isRematch() && !WarboatsModel.isOpponentRematch()) {

                    Platform.runLater(new Runnable() {
                        public void run() {
                            Alert a = new Alert(AlertType.INFORMATION);
                            a.setTitle("Waiting For Opponent");
                            a.setHeaderText(
                                    "Opponent has not yet confirmed rematch");
                            a.showAndWait();
                        }
                    });
                    break;
                }
            }

            return null;
        }
    }

    class notifyBeginGame extends Task<Void> {

        protected Void call() throws Exception {
            while (true) {
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

                Thread.sleep(100);
            }
            return null;
        }
    }

    class updateStatsTask extends Task<Void> {

        protected Void call() throws Exception {
            while (true) {
                Platform.runLater(new Runnable() {
                    public void run() {
                        ValueUpdateUtility.updateStatsLabels(theModel, theView);
                    }
                });

                Thread.sleep(500);

                if (theModel.isLost() || theModel.isWon()) {
                    break;
                }
            }
            return null;
        }
    }
}
