/* *****************************************
* CSCI205 - Software Engineering and Design
* Spring 2017 - Final Project
*
* Name: Christian Ouellette, Keller Chambers, Stephen Haberle, Peyton Rumachik
* Date: Apr 12, 2017
* Time: 10:37:26 AM
*
* Project: warboats
* Package: warboats
* File: WarboatsModel
* Description: Handles creation of game boards and ships and stores them in fields.
*
* ****************************************
 */
package warboats.model;

import java.util.ArrayList;
import warboats.boats.Battleship;
import warboats.boats.Boat;
import warboats.boats.Carrier;
import warboats.boats.Destroyer;
import warboats.boats.PatrolBoat;
import warboats.boats.Submarine;
import warboats.network.BeginGame;
import warboats.network.Coordinates;
import warboats.network.WarboatsClient;
import warboats.network.WarboatsServer;

/**
 * The main model that creates the user and opponent boards, handles adding and
 * updating ship placement within the backend, and allows for shots to be sent
 * to the opponent
 *
 * @author clo006
 */
public class WarboatsModel {

    private static Board myBoard;//Board with all of player's ships on it along with necessary hit indicators
    private static Board opponentBoard; //Board keeping track of shots taken at opponent's board (hits/misses)

    private WarboatsClient curClient = null;
    private WarboatsServer curServer = null;
    public static boolean playerTurn = false; // boolean that keeps track of whether it is the user's turn or not

    //ArrayList of all ships placed on myBoard
    private static ArrayList<Boat> navy;

    //the boat types
    private Carrier carrier;
    private Battleship battleship;
    private Destroyer destroyer;
    private Submarine submarine;
    private PatrolBoat patrolBoat;

    private static Coordinates lastShot;
    private static boolean lost = false;
    private static boolean won = false;
    private static boolean playerReady = false;
    private static boolean opponentReady = false;
    private static int shipsRemaining;

    /**
     *
     * @param theClient The user's client
     * @param theServer The server that connects the clients together
     */
    public WarboatsModel(WarboatsClient theClient, WarboatsServer theServer) {

        curClient = theClient;
        curServer = theServer;

        myBoard = new Board();
        opponentBoard = new Board();

        navy = new ArrayList<>(); //the fleet
    }

    /**
     * Takes coordinates supplied by player and sends them to opponent.
     *
     * @param x x coordinate (number axis)
     * @param y y coordinate (letter axis)
     *
     * @throws java.lang.InterruptedException
     * @throws java.lang.Exception
     */
    public void sendPlayerMove(int x, int y) throws InterruptedException, Exception {

        if (playerReady) {
            Coordinates sendCords = new Coordinates(x, y);

            //program is client
            if (curServer == null) {
                System.out.println("SENDING CLIENT");
                this.setLastShot(sendCords);
                curClient.client.sendTCP(sendCords); // sends shot coordinates to opponent
                WarboatsModel.togglePlayerTurn(); // let opponent make next move
                Thread.sleep(100);
                System.out.println("SENT CLIENT");
            }
            //program is server
            else {
                System.out.println("SENDING SERVER");
                this.setLastShot(sendCords);
                curServer.server.sendToTCP(1, sendCords);
                WarboatsModel.togglePlayerTurn(); // let user make next move
                Thread.sleep(100);
                System.out.println("SENT SERVER");
            }
        }

    }

    /**
     * Sends a boolean value to the client/server indicating that they are ready
     * to begin
     */
    public void signalBeginGame() {
        BeginGame indicator = new BeginGame();
        if (curServer == null) {
            curClient.client.sendTCP(indicator);
        }
        else {
            curServer.server.sendToTCP(1, indicator);
        }
    }

