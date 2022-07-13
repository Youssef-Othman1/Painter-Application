/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package module;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 */
public abstract class Shape implements Cloneable  {
    private String shapeName;
    private Color color = Color.BLACK;
    private boolean filled;
    private boolean selected;
    private boolean visible=true;
    private MyPoint pressedPoint;
    private MyPoint draggedPoint;
    public static final int ERROR = 3;
    public static final int SHIFT = 30;
    
    public Shape(Color color, boolean filled) {
        this.color = color;
        this.filled = filled;
    }

    public Shape() {
    }

    public String getShapeName() {
        return shapeName;
    }

    public void setShapeName(String shapeName) {
        this.shapeName = shapeName;
    }
    
    public MyPoint getPressedPoint() {
        return pressedPoint;
    }

    public void setPressedPoint(MyPoint pressedPoint) {
        this.pressedPoint = pressedPoint;
    }

    public MyPoint getDraggedPoint() {
        return draggedPoint;
    }

    public void setDraggedPoint(MyPoint draggedPoint) {
        this.draggedPoint = draggedPoint;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
    
    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public boolean isFilled() {
        return filled;
    }

    public void setFilled(boolean filled) {
        this.filled = filled;
    }
    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
   
    public abstract void draw(Graphics g);
    public abstract boolean contains(MyPoint point);
    public abstract Shape copyShape();
    public abstract Shape copyWithoutShift();
    public abstract void moveShape();
    public abstract void resizeShape(MyPoint point);
    public abstract boolean equals(Shape shape); 
    public abstract Shape paste();
        
}
