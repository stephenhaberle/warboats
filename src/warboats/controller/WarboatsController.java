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

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
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

    public WarboatsController(WarboatsModel theModel, WarboatsView theView) {
        this.theModel = theModel;
        this.theView = theView;

        //this.theView.getPlaceShips().setOnAction((this::handlePlaceShips));
        source = this.theView.getCarrierView().image;
        target = this.theView.getPlayerBoard();

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
        source.setOnMouseReleased(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                source.setMouseTransparent(false);
                System.out.println("Event on Source: mouse released");
            }
        });
        /*
        source.setOnMouseDragged(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                System.out.println("Event on Source: mouse dragged");
                event.setDragDetect(false);
            }
        });
         */
        source.setOnKeyPressed(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent event) {
                System.out.println("FLIP");
            }
        });

        source.setOnDragDetected(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                Dragboard db = source.startDragAndDrop(TransferMode.ANY);
                //source.startFullDrag();
                System.out.println("Event on Source: drag detected");
                //Put ImageView on dragboard
                ClipboardContent cbContent = new ClipboardContent();
                cbContent.putImage(source.getImage());
                //cbContent.put(DataFormat.)
                db.setContent(cbContent);
                source.setVisible(false);
                event.consume();

            }
        });

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

        source.setOnDragDone(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                //the drag and drop gesture has ended
                //if the data was successfully moved, clear it
                if (event.getTransferMode() == TransferMode.MOVE) {
                    source.setVisible(false);
                }
                event.consume();
            }
        });

    }

    public void handleDragShips(DragEvent event) {
        Dragboard db = event.getDragboard();

        boolean success = false;

        Node node = event.getPickResult().getIntersectedNode();

        GridPane board = (GridPane) node.getParent();

        if (node != target && db.hasImage()) {

            Integer cIndex = GridPane.getColumnIndex(node);
            Integer rIndex = GridPane.getRowIndex(node);

            int x = cIndex == null ? 0 : cIndex;
            int y = rIndex == null ? 0 : rIndex;

            ImageView image = new ImageView(db.getImage());

            // TODO: set image size; use correct column/row span
            // need to do this check based on if piece or horizontal or vertical
            // currently only capable of
            if (cIndex + 5 > 11 || cIndex == 0 || rIndex == 0) {
                throw new ClassCastException("SHIP CANT BE OFF BOARD");
            }
            else {
                board.add(image, x, y, 5, 1);

                success = true;

                //only support horizontal carrier for now
                theModel.addShip(5, x, y, x + 4, y);
                System.out.println(theModel.getMyBoard());
            }

        }
        //let the source know whether the image was successfully transferred and used
        event.setDropCompleted(success);

        event.consume();

        if (!success) {
            throw new ClassCastException("RESET POS");
        }

        /*
                MarkerNode node = (MarkerNode) event.getPickResult().getIntersectedNode();
                System.out.println(node.getMarker());
                ((VBox) source.getParent()).getChildren().remove(source);

                //testCircle.setCenterX(node.getCenterX());
                //testCircle.setCenterY(node.getCenterY());
                int x = GridPane.getColumnIndex(node);
                int y = GridPane.getRowIndex(node);

                ((GridPane) node.getParent()).add(source, x, y);
                //System.out.println(source.getCenterX());
                //System.out.println(source.getCenterY());
                System.out.println("Event on Target: mouse drag released");
                System.out.println(event.getPickResult().getIntersectedNode());
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
