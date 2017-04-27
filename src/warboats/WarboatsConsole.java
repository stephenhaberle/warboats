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
* File: WarboatsConsole
* Description: Main class to test WarboatsConsole using console IO
*
* ****************************************
 */
package warboats;

import warboats.model.WarboatsModel;
import warboats.network.WarboatsClient;
import warboats.network.WarboatsServer;

/**
 * Main console class that sets up a server between self and dummy computer
 * Tests the basic functionality of the game (taking shots, win/loss, server,
 * etc...)
 *
 * @author clo006
 */
public class WarboatsConsole {

    static WarboatsClient activeClient = null;
    static WarboatsServer activeServer = null;
    private static WarboatsModel theModel;

    /**
     * @param args the command line arguments. Does nothing
     * @throws java.lang.Exception if there is an issue with connecting the
     * client to the server
     */
    public static void main(String[] args) throws Exception {
        try { //checks to see if there is a server already running and tries to connect.
            System.out.println("Checking if server is online");
            activeClient = new WarboatsClient();
            activeClient.run();

        } catch (Exception e) { // creates a new server
            System.out.println(
                    "Connection failed. No existing server. Building server.");
            activeServer = new WarboatsServer();
            activeServer.run();
            WarboatsModel.togglePlayerTurn();

        }

        //does not begin playing until client connected
        if (activeServer != null) {
            while (activeServer.server.getConnections().length == 0) {
                Thread.sleep(50);
            }
        }

        theModel = new WarboatsModel(activeClient, activeServer);

        theModel.getConsolePlacements();

        System.out.println(theModel.getMyBoard());

        /*
        System.out.print("Begin play?");
        Scanner in = new Scanner(System.in);
        in.nextLine();
         */
        //TODO: MOVE PLAY/MOVE LOGIC TO MAIN CLASS IN ModelViewController
        //automatic play
        int xCounter = 0;
        int yCounter = 0;
        Integer[] xMoves = {2, 3, 4, 5, 6, 2, 3, 4, 5, 3, 3, 3, 8, 8, 8, 9, 9};
        Integer[] yMoves = {1, 1, 1, 1, 1, 3, 3, 3, 3, 5, 6, 7, 5, 6, 7, 9, 10};

        while (true) {//tests whether one player can win and the opther can lose
            try {
                if (theModel.isLost()) {
                    throw new Exception("YOU LOSE, NO MORE TURNS");
                }
                else if (theModel.isWon()) {
                    throw new Exception("YOU WIN, NO MORE TURNS");
                }
                else if (WarboatsModel.isPlayerTurn()) {//advances the game if nobody has won and it is players turn
                    int x = xMoves[xCounter];
                    int y = yMoves[yCounter];
                    theModel.sendPlayerMove(x, y);
                    Thread.sleep(1000);
                    xCounter++;
                    yCounter++;
                }
                else { // wait for opponent to complete move
                    Thread.sleep(100);
                }

                /*
                if (xCounter == xMoves.length) {
                    break;
                }
                 */
            } catch (Exception e) { // else the game must be over
                System.out.println("GAME OVER");
                System.out.println(e);
                break;
            }

        }
        /*
        System.out.println("new game?");
        in.nextLine();
         */
    }

    /**
     * Getter for the model of the console game
     *
     * @return a WarboatsModel object
     */
    public static WarboatsModel getTheModel() {
        return theModel;
    }

}
