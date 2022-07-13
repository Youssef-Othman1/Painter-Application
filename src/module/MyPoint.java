/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package module;

/**
 *
 */
public class MyPoint {
    private int x;
    private int y;
    public MyPoint()
    {
        
    }
    public MyPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    public boolean equals(MyPoint p)
    {
        if (p.getX() == this.getX() && p.getY() == this.getY())
            return true;
        return false;
    }
    
    
}
