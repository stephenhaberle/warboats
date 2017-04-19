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

    private WarboatsNetwork theNetwork;
    private WarboatsView theView;
    private WarboatsController theCtrl;
    private WarboatsModel theModel;

    @Override
    public void init() throws Exception {
        super.init();
        theNetwork = new WarboatsNetwork();
        theModel = new WarboatsModel(WarboatsNetwork.getActiveClient(),
                                     WarboatsNetwork.getActiveServer());
        theModel.getConsolePlacements();
        theView = new WarboatsView(this.theModel);
        theCtrl = new WarboatsController();

    }

    @Override
    public void start(Stage primaryStage) {

        Scene scene = new Scene(this.theView.getRootNode());

        primaryStage.setTitle("WARBOATS");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
