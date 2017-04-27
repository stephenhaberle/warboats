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
* File: DragDropController
* Description: Sub controller to handle the dragging and dropping of ships onto the user's board
*
* ****************************************
 */
package warboats.controller;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;
import warboats.model.WarboatsModel;
import warboats.view.ShipView;
import warboats.view.WarboatsView;

/**
 * Main controller for handling the dragging and dropping of ships for the user
 * in the setup period of the game
 *
 * @author clo006
 */
public class DragDropController {

    private WarboatsView theView;
    private WarboatsModel theModel;
    private WarboatsController theCtrl;

    //images of the boats
    private ImageView cImg;
    private ImageView bImg;
    private ImageView dImg;
    private ImageView sImg;
    private ImageView pImg;

    private GridPane target;
    private ImageView source;
    private ImageView tempShipImage = new ImageView();
    private boolean vImage = false;

    /**
     * Initializes the sub controller and sets up the images of the respective
     * boats
     *
     * @param theView The view to be changed
     * @param theModel The model of the game
     * @param theCtrl The main controller that is passed in
     */
    public DragDropController(WarboatsView theView, WarboatsModel theModel,
                              WarboatsController theCtrl) {
        this.theModel = theModel;
        this.theView = theView;

        target = this.theView.getPlayerBoard();

        cImg = this.theView.getCarrierView().getBoatImage();
        bImg = this.theView.getBshipView().getBoatImage();
        dImg = this.theView.getDestroyView().getBoatImage();
        sImg = this.theView.getSubView().getBoatImage();
        pImg = this.theView.getPtView().getBoatImage();

        //carrier
        cImg.setOnDragEntered(this::handleSetOnDragEntered);
        cImg.setOnMouseReleased(this::handleSetOnMouseReleased);
        cImg.setOnDragDetected(this::handleSetOnDragDetected);
        cImg.setOnDragDone(this::handleSetOnDragDone);
        cImg.setOnDragExited(this::handleSetOnDragExited);
        cImg.setOnDragDropped(this::handleDragShips);
        cImg.setOnMousePressed(this::handleSetOnMouseClicked);

        //battleship
        bImg.setOnDragEntered(this::handleSetOnDragEntered);
        bImg.setOnMouseReleased(this::handleSetOnMouseReleased);
        bImg.setOnDragDetected(this::handleSetOnDragDetected);
        bImg.setOnDragDone(this::handleSetOnDragDone);
        bImg.setOnDragExited(this::handleSetOnDragExited);
        bImg.setOnDragDropped(this::handleDragShips);
        bImg.setOnMousePressed(this::handleSetOnMouseClicked);

        //destroyer
        dImg.setOnDragEntered(this::handleSetOnDragEntered);
        dImg.setOnMouseReleased(this::handleSetOnMouseReleased);
        dImg.setOnDragDetected(this::handleSetOnDragDetected);
        dImg.setOnDragDone(this::handleSetOnDragDone);
        dImg.setOnDragExited(this::handleSetOnDragExited);
        dImg.setOnDragDropped(this::handleDragShips);
        dImg.setOnMousePressed(this::handleSetOnMouseClicked);

        //submarine
        sImg.setOnDragEntered(this::handleSetOnDragEntered);
        sImg.setOnMouseReleased(this::handleSetOnMouseReleased);
        sImg.setOnDragDetected(this::handleSetOnDragDetected);
        sImg.setOnDragDone(this::handleSetOnDragDone);
        sImg.setOnDragExited(this::handleSetOnDragExited);
        sImg.setOnDragDropped(this::handleDragShips);
        sImg.setOnMousePressed(this::handleSetOnMouseClicked);

        //patrol boat
        pImg.setOnDragEntered(this::handleSetOnDragEntered);
        pImg.setOnMouseReleased(this::handleSetOnMouseReleased);
        pImg.setOnDragDetected(this::handleSetOnDragDetected);
        pImg.setOnDragDone(this::handleSetOnDragDone);
        pImg.setOnDragExited(this::handleSetOnDragExited);
        pImg.setOnDragDropped(this::handleDragShips);
        pImg.setOnMousePressed(this::handleSetOnMouseClicked);

        //event handler for drag events
        target.setOnDragOver(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                //System.out.println("Event on Target: mouse drag over");
                if (event.getGestureSource() != target && event.getDragboard().hasImage()) {
                    //allow for moving
                    event.acceptTransferModes(TransferMode.MOVE);
                }

                //stops the event from further propogation once it is completed
                event.consume();
            }
        });

        //sets up remaining event handling operations for various actions
        target.setOnDragEntered(this::handleSetOnDragEntered);
        target.setOnDragDropped(this::handleDragShips);
        target.setOnDragExited(this::handleSetOnDragExited);
        target.setOnMouseClicked(this::targetSetMouseClicked);

    }

    /**
     * //TODO: FINISH THIS JAVADOC
     *
     * @param event
     */
    public void targetSetMouseClicked(MouseEvent event) {
        try {
            if (event.getButton().equals(MouseButton.PRIMARY)) {
                if (event.getClickCount() == 2) {
                    System.out.println("target");
                    ImageView target = (ImageView) event.getPickResult().getIntersectedNode();
                    System.out.println(target);
                    this.placePlayerShip(target);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Invalid click");
        }
    }

    /**
     * Handler that sets the source based on what boat image was selected
     *
     * @param event
     */
    public void handleSetOnMouseClicked(MouseEvent event) {
        ImageView source = (ImageView) event.getSource();
        this.source = source;
    }

    /**
     *
     * @param event
     */
    public void handleSetOnDragEntered(DragEvent event) {
        //System.out.println("Event on Target: mouse dragged");
        if (event.getGestureSource() != target && event.getDragboard().hasImage()) {
            source.setVisible(false);
            target.setOpacity(0.7);
            System.out.println("Drag entered");
        }

        event.consume();
    }

    /**
     * Updates the transparency of the mouse once the ship has been dropped on
     * the gridpane
     *
     * @param event a Mouse event (release)
     */
    public void handleSetOnMouseReleased(MouseEvent event) {
        source.setMouseTransparent(false);
        System.out.println("Event on Source: mouse released");
    }

    /**
     * Handler that instantiates a dragboard so that images of boats can be
     * dragged from the right pane.
     *
     * @param event Mouse event (click + move)
     */
    public void handleSetOnDragDetected(MouseEvent event) {
        Dragboard db = source.startDragAndDrop(TransferMode.MOVE);
        //source.startFullDrag();
        System.out.println("Event on Source: drag detected");
        //Put ImageView on dragboard
        ClipboardContent cbContent = new ClipboardContent();
        cbContent.putImage(source.getImage());
        //cbContent.put(DataFormat.)
        db.setContent(cbContent);
        //source.setVisible(false); //WAS MAKING BOATS DISSAPEAR FOREVER
        event.consume();

    }

    /**
     * Handler that checks for completion of the drag/drop gesture
     *
     * @param event DragEvent (release of mouse)
     */
    public void handleSetOnDragDone(DragEvent event) {
        //if the data was successfully moved, clear it
        if (event.getTransferMode() == TransferMode.MOVE) {
            source.setVisible(false);
        }
        event.consume();
    }

    /**
     *
     * @param event
     */
    public void handleSetOnDragExited(DragEvent event) {
        System.out.println("Event on Target: mouse drag exited");
        source.setVisible(true);
        target.setOpacity(1);

        event.consume();
    }

    /**
     * Main handler for dragging and dropping horizontal boats implementing
     * logic to prevent wonky placements
     *
     * @param event DragEvent
     */
    public void handleDragShips(DragEvent event) {
        Dragboard db = event.getDragboard();

        boolean success = false;

        try {
            Node node = event.getPickResult().getIntersectedNode();

            GridPane board = (GridPane) node.getParent();

            if (node != target && db.hasImage()) {

                Integer cIndex = GridPane.getColumnIndex(node);
                Integer rIndex = GridPane.getRowIndex(node);

                int x = cIndex == null ? 0 : cIndex;
                int y = rIndex == null ? 0 : rIndex;

                tempShipImage = new ImageView(db.getImage());

                int id = Integer.parseInt(source.getId());
                tempShipImage.setId(source.getId());

                Integer[] shipLengths = {2, 3, 3, 4, 5};

                // TODO: set image size; use correct column/row span
                // need to do this check based on if piece or horizontal or vertical
                // currently only capable of
                if (cIndex + shipLengths[id - 1] - 1 > 10 || cIndex == 0 || rIndex == 0) {
                    throw new ClassCastException("SHIP CANT BE OFF BOARD");
                }
                else {
                    System.out.println("ID " + id);

                    ShipView ship = theView.getPlacedShips().get(id - 1);

                    //only support for horizontal placement, place ships handles vertical conversion
                    theModel.addShip(id, x, y, x + (shipLengths[id - 1] - 1), y);
                    System.out.println(theModel.getMyBoard());
                    this.assignShipToView(id);

                    board.add(tempShipImage, x, y, shipLengths[id - 1], 1);

                    success = true;
                }

            }

        } catch (ClassCastException e) {
            System.out.println(e);
            System.out.println("Invalid node selection. Try again");
        }
        //let the source know whether the image was successfully transferred and used
        event.setDropCompleted(success);

        event.consume();

        //this throws an error in the console that is undesirable but is the only way to get the ships to reset
        if (!success) {
            throw new ClassCastException("RESET POS");
        }
    }

    /**
     * Assigns the respective boat to the view Updates the GUI
     *
     * @param id ID of the ship to be placed
     */
    public void assignShipToView(int id) {
        switch (id) {
            case 5:
                theView.getCarrierView().setModel(theModel.getCarrier());
                break;
            case 4:
                theView.getBshipView().setModel(theModel.getBattleship());
                break;
            case 3:
                theView.getDestroyView().setModel(theModel.getDestroyer());
                break;
            case 2:
                theView.getSubView().setModel(theModel.getSubmarine());
                break;
            case 1:
                theView.getPtView().setModel(theModel.getPatrolBoat());
                break;
        }
    }

    /**
     * Places a ship once it has been dropped to be either horizontal or
     * vertical and updates image of the boat if needed
     *
     * @param image
     */
    public void placePlayerShip(ImageView image) {
        Integer[] shipLengths = {2, 3, 3, 4, 5};
        int id = Integer.parseInt(image.getId());
        int row = GridPane.getRowIndex(image);
        int col = GridPane.getColumnIndex(image);
        System.out.println(col);
        System.out.println(row);
        GridPane board = (GridPane) image.getParent();

        //set up images of the ships to be either vertical or horizontal based on user input
        String orientation = vImage ? "H" : "V";
        String boatFile = "";

        switch (id) {
            case 5:
                boatFile = "carrier";
                break;
            case 4:
                boatFile = "battleship";
                break;
            case 3:
                boatFile = "destroyer";
                break;
            case 2:
                boatFile = "sub";
                break;
            case 1:
                boatFile = "pt";
                break;
        }

        //assemble the filename of the respective boat png files
        String filename = String.format("file:ships/%s%s.png", boatFile,
                                        orientation);

        Image img;
        //if image is vertical, convert it to a horizontal orientation
        if (vImage) {
            try {
                //updates the coordinates of the ship being rotated
                theModel.updateShip(id, col, row,
                                    col + (shipLengths[id - 1] - 1), row);

                //updates the orientation of the image
                img = new Image(filename);
                vImage = false;
                image.setImage(img);
                board.getChildren().remove(image);
                board.add(image, col, row, shipLengths[id - 1], 1);
            } catch (Exception e) {
                System.out.println(e);
                System.out.println("unable to horiz");

                //reverts to the old position
                theModel.updateShip(id, col, row, col,
                                    row + (shipLengths[id - 1] - 1));
            }
        }
        //if image is horizontal, convert it to a vertical orientation
        else {
            try {
                //updates the coordinates of the ship being rotated
                theModel.updateShip(id, col, row, col,
                                    row + (shipLengths[id - 1] - 1));

                //updates the orientation of the image
                img = new Image(filename);
                vImage = true;
                image.setImage(img);
                board.getChildren().remove(image);
                board.add(image, col, row, 1, shipLengths[id - 1]);
            } catch (Exception e) {
                System.out.println(e);
                System.out.println("unable to vert");

                //reverts to the old position
                theModel.updateShip(id, col, row,
                                    col + (shipLengths[id - 1] - 1), row);
            }
        }

    }

    /**
     * Getter for the target (opponent's board)
     *
     * @return a GridPane representing the opponent's board
     */
    public GridPane getTarget() {
        return target;
    }

}
