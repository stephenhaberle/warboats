/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package warboats;

/**
 *
 * @author clo006
 */
public class Warboats {

    static int udpPort = 27960, tcpPort = 27960;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        try {
            System.out.println("Checking if server is online");
            warboatsClient.run();
        } catch (Exception e) {
            System.out.println(
                    "Connection failed. No existing server. Building server.");
            warboatsServer.run();
        }
    }

}
