/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
import warboats.WarboatsGUI;
import warboats.model.WarboatsModel;
import warboats.utility.SoundUtility;
import warboats.view.WarboatsView;

/**
 *
 * @author clo006
 */
public class GameProgressionController {

    private WarboatsView theView;
    private WarboatsModel theModel;
    private WarboatsGUI theMain;
    private WarboatsController theCtrl;
    private DragDropController dragCtrl;

    private AtomicInteger taskCount = new AtomicInteger(0);
    private ExecutorService exec = Executors.newSingleThreadExecutor(r -> {
        Thread t = new Thread(r);
        t.setDaemon(true);
        return t;
    });

    public GameProgressionController(WarboatsModel theModel,
                                     WarboatsView theView,
                                     WarboatsController theCtrl,
                                     WarboatsGUI theMain,
                                     DragDropController dragCtrl) {
        this.theModel = theModel;
        this.theView = theView;
        this.theMain = theMain;
        this.theCtrl = theCtrl;
        this.dragCtrl = dragCtrl;

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
                    rematchTask.setOnSucceeded(taskEvent -> pendingTasks.set(
                            pendingTasks.get() - 1));
                    try {
                        rematchTask.setOnSucceeded(
                                taskEvent -> theMain.restart());
                    } catch (Exception e) {
                        System.out.println(
                                "Unsupported exception thrown by restart() in controller");
                    }
                    exec.submit(rematchTask);

                    exec.shutdown();
                }
                else {
                    Alert a = new Alert(Alert.AlertType.ERROR);
                    a.setTitle("Cannot Begin Game");
                    a.setHeaderText("Unplaced ships remain");
                    a.setContentText(
                            "Not all ships placed. Please drag all ships onto \nyour board and position them to begin game");
                    a.showAndWait();
                }
            }
        });
    }

    class notifyTurn extends Task<Void> {

        final int taskNumber = taskCount.incrementAndGet();

        protected Void call() throws Exception {
            boolean inTurn = false;
            Runnable rematch = new Runnable() {
                public void run() {
                    Alert a = new Alert(Alert.AlertType.CONFIRMATION);
                    a.setTitle("Rematch?");
                    a.setHeaderText("Do you want to play again?");
                    a.showAndWait();

                    if (a.getResult().getText().equals("OK")) {
                        WarboatsModel.setPlayerRematch(true);
                        theModel.sendRematch(true);
                    }
                    else {
                        WarboatsModel.setPlayerRematch(false);
                        theModel.sendRematch(false);
                    }

                    synchronized (this) {
                        this.notify();
                    }
                }
            };

            while (true) {
                if (WarboatsModel.isLost()) {
                    Runnable loser = new Runnable() {
                        public void run() {
                            SoundUtility.loss();
                            Alert a = new Alert(Alert.AlertType.INFORMATION);
                            a.setTitle("LOSER");
                            a.setHeaderText("You've been outplayed, fool");
                            a.setContentText("Hang your head in shame");
                            a.showAndWait();

                            synchronized (this) {
                                this.notify();
                            }
                        }
                    };

                    synchronized (loser) {
                        Platform.runLater(loser);
                        loser.wait();
                        break;
                    }
                }
                else if (WarboatsModel.isWon()) {
                    Runnable winner = new Runnable() {
                        public void run() {
                            SoundUtility.win();
                            Alert a = new Alert(Alert.AlertType.INFORMATION);
                            a.setTitle("WINNER");
                            a.setHeaderText("You have won the battle!");
                            a.setContentText("Congratulations Commander");
                            a.showAndWait();

                            synchronized (this) {
                                this.notify();
                            }

                        }
                    };

                    synchronized (winner) {
                        Platform.runLater(winner);
                        winner.wait();
                        break;
                    }

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

            synchronized (rematch) {
                Platform.runLater(rematch);
                rematch.wait();
            }

            return null;
        }
    }

    //contains debugging print statements, remove for final product
    class notifyRematch extends Task<Void> {

        final int taskNumber = taskCount.incrementAndGet();

        protected Void call() throws Exception {

            boolean alreadyShown = false;

            while (true) {
                if (!WarboatsModel.isRematch()) {
                    Platform.runLater(() -> {
                        Alert exitAlert = new Alert(Alert.AlertType.INFORMATION);
                        exitAlert.setTitle("Exit");
                        exitAlert.setHeaderText("Thanks for playing!");
                        exitAlert.showAndWait();
                    });
                    break;
                }
                else if (WarboatsModel.isRematch() && WarboatsModel.isOpponentRematch() != null && !WarboatsModel.isOpponentRematch()) {
                    System.out.println("in denied situation");
                    Runnable noRematch = new Runnable() {
                        @Override
                        public void run() {
                            Alert exitAlert = new Alert(Alert.AlertType.INFORMATION);
                            exitAlert.setTitle("Exit");
                            exitAlert.setHeaderText("Thanks for playing!");
                            exitAlert.setContentText("Opponent has denied rematch");
                            exitAlert.showAndWait();
                        
                            synchronized (this) {
                                this.notify();
                            }
                        }
                    };
                    
                    synchronized(noRematch) {
                        Platform.runLater(noRematch);
                        noRematch.wait();
                        WarboatsGUI.setRestart(false); 
                        break; 
                    }
                }
                else if (WarboatsModel.isRematch() && (WarboatsModel.isOpponentRematch() == null) && !alreadyShown) {
                    System.out.println("IN NULL SITUATION");
                    Platform.runLater(() -> {
                        Alert a = new Alert(Alert.AlertType.INFORMATION);
                        a.setTitle("Waiting For Opponent");
                        a.setHeaderText("Opponent has not yet confirmed rematch");
                        a.showAndWait();
                    });
                    alreadyShown = true;
                }
                else if (WarboatsModel.isRematch() && WarboatsModel.isOpponentRematch() != null && WarboatsModel.isOpponentRematch()) {
                    System.out.println("rematch confirmed");
                    Runnable rematch = new Runnable() {
                        @Override
                        public void run() {
                            Alert a = new Alert(Alert.AlertType.INFORMATION);
                            a.setTitle("REMATCH CONFIRMED");
                            a.setHeaderText("Opponent has confirmed rematch");
                            a.setContentText(
                                    "Prepare to get stuck in an infinite loop");
                            a.showAndWait();

                            synchronized (this) {
                                this.notify();
                            }

                        }
                    };

                    synchronized (rematch) {
                        Platform.runLater(rematch);
                        rematch.wait();
                        WarboatsGUI.setRestart(true);
                        break;
                    }

                }
                else {
                    Thread.sleep(100);
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
                            SoundUtility.beginGame();
                            Alert a = new Alert(Alert.AlertType.INFORMATION);
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

    public ExecutorService getExec() {
        return exec;
    }

}
