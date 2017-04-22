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

import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import warboats.model.WarboatsModel;
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

    public WarboatsController(WarboatsModel theModel, WarboatsView theView) {
        this.theModel = theModel;
        this.theView = theView;
        this.dragCtrl = new DragDropController(this.theView, this.theModel, this);

        //this.theView.getPlaceShips().setOnAction((this::handlePlaceShips));
        //source = this.theView.getCarrierView().image;
        //target = this.theView.getPlayerBoard();
        //cImg.setOnMouseReleased(this::handleSetOnMouseReleased);

        /*
        source.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                source.setMouseTransparent(true);
                System.out.println("Event on Source: mouse pressed");
                event.setDragDetect(true);
            }
        });
         */
        //TODO: boat can still disappear sometimes when dragged to incorrect place instead of resetting it's position
        // add keypress gesture that changes boat direction
        // http://stackoverflow.com/questions/23399283/javafx-keyevent-not-raised-when-dragging-gesture-active
        //source.setOnMouseReleased(this::handleSetOnMouseReleased);

        /*
        source.setOnMouseDragged(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                System.out.println("Event on Source: mouse dragged");
                event.setDragDetect(false);
            }
        });

        source.setOnKeyPressed(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent event) {
                System.out.println("FLIP");
            }
        });

        source.setOnDragDone(this::handleSetOnDragDone);

        source.setOnDragDetected(this::handleSetOnDragDetected);

        // Add mouse event handlers for the target
        target.setOnDragEntered(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                System.out.println("Event on Target: mouse dragged");
                if (event.getGestureSource() != target && event.getDragboard().hasImage()) {
                    source.setVisible(false);
                    target.setOpacity(0.7);
                    System.out.println("Drag entered");
                }
                event.consume();
            }
        });

        target.setOnDragOver(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                System.out.println("Event on Target: mouse drag over");
                if (event.getGestureSource() != target && event.getDragboard().hasImage()) {
                    //allow for moving
                    event.acceptTransferModes(TransferMode.MOVE);
                }
                event.consume();
            }
        });

        target.setOnDragDropped(this::handleDragShips);

        target.setOnDragExited(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                System.out.println("Event on Target: mouse drag exited");
                source.setVisible(true);
                target.setOpacity(1);

                event.consume();
            }
        });
         */
    }

    /*
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
     */
}
