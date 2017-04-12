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
* File: gar
* Description:
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

    //a boolean value, going to use it to ensure turn based?
    static boolean myTurn = false;

    public static void run() throws Exception {
        System.out.println("Connecting to the server...");
        //create the client
        client = new Client();

        //register the packet object
        client.getKryo().register(TestCoordinates.class);

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
                "Connected! The client program is now waiting for a packet...");

    }

    //only method that needs to be implemented from listener class because this is the only one needed
    public void received(Connection c, Object p) {
        //is the received packet the same class as PacketMessage.class?
        if (p instanceof TestCoordinates) {
            //cast it, so we can access the message within
            TestCoordinates packet = (TestCoordinates) p;
            System.out.println(
                    "Received a message from the host: " + packet.message);
            //we have now received the message
            //messageReceived = true;
            try {
                Thread.sleep(100);
            } catch (Exception e) {
                System.out.println("SLEEP DIDNT WORK");
            }
        }
    }
}
