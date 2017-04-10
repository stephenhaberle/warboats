/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
public class warboatsClient extends Listener {

    static Client client;
    static String ip = "localhost";
    static int tcpPort = 27960, udpPort = 27960;

    //a boolean value
    static boolean messageReceived = false;

    public static void run() throws Exception {
        System.out.println("Connecting to the server...");
        //create the client
        client = new Client();

        //register the packet object
        client.getKryo().register(TestCoordinates.class);

        //start the client
        client.start();
        //the client MUST be started before connecting can take place

        //connect to the server - wait 5000ms before failing
        client.connect(5000, InetAddress.getLocalHost(), tcpPort, udpPort);

        //add a listener
        client.addListener(new warboatsClient());

        System.out.println(
                "Connected! The client program is now waiting for a packet...\n");

        //this is here to stop the program from closing before we received a messsage
        while (!messageReceived) {
            Thread.sleep(100);
        }
        System.out.println("Client will now exit.");
        System.exit(0);
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
            messageReceived = true;
        }
    }
}
