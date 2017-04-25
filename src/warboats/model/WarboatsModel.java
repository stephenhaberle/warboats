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
import warboats.network.Rematch;
import warboats.network.WarboatsClient;
import warboats.network.WarboatsServer;

/**
 *
 * @author clo006
 */
public class WarboatsModel {

    //Board with all of player's ships on it along with necessary hit indicators
    private static Board myBoard;
    //Board keeping track of shots taken at opponent's board (hits/misses)
    private static Board opponentBoard;

    private WarboatsClient curClient = null;
    private WarboatsServer curServer = null;
    public static boolean playerTurn = false;

    //ArrayList of all ships placed on myBoard
    private static ArrayList<Boat> navy;
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
    private static boolean playerRematch = false;
    private static boolean opponentRematch = false;
    private static int shipsRemaining;

    public WarboatsModel(WarboatsClient theClient, WarboatsServer theServer) {

        curClient = theClient;
        curServer = theServer;

        myBoard = new Board();
        opponentBoard = new Board();

        navy = new ArrayList<>();
    }

    /**
     * Takes coordinates supplied by player and sends them to opponent.
     *
     * @param x x coordinate (number axis)
     * @param y y coordinate (letter axis)
     */
    public void sendPlayerMove(int x, int y) throws InterruptedException, Exception {

        if (playerReady) {
            Coordinates sendCords = new Coordinates(x, y);

            //program is client
            if (curServer == null) {
                System.out.println("SENDING CLIENT");
                this.setLastShot(sendCords);
                curClient.client.sendTCP(sendCords);
                WarboatsModel.togglePlayerTurn();
                Thread.sleep(100);
                System.out.println("SENT CLIENT");
            }
            //program is server
            else {
                System.out.println("SENDING SERVER");
                this.setLastShot(sendCords);
                curServer.server.sendToTCP(1, sendCords);
                WarboatsModel.togglePlayerTurn();
                Thread.sleep(100);
                System.out.println("SENT SERVER");
            }
        }

    }

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
     * navy.
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

        this.setPlayerReady(true);

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

    public void sendRematch(boolean choice) {
        WarboatsModel.playerRematch = choice;
        Rematch msg = new Rematch(choice);
        if (curServer == null) {
            curClient.client.sendTCP(msg);
        }
        else {
            curServer.server.sendToTCP(1, msg);
        }
    }

    public static Board getMyBoard() {
        return myBoard;
    }

    public static Board getOpponentBoard() {
        return opponentBoard;
    }

    public static Coordinates getLastShot() {
        return lastShot;
    }

    public static void setLastShot(Coordinates lastShot) {
        WarboatsModel.lastShot = lastShot;
    }

    public ArrayList<Boat> getNavy() {
        return navy;
    }

    public static boolean isLost() {
        return lost;
    }

    public static boolean isPlayerTurn() {
        return playerTurn;
    }

    public static void togglePlayerTurn() {
        WarboatsModel.playerTurn = !playerTurn;
    }

    public static boolean isWon() {
        return won;
    }

    public static void setWon(boolean won) {
        WarboatsModel.won = won;
    }

    public Carrier getCarrier() {
        return carrier;
    }

    public Battleship getBattleship() {
        return battleship;
    }

    public Destroyer getDestroyer() {
        return destroyer;
    }

    public Submarine getSubmarine() {
        return submarine;
    }

    public PatrolBoat getPatrolBoat() {
        return patrolBoat;
    }

    public static boolean isPlayerReady() {
        return playerReady;
    }

    public void setPlayerReady(boolean ready) {
        this.playerReady = ready;
    }

    public int getShipsRemaining() {
        return shipsRemaining;
    }

    public static boolean isOpponentReady() {
        return opponentReady;
    }

    public static void setOpponentReady(boolean opponentReady) {
        WarboatsModel.opponentReady = opponentReady;
    }

    public static void setPlayerRematch(boolean playerRematch) {
        WarboatsModel.playerRematch = playerRematch;
    }

    public static boolean isRematch() {
        return playerRematch;
    }

    public static boolean isOpponentRematch() {
        return opponentRematch;
    }

    public static void setOpponentRematch(boolean opponentRematch) {
        WarboatsModel.opponentRematch = opponentRematch;
    }

}
