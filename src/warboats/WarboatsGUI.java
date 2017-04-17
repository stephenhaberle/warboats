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
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
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

        theView = new WarboatsView();
        theCtrl = new WarboatsController();
        theModel = new WarboatsModel(WarboatsNetwork.getActiveClient(),
                                     WarboatsNetwork.getActiveServer());

    }

    @Override
    public void start(Stage primaryStage) {
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });

        StackPane root = new StackPane();
        root.getChildren().add(btn);

        Scene scene = new Scene(root, 300, 250);

        primaryStage.setTitle("Hello World!");
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
