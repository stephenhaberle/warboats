/* *****************************************
* CSCI205 - Software Engineering and Design
* Spring 2017 - Final Project
*
* Name: Christian Ouellette, Keller Chambers, Stephen Haberle, Peyton Rumachik
* Date: Apr 14, 2017
* Time: 1:25:40 PM
*
* Project: warboats
* Package: warboats.view
* File: WarboatsView
* Description:The dopest view that you could ever view
*
* ****************************************
 */
//fill board with custom marker object
package warboats.view;

import java.util.ArrayList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import warboats.model.Marker;
import warboats.model.WarboatsModel;

/**
 *
 * @author clo006
 */
public class WarboatsView {

    private BorderPane root;
    private WarboatsModel theModel;
    private VBox topBox;
    private MenuBar menuBar;
    private Menu fileItem;
    private Menu editItem;
    private Menu helpItem;
    private HBox boardPane;
    private VBox statsPane;
    private VBox playerPane;
    private VBox opPane;
    private GridPane playerBoard;
    private GridPane opponentBoard;
    private VBox shipPane;
    private Label shipRemainingLabel;
    private Label hitsLabel;
    private Label missesLabel;
    private ArrayList<ShipView> placedShips;
    private Button beginGame;
    private ShipView carrierView;
    private ShipView bshipView;
    private ShipView destroyView;
    private ShipView ptView;
    private ShipView subView;

    public WarboatsView(WarboatsModel theModel) {
        this.theModel = theModel;

        root = new BorderPane();
        root.setPrefSize(800, 700);

        placedShips = new ArrayList<>();

        generateMenuBar();
        generatePlayerBoardPane();
        generateOpponentBoardPane();
        generateShipPane();
        root.setCenter(boardPane);
        root.setLeft(shipPane);

        //
        //PEYTON: Peyton would like to refactor the code so that the method names are more easily understandable
    }

    private void generateShipPane() {
        shipPane = new VBox();
        shipPane.setPrefSize(150, 200);
        shipPane.getChildren().add(new Label("Ships Remaining"));
        shipRemainingLabel = new Label();
        shipPane.getChildren().add(shipRemainingLabel);
        shipPane.getChildren().add(new Label("Number of Hits"));

        hitsLabel = new Label();
        shipPane.getChildren().add(hitsLabel);
        shipPane.getChildren().add(new Label("Number of Misses"));
        missesLabel = new Label();
        shipPane.getChildren().add(missesLabel);

        Label shipLabel = new Label("Ships");
        shipLabel.setFont(new Font("Ariel", 32));
        shipPane.getChildren().add(shipLabel);
        shipPane.setPadding(new Insets(10, 10, 10, 10));
        shipLabel.setAlignment(Pos.CENTER);

        beginGame = new Button("Begin Game");
        shipPane.getChildren().add(beginGame);

        //drag and drop
        carrierView = new ShipView(5);
        bshipView = new ShipView(4);
        destroyView = new ShipView(3);
        subView = new ShipView(2);
        ptView = new ShipView(1);

        placedShips.add(ptView);
        placedShips.add(subView);
        placedShips.add(destroyView);
        placedShips.add(bshipView);
        placedShips.add(carrierView);

        Image img = new Image("file:ships/carrierH.png");
        carrierView.setBoatImage(new ImageView());
        carrierView.getBoatImage().setImage(img);
        carrierView.getBoatImage().setFitWidth(200);
        carrierView.getBoatImage().setFitHeight(40);
        carrierView.getBoatImage().setId("5");
        shipPane.getChildren().add(carrierView.getBoatImage());

        img = new Image("file:ships/battleshipH.png");
        bshipView.setBoatImage(new ImageView());
        bshipView.getBoatImage().setImage(img);
        bshipView.getBoatImage().setFitWidth(160);
        bshipView.getBoatImage().setFitHeight(40);
        bshipView.getBoatImage().setId("4");
        shipPane.getChildren().add(bshipView.getBoatImage());

        img = new Image("file:ships/destroyerH.png");
        destroyView.setBoatImage(new ImageView());
        destroyView.getBoatImage().setImage(img);
        destroyView.getBoatImage().setFitWidth(120);
        destroyView.getBoatImage().setFitHeight(40);
        destroyView.getBoatImage().setId("3");
        shipPane.getChildren().add(destroyView.getBoatImage());

        img = new Image("file:ships/subH.png");
        subView.setBoatImage(new ImageView());
        subView.getBoatImage().setImage(img);
        subView.getBoatImage().setFitWidth(120);
        subView.getBoatImage().setFitHeight(40);
        subView.getBoatImage().setId("2");
        shipPane.getChildren().add(subView.getBoatImage());

        img = new Image("file:ships/ptH.png");
        ptView.setBoatImage(new ImageView());
        ptView.getBoatImage().setImage(img);
        ptView.getBoatImage().setFitWidth(80);
        ptView.getBoatImage().setFitHeight(40);
        ptView.getBoatImage().setId("1");
        shipPane.getChildren().add(ptView.getBoatImage());

    }

