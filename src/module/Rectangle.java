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
public class Rectangle extends Shape {

    private MyPoint initialPoint;
    private MyPoint finalPoint;

    public Rectangle() {
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
        if ((initialPoint.getX() <= finalPoint.getX()) && (initialPoint.getY() <= finalPoint.getY())) {
            if (this.isFilled()) {
                g.fillRect(initialPoint.getX(), initialPoint.getY(), Math.abs(finalPoint.getX() - initialPoint.getX()), Math.abs(finalPoint.getY() - initialPoint.getY()));
            } else {
                g.drawRect(initialPoint.getX(), initialPoint.getY(), Math.abs(finalPoint.getX() - initialPoint.getX()), Math.abs(finalPoint.getY() - initialPoint.getY()));
            }
        } else if ((initialPoint.getX() >= finalPoint.getX()) && (initialPoint.getY() <= finalPoint.getY())) {
            if (this.isFilled()) {
                g.fillRect(finalPoint.getX(), initialPoint.getY(), Math.abs(finalPoint.getX() - initialPoint.getX()), Math.abs(finalPoint.getY() - initialPoint.getY()));
            } else {
                g.drawRect(finalPoint.getX(), initialPoint.getY(), Math.abs(finalPoint.getX() - initialPoint.getX()), Math.abs(finalPoint.getY() - initialPoint.getY()));
            }
        } else if ((initialPoint.getX() <= finalPoint.getX()) && (initialPoint.getY() >= finalPoint.getY())) {
            if (this.isFilled()) {
                g.fillRect(initialPoint.getX(), finalPoint.getY(), Math.abs(finalPoint.getX() - initialPoint.getX()), Math.abs(finalPoint.getY() - initialPoint.getY()));
            } else {
                g.drawRect(initialPoint.getX(), finalPoint.getY(), Math.abs(finalPoint.getX() - initialPoint.getX()), Math.abs(finalPoint.getY() - initialPoint.getY()));
            }
        } else {
            if (this.isFilled()) {
                g.fillRect(finalPoint.getX(), finalPoint.getY(), Math.abs(finalPoint.getX() - initialPoint.getX()), Math.abs(finalPoint.getY() - initialPoint.getY()));
            } else {
                g.drawRect(finalPoint.getX(), finalPoint.getY(), Math.abs(finalPoint.getX() - initialPoint.getX()), Math.abs(finalPoint.getY() - initialPoint.getY()));
            }
        }
    }

    double distance(int x1, int y1, int x2, int y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }

    @Override
    public boolean contains(MyPoint point) {
        int x1 = initialPoint.getX();
        int y1 = initialPoint.getY();
        int x2 = finalPoint.getX();
        int y2 = finalPoint.getY();
        int a = point.getX();
        int b = point.getY();
        if ((initialPoint.getX() <= finalPoint.getX()) && (initialPoint.getY() <= finalPoint.getY())) {
            if (this.isFilled()) {
                if ((a >= x1 && a <= x2) && (b >= y1 && b <= y2)) {
                    return true;
                }
            }
        } else if ((initialPoint.getX() >= finalPoint.getX()) && (initialPoint.getY() <= finalPoint.getY())) {
            if (this.isFilled()) {
                if ((a >= x2 && a <= x1) && (b >= y1 && b <= y2)) {
                    return true;
                }
            }
        } else if ((initialPoint.getX() <= finalPoint.getX()) && (initialPoint.getY() >= finalPoint.getY())) {
            if (this.isFilled()) {
                if ((a >= x1 && a <= x2) && (b >= y2 && b <= y1)) {
                    return true;
                }
            }
        } else {
            if (this.isFilled()) {
                if ((a >= x2 && a <= x1) && (b >= y2 && b <= y1)) {
                    return true;
                }
            }
        }
        if (!this.isFilled()) {
            if (Math.abs(y1 - b) < ERROR) {
                if (Math.abs((distance(x1, y1, a, b) + distance(a, b, x2, y1)) - distance(x1, y1, x2, y1)) < ERROR) {
                    return true;
                }
            } else if (Math.abs(x1 - a) < ERROR) {
                if (Math.abs((distance(x1, y1, a, b) + distance(a, b, x1, y2)) - distance(x1, y1, x1, y2)) < ERROR) {
                    return true;
                }
            } else if (Math.abs(x2 - a) < ERROR) {
                if (Math.abs((distance(x2, y1, a, b) + distance(a, b, x2, y2)) - distance(x2, y1, x2, y2)) < ERROR) {
                    return true;
                }
            } else if (Math.abs(y2 - b) < ERROR) {
                if (Math.abs((distance(x1, y2, a, b) + distance(a, b, x2, y2)) - distance(x1, y2, x2, y2)) < ERROR) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public Shape copyShape() {
        try {
            Rectangle newRectangle = (Rectangle) this.clone();
            newRectangle.setInitialPoint(new MyPoint(newRectangle.getInitialPoint().getX() + SHIFT, newRectangle.getInitialPoint().getY() + SHIFT));
            newRectangle.setFinalPoint(new MyPoint(newRectangle.getFinalPoint().getX() + SHIFT, newRectangle.getFinalPoint().getY() + SHIFT));
            Color newColor = (Color) this.getColor();
            newRectangle.setColor(newColor);
            return newRectangle;
        } catch (CloneNotSupportedException ex) {
            return null;
        }
    }

    @Override
    public void resizeShape(MyPoint point) {
        this.setFinalPoint(point);
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
                        Rectangle rect = (Rectangle) shape;
                        if (this.getInitialPoint().equals(rect.getInitialPoint()) && this.getFinalPoint().equals(rect.getFinalPoint())) {
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
            Rectangle newRectangle = (Rectangle) this.clone();
            newRectangle.setInitialPoint(new MyPoint(newRectangle.getInitialPoint().getX(), newRectangle.getInitialPoint().getY()));
            newRectangle.setFinalPoint(new MyPoint(newRectangle.getFinalPoint().getX(), newRectangle.getFinalPoint().getY()));
            Color newColor = (Color) this.getColor();
            newRectangle.setColor(newColor);
            return newRectangle;
        } catch (CloneNotSupportedException ex) {
            return null;
        }
    }

    @Override
    public Shape paste() {
        int diffX = this.getDraggedPoint().getX() - this.getPressedPoint().getX();
        int diffY = this.getDraggedPoint().getY() - this.getPressedPoint().getY();
        try {
            Rectangle newRectangle = (Rectangle) this.clone();
            newRectangle.setInitialPoint(new MyPoint(newRectangle.getInitialPoint().getX() + diffX, newRectangle.getInitialPoint().getY() + diffY));
            newRectangle.setFinalPoint(new MyPoint(newRectangle.getFinalPoint().getX() + diffX, newRectangle.getFinalPoint().getY() + diffY));
            Color newColor = (Color) this.getColor();
            newRectangle.setColor(newColor);
            return newRectangle;
        } catch (CloneNotSupportedException ex) {
            return null;
        }    }

}
