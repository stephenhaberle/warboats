/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package warboats.controller;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import warboats.model.WarboatsModel;
import warboats.view.WarboatsView;

/**
 *
 * @author pcr008
 */
public class sendShotController {

    private WarboatsView theView;
    private WarboatsModel theModel;
    private WarboatsController theCtrl;
    private GridPane target;

    public sendShotController(WarboatsView theView, WarboatsModel theModel,
                              WarboatsController theCtrl) {
        this.theModel = theModel;
        this.theView = theView;

        this.target = theView.getOpponentBoard();

        target.addEventFilter(MouseEvent.MOUSE_PRESSED,
                              new EventHandler<MouseEvent>() {
                          @Override
                          public void handle(MouseEvent mouseEvent) {
                              System.out.println(
                                      "mouse click detected! " + mouseEvent.getSource());
                          }
                      });
    }

//    public void handleMouseClick(MouseEvent event) {
//        System.out.println("mouse click detected!" + event.getSource());// see if you can extrac coordinates from this.
//    }
}
