/* *****************************************
* CSCI205 - Software Engineering and Design
* Spring 2017 - Final Project
*
* Name: Christian Ouellette, Keller Chambers, Stephen Haberle, Peyton Rumachik
* Date: Apr 10, 2017
* Time: 12:17:28 PM
*
* Project: warboats
* Package: warboats
* File: WarboatsClient
* Description: Handles running the client side of the program. Connect to
*               available server. Uses kryonet which threads this process.
*
* ****************************************
 */
package warboats;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import java.net.InetAddress;

/**
 *
 * @author clo006
 */
public class WarboatsClient extends Listener {

    static Client client;
    static InetAddress ip;
    static int tcpPort = 27960, udpPort = 27960;

    public static void run() throws Exception {
        System.out.println("Connecting to the server...");
        //create the client
        client = new Client();

        //register the packet object
        client.getKryo().register(Coordinates.class);

        //start the client
        client.start();
        //the client MUST be started before connecting can take place

        //Sets "address" to address of first server found running on UDP port 27960
        ip = client.discoverHost(27960, 5000);

        //connect to the server - wait 5000ms before failing
        client.connect(5000, ip, tcpPort, udpPort);

        //add a listener
        client.addListener(new WarboatsClient());

        System.out.println(
                "CLIENT: Connected! The client program is now waiting for a packet...");

    }

    /**
     * Receives sent objects/packets from specified connection and casts them to
     * appropriate objects. Overridden from Listener class.
     *
     * @param c Connection from which object was received
     * @param p object received from sender
     */
    public void received(Connection c, Object p) {
        //is the received packet the same class as PacketMessage.class?
        if (p instanceof Coordinates) {
            //cast it, so we can access the message within
            Coordinates packet = (Coordinates) p;
            System.out.print("MESSAGE FROM SERVER  ");
            System.out.print("X: " + packet.x);
            System.out.println(" Y: " + packet.y);
            //we have now received the message

            boolean hitIndicator = Warboats.getTheModel().getMyBoard().checkHit(
                    packet.x, packet.y, Warboats.getTheModel());

            c.sendTCP(hitIndicator);

            if (Warboats.getTheModel().isLost()) {
                c.sendTCP(new String("YOU WIN"));
            }

            Warboats.togglePlayerTurn();
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                System.out.println("SLEEP DIDNT WORK");
            }
        }
        //For receiving server connect or win confirmation
        else if (p instanceof String) {
            String packet = (String) p;
            System.out.println(packet);
        }
        //For receiving hit/miss confirmation
        else if (p instanceof Boolean) {
            Boolean packet = (Boolean) p;
            Warboats.getTheModel().getOpponentBoard().hitMiss(
                    packet.booleanValue(),
                    Warboats.getTheModel().getLastShot());
            System.out.println("THEIR BOARD");
            System.out.println(Warboats.getTheModel().getOpponentBoard());
            System.out.println("MY BOARD");
            System.out.println(Warboats.getTheModel().getMyBoard());
        }
    }
}
