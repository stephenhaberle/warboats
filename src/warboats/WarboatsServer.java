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
* File: gar
* Description:
*
* ****************************************
 */
package warboats;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

/**
 *
 * @author clo006
 */
public class WarboatsServer extends Listener {

    static Server server;
    static int udpPort = 27960, tcpPort = 27960;

    public static void run() throws Exception {
        System.out.println("Creating the server...");
        //create the server
        server = new Server();

        //register a packet class
        server.getKryo().register(Coordinates.class);
        //we can only send objects as packets if they are registered

        //bind to a port
        server.bind(tcpPort, udpPort);

        //start the server
        server.start();

        //add the listener
        server.addListener(new WarboatsServer());

        System.out.println("Server is operational");
    }

    //this is run when a connection is received
    public void connected(Connection c) {
        System.out.println(
                "\nReceived a connection from " + c.getRemoteAddressTCP().getHostString() + "\n");
        //create a message packet
        String packetMessage = "";
        //assign the message text
        packetMessage = "SERVER: Connection to server established.";

        //send the message
        c.sendTCP(packetMessage);
    }

    //this is run when we receive a packet
    public void received(Connection c, Object p) {
        if (p instanceof Coordinates) {
            //cast it, so we can access the message within
            Coordinates packet = (Coordinates) p;
            System.out.print("MESSAGE FROM CLIENT  ");
            System.out.print("X: " + packet.x);
            System.out.println(" Y: " + packet.y);
            //we have now received the message

            Boolean hitIndicator = Warboats.getTheModel().getMyBoard().checkHit(
                    packet.x, packet.y);

            c.sendTCP(hitIndicator);

            Warboats.togglePlayerTurn();

            try {
                Thread.sleep(100);
            } catch (Exception e) {
                System.out.println("SLEEP DIDNT WORK");
            }

        }

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

    //this is run when a client has disconnected
    public void disconnected(Connection c) {
        System.out.println("A client disconnected!");
    }

}
