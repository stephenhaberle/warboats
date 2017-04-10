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

    //a boolean value, going to use it to ensure turn based?
    static boolean messageReceived = false;

    public static void run() throws Exception {
        System.out.println("Creating the server...");
        //create the server
        server = new Server();

        //register a packet class
        server.getKryo().register(TestCoordinates.class);
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
                "Received a connection from " + c.getRemoteAddressTCP().getHostString());
        //create a message packet
        TestCoordinates packetMessage = new TestCoordinates();
        //assign the message text
        packetMessage.message = "Connection to server established.";

        //send the message
//        c.sendTCP(packetMessage);
        //alternatively, we could do:
        //c.sendUDP(packetMessage);
        //to send over UDP
    }

    //this is run when we receive a packet
    public void received(Connection c, Object p) {
        if (p instanceof TestCoordinates) {
            //cast it, so we can access the message within
            TestCoordinates packet = (TestCoordinates) p;
            System.out.println(
                    "Received a message from the client: " + packet.message);
            //we have now received the message
            messageReceived = true;

            try {
                Thread.sleep(100);
            } catch (Exception e) {
                System.out.println("SLEEP DIDNT WORK");
            }

//            packet.message = new Date().toString();
//            c.sendTCP(packet);
        }
    }

    //this is run when a client has disconnected
    public void disconnected(Connection c) {
        System.out.println("A client disconnected!");
    }

}
