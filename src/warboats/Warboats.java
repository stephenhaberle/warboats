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
* File: gar
* Description:
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

        while (true) {
            System.out.print("Enter coordinates [x] [y]: ");
            Scanner in = new Scanner(System.in);
            int x = in.nextInt();
            int y = in.nextInt();
            Coordinates t = new Coordinates();
            t.x = x;
            t.y = y;

            if (activeServer == null) {
                if (playerTurn) {
                    theModel.setLastShot(t);
                    activeClient.client.sendTCP(t);
                    Warboats.togglePlayerTurn();
                    System.out.println("THEIR BOARD");
                    System.out.println(theModel.getOpponentBoard());
                    System.out.println("MY BOARD");
                    System.out.println(theModel.getMyBoard());
                }
            }
            else {
                if (playerTurn) {
                    theModel.setLastShot(t);
                    //Currently hardcoded to 1 connection
                    activeServer.server.sendToTCP(1, t);
                    Warboats.togglePlayerTurn();
                    System.out.println("THEIR BOARD");
                    System.out.println(theModel.getOpponentBoard());
                    System.out.println("MY BOARD");
                    System.out.println(theModel.getMyBoard());
                }

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
