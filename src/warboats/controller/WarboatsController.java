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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import warboats.WarboatsGUI;
import warboats.model.WarboatsModel;
import warboats.utility.SoundUtility;
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
    private WarboatsGUI theMain;
    private GameProgressionController gameCtrl;

    /**
     * Instantiates the main controller for the game and prepares threads for
     * updating the statistics
     *
     * @param theModel Model object of the game
     * @param theView View object of the game
     */
    public WarboatsController(WarboatsModel theModel, WarboatsView theView,
                              WarboatsGUI theMain) {
        this.theMain = theMain;
        this.theModel = theModel;
        this.theView = theView;

        //setup sub-controllers
        this.dragCtrl = new DragDropController(this.theView, this.theModel, this);
        this.shotCtrl = new SendShotController(this.theView, this.theModel, this);
        this.gameCtrl = new GameProgressionController(this.theModel,
                                                      this.theView, this,
                                                      this.theMain,
                                                      this.dragCtrl);

        Thread updateLabelsThread = new Thread(new updateStatsTask());
        updateLabelsThread.setDaemon(true);
        updateLabelsThread.start();

        this.theView.getLogo().setOnMouseClicked((MouseEvent event) -> {
            SoundUtility.logoClick();
        });
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
                        ValueUpdateUtility.updateStatsLabels(theModel,
                                                             theView);
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

    public GameProgressionController getGameCtrl() {
        return gameCtrl;
    }

}
