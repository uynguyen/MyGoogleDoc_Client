/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CustomComponents;

import Actions.Action;

/**
 *
 * @author Vin
 */
public class ActionChangeEvent {

    private Action action;
 public ActionChangeEvent(Actions.Action source) {
       action = source;
    }
    /**
     * @return the action
     */
    public Action getAction() {
        return action;
    }

    /**
     * @param action the action to set
     */
    public void setAction(Action action) {
        this.action = action;
    }
    
}
