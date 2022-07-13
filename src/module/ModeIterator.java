/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package module;

import GUI.Drawer;
import java.util.Iterator;

/**
 *
 */
public class ModeIterator implements Iterator {
    private int index = 0;
    private String[] modes = {"DRAW", "COPY", "RESIZE", "MOVE", "DELETE","CHANGECOLOR","COPYTOPASTE" ,"PASTE"};
    
       @Override
    public boolean hasNext() {
        if (index < modes.length) {
            return true;
        }
        return false;
    }

    @Override
    public Object next() {
        if (index == modes.length) {
            return null;
        } else {
            return modes[index++];
        }
    }
    
    public void setMode(Drawer drawer, String selectedMode)
    {
        while (this.hasNext())
        {
            String current =(String) this.next();
            if (current.equals(selectedMode))
                drawer.setMode(selectedMode);
        }
    }
    
}
