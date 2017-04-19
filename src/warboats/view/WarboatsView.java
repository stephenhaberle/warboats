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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import warboats.boats.Boat;
import warboats.model.Marker;
import warboats.model.WarboatsModel;

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
    private VBox boardPane;
    private VBox statsPane;
    private GridPane playerBoard;
    private GridPane opponentBoard;
    private VBox shipPane;
    private Label shipsRemainingTextField;
    private Label hitsTextField;
    private Label missesTextField;
    private ArrayList<ShipNode> placedShipNodes;
    private ArrayList<TextField> carrierCoordinates;
    private ArrayList<TextField> battleshipCoordinates;
    private ArrayList<TextField> destroyerCoordinates;
    private ArrayList<TextField> submarineCoordinates;
    private ArrayList<TextField> patrolBoatCoordinates;
    private Button placeShips;

    public WarboatsView(WarboatsModel theModel) {
        this.theModel = theModel;

        root = new BorderPane();
        root.setPrefSize(800, 800);

        generateMenuBar();
        generateOpponentBoardPane();
        generatePlayerBoardPane();
        generateShipPane();
        root.setCenter(boardPane);
        root.setLeft(shipPane);
        placeShips();
        //
        //PEYTON: Peyton would like to refactor the code so that the method names are more easily understandable
    }

    private void generateShipPane() {
        shipPane = new VBox();
        shipPane.setPrefSize(150, 200);
        shipPane.getChildren().add(new Label("Ships Remaining"));
        shipsRemainingTextField = new Label();
        shipPane.getChildren().add(shipsRemainingTextField);
        shipPane.getChildren().add(new Label("Number of Hits"));

        hitsTextField = new Label();
        shipPane.getChildren().add(hitsTextField);
        shipPane.getChildren().add(new Label("Number of Misses"));
        missesTextField = new Label();
        shipPane.getChildren().add(missesTextField);

        Label shipLabel = new Label("Ships");
        shipLabel.setFont(new Font("Ariel", 32));
        shipPane.getChildren().add(shipLabel);
        shipPane.setPadding(new Insets(10, 10, 10, 10));
        shipLabel.setAlignment(Pos.CENTER);

        carrierCoordinates = new ArrayList<>();
        Label carrier = new Label("Carrier");
        TextField carrierx1 = new TextField("x1");
        TextField carrierx2 = new TextField("x2");
        TextField carriery1 = new TextField("y1");
        TextField carriery2 = new TextField("y2");
        carrierCoordinates.add(carrierx1);
        carrierCoordinates.add(carriery1);
        carrierCoordinates.add(carrierx2);
        carrierCoordinates.add(carriery2);

        battleshipCoordinates = new ArrayList<>();
        Label battleship = new Label("Battleship");
        TextField battlex1 = new TextField("x1");
        TextField battlex2 = new TextField("x2");
        TextField battley1 = new TextField("y1");
        TextField battley2 = new TextField("y2");
        battleshipCoordinates.add(battlex1);
        battleshipCoordinates.add(battley1);
        battleshipCoordinates.add(battlex2);
        battleshipCoordinates.add(battley2);

        destroyerCoordinates = new ArrayList<>();
        Label destroyer = new Label("Destroyer");
        TextField destroyerx1 = new TextField("x1");
        TextField destroyerx2 = new TextField("x2");
        TextField destroyery1 = new TextField("y1");
        TextField destroyery2 = new TextField("y2");
        destroyerCoordinates.add(destroyerx1);
        destroyerCoordinates.add(destroyery1);
        destroyerCoordinates.add(destroyerx2);
        destroyerCoordinates.add(destroyery2);

        submarineCoordinates = new ArrayList<>();
        Label submarine = new Label("Submarine");
        TextField subx1 = new TextField("x1");
        TextField subx2 = new TextField("x2");
        TextField suby1 = new TextField("y1");
        TextField suby2 = new TextField("y2");
        submarineCoordinates.add(subx1);
        submarineCoordinates.add(suby1);
        submarineCoordinates.add(subx2);
        submarineCoordinates.add(suby2);

        patrolBoatCoordinates = new ArrayList<>();
        Label patrol = new Label("Patrol Boat");
        TextField patrolx1 = new TextField("x1");
        TextField patrolx2 = new TextField("x2");
        TextField patroly1 = new TextField("y1");
        TextField patroly2 = new TextField("y2");
        patrolBoatCoordinates.add(patrolx1);
        patrolBoatCoordinates.add(patroly1);
        patrolBoatCoordinates.add(patrolx2);
        patrolBoatCoordinates.add(patroly2);

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

        placeShips = new Button("Place Ships");
        shipPane.getChildren().add(placeShips);
    }

    private void generateOpponentBoardPane() {
        boardPane = new VBox(10);
        opponentBoard = new GridPane();
        opponentBoard.setGridLinesVisible(true);
        opponentBoard.setAlignment(Pos.TOP_CENTER);
        ArrayList<Label> labels = new ArrayList<>();

        //maybe fill with rectangles instead?
        for (int i = 0; i <= 10; i++) {
            opponentBoard.getColumnConstraints().add(new ColumnConstraints(30));
            opponentBoard.getRowConstraints().add(new RowConstraints(30));

            for (int j = 0; j <= 10; j++) {
                if (j == 0) {
                    Label numLabel = new Label(String.format("%d", i));
                    opponentBoard.add(numLabel, j, i);
                    GridPane.setHalignment(numLabel, HPos.CENTER);
                }
                else if (i == 0) {
                    switch (j) {
                        case 0:
                            opponentBoard.add(new Label(" "), j, i);
                            break;

                        case 1:
                            Label aLabel = new Label("A");
                            opponentBoard.add(aLabel, j, i);
                            labels.add(aLabel);
                            break;

                        case 2:
                            Label bLabel = new Label("B");
                            opponentBoard.add(bLabel, j, i);
                            labels.add(bLabel);
                            break;

                        case 3:
                            Label cLabel = new Label("C");
                            opponentBoard.add(cLabel, j, i);
                            labels.add(cLabel);
                            break;

                        case 4:
                            Label dLabel = new Label("D");
                            opponentBoard.add(dLabel, j, i);
                            labels.add(dLabel);
                            break;

                        case 5:
                            Label eLabel = new Label("E");
                            opponentBoard.add(eLabel, j, i);
                            labels.add(eLabel);
                            break;

                        case 6:
                            Label fLabel = new Label("F");
                            opponentBoard.add(fLabel, j, i);
                            labels.add(fLabel);
                            break;

                        case 7:
                            Label gLabel = new Label("G");
                            opponentBoard.add(gLabel, j, i);
                            labels.add(gLabel);
                            break;

                        case 8:
                            Label hLabel = new Label("H");
                            opponentBoard.add(hLabel, j, i);
                            labels.add(hLabel);
                            break;

                        case 9:
                            Label iLabel = new Label("I");
                            opponentBoard.add(iLabel, j, i);
                            labels.add(iLabel);
                            break;

                        case 10:
                            Label jLabel = new Label("J");
                            opponentBoard.add(jLabel, j, i);
                            labels.add(jLabel);
                            break;
                    }

                    for (Label label : labels) {
                        GridPane.setHalignment(label, HPos.CENTER);
                    }
                }
                else {
                    MarkerNode node = new MarkerNode(new Marker(j, i));
                    opponentBoard.add(node, j, i);
                    GridPane.setHalignment(node, HPos.CENTER);
                }
            }
        }
        opponentBoard.setAlignment(Pos.CENTER);
        boardPane.getChildren().add(new Label("Opponent's Fleet"));
        boardPane.getChildren().add(opponentBoard);
        boardPane.setAlignment(Pos.CENTER);
    }

    private void generatePlayerBoardPane() {
        playerBoard = new GridPane();
        playerBoard.setGridLinesVisible(true);
        playerBoard.setAlignment(Pos.TOP_CENTER);
        ArrayList<Label> labels = new ArrayList<>();

        //maybe fill with rectangles instead?
        for (int i = 0; i <= 10; i++) {
            playerBoard.getColumnConstraints().add(new ColumnConstraints(30));
            playerBoard.getRowConstraints().add(new RowConstraints(30));

            for (int j = 0; j <= 10; j++) {
                if (j == 0) {
                    Label numLabel = new Label(String.format("%d", i));
                    playerBoard.add(numLabel, j, i);
                    GridPane.setHalignment(numLabel, HPos.CENTER);
                }
                else if (i == 0) {
                    switch (j) {
                        case 0:
                            playerBoard.add(new Label(" "), j, i);
                            break;

                        case 1:
                            Label aLabel = new Label("A");
                            playerBoard.add(aLabel, j, i);
                            labels.add(aLabel);
                            break;

                        case 2:
                            Label bLabel = new Label("B");
                            playerBoard.add(bLabel, j, i);
                            labels.add(bLabel);
                            break;

                        case 3:
                            Label cLabel = new Label("C");
                            playerBoard.add(cLabel, j, i);
                            labels.add(cLabel);
                            break;

                        case 4:
                            Label dLabel = new Label("D");
                            playerBoard.add(dLabel, j, i);
                            labels.add(dLabel);
                            break;

                        case 5:
                            Label eLabel = new Label("E");
                            playerBoard.add(eLabel, j, i);
                            labels.add(eLabel);
                            break;

                        case 6:
                            Label fLabel = new Label("F");
                            playerBoard.add(fLabel, j, i);
                            labels.add(fLabel);
                            break;

                        case 7:
                            Label gLabel = new Label("G");
                            playerBoard.add(gLabel, j, i);
                            labels.add(gLabel);
                            break;

                        case 8:
                            Label hLabel = new Label("H");
                            playerBoard.add(hLabel, j, i);
                            labels.add(hLabel);
                            break;

                        case 9:
                            Label iLabel = new Label("I");
                            playerBoard.add(iLabel, j, i);
                            labels.add(iLabel);
                            break;

                        case 10:
                            Label jLabel = new Label("J");
                            playerBoard.add(jLabel, j, i);
                            labels.add(jLabel);
                            break;
                    }

                    for (Label label : labels) {
                        GridPane.setHalignment(label, HPos.CENTER);
                    }
                }
                else {
                    MarkerNode node = new MarkerNode(new Marker(j, i));
                    playerBoard.add(node, j, i);
                    GridPane.setHalignment(node, HPos.CENTER);
                }
            }
        }
        playerBoard.setAlignment(Pos.CENTER);
        boardPane.getChildren().add(new Label("Your Fleet"));
        boardPane.getChildren().add(playerBoard);
    }

    private void generateMenuBar() {
        menuBar = new MenuBar();
        fileItem = new Menu("File");
        editItem = new Menu("Edit");
        helpItem = new Menu("Help");
        menuBar.getMenus().addAll(fileItem, editItem, helpItem);
        root.setTop(menuBar);

    }

    private void placeShips() {
        ArrayList<Boat> playersPlacedBoats = this.theModel.getNavy(); //gets players placed ships
        System.out.println(playersPlacedBoats.toString());
        placedShipNodes = new ArrayList<>();

        for (Boat boat : playersPlacedBoats) {
            if (boat.getStartX() == boat.getEndX()) {//boat is vertical. only care about Y values
                if (boat.getStartY() < boat.getEndY()) {//this is normal
                    for (int i = boat.getStartY(); i <= boat.getEndY(); i++) { // i is a Y
                        ShipNode ship = new ShipNode(
                                new Marker(boat.getStartX(), i));
                        playerBoard.add(ship, boat.getStartX(), i);
                        placedShipNodes.add(ship);
                    }
                }
                else { //wonkyness achieved. End > Start
                    for (int i = boat.getEndY(); i <= boat.getStartY(); i++) { //i is a Y
                        ShipNode ship = new ShipNode(
                                new Marker(boat.getStartX(), i));
                        playerBoard.add(ship, boat.getStartX(), i);
                        placedShipNodes.add(ship);
                    }
                }

            }
            else { //boat is horizontal. Only care about X values
                if (boat.getStartX() < boat.getEndX()) {//this is normal
                    for (int i = boat.getStartX(); i <= boat.getEndX(); i++) { //i is an X
                        ShipNode ship = new ShipNode(new Marker(i,
                                                                boat.getStartY()));
                        playerBoard.add(ship, i, boat.getStartY());
                        placedShipNodes.add(ship);
                    }
                }
                else { //wonkyness achieved. End > Start
                    for (int i = boat.getEndX(); i <= boat.getStartX(); i++) { //i is an X
                        ShipNode ship = new ShipNode(new Marker(i,
                                                                boat.getStartY()));
                        playerBoard.add(ship, i, boat.getStartY());
                        placedShipNodes.add(ship);
                    }
                }

            }
        }

    }

    public BorderPane getRootNode() {
        return root;
    }

}
