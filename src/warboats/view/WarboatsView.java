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
* Description: The dopest view that you could ever view. Creates the GUI
*
* ****************************************
 */
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
import warboats.view.boatsView.BattleshipView;
import warboats.view.boatsView.CarrierView;
import warboats.view.boatsView.DestroyerView;
import warboats.view.boatsView.PatrolBoatView;
import warboats.view.boatsView.ShipView;
import warboats.view.boatsView.SubmarineView;

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
    private CarrierView carrierView;
    private BattleshipView bshipView;
    private DestroyerView destroyView;
    private PatrolBoatView ptView;
    private SubmarineView subView;

    /**
     * Constructor for the view of the game. Sets up the entire GUI
     *
     * @param theModel the model that the view will implement
     */
    public WarboatsView(WarboatsModel theModel) {
        this.theModel = theModel;

        root = new BorderPane();
        root.setPrefSize(800, 700);

        placedShips = new ArrayList<>();

        //generate various parts of the GUI
        generateMenuBar();
        generatePlayerBoardPane();
        generateOpponentBoardPane();
        generateShipPane();

        //set alignments
        root.setCenter(boardPane);
        root.setLeft(shipPane);

        //PEYTON: Peyton would like to refactor the code so that the method names are more easily understandable
    }

    /**
     * Creates the statistical information for the game and holds the ship
     * images that are draggable
     */
    private void generateShipPane() {
        //##################
        //### Statistics ###
        //##################
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

        //#####################
        //### drag and drop ###
        //#####################
        carrierView = new CarrierView();
        bshipView = new BattleshipView();
        destroyView = new DestroyerView();
        subView = new SubmarineView();
        ptView = new PatrolBoatView();

        //add ships to an ArrayList to be referenced later
        placedShips.add(ptView);
        placedShips.add(subView);
        placedShips.add(destroyView);
        placedShips.add(bshipView);
        placedShips.add(carrierView);

        //setup images and add them to the view
        Image img = new Image("file:ships/carrierH.png");
        carrierView.image = new ImageView();
        carrierView.image.setImage(img);
        carrierView.image.setFitWidth(200);
        carrierView.image.setFitHeight(40);
        carrierView.image.setId("5");
        shipPane.getChildren().add(carrierView.image);

        //setup images and add them to the view
        img = new Image("file:ships/battleshipH.png");
        bshipView.image = new ImageView();
        bshipView.image.setImage(img);
        bshipView.image.setFitWidth(160);
        bshipView.image.setFitHeight(40);
        bshipView.image.setId("4");
        shipPane.getChildren().add(bshipView.image);

        //setup images and add them to the view
        img = new Image("file:ships/destroyerH.png");
        destroyView.image = new ImageView();
        destroyView.image.setImage(img);
        destroyView.image.setFitWidth(120);
        destroyView.image.setFitHeight(40);
        destroyView.image.setId("3");
        shipPane.getChildren().add(destroyView.image);

        //setup images and add them to the view
        img = new Image("file:ships/subH.png");
        subView.image = new ImageView();
        subView.image.setImage(img);
        subView.image.setFitWidth(120);
        subView.image.setFitHeight(40);
        subView.image.setId("2");
        shipPane.getChildren().add(subView.image);

        //setup images and add them to the view
        img = new Image("file:ships/ptH.png");
        ptView.image = new ImageView();
        ptView.image.setImage(img);
        ptView.image.setFitWidth(80);
        ptView.image.setFitHeight(40);
        ptView.image.setId("1");
        shipPane.getChildren().add(ptView.image);

    }

    /**
     * Generates the opponent's board (right gridpane in the view)
     */
    private void generateOpponentBoardPane() {

        opPane = new VBox(10);

        opponentBoard = generateBoardPane(1); // 1 is for the opponent

        opponentBoard.setAlignment(Pos.CENTER);

        opPane.getChildren().add(new Label("Opponent's Fleet"));
        opPane.getChildren().add(opponentBoard);

        boardPane.getChildren().add(opPane);
    }

    /**
     * Generates the user's board (left gridpane in the view)
     */
    private void generatePlayerBoardPane() {
        boardPane = new HBox(10);
        boardPane.setAlignment(Pos.CENTER);
        playerPane = new VBox(10);

        playerBoard = generateBoardPane(0); // 0 is for the user

        playerBoard.setAlignment(Pos.CENTER);
        playerPane.getChildren().add(new Label("Your Fleet"));
        playerPane.getChildren().add(playerBoard);

        boardPane.getChildren().add(playerPane);
    }

    /**
     * Generates the boarderpane that holds the respective player's gridpane
     * Introduces numbers and letters to index the columns and rows
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

            //set up indexing labels
            for (int j = 0; j <= 10; j++) {
                //set up numbers
                if (j == 0) {
                    Label numLabel = new Label(String.format("%d", i));
                    genBoard.add(numLabel, j, i);
                    GridPane.setHalignment(numLabel, HPos.CENTER);
                }
                //set up letters
                else if (i == 0) {
                    Label tempLabel = new Label(boardLetters[j]);
                    genBoard.add(tempLabel, j, i);
                    labels.add(tempLabel);

                    for (Label label : labels) {
                        GridPane.setHalignment(label, HPos.CENTER);
                    }
                }
                else {
                    if (player == 1) { //opponent
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

    /**
     * Creates the menu bar and aligns the logo appropriately
     */
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

    /**
     * Getter for the rootNode attribute
     *
     * @return a BoarderPane object representing the entire GUI
     */
    public BorderPane getRootNode() {
        return root;
    }

    /**
     * Getter for the placedShips attribute
     *
     * @return ArrayList of ShipViews, representing the images, placements, and
     * types of the ships
     */
    public ArrayList<ShipView> getPlacedShips() {
        return placedShips;
    }

    /**
     * Getter for the beginGame attribute
     *
     * @return Button object that allows the game to be started
     */
    public Button getBeginGame() {
        return beginGame;
    }

    /**
     * Getter for the playerBoard attribute
     *
     * @return GridPane object representing the visual aspect of the player's
     * board
     */
    public GridPane getPlayerBoard() {
        return playerBoard;
    }

    /**
     * Getter for the opponnentBoard attribute
     *
     * @return GridPane object representing the visual aspect of the opponent's
     * board
     */
    public GridPane getOpponentBoard() {
        return opponentBoard;
    }

    /**
     * Getter for the shipPane attribute
     *
     * @return VBox object representing the place in the GUI where the ship
     * images are stored
     */
    public VBox getShipPane() {
        return shipPane;
    }

    /**
     * Getter for the carrierView attribute
     *
     * @return CarrierView object representing the image, placement, and type of
     * the ship
     */
    public CarrierView getCarrierView() {
        return carrierView;
    }

    /**
     * Getter for the bshipView attribute
     *
     * @return BattleshipView object representing the image, placement, and type
     * of the ship
     */
    public BattleshipView getBshipView() {
        return bshipView;
    }

    /**
     * Getter for the destroyView attribute
     *
     * @return DestroyerView object representing the image, placement, and type
     * of the ship
     */
    public DestroyerView getDestroyView() {
        return destroyView;
    }

    /**
     * Getter for the ptView attribute
     *
     * @return PatrolBoatView object representing the image, placement, and type
     * of the ship
     */
    public PatrolBoatView getPtView() {
        return ptView;
    }

    /**
     * Getter for the subView attribute
     *
     * @return SubarineView object representing the image, placement, and type
     * of the ship
     */
    public SubmarineView getSubView() {
        return subView;
    }

    /**
     * Getter for shipsRemainingLabel
     *
     * @return Label representing the number of ships the user has left
     */
    public Label getShipRemainingLabel() {
        return shipRemainingLabel;
    }

    /**
     * Getter for the hitsLabel
     *
     * @return Label representing the number of hits the user has
     */
    public Label getHitsLabel() {
        return hitsLabel;
    }

    /**
     * Getter for missedLabel
     *
     * @return Label representing the number of misses the user has
     */
    public Label getMissesLabel() {
        return missesLabel;
    }

}
