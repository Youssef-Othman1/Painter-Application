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
public class Line extends Shape {

    private MyPoint initialPoint;
    private MyPoint finalPoint;

    public Line() {

    }

    public Line(MyPoint initialPoint, MyPoint finalPoint, Color color, boolean isFilled) {
        super(color, isFilled);
        this.initialPoint = initialPoint;
        this.finalPoint = finalPoint;
    }

    public MyPoint getInitialPoint() {
        return initialPoint;
    }

    public void setInitialPoint(MyPoint initialPoint) {
        this.initialPoint = initialPoint;
    }

    public MyPoint getFinalPoint() {
        return finalPoint;
    }

    public void setFinalPoint(MyPoint finalPoint) {
        this.finalPoint = finalPoint;
    }

    @Override
    public void draw(Graphics g) {
        g.drawLine(initialPoint.getX(), initialPoint.getY(), finalPoint.getX(), finalPoint.getY());
    }

    double distance(MyPoint a, MyPoint b) {
        return Math.sqrt((a.getX() - b.getX()) * (a.getX() - b.getX()) + (a.getY() - b.getY()) * (a.getY() - b.getY()));
    }

    @Override
    public boolean contains(MyPoint point) {
        if (Math.abs((distance(initialPoint, point) + distance(finalPoint, point)) - distance(initialPoint, finalPoint)) < ERROR) {
            return true;
        }
        return false;
    }

    @Override
    public Shape copyShape() {
        try {
            Line newLine = (Line) this.clone();
            newLine.setInitialPoint(new MyPoint(newLine.getInitialPoint().getX() + SHIFT, newLine.getInitialPoint().getY() + SHIFT));
            newLine.setFinalPoint(new MyPoint(newLine.getFinalPoint().getX() + SHIFT, newLine.getFinalPoint().getY() + SHIFT));
            Color newColor = (Color) this.getColor();
            newLine.setColor(newColor);
            return newLine;
        } catch (CloneNotSupportedException ex) {
            return null;
        }
    }

    @Override
    public void resizeShape(MyPoint point) {
        this.finalPoint = point;
    }

    @Override
    public void moveShape() {
        int x1 = this.getInitialPoint().getX();
        int y1 = this.getInitialPoint().getY();
        int x2 = this.getFinalPoint().getX();
        int y2 = this.getFinalPoint().getY();
        int diffX = this.getDraggedPoint().getX() - this.getPressedPoint().getX();
        int diffY = this.getDraggedPoint().getY() - this.getPressedPoint().getY();
        this.setInitialPoint(new MyPoint(x1 + diffX, y1 + diffY));
        this.setFinalPoint(new MyPoint(x2 + diffX, y2 + diffY));
        this.setPressedPoint(new MyPoint(this.getPressedPoint().getX() + diffX, this.getPressedPoint().getY() + diffY));
    }

    @Override
    public boolean equals(Shape shape) {
        if (this.getShapeName().equals(shape.getShapeName())) {
            if (shape.getColor() != null && this.getColor() != null) {
                if (this.getColor().equals(shape.getColor())) {
                    if ((this.isFilled() == shape.isFilled()) && (this.isSelected() == shape.isSelected()) && (this.isVisible() == shape.isVisible())) {
                        Line s = (Line) shape;
                        if (this.getInitialPoint().equals(s.getInitialPoint()) && this.getFinalPoint().equals(s.getFinalPoint())) {
                            
                            return true;
                        }

                    }
                }
            }
        }
        return false;
    }

    @Override
    public Shape copyWithoutShift() {
        try {
            Line newLine = (Line) this.clone();
            newLine.setInitialPoint(new MyPoint(newLine.getInitialPoint().getX(), newLine.getInitialPoint().getY()));
            newLine.setFinalPoint(new MyPoint(newLine.getFinalPoint().getX(), newLine.getFinalPoint().getY()));
            Color newColor = (Color) this.getColor();
            newLine.setColor(newColor);
            return newLine;
        } catch (CloneNotSupportedException ex) {
            return null;
        }
    }

    @Override
    public Shape paste() {
        int diffX = this.getDraggedPoint().getX() - this.getPressedPoint().getX();
        int diffY = this.getDraggedPoint().getY() - this.getPressedPoint().getY();
        try {
            Line newLine = (Line) this.clone();
            newLine.setInitialPoint(new MyPoint(newLine.getInitialPoint().getX() + diffX, newLine.getInitialPoint().getY() + diffY));
            newLine.setFinalPoint(new MyPoint(newLine.getFinalPoint().getX() + diffX, newLine.getFinalPoint().getY() + diffY));
            Color newColor = (Color) this.getColor();
            newLine.setColor(newColor);
            return newLine;
        } catch (CloneNotSupportedException ex) {
            return null;
        }    }

}
