/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package warboats.controller;

import java.util.ArrayList;
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
import warboats.view.WarboatsView;
import warboats.view.boatsView.ShipView;

/**
 *
 * @author clo006
 */
public class DragDropController {

    private WarboatsView theView;
    private WarboatsModel theModel;
    private WarboatsController theCtrl;
    private ImageView cImg;
    private ImageView bImg;
    private ImageView dImg;
    private ImageView sImg;
    private ImageView pImg;
    private GridPane target;
    private ImageView source;
    private ImageView tempShipImage = new ImageView();
    private boolean vImage = false;

    public DragDropController(WarboatsView theView, WarboatsModel theModel,
                              WarboatsController theCtrl) {
        this.theModel = theModel;
        this.theView = theView;

        target = this.theView.getPlayerBoard();

        cImg = this.theView.getCarrierView().image;
        bImg = this.theView.getBshipView().image;
        dImg = this.theView.getDestroyView().image;
        sImg = this.theView.getSubView().image;
        pImg = this.theView.getPtView().image;

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

        target.setOnDragOver(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                //System.out.println("Event on Target: mouse drag over");
                if (event.getGestureSource() != target && event.getDragboard().hasImage()) {
                    //allow for moving
                    event.acceptTransferModes(TransferMode.MOVE);
                }
                event.consume();
            }
        });
        target.setOnDragEntered(this::handleSetOnDragEntered);
        target.setOnDragDropped(this::handleDragShips);
        target.setOnDragExited(this::handleSetOnDragExited);
        target.setOnMouseClicked(this::targetSetMouseClicked);

    }

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
            System.out.println("Invalid click");
        }
    }

    public void handleSetOnMouseClicked(MouseEvent event) {
        ImageView source = (ImageView) event.getSource();
        this.source = source;
    }

    public void handleSetOnDragEntered(DragEvent event) {
        //System.out.println("Event on Target: mouse dragged");
        if (event.getGestureSource() != target && event.getDragboard().hasImage()) {
            source.setVisible(false);
            target.setOpacity(0.7);
            System.out.println("Drag entered");
        }

        event.consume();
    }

    public void handleSetOnMouseReleased(MouseEvent event) {
        source.setMouseTransparent(false);
        System.out.println("Event on Source: mouse released");
    }

    public void handleSetOnDragDetected(MouseEvent event) {
        Dragboard db = source.startDragAndDrop(TransferMode.MOVE);
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

    public void handleSetOnDragDone(DragEvent event) {
        //the drag and drop gesture has ended
        //if the data was successfully moved, clear it
        if (event.getTransferMode() == TransferMode.MOVE) {
            source.setVisible(false);
        }
        event.consume();
    }

    public void handleSetOnDragExited(DragEvent event) {
        System.out.println("Event on Target: mouse drag exited");
        source.setVisible(true);
        target.setOpacity(1);

        event.consume();
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
                //theModel.addShip(id, x, y, x + (shipLengths[id - 1] - 1), y);
                //System.out.println(theModel.getMyBoard());
                board.add(tempShipImage, x, y, shipLengths[id - 1], 1);
                ArrayList<Integer> coords = new ArrayList<>();
                coords.add(x);
                coords.add(y);
                coords.add(x + shipLengths[id - 1] - 1);
                coords.add(y);

                ship.setInitializedCoordinates(coords);
                System.out.println(coords);

                success = true;
            }

        }
        //let the source know whether the image was successfully transferred and used
        event.setDropCompleted(success);

        event.consume();

        if (!success) {
            throw new ClassCastException("RESET POS");
        }

    }

    public void placePlayerShip(ImageView image) {
        Integer[] shipLengths = {2, 3, 3, 4, 5};
        int id = Integer.parseInt(image.getId());
        int row = GridPane.getRowIndex(image);
        int col = GridPane.getColumnIndex(image);
        System.out.println(col);
        System.out.println(row);
        GridPane board = (GridPane) image.getParent();

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

        String filename = String.format("file:ships/%s%s.png", boatFile,
                                        orientation);

        Image img;
        if (vImage) {
            if (col + shipLengths[id - 1] - 1 <= 10) {
                board.getChildren().remove(image);
                img = new Image(filename);
                vImage = false;
                image.setImage(img);
                board.add(image, col, row, shipLengths[id - 1], 1);
            }
        }
        else {
            if (row + shipLengths[id - 1] - 1 <= 10) {
                board.getChildren().remove(image);
                img = new Image(filename);
                vImage = true;
                image.setImage(img);
                board.add(image, col, row, 1, shipLengths[id - 1]);
            }
        }

    }

}
