/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package warboats.controller;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;
import warboats.model.WarboatsModel;
import warboats.view.WarboatsView;

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

        //battleship
        bImg.setOnDragEntered(this::handleSetOnDragEntered);
        bImg.setOnMouseReleased(this::handleSetOnMouseReleased);
        bImg.setOnDragDetected(this::handleSetOnDragDetected);
        bImg.setOnDragDone(this::handleSetOnDragDone);
        bImg.setOnDragExited(this::handleSetOnDragExited);
        bImg.setOnDragDropped(this::handleDragShips);

        //destroyer
        dImg.setOnDragEntered(this::handleSetOnDragEntered);
        dImg.setOnMouseReleased(this::handleSetOnMouseReleased);
        dImg.setOnDragDetected(this::handleSetOnDragDetected);
        dImg.setOnDragDone(this::handleSetOnDragDone);
        dImg.setOnDragExited(this::handleSetOnDragExited);
        dImg.setOnDragDropped(this::handleDragShips);

        //submarine
        sImg.setOnDragEntered(this::handleSetOnDragEntered);
        sImg.setOnMouseReleased(this::handleSetOnMouseReleased);
        sImg.setOnDragDetected(this::handleSetOnDragDetected);
        sImg.setOnDragDone(this::handleSetOnDragDone);
        sImg.setOnDragExited(this::handleSetOnDragExited);
        sImg.setOnDragDropped(this::handleDragShips);

        //patrol boat
        pImg.setOnDragEntered(this::handleSetOnDragEntered);
        pImg.setOnMouseReleased(this::handleSetOnMouseReleased);
        pImg.setOnDragDetected(this::handleSetOnDragDetected);
        pImg.setOnDragDone(this::handleSetOnDragDone);
        pImg.setOnDragExited(this::handleSetOnDragExited);
        pImg.setOnDragDropped(this::handleDragShips);

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
        ImageView source = (ImageView) event.getSource();
        this.source = source;
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

            ImageView image = new ImageView(db.getImage());

            int id = Integer.parseInt(source.getId());

            // TODO: set image size; use correct column/row span
            // need to do this check based on if piece or horizontal or vertical
            // currently only capable of
            if (cIndex + id > 11 || cIndex == 0 || rIndex == 0) {
                throw new ClassCastException("SHIP CANT BE OFF BOARD");
            }
            else {
                System.out.println("ID " + id);
                Integer[] shipLengths = {2, 3, 3, 4, 5};

                //only support horizontal carrier for now
                theModel.addShip(id, x, y, x + (shipLengths[id - 1] - 1), y);
                System.out.println(theModel.getMyBoard());

                board.add(image, x, y, shipLengths[id - 1], 1);

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

}
