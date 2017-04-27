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
* Description: Main class to test WarboatsConsole using GUI IO techniques.
*
* ****************************************
 */
package warboats;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import warboats.controller.WarboatsController;
import warboats.model.WarboatsModel;
import warboats.network.WarboatsClient;
import warboats.network.WarboatsNetwork;
import warboats.network.WarboatsServer;
import warboats.utility.SoundUtility;
import warboats.view.WarboatsView;

/**
 * Main class for the game. Sets up basic stage and MVC architecture
 *
 * @author clo006
 */
public class WarboatsGUI extends Application {

    private WarboatsView theView;
    private WarboatsController theCtrl;
    private static WarboatsModel theModel;
    private static boolean restart = false;
    private Stage theStage;

    /**
     * Initializer for the game. Sets up the MVC architecture
     *
     * @throws Exception mostly for network failure
     */
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
    public void stop() throws InterruptedException {
        //Not elegant and if there's time I want to add shutdown hooks to threads -Christian
        //However, it does manage to effectively kill the program.
        SoundUtility.shutdown();
        //wait for sound to finish
        Thread.sleep(1500);
        System.exit(0);
    }

    /**
     * At this point, the system is ready for the application to begin running.
     * Extracts the root node from the view and displays it in a window.
     *
     * @param primaryStage The window that everything will be displayed in
     */
    @Override
    public void start(Stage primaryStage) {

        theStage = primaryStage;

        Scene scene = new Scene(this.theView.getRootNode()); //extract GUI from view

        primaryStage.setTitle("WARBOATS");
        primaryStage.setScene(scene);
        primaryStage.show();

        SoundUtility.startup();
    }

    void cleanup() {
        theModel = new WarboatsModel(WarboatsNetwork.getActiveClient(),
                                     WarboatsNetwork.getActiveServer());
        //only for when we want preset placements
        //theModel.getConsolePlacements();
        theView = new WarboatsView(this.theModel);
        theCtrl = new WarboatsController(theModel, theView, this);
        SoundUtility.restart();
    }

    public void restart() {
        try {
            if (restart) {
                System.out.println("RESTARTING");
                restart = false;
                this.cleanup();
                start(theStage);
            }
            else {
                System.out.println("STOPPING");
                theStage.close();
                if (WarboatsNetwork.getActiveClient() == null) {
                    WarboatsServer.server.stop();
                }
                else {
                    WarboatsClient.client.stop();
                }
                this.stop();
            }
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Unsupported exception thrown in restart()");
        }
    }

    /**
     * Starts the game
     *
     * @param args the command line arguments. Does nothing.
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Getter for the model of the game
     *
     * @return a WarboatsModel object
     */
    public static WarboatsModel getTheModel() {
        return theModel;
    }

    public static void setRestart(boolean restart) {
        WarboatsGUI.restart = restart;
    }

}
