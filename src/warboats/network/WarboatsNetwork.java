/* *****************************************
* CSCI205 - Software Engineering and Design
* Spring 2017 - Final Project
*
* Name: Christian Ouellette, Keller Chambers, Stephen Haberle, Peyton Rumachik
* Date: Apr 17, 2017
* Time: 10:39:33 AM
*
* Project: warboats
* Package: warboats.network
* File: WarboatsNetwork
* Description: Creates a connection between the client and server if the two exist
*
* ****************************************
 */
package warboats.network;

import warboats.model.WarboatsModel;

/**
 * Creates the client and server as needed and connects the two
 *
 * @author clo006
 */
public class WarboatsNetwork {

    private static WarboatsClient activeClient = null;
    private static WarboatsServer activeServer = null;

    /**
     * Creates a client if a server already exists, if not a server is created
     * first
     *
     * @throws Exception
     */
    public static void buildNetwork() throws Exception {
        try {
            //check to see if a server exists
            System.out.println("Checking if server is online");
            activeClient = new WarboatsClient();
            activeClient.run();

        } catch (Exception e) { //server DNE and must be created
            System.out.println(
                    "Connection failed. No existing server. Building server.");
            activeServer = new WarboatsServer();
            activeServer.run();
            WarboatsModel.togglePlayerTurn();

        }
    }

    /**
     * Getter for the activeClient attribute
     *
     * @return WarboatsClient object that represents the active client
     */
    public static WarboatsClient getActiveClient() {
        return activeClient;
    }

    /**
     * Getter for the activeServer attribute
     *
     * @return a WarboatsServer object that represents the active server
     */
    public static WarboatsServer getActiveServer() {
        return activeServer;
    }

}
