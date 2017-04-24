/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package warboats.view.boatsView;

import warboats.boats.Carrier;

/**
 *
 * @author clo006
 */
public class CarrierView extends ShipView {

    private Carrier modelCarrier;

    public CarrierView() {
    }

    public Carrier getModelCarrier() {
        return modelCarrier;
    }

    public void setModelCarrier(Carrier modelCarrier) {
        this.modelCarrier = modelCarrier;
    }

}
