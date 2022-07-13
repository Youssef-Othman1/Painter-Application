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
public class IsoscelesTriangle extends Triangle {

    @Override
    public void draw(Graphics g) {
        int xPoints[] = {pointA.getX(), pointB.getX(), pointA.getX() - (pointB.getX() - pointA.getX())};
        int yPoints[] = {pointA.getY(), pointB.getY(), pointB.getY()};
        if (this.isFilled()) {
            g.fillPolygon(xPoints, yPoints, 3);
        } else {
            g.drawPolygon(xPoints, yPoints, 3);
        }
    }

    float area(int x1, int y1, int x2, int y2, int x3, int y3) {
        return (float) Math.abs((x1 * (y2 - y3) + x2 * (y3 - y1) + x3 * (y1 - y2)) / 2.0);
    }

    double distance(MyPoint a, MyPoint b) {
        return Math.sqrt((a.getX() - b.getX()) * (a.getX() - b.getX()) + (a.getY() - b.getY()) * (a.getY() - b.getY()));
    }

    @Override
    public boolean contains(MyPoint point) {
        MyPoint p = new MyPoint(pointA.getX() - (pointB.getX() - pointA.getX()), pointB.getY());
        double side1 = distance(pointA, pointB);
        double side2 = distance(pointA, p);
        double side3 = distance(pointB, p);
        double d1 = distance(point, pointA);
        double d2 = distance(point, pointB);
        double d3 = distance(point, p);
        if (isFilled()) {
            float A = area(pointA.getX(), pointA.getY(), pointB.getX(), pointB.getY(), pointA.getX() - (pointB.getX() - pointA.getX()), pointB.getY());
            float A1 = area(point.getX(), point.getY(), pointB.getX(), pointB.getY(), pointA.getX() - (pointB.getX() - pointA.getX()), pointB.getY());
            float A2 = area(pointA.getX(), pointA.getY(), point.getX(), point.getY(), pointA.getX() - (pointB.getX() - pointA.getX()), pointB.getY());
            float A3 = area(pointA.getX(), pointA.getY(), pointB.getX(), pointB.getY(), point.getX(), point.getY());
            return (A == A1 + A2 + A3);
        } else if (Math.abs(d1 - (side2 - d3)) <= ERROR || Math.abs(d1 - (side2 - d3)) <= ERROR || Math.abs(d1 - (side1 - d2)) <= ERROR || Math.abs(d2 - (side3 - d3)) <= ERROR) {
            return true;
        } else {
            return false;
        }

    }

    @Override
    public Shape copyShape() {
        try {
            IsoscelesTriangle newIsoscelesTriangle = (IsoscelesTriangle) this.clone();
            newIsoscelesTriangle.setPointA(new MyPoint(newIsoscelesTriangle.getPointA().getX() + SHIFT, newIsoscelesTriangle.getPointA().getY() + SHIFT));
            newIsoscelesTriangle.setPointB(new MyPoint(newIsoscelesTriangle.getPointB().getX() + SHIFT, newIsoscelesTriangle.getPointB().getY() + SHIFT));
            Color newColor = (Color) this.getColor();
            newIsoscelesTriangle.setColor(newColor);
            return newIsoscelesTriangle;
        } catch (CloneNotSupportedException ex) {
            return null;
        }
    }

    @Override
    public void resizeShape(MyPoint point) {
        this.setPointB(point);
    }

    @Override
    public void moveShape() {
        int x1 = this.getPointA().getX();
        int y1 = this.getPointA().getY();
        int x2 = this.getPointB().getX();
        int y2 = this.getPointB().getY();
        int diffX = this.getDraggedPoint().getX() - this.getPressedPoint().getX();
        int diffY = this.getDraggedPoint().getY() - this.getPressedPoint().getY();
        this.setPointA(new MyPoint(x1 + diffX, y1 + diffY));
        this.setPointB(new MyPoint(x2 + diffX, y2 + diffY));
        this.setPressedPoint(new MyPoint(this.getPressedPoint().getX() + diffX, this.getPressedPoint().getY() + diffY));
    }

    @Override
    public boolean equals(Shape shape) {
        if (shape.getShapeName().equals(this.getShapeName())) {
            if (shape.getColor() != null && this.getColor() != null) {
                if (this.getColor().equals(shape.getColor())) {
                    if ((this.isFilled() == shape.isFilled()) && (this.isSelected() == shape.isSelected()) && (this.isVisible() == shape.isVisible())) {
                        IsoscelesTriangle s = (IsoscelesTriangle) shape;
                        if (this.getPointA().equals(s.getPointA()) && this.getPointB().equals(s.getPointB()) && this.getPointC().equals(s.getPointC())) {
                           
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
            IsoscelesTriangle newIsoscelesTriangle = (IsoscelesTriangle) this.clone();
            newIsoscelesTriangle.setPointA(new MyPoint(newIsoscelesTriangle.getPointA().getX(), newIsoscelesTriangle.getPointA().getY()));
            newIsoscelesTriangle.setPointB(new MyPoint(newIsoscelesTriangle.getPointB().getX(), newIsoscelesTriangle.getPointB().getY()));
            Color newColor = (Color) this.getColor();
            newIsoscelesTriangle.setColor(newColor);
            return newIsoscelesTriangle;
        } catch (CloneNotSupportedException ex) {
            return null;
        }
    }

    @Override
    public Shape paste() {
        int diffX = this.getDraggedPoint().getX() - this.getPressedPoint().getX();
        int diffY = this.getDraggedPoint().getY() - this.getPressedPoint().getY();
        try {
            IsoscelesTriangle s = (IsoscelesTriangle) this.clone();
            s.setPointA(new MyPoint(s.getPointA().getX() + diffX, s.getPointA().getY() + diffY));
            s.setPointB(new MyPoint(s.getPointB().getX() + diffX, s.getPointB().getY() + diffY));
            Color newColor = (Color) this.getColor();
            s.setColor(newColor);
            return s;
        } catch (CloneNotSupportedException ex) {
            return null;
        }      }
}
