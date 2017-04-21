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

import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import warboats.boats.Boat;
import warboats.model.Marker;
import warboats.model.WarboatsModel;
import warboats.view.MarkerNode;
import warboats.view.WarboatsView;
import warboats.view.boatsView.ShipNode;

/**
 *
 * @author clo006
 */
public class WarboatsController {

    private WarboatsView theView;
    private WarboatsModel theModel;

    public WarboatsController(WarboatsModel theModel, WarboatsView theView) {
        this.theModel = theModel;
        this.theView = theView;

        this.theView.getPlaceShips().setOnAction((this::handlePlaceShips));

        Rectangle test = this.theView.getCarrierView().view;
        Rectangle testCircle = this.theView.getCarrierView().nodes.get(0);
        GridPane targetFld = this.theView.getPlayerBoard();

        testCircle.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                testCircle.setMouseTransparent(true);
                System.out.println("Event on Source: mouse pressed");
                event.setDragDetect(true);
            }
        });

        testCircle.setOnMouseReleased(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                testCircle.setMouseTransparent(false);
                System.out.println("Event on Source: mouse released");
            }
        });

        testCircle.setOnMouseDragged(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                System.out.println("Event on Source: mouse dragged");
                event.setDragDetect(false);
            }
        });

        testCircle.setOnDragDetected(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                testCircle.startFullDrag();
                System.out.println("Event on Source: drag detected");
            }
        });

        // Add mouse event handlers for the target
        targetFld.setOnMouseDragEntered(new EventHandler<MouseDragEvent>() {
            public void handle(MouseDragEvent event) {
                System.out.println("Event on Target: mouse dragged");
            }
        });

        targetFld.setOnMouseDragOver(new EventHandler<MouseDragEvent>() {
            public void handle(MouseDragEvent event) {
                System.out.println("Event on Target: mouse drag over");
            }
        });

        targetFld.setOnMouseDragReleased(new EventHandler<MouseDragEvent>() {
            public void handle(MouseDragEvent event) {
                MarkerNode node = (MarkerNode) event.getPickResult().getIntersectedNode();
                System.out.println(node.getMarker());
                ((VBox) testCircle.getParent()).getChildren().remove(testCircle);

                //testCircle.setCenterX(node.getCenterX());
                //testCircle.setCenterY(node.getCenterY());
                int x = GridPane.getColumnIndex(node);
                int y = GridPane.getRowIndex(node);

                ((GridPane) node.getParent()).add(testCircle, x, y);
                //System.out.println(testCircle.getCenterX());
                //System.out.println(testCircle.getCenterY());
                System.out.println("Event on Target: mouse drag released");
                System.out.println(event.getPickResult().getIntersectedNode());
            }
        });

        targetFld.setOnMouseDragExited(new EventHandler<MouseDragEvent>() {
            public void handle(MouseDragEvent event) {
                System.out.println("Event on Target: mouse drag exited");
            }
        });

    }

    public void handleDragShips(ActionEvent event) {

    }

    public void handlePlaceShips(ActionEvent event) {
        //assign inputs to boats
        for (ShipNode ship : theView.getPlacedShipNodes()) {
            int x1 = Integer.parseInt(
                    ship.getInitializedCoordinates().get(0).getText());
            int y1 = Integer.parseInt(
                    ship.getInitializedCoordinates().get(1).getText());
            int x2 = Integer.parseInt(
                    ship.getInitializedCoordinates().get(2).getText());
            int y2 = Integer.parseInt(
                    ship.getInitializedCoordinates().get(3).getText());
            theModel.addShip(ship.getShipType(), x1, y1, x2, y2);

        }

        //place the boats on board
        ArrayList<Boat> playersPlacedBoats = this.theModel.getNavy(); //gets players placed ships
        System.out.println(playersPlacedBoats.toString());

        for (Boat boat : playersPlacedBoats) {
            if (boat.getStartX() == boat.getEndX()) {//boat is vertical. only care about Y values
                if (boat.getStartY() < boat.getEndY()) {//this is normal
                    for (int i = boat.getStartY(); i <= boat.getEndY(); i++) { // i is a Y
                        ShipNode ship = new ShipNode(
                                new Marker(boat.getStartX(), i));
                        theView.getPlayerBoard().add(ship, boat.getStartX(), i);
                        theView.getPlacedShipNodes().add(ship);
                    }
                }
                else { //wonkyness achieved. End > Start
                    for (int i = boat.getEndY(); i <= boat.getStartY(); i++) { //i is a Y
                        ShipNode ship = new ShipNode(
                                new Marker(boat.getStartX(), i));
                        theView.getPlayerBoard().add(ship, boat.getStartX(), i);
                        theView.getPlacedShipNodes().add(ship);
                    }
                }

            }
            else { //boat is horizontal. Only care about X values
                if (boat.getStartX() < boat.getEndX()) {//this is normal
                    for (int i = boat.getStartX(); i <= boat.getEndX(); i++) { //i is an X
                        ShipNode ship = new ShipNode(new Marker(i,
                                                                boat.getStartY()));
                        theView.getPlayerBoard().add(ship, i, boat.getStartY());
                        theView.getPlacedShipNodes().add(ship);
                    }
                }
                else { //wonkyness achieved. End > Start
                    for (int i = boat.getEndX(); i <= boat.getStartX(); i++) { //i is an X
                        ShipNode ship = new ShipNode(new Marker(i,
                                                                boat.getStartY()));
                        theView.getPlayerBoard().add(ship, i, boat.getStartY());
                        theView.getPlacedShipNodes().add(ship);
                    }
                }

            }
        }
    }

}
