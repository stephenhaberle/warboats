/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package warboats;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import java.util.Date;

/**
 *
 * @author clo006
 */
public class warboatsServer extends Listener {

    static Server server;
    static int udpPort = 27960, tcpPort = 27960;

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
        server.addListener(new warboatsServer());

        System.out.println("Server is operational");
    }

    //this is run when a connection is received
    public void connected(Connection c) {
        System.out.println(
                "Received a connection from " + c.getRemoteAddressTCP().getHostString());
        //create a message packet
        TestCoordinates packetMessage = new TestCoordinates();
        //assign the message text
        packetMessage.message = "Hello friend! The time is: " + new Date().toString();

        //send the message
        c.sendTCP(packetMessage);
        //alternatively, we could do:
        //c.sendUDP(packetMessage);
        //to send over UDP
    }

    //this is run when we receive a packet
    public void received(Connection c, Object p) {
        //we will do nothing here
        //we do not expect to receive any packets
    }

    //this is run when a client has disconnected
    public void disconnected(Connection c) {
        System.out.println("A client disconnected!");
    }

}