    /**
     * Takes in ship type and adds ship to navy.
     *
     * @param boatType indicates type of ship to be built
     * @param x1 starting x coordinate
     * @param y1 starting y coordinate
     * @param x2 ending x coordinate
     * @param y2 ending y coordinate
     *
     * PT = 1, sub = 2, breaker = 3, battle = 4, carrier = 5
     */
    public void addShip(int boatType, int x1, int y1, int x2, int y2) throws ClassCastException {
        switch (boatType) {
            case 1:
                patrolBoat = new PatrolBoat(x1, y1, x2, y2, this.myBoard);
                navy.add(patrolBoat);
                break;
            case 2:
                submarine = new Submarine(x1, y1, x2, y2, this.myBoard);
                navy.add(submarine);
                break;
            case 3:
                destroyer = new Destroyer(x1, y1, x2, y2, this.myBoard);
                navy.add(destroyer);
                break;
            case 4:
                battleship = new Battleship(x1, y1, x2, y2, this.myBoard);
                navy.add(battleship);
                break;
            case 5:
                carrier = new Carrier(x1, y1, x2, y2, this.myBoard);
                navy.add(carrier);
                break;
            default:
                System.out.println("INVALID BOAT TYPE NUMBER");
        }

        shipsRemaining = navy.size();
    }

    /**
     * Updates the current positions of the ships in case of rotation
     *
     * @param boatType indicates type of ship to be built
     * @param x1 starting x coordinate
     * @param y1 starting y coordinate
     * @param x2 ending x coordinate
     * @param y2 ending y coordinate
     *
     * @throws ClassCastException
     */
    public void updateShip(int boatType, int x1, int y1, int x2, int y2) throws ClassCastException {
        switch (boatType) {
            case 1:
                patrolBoat.updatePosition(x1, y1, x2, y2);
                break;
            case 2:
                submarine.updatePosition(x1, y1, x2, y2);
                break;
            case 3:
                destroyer.updatePosition(x1, y1, x2, y2);
                break;
            case 4:
                battleship.updatePosition(x1, y1, x2, y2);
                break;
            case 5:
                carrier.updatePosition(x1, y1, x2, y2);
                break;
            default:
                System.out.println("INVALID BOAT TYPE NUMBER");
        }
    }

    /**
     * Only used when playing game in console so players can manually build
     * navy, THIS METHOD IS NOT USED ELSEWHERE.
     *
     * Can be setup to auto-construct navy for testing purposes.
     */
    public void getConsolePlacements() {
        System.out.println("BUILD YOUR NAVY");
        System.out.println("Carrer(5) coordinates [x1][y1][x2][y2]:");
        //Scanner in = new Scanner(System.in);
        int x1 = 2;//in.nextInt();
        int y1 = 1;//in.nextInt();
        int x2 = 6;//in.nextInt();
        int y2 = 1;//in.nextInt();
        this.addShip(5, x1, y1, x2, y2);

        System.out.println("Battleship(4) coordinates [x1][y1][x2][y2]:");
        x1 = 2;//in.nextInt();
        y1 = 3;//in.nextInt();
        x2 = 5;//in.nextInt();
        y2 = 3;//in.nextInt();
        this.addShip(4, x1, y1, x2, y2);

        System.out.println("Destroyer(3) coordinates [x1][y1][x2][y2]:");
        x1 = 3;//in.nextInt();
        y1 = 5;//in.nextInt();
        x2 = 3;//in.nextInt();
        y2 = 7;//in.nextInt();
        this.addShip(3, x1, y1, x2, y2);

        System.out.println("Submarine(3) coordinates [x1][y1][x2][y2]:");
        x1 = 8;//in.nextInt();
        y1 = 5;//in.nextInt();
        x2 = 8;//in.nextInt();
        y2 = 7;//in.nextInt();
        this.addShip(2, x1, y1, x2, y2);

        System.out.println("PT(2) coordinates [x1][y1][x2][y2]:");
        x1 = 9;//in.nextInt();
        y1 = 9;//in.nextInt();
        x2 = 9;//in.nextInt();
        y2 = 10;//in.nextInt();
        this.addShip(1, x1, y1, x2, y2);

        this.togglePlay();

    }

