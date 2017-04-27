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

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import warboats.WarboatsGUI;
import warboats.model.WarboatsModel;
import warboats.utility.SoundUtility;
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
    private GameProgressionController gameCtrl;

    public WarboatsController(WarboatsModel theModel, WarboatsView theView,
                              WarboatsGUI theMain) {
        this.theMain = theMain;
        this.theModel = theModel;
        this.theView = theView;
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

    class updateStatsTask extends Task<Void> {

        protected Void call() throws Exception {
            while (true) {
                Platform.runLater(new Runnable() {
                    public void run() {
                        ValueUpdateUtility.updateStatsLabels(theModel,
                                                             theView);
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

    public GameProgressionController getGameCtrl() {
        return gameCtrl;
    }

}
