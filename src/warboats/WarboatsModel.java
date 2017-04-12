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
* Description:
*
* ****************************************
 */
package warboats;

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

    private Board myBoard;
    private Board opponentBoard;
    private ArrayList<Boat> navy;
    private Coordinates lastShot;

    public WarboatsModel() {

        myBoard = new Board();
        opponentBoard = new Board();

        navy = new ArrayList<>();
    }

    /**
     * Takes in ship type and adds ship to navy
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
     * Only used when playing game in console so players can manually build navy
     */
    public void getConsolePlacements() {
        System.out.println("BUILD YOUR NAVY");
        System.out.println("Carrer(5) coordinates [x1][y1][x2][y2]:");
        //Scanner in = new Scanner(System.in);
        int x1 = 1;//in.nextInt();
        int y1 = 2;//in.nextInt();
        int x2 = 1;//in.nextInt();
        int y2 = 6;//in.nextInt();
        this.addShip(5, x1, y1, x2, y2);

        System.out.println("Battleship(4) coordinates [x1][y1][x2][y2]:");
        x1 = 3;//in.nextInt();
        y1 = 2;//in.nextInt();
        x2 = 3;//in.nextInt();
        y2 = 5;//in.nextInt();
        this.addShip(4, x1, y1, x2, y2);

        System.out.println("Destroyer(3) coordinates [x1][y1][x2][y2]:");
        x1 = 5;//in.nextInt();
        y1 = 3;//in.nextInt();
        x2 = 7;//in.nextInt();
        y2 = 3;//in.nextInt();
        this.addShip(3, x1, y1, x2, y2);

        System.out.println("Submarine(3) coordinates [x1][y1][x2][y2]:");
        x1 = 5;//in.nextInt();
        y1 = 8;//in.nextInt();
        x2 = 7;//in.nextInt();
        y2 = 8;//in.nextInt();
        this.addShip(2, x1, y1, x2, y2);

        System.out.println("PT(2) coordinates [x1][y1][x2][y2]:");
        x1 = 9;//in.nextInt();
        y1 = 9;//in.nextInt();
        x2 = 10;//in.nextInt();
        y2 = 9;//in.nextInt();
        this.addShip(1, x1, y1, x2, y2);

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

}
