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
* Description:
*
* ****************************************
 */
//fill board with custom marker object
package warboats.view;

import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
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

        //PEYTON: What we need to do is loop through the "navy" arraylist in the model and for each ship place a ShipNode
        //at each index where there is a shit. The ShipNode extends from Rectangle so it'll basically just placing a rectangle over
        //the empty label that is in the grid right now.\
        //makrers should be placed on top lof shipnodes
        //
        //CHRIS: I know we talked about filling the grid with markers, but I don't think that makes sense because markers
        //are only used to indicate if there is a hit, miss, or ship on that index. I suppose we can make three new classes
        //that extend from MarkerNode: HitNode, MissNode, and EmptyNode, but I'm not sure it's necessary. Thoughts?
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

    }

    private void generateOpponentBoardPane() {
        boardPane = new VBox(10);
        opponentBoard = new GridPane();
        opponentBoard.setGridLinesVisible(true);
        opponentBoard.setAlignment(Pos.TOP_CENTER);

        //maybe fill with rectangles instead?
        for (int i = 0; i <= 10; i++) {
            opponentBoard.getColumnConstraints().add(
                    new ColumnConstraints(
                            30));
            opponentBoard.getRowConstraints().add(new RowConstraints(30));
            for (int j = 0; j <= 10; j++) {
                if (j == 0) {
                    opponentBoard.add(new Label(String.format("%d", i)), j, i);
                }
                else if (i == 0) {
                    switch (j) {
                        case 0:
                            opponentBoard.add(new Label(" "), j, i);
                            break;
                        case 1:
                            opponentBoard.add(new Label("A"), j, i);
                            break;
                        case 2:
                            opponentBoard.add(new Label("B"), j, i);
                            break;
                        case 3:
                            opponentBoard.add(new Label("C"), j, i);
                            break;
                        case 4:
                            opponentBoard.add(new Label("D"), j, i);
                            break;
                        case 5:
                            opponentBoard.add(new Label("E"), j, i);
                            break;
                        case 6:
                            opponentBoard.add(new Label("F"), j, i);
                            break;
                        case 7:
                            opponentBoard.add(new Label("G"), j, i);
                            break;
                        case 8:
                            opponentBoard.add(new Label("H"), j, i);
                            break;
                        case 9:
                            opponentBoard.add(new Label("I"), j, i);

                            break;
                        case 10:
                            opponentBoard.add(new Label("J"), j, i);
                            break;
                    }
                }
                else {
                    opponentBoard.add(new Label(" "), j, i);
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

        //maybe fill with rectangles instead?
        for (int i = 0; i <= 10; i++) {
            playerBoard.getColumnConstraints().add(
                    new ColumnConstraints(
                            30));
            playerBoard.getRowConstraints().add(new RowConstraints(30));
            for (int j = 0; j <= 10; j++) {
                if (j == 0) {
                    playerBoard.add(new Label(String.format("%d", i)), j, i);
                }
                else if (i == 0) {
                    switch (j) {
                        case 0:
                            playerBoard.add(new Label(" "), j, i);
                            break;
                        case 1:
                            playerBoard.add(new Label("A"), j, i);
                            break;
                        case 2:
                            playerBoard.add(new Label("B"), j, i);
                            break;
                        case 3:
                            playerBoard.add(new Label("C"), j, i);
                            break;
                        case 4:
                            playerBoard.add(new Label("D"), j, i);
                            break;
                        case 5:
                            playerBoard.add(new Label("E"), j, i);
                            break;
                        case 6:
                            playerBoard.add(new Label("F"), j, i);
                            break;
                        case 7:
                            playerBoard.add(new Label("G"), j, i);
                            break;
                        case 8:
                            playerBoard.add(new Label("H"), j, i);
                            break;
                        case 9:
                            playerBoard.add(new Label("I"), j, i);

                            break;
                        case 10:
                            playerBoard.add(new Label("J"), j, i);
                            break;
                    }
                }
                else {
                    playerBoard.add(new MarkerNode(new Marker(j, i)), j, i);
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
                if (boat.getStartX() > boat.getEndX()) {//this is normal
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
