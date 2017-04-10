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

import static java.lang.Thread.sleep;

/**
 *
 * @author clo006
 */
public class Warboats {

    static WarboatsClient client;
    static WarboatsServer server;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        try {
            System.out.println("Checking if server is online");
            client = new WarboatsClient();
            client.run();

        } catch (Exception e) {
            System.out.println(
                    "Connection failed. No existing server. Building server.");
            server = new WarboatsServer();
            server.run();
        }

        while (true) {
            System.out.println("= \r");
            sleep(1000);
            System.out.println("% \r");
            sleep(1000);

        }
    }

}