    private void generateOpponentBoardPane() {

        opPane = new VBox(10);

        opponentBoard = generateBoardPane(1);

        opponentBoard.setAlignment(Pos.CENTER);

        opPane.getChildren().add(new Label("Opponent's Fleet"));
        opPane.getChildren().add(opponentBoard);

        boardPane.getChildren().add(opPane);

    }

    private void generatePlayerBoardPane() {
        boardPane = new HBox(10);
        boardPane.setAlignment(Pos.CENTER);
        playerPane = new VBox(10);

        playerBoard = generateBoardPane(0);

        playerBoard.setAlignment(Pos.CENTER);
        playerPane.getChildren().add(new Label("Your Fleet"));
        playerPane.getChildren().add(playerBoard);

        boardPane.getChildren().add(playerPane);
    }

    /**
     *
     * @param player - 0 if for player board, 1 for opp board.
     * @return GridPane - the grid representation of ships and markers.
     */
    private GridPane generateBoardPane(int player) {
        GridPane genBoard = new GridPane();
        genBoard.setGridLinesVisible(true);
        genBoard.setAlignment(Pos.TOP_CENTER);
        ArrayList<Label> labels = new ArrayList<>();

        //maybe fill with rectangles instead?
        for (int i = 0; i <= 10; i++) {
            genBoard.getColumnConstraints().add(new ColumnConstraints(40));
            genBoard.getRowConstraints().add(new RowConstraints(40));

            String[] boardLetters = {" ", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};

            for (int j = 0; j <= 10; j++) {
                if (j == 0) {
                    Label numLabel = new Label(String.format("%d", i));
                    genBoard.add(numLabel, j, i);
                    GridPane.setHalignment(numLabel, HPos.CENTER);
                }
                else if (i == 0) {
                    Label tempLabel = new Label(boardLetters[j]);
                    genBoard.add(tempLabel, j, i);
                    labels.add(tempLabel);

                    for (Label label : labels) {
                        GridPane.setHalignment(label, HPos.CENTER);
                    }
                }
                else {
                    if (player == 1) {
                        SquareMarkerNode node = new SquareMarkerNode(new Marker(
                                j, i));

                        Image img = new Image("file:markers/water.png");
                        node.image = new ImageView();
                        node.image.setImage(img);
                        node.image.setFitWidth(38);
                        node.image.setFitHeight(38);

                        genBoard.add(node.image, j, i);

                        MarkerNode node2 = new MarkerNode(new Marker(j, i));
                        genBoard.add(node2, j, i);
                        GridPane.setHalignment(node.image, HPos.CENTER);
                    }
                    else {
                        SquareMarkerNode node = new SquareMarkerNode(new Marker(
                                j, i));

                        Image img = new Image("file:markers/water.png");
                        node.image = new ImageView();
                        node.image.setImage(img);
                        node.image.setFitWidth(38);
                        node.image.setFitHeight(38);

                        genBoard.add(node.image, j, i);
                        GridPane.setHalignment(node.image, HPos.CENTER);
                    }
                }
            }
        }

        return genBoard;

    }

    private void generateMenuBar() {
        topBox = new VBox(10);

        menuBar = new MenuBar();
        fileItem = new Menu("File");
        editItem = new Menu("Edit");
        helpItem = new Menu("Help");
        menuBar.getMenus().addAll(fileItem, editItem, helpItem);
        topBox.getChildren().add(menuBar);

        //Adds logo under menubar
        Image img = new Image("file:images/logo.png");
        ImageView image = new ImageView();
        image.setImage(img);
        image.setFitWidth(600);
        image.setFitHeight(150);
        topBox.getChildren().add(image);

        topBox.setAlignment(Pos.CENTER);
        root.setTop(topBox);

    }

    public BorderPane getRootNode() {
        return root;
    }

    public ArrayList<ShipView> getPlacedShips() {
        return placedShips;
    }

    public Button getBeginGame() {
        return beginGame;
    }

    public GridPane getPlayerBoard() {
        return playerBoard;
    }

    public GridPane getOpponentBoard() {
        return opponentBoard;
    }

    public VBox getShipPane() {
        return shipPane;
    }

    public ShipView getCarrierView() {
        return carrierView;
    }

    public ShipView getBshipView() {
        return bshipView;
    }

    public ShipView getDestroyView() {
        return destroyView;
    }

    public ShipView getPtView() {
        return ptView;
    }

    public ShipView getSubView() {
        return subView;
    }

    public Label getShipRemainingLabel() {
        return shipRemainingLabel;
    }

    public Label getHitsLabel() {
        return hitsLabel;
    }

    public Label getMissesLabel() {
        return missesLabel;
    }

}
