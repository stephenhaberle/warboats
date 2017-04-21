/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package warboats.view.boatsView;

import java.util.ArrayList;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author clo006
 */
public class CarrierView {

    public ArrayList<ShipNode> nodes;
    public Rectangle view;

    public CarrierView() {
        view = new Rectangle(30, 150, Color.GREY);
        nodes = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            ShipNode temp = new ShipNode();
            temp.setShipType(5);
            nodes.add(temp);
        }
    }

}
