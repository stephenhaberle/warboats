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
import javafx.scene.control.TextField;
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
    private ArrayList<ArrayList<MarkerNode>> placedShipNodes;
    private ArrayList<ShipView> placedShips;
    private ArrayList<ArrayList<TextField>> shipCoordinates;
    private ArrayList<TextField> carrierCoordinates;
    private ArrayList<TextField> battleshipCoordinates;
    private ArrayList<TextField> destroyerCoordinates;
    private ArrayList<TextField> submarineCoordinates;
    private ArrayList<TextField> patrolBoatCoordinates;
    private Button beginGame;
    private CarrierView carrierView;
    private BattleshipView bshipView;
    private DestroyerView destroyView;
    private PatrolBoatView ptView;
    private SubmarineView subView;

    public WarboatsView(WarboatsModel theModel) {
        this.theModel = theModel;

        placedShipNodes = new ArrayList<>();

        root = new BorderPane();
        root.setPrefSize(800, 550);

        shipCoordinates = new ArrayList<>();
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

        /*
        //carrier get coordinates
        carrierCoordinates = new ArrayList<>();
        shipCoordinates.add(carrierCoordinates);
        Label carrier = new Label("Carrier");
        TextField carrierx1 = new TextField("x1");
        TextField carrierx2 = new TextField("x2");
        TextField carriery1 = new TextField("y1");
        TextField carriery2 = new TextField("y2");

        carrierView = new CarrierView();

        carrierView.getInitializedCoordinates().add(carrierx1);
        carrierView.getInitializedCoordinates().add(carriery1);
        carrierView.getInitializedCoordinates().add(carrierx2);
        carrierView.getInitializedCoordinates().add(carriery2);
        placedShips.add(carrierView);

        //battleship get coordinates
        battleshipCoordinates = new ArrayList<>();
        shipCoordinates.add(battleshipCoordinates);
        Label battleship = new Label("Battleship");
        TextField battlex1 = new TextField("x1");
        TextField battlex2 = new TextField("x2");
        TextField battley1 = new TextField("y1");
        TextField battley2 = new TextField("y2");

        bshipView = new BattleshipView();
        bshipView.getInitializedCoordinates().add(battlex1);
        bshipView.getInitializedCoordinates().add(battley1);
        bshipView.getInitializedCoordinates().add(battlex2);
        bshipView.getInitializedCoordinates().add(battley2);
        placedShips.add(bshipView);

        //destroyer get coordinates
        destroyerCoordinates = new ArrayList<>();
        shipCoordinates.add(destroyerCoordinates);
        Label destroyer = new Label("Destroyer");
        TextField destroyerx1 = new TextField("x1");
        TextField destroyerx2 = new TextField("x2");
        TextField destroyery1 = new TextField("y1");
        TextField destroyery2 = new TextField("y2");

        destroyView = new DestroyerView();
        destroyView.getInitializedCoordinates().add(destroyerx1);
        destroyView.getInitializedCoordinates().add(destroyery1);
        destroyView.getInitializedCoordinates().add(destroyerx2);
        destroyView.getInitializedCoordinates().add(destroyery2);
        placedShips.add(destroyView);

        //submarine get coordinates
        submarineCoordinates = new ArrayList<>();
        shipCoordinates.add(submarineCoordinates);
        Label submarine = new Label("Submarine");
        TextField subx1 = new TextField("x1");
        TextField subx2 = new TextField("x2");
        TextField suby1 = new TextField("y1");
        TextField suby2 = new TextField("y2");

        subView = new SubmarineView();
        subView.getInitializedCoordinates().add(subx1);
        subView.getInitializedCoordinates().add(suby1);
        subView.getInitializedCoordinates().add(subx2);
        subView.getInitializedCoordinates().add(suby2);
        placedShips.add(subView);

        //patrol boat get coordinates
        patrolBoatCoordinates = new ArrayList<>();
        shipCoordinates.add(patrolBoatCoordinates);
        Label patrol = new Label("Patrol Boat");
        TextField patrolx1 = new TextField("x1");
        TextField patrolx2 = new TextField("x2");
        TextField patroly1 = new TextField("y1");
        TextField patroly2 = new TextField("y2");

        ptView = new PatrolBoatView();
        ptView.getInitializedCoordinates().add(patrolx1);
        ptView.getInitializedCoordinates().add(patroly1);
        ptView.getInitializedCoordinates().add(patrolx2);
        ptView.getInitializedCoordinates().add(patroly2);
        placedShips.add(ptView);

        for (TextField field : carrierCoordinates) {
            field.setPrefWidth(40);
        }

        for (TextField field : battleshipCoordinates) {
            field.setPrefWidth(40);
        }

        for (TextField field : destroyerCoordinates) {
            field.setPrefWidth(40);
        }

        for (TextField field : submarineCoordinates) {
            field.setPrefWidth(40);
        }
        for (TextField field : patrolBoatCoordinates) {
            field.setPrefWidth(40);
        }

        HBox carrierB = new HBox();
        carrierB.getChildren().addAll(carrierCoordinates);

        HBox battleshipB = new HBox();
        battleshipB.getChildren().addAll(battleshipCoordinates);

        HBox destroyerB = new HBox();
        destroyerB.getChildren().addAll(destroyerCoordinates);

        HBox submarineB = new HBox();
        submarineB.getChildren().addAll(submarineCoordinates);

        HBox patrolB = new HBox();
        patrolB.getChildren().addAll(patrolBoatCoordinates);

        shipPane.getChildren().add(carrier);
        shipPane.getChildren().add(carrierB);

        shipPane.getChildren().add(battleship);
        shipPane.getChildren().add(battleshipB);

        shipPane.getChildren().add(destroyer);
        shipPane.getChildren().add(destroyerB);

        shipPane.getChildren().add(submarine);
        shipPane.getChildren().add(submarineB);

        shipPane.getChildren().add(patrol);
        shipPane.getChildren().add(patrolB);
         */
        beginGame = new Button("Begin Game");
        shipPane.getChildren().add(beginGame);
        //drag and drop
        carrierView = new CarrierView();
        bshipView = new BattleshipView();
        destroyView = new DestroyerView();
        subView = new SubmarineView();
        ptView = new PatrolBoatView();

        placedShips.add(ptView);
        placedShips.add(subView);
        placedShips.add(destroyView);
        placedShips.add(bshipView);
        placedShips.add(carrierView);

        Image img = new Image("file:ships/carrierH.png");
        carrierView.image = new ImageView();
        carrierView.image.setImage(img);
        carrierView.image.setFitWidth(200);
        carrierView.image.setFitHeight(40);
        carrierView.image.setId("5");
        shipPane.getChildren().add(carrierView.image);

        img = new Image("file:ships/battleshipH.png");
        bshipView.image = new ImageView();
        bshipView.image.setImage(img);
        bshipView.image.setFitWidth(160);
        bshipView.image.setFitHeight(40);
        bshipView.image.setId("4");
        shipPane.getChildren().add(bshipView.image);

        img = new Image("file:ships/destroyerH.png");
        destroyView.image = new ImageView();
        destroyView.image.setImage(img);
        destroyView.image.setFitWidth(120);
        destroyView.image.setFitHeight(40);
        destroyView.image.setId("3");
        shipPane.getChildren().add(destroyView.image);

        img = new Image("file:ships/subH.png");
        subView.image = new ImageView();
        subView.image.setImage(img);
        subView.image.setFitWidth(120);
        subView.image.setFitHeight(40);
        subView.image.setId("2");
        shipPane.getChildren().add(subView.image);

        img = new Image("file:ships/ptH.png");
        ptView.image = new ImageView();
        ptView.image.setImage(img);
        ptView.image.setFitWidth(80);
        ptView.image.setFitHeight(40);
        ptView.image.setId("1");
        shipPane.getChildren().add(ptView.image);

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
                        MarkerNode node = new MarkerNode(new Marker(j, i));
                        genBoard.add(node, j, i);
                        GridPane.setHalignment(node, HPos.CENTER);
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
        menuBar = new MenuBar();
        fileItem = new Menu("File");
        editItem = new Menu("Edit");
        helpItem = new Menu("Help");
        menuBar.getMenus().addAll(fileItem, editItem, helpItem);
        root.setTop(menuBar);

    }

    public BorderPane getRootNode() {
        return root;
    }

    public ArrayList<ShipView> getPlacedShips() {
        return placedShips;
    }

    public ArrayList<TextField> getCarrierCoordinates() {
        return carrierCoordinates;
    }

    public ArrayList<TextField> getBattleshipCoordinates() {
        return battleshipCoordinates;
    }

    public ArrayList<TextField> getDestroyerCoordinates() {
        return destroyerCoordinates;
    }

    public ArrayList<TextField> getSubmarineCoordinates() {
        return submarineCoordinates;
    }

    public ArrayList<TextField> getPatrolBoatCoordinates() {
        return patrolBoatCoordinates;
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

    public ArrayList<ArrayList<TextField>> getShipCoordinates() {
        return shipCoordinates;
    }

    public VBox getShipPane() {
        return shipPane;
    }

    public CarrierView getCarrierView() {
        return carrierView;
    }

    public BattleshipView getBshipView() {
        return bshipView;
    }

    public DestroyerView getDestroyView() {
        return destroyView;
    }

    public PatrolBoatView getPtView() {
        return ptView;
    }

    public SubmarineView getSubView() {
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
