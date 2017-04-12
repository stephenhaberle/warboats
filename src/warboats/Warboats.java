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
import warboats.boats.Boat;

/**
 *
 * @author clo006
 */
public class Warboats {

    static WarboatsClient activeClient = null;
    static WarboatsServer activeServer = null;
    private boolean playerTurn = false;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        try {
            System.out.println("Checking if server is online");
            activeClient = new WarboatsClient();
            activeClient.run();

            //indicates this instance is associated with activeClient
            //this.playerType = true;
        } catch (Exception e) {
            System.out.println(
                    "Connection failed. No existing server. Building server.");
            activeServer = new WarboatsServer();
            activeServer.run();

            //indicates this instance is associated with activeServer
            //this.playerType = false;
        }

        Board test = new Board();
        System.out.println(test);

        Boat testBoat = new Boat(2, 2, 3, 5, test);
        System.out.println(test);

        while (true) {
            System.out.print("Enter some shit: ");
            Scanner in = new Scanner(System.in);
            String temp = in.nextLine();
            TestCoordinates t = new TestCoordinates();
            t.message = temp;

            if (activeServer == null) {
                activeClient.client.sendTCP(t);
            }
            else {
                //Currently hardcoded to 1 connection
                activeServer.server.sendToTCP(1, t);

            }
        }
    }

    public boolean isPlayerTurn() {
        return playerTurn;
    }

    public void togglePlayerTurn() {
        this.playerTurn = !(playerTurn);
    }

}
