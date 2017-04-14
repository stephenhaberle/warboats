/* *****************************************
* CSCI205 - Software Engineering and Design
* Spring 2017 - Final Project
*
* Name: Christian Ouellette, Keller Chambers, Stephen Haberle, Peyton Rumachik
* Date: Apr 10, 2017
* Time: 12:10:11 PM
*
* Project: warboats
* Package: warboats
* File: Warboats
* Description: Main class to test Warboats using console IO
*
* ****************************************
 */
package warboats;

import java.util.Scanner;

/**
 *
 * @author clo006
 */
public class Warboats {

    static WarboatsClient activeClient = null;
    static WarboatsServer activeServer = null;
    private static boolean playerTurn = false;
    private static WarboatsModel theModel;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        try {
            System.out.println("Checking if server is online");
            activeClient = new WarboatsClient();
            activeClient.run();

        } catch (Exception e) {
            System.out.println(
                    "Connection failed. No existing server. Building server.");
            activeServer = new WarboatsServer();
            activeServer.run();
            //sets server's playerTurn as true so host always goes first
            Warboats.togglePlayerTurn();

        }

        theModel = new WarboatsModel();

        theModel.getConsolePlacements();

        System.out.println(theModel.getMyBoard());

        System.out.print("Begin play?");
        Scanner in = new Scanner(System.in);
        in.nextLine();

        //automatic play
        int xCounter = 0;
        int yCounter = 0;
        Integer[] xMoves = {2, 3, 4, 5, 6, 2, 3, 4, 5, 3, 3, 3, 8, 8, 8, 9, 9};
        Integer[] yMoves = {1, 1, 1, 1, 1, 3, 3, 3, 3, 5, 6, 7, 5, 6, 7, 9, 10};

        while (true) {
            //System.out.println("CYCLE ");
            //in.nextLine();
            int x = xMoves[xCounter];
            int y = yMoves[yCounter];
            Coordinates t = new Coordinates();
            t.x = x;
            t.y = y;
            Thread.sleep(1000);

            if (activeServer == null) {
                if (playerTurn) {
                    System.out.println("CLIENT ");
                    theModel.setLastShot(t);
                    activeClient.client.sendTCP(t);
                    Warboats.togglePlayerTurn();
                    Thread.sleep(100);
                    xCounter++;
                    yCounter++;
                    System.out.println("CLIENT SENT");
                }
            }
            else {
                if (playerTurn) {
                    System.out.println("SERVER ");
                    theModel.setLastShot(t);
                    //Currently hardcoded to 1 connection
                    activeServer.server.sendToTCP(1, t);
                    Warboats.togglePlayerTurn();
                    Thread.sleep(100);
                    xCounter++;
                    yCounter++;
                    System.out.println("SERVER SENT");
                }

            }
            if (xCounter == xMoves.length) {
                break;
            }

        }
    }

    public static boolean isPlayerTurn() {
        return playerTurn;
    }

    public static void togglePlayerTurn() {
        Warboats.playerTurn = !(playerTurn);
    }

    public static WarboatsModel getTheModel() {
        return theModel;
    }

}
