/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package warboats.view.boatsView;

import java.util.ArrayList;
import javafx.scene.image.ImageView;

/**
 *
 * @author clo006
 */
public class CarrierView extends ShipView {

    public ArrayList<ShipView> nodes;
    public ImageView image;

    public CarrierView() {
        nodes = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            ShipView temp = new ShipView();
            temp.setShipType(5);
            nodes.add(temp);
        }
    }

}
