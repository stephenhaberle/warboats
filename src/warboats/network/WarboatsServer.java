/* *****************************************
* CSCI205 - Software Engineering and Design
* Spring 2017 - Final Project
*
* Name: Christian Ouellette, Keller Chambers, Stephen Haberle, Peyton Rumachik
* Date: Apr 10, 2017
* Time: 12:13:14 PM
*
* Project: warboats
* Package: warboats
* File: WarboatsServer
* Description: Class that handles running the server side of the program.
*              Uses kryonet which threads this process.
*
* ****************************************
 */
package warboats.network;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import warboats.model.WarboatsModel;

/**
 * Handles connecting to the client and receiving info from the client on the
 * server's side
 *
 * @author clo006
 */
public class WarboatsServer extends Listener {

    //ONLY MADE PUBLIC SO IT CAN BE READ BY WARBOATS PACKAGE, REMEDY THIS AND CHANGE BACK TO DEFAULT
    public static Server server;
    static int udpPort = 27960, tcpPort = 27960;

    public static void run() throws Exception {
        System.out.println("Creating the server...");
        //create the server
        server = new Server();

        //register a packet class
        server.getKryo().register(Coordinates.class);
        server.getKryo().register(GameOver.class);
        server.getKryo().register(BeginGame.class);
        server.getKryo().register(Rematch.class);
        //we can only send objects as packets if they are registered

        //bind to a port
        server.bind(tcpPort, udpPort);

        //start the server
        server.start();

        //add the listener
        server.addListener(new WarboatsServer());

        System.out.println("Server is operational");
    }

    /**
     * Runs when a client connects to a port assigned to the server.
     *
     * @param c connection used by client
     */
    public void connected(Connection c) {
        System.out.println(
                "\nReceived a connection from " + c.getRemoteAddressTCP().getHostString() + "\n");

        //create verification message
        String packetMessage = "SERVER: Connection to server established.";

        //send the message
        c.sendTCP(packetMessage);
    }

    /**
     * Receives sent objects/packets from specified connection and casts them to
     * appropriate objects. Overridden from Listener class.
     *
     * @param c Connection from which object was received
     * @param p object received from sender
     */
    public void received(Connection c, Object p) {
        if (p instanceof Coordinates) {
            //cast it, so we can access the message within
            Coordinates packet = (Coordinates) p;
            System.out.print("MESSAGE FROM CLIENT  ");
            System.out.print("X: " + packet.x);
            System.out.println(" Y: " + packet.y);
            //we have now received the message

            Boolean hitIndicator = WarboatsModel.getMyBoard().checkHit(packet.x,
                                                                       packet.y);

            c.sendTCP(hitIndicator);

            if (WarboatsModel.isLost()) {
                c.sendTCP(new GameOver());
            }

            WarboatsModel.togglePlayerTurn();

            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                System.out.println("SLEEP DIDNT WORK");
            }

        }
        //For receiving win confirmation
        else if (p instanceof String) {
            String packet = (String) p;
            System.out.println(packet);
        }
        //For receiving win confirmation object
        else if (p instanceof GameOver) {
            GameOver packet = (GameOver) p;
            WarboatsModel.setWon(packet.winFlag);
        }

        else if (p instanceof Boolean) {
            Boolean packet = (Boolean) p;
            WarboatsModel.getOpponentBoard().hitMiss(
                    packet.booleanValue(),
                    WarboatsModel.getLastShot());
            System.out.println("THEIR BOARD");
            System.out.println(WarboatsModel.getOpponentBoard());
            System.out.println("MY BOARD");
            System.out.println(WarboatsModel.getMyBoard());
        }
        else if (p instanceof BeginGame) {
            BeginGame packet = (BeginGame) p;
            WarboatsModel.setOpponentReady(packet.isReady);
        }
        else if (p instanceof Rematch) {
            Rematch packet = (Rematch) p;
            WarboatsModel.setOpponentRematch(packet.rematch);
        }
    }

    /**
     * Runs when a client disconnects.
     *
     * @param c connection which the client used to be running on
     */
    public void disconnected(Connection c) {
        System.out.println("A client disconnected!");
    }

}
