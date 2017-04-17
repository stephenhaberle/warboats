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
* Description:
*
* ****************************************
 */
package warboats.network;

import warboats.model.WarboatsModel;

/**
 *
 * @author clo006
 */
public class WarboatsNetwork {

    private static WarboatsClient activeClient = null;
    private static WarboatsServer activeServer = null;

    public static void buildNetwork() throws Exception {
        try {
            System.out.println("Checking if server is online");
            activeClient = new WarboatsClient();
            activeClient.run();

        } catch (Exception e) {
            System.out.println(
                    "Connection failed. No existing server. Building server.");
            activeServer = new WarboatsServer();
            activeServer.run();
            WarboatsModel.togglePlayerTurn();

        }
    }

    public static WarboatsClient getActiveClient() {
        return activeClient;
    }

    public static WarboatsServer getActiveServer() {
        return activeServer;
    }

}