    /**
     * Checks if all ships in navy have been sunk, if so, sets lost field to
     * true.
     */
    public static void checkLoss() {
        int shipsLeft = navy.size();
        for (Boat temp : navy) {
            if (!(temp.isAlive())) {
                shipsLeft--;
            }
        }

        shipsRemaining = shipsLeft;

        if (shipsRemaining == 0) {
            lost = true;
        }
    }

    /**
     * Getter for myBoard attribute
     *
     * @return Board object representing the user's gridpane and ship placements
     */
    public static Board getMyBoard() {
        return myBoard;
    }

    /**
     * Getter for opponentBoard attribute
     *
     * @return Board object representing the opponent's gridpane and ship
     * placements
     */
    public static Board getOpponentBoard() {
        return opponentBoard;
    }

    /**
     * Getter for lastShot attribute
     *
     * @return Coordinates object representing the tile on the gridpane that was
     * last shot at
     */
    public static Coordinates getLastShot() {
        return lastShot;
    }

    /**
     * Setter for lastShot attribute
     *
     * @param lastShot Coordinates object representing the tile on the gridpane
     * that was last shot at
     */
    public static void setLastShot(Coordinates lastShot) {
        WarboatsModel.lastShot = lastShot;
    }

    /**
     * Getter for navy attribute
     *
     * @return an ArrayList of Boat objects representing the fleet placed on a
     * gridpane
     */
    public ArrayList<Boat> getNavy() {
        return navy;
    }

    /**
     * Getter for isLost attribute
     *
     * @return a boolean representing whether the player has lost or not.
     */
    public static boolean isLost() {
        return lost;
    }

    /**
     * Getter for isPlayerTurn attribute
     *
     * @return a boolean representing whether or not it is time for the player
     * to shoot
     */
    public static boolean isPlayerTurn() {
        return playerTurn;
    }

    /**
     * Flips the boolean isPlayerTurn attribute
     */
    public static void togglePlayerTurn() {
        WarboatsModel.playerTurn = !playerTurn;
    }

    /**
     * Getter for won attribute
     *
     * @return a boolean representing whether the player has won or not.
     */
    public static boolean isWon() {
        return won;
    }

    /**
     * Setter for won attribute
     *
     * @param won a boolean value representing whether the player has won or
     * not.
     */
    public static void setWon(boolean won) {
        WarboatsModel.won = won;
    }

    /**
     * Getter for the Carrier object in one's navy
     *
     * @return carrier object
     */
    public Carrier getCarrier() {
        return carrier;
    }

    /**
     * Getter for the battleship object in one's navy
     *
     * @return battleship object
     */
    public Battleship getBattleship() {
        return battleship;
    }

    /**
     * Getter for the destroyer object in one's navy
     *
     * @return destroyer object
     */
    public Destroyer getDestroyer() {
        return destroyer;
    }

    /**
     * Getter for the submarine object in one's navy
     *
     * @return Submarine object
     */
    public Submarine getSubmarine() {
        return submarine;
    }

    /**
     * Getter for the patrol boat object in one's navy
     *
     * @return Patrol Boat object
     */
    public PatrolBoat getPatrolBoat() {
        return patrolBoat;
    }

    /**
     * Getter for isPlayerReady attribute
     *
     * @return a boolean value representing whether or not the user is ready to
     * begin playing
     */
    public static boolean isPlayerReady() {
        return playerReady;
    }

    /**
     * Flips the playerReady boolean attribute
     */
    public void togglePlay() {
        this.playerReady = !playerReady;
    }

    /**
     * Getter for the shipsRemaining attribute
     *
     * @return int representing the number of ships left in your navy/fleet
     */
    public int getShipsRemaining() {
        return shipsRemaining;
    }

    /**
     * Getter for opponentReady attribute
     *
     * @return boolean value representing whether or not the opponent is ready
     * to begin playing
     */
    public static boolean isOpponentReady() {
        return opponentReady;
    }

    /**
     * Setter for opponentReady attribute
     *
     * @param opponentReady boolean value representing whether or not the
     * opponent is ready to begin playing
     */
    public static void setOpponentReady(boolean opponentReady) {
        WarboatsModel.opponentReady = opponentReady;
    }

}
