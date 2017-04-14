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

import warboats.network.Coordinates;
import warboats.model.Board;
import java.util.ArrayList;
import warboats.boats.Battleship;
import warboats.boats.Boat;
import warboats.boats.Carrier;
import warboats.boats.Destroyer;
import warboats.boats.PatrolBoat;
import warboats.boats.Submarine;

/**
 *
 * @author clo006
 */
public class WarboatsModel {

    //Board with all of player's ships on it along with necessary hit indicators
    private Board myBoard;
    //Board keeping track of shots taken at opponent's board (hits/misses)
    private Board opponentBoard;

    //ArrayList of all ships placed on myBoard
    private ArrayList<Boat> navy;
    private Coordinates lastShot;
    private boolean lost = false;

    public WarboatsModel() {

        myBoard = new Board();
        opponentBoard = new Board();

        navy = new ArrayList<>();
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
    public void addShip(int boatType, int x1, int y1, int x2, int y2) {
        switch (boatType) {
            case 1:
                navy.add(new PatrolBoat(x1, y1, x2, y2, this.myBoard));
                break;
            case 2:
                navy.add(new Submarine(x1, y1, x2, y2, this.myBoard));
                break;
            case 3:
                navy.add(new Destroyer(x1, y1, x2, y2, this.myBoard));
                break;
            case 4:
                navy.add(new Battleship(x1, y1, x2, y2, this.myBoard));
                break;
            case 5:
                navy.add(new Carrier(x1, y1, x2, y2, this.myBoard));
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

    }

    /**
     * Checks if all ships in navy have been sunk, if so, sets lost field to
     * true.
     */
    public void checkLoss() {
        int shipsLeft = navy.size();
        for (Boat temp : navy) {
            if (!(temp.isAlive())) {
                shipsLeft--;
            }
        }

        if (shipsLeft == 0) {
            this.lost = true;
        }
    }

    public Board getMyBoard() {
        return myBoard;
    }

    public Board getOpponentBoard() {
        return opponentBoard;
    }

    public Coordinates getLastShot() {
        return lastShot;
    }

    public void setLastShot(Coordinates lastShot) {
        this.lastShot = lastShot;
    }

    public ArrayList<Boat> getNavy() {
        return navy;
    }

    public boolean isLost() {
        return lost;
    }

}
