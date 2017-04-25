/* *****************************************
* CSCI205 - Software Engineering and Design
* Spring 2017 - Final Project
*
* Name: Christian Ouellette, Keller Chambers, Stephen Haberle, Peyton Rumachik
* Date: Apr 14, 2017
* Time: 2:59:19 PM
*
* Project: warboats
* Package: warboats
* File: WarboatsGUI
* Description:
*
* ****************************************
 */
package warboats;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import warboats.controller.WarboatsController;
import warboats.model.WarboatsModel;
import warboats.network.WarboatsNetwork;
import warboats.view.WarboatsView;

/**
 *
 * @author clo006
 */
public class WarboatsGUI extends Application {

    private WarboatsView theView;
    private WarboatsController theCtrl;
    private static WarboatsModel theModel;
    private static boolean restart = false;

    @Override
    public void init() throws Exception {
        super.init();
        WarboatsNetwork.buildNetwork();
        theModel = new WarboatsModel(WarboatsNetwork.getActiveClient(),
                                     WarboatsNetwork.getActiveServer());
        //only for when we want preset placements
        //theModel.getConsolePlacements();
        theView = new WarboatsView(this.theModel);
        theCtrl = new WarboatsController(theModel, theView, this);

    }

    @Override
    public void start(Stage primaryStage) {

        Scene scene = new Scene(this.theView.getRootNode());

        primaryStage.setTitle("WARBOATS");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void restart() throws Exception {
        if (restart) {
            System.out.println("RESTARTING");
            restart = false;
            this.init();
        }
        else {
            System.out.println("STOPPING");
            this.stop();
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    public static WarboatsModel getTheModel() {
        return theModel;
    }

    public static void setRestart(boolean restart) {
        WarboatsGUI.restart = restart;
    }

}
