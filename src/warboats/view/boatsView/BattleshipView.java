/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package warboats.view.boatsView;

import warboats.boats.Battleship;

/**
 *
 * @author clo006
 */
public class BattleshipView extends ShipView {

    private Battleship modelBattleship;

    public BattleshipView() {
        this.setShipType(4);
    }

    public Battleship getModelBattleship() {
        return modelBattleship;
    }

    public void setModelBattleship(Battleship modelBattleship) {
        this.modelBattleship = modelBattleship;
    }

}
