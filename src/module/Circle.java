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
public class Circle extends Shape {

    private MyPoint initialPoint;
    private MyPoint finalPoint;

    public Circle() {
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
                g.fillOval(initialPoint.getX(), initialPoint.getY(), Math.abs(finalPoint.getX() - initialPoint.getX()), Math.abs(finalPoint.getX() - initialPoint.getX()));
            } else {
                g.drawOval(initialPoint.getX(), initialPoint.getY(), Math.abs(finalPoint.getX() - initialPoint.getX()), Math.abs(finalPoint.getX() - initialPoint.getX()));
            }
        } else if ((initialPoint.getX() >= finalPoint.getX()) && (initialPoint.getY() <= finalPoint.getY())) {
            if (this.isFilled()) {
                g.fillOval(finalPoint.getX(), initialPoint.getY(), Math.abs(finalPoint.getX() - initialPoint.getX()), Math.abs(finalPoint.getX() - initialPoint.getX()));
            } else {
                g.drawOval(finalPoint.getX(), initialPoint.getY(), Math.abs(finalPoint.getX() - initialPoint.getX()), Math.abs(finalPoint.getX() - initialPoint.getX()));
            }
        } else if ((initialPoint.getX() <= finalPoint.getX()) && (initialPoint.getY() >= finalPoint.getY())) {
            if (this.isFilled()) {
                g.fillOval(initialPoint.getX(), finalPoint.getY(), Math.abs(finalPoint.getY() - initialPoint.getY()), Math.abs(finalPoint.getY() - initialPoint.getY()));
            } else {
                g.drawOval(initialPoint.getX(), finalPoint.getY(), Math.abs(finalPoint.getY() - initialPoint.getY()), Math.abs(finalPoint.getY() - initialPoint.getY()));
            }
        } else {
            int differenceInX = initialPoint.getY() - finalPoint.getY();
            int differenceInY = initialPoint.getX() - finalPoint.getX();
            int diagonal = (int) Math.sqrt(differenceInY * differenceInY + differenceInX * differenceInX);
            int startX = (int) (initialPoint.getX() - diagonal * Math.cos(Math.PI / 4));
            int startY = (int) (initialPoint.getY() - diagonal * Math.cos(Math.PI / 4));
            if (this.isFilled()) {
                g.fillOval(startX, startY, Math.abs(startX - initialPoint.getX()), Math.abs(startX - initialPoint.getX()));
            } else {
                g.drawOval(startX, startY, Math.abs(startX - initialPoint.getX()), Math.abs(startX - initialPoint.getX()));
            }
        }
    }

    double distance(MyPoint a, MyPoint b) {
        return Math.sqrt((a.getX() - b.getX()) * (a.getX() - b.getX()) + (a.getY() - b.getY()) * (a.getY() - b.getY()));
    }

    @Override
    public boolean contains(MyPoint point) {
        int x1 = initialPoint.getX();
        int y1 = initialPoint.getY();
        MyPoint center = new MyPoint();
        int radius;
        int x2, y2;
        int a = point.getX();
        int b = point.getY();
        int side;
        if ((initialPoint.getX() <= finalPoint.getX()) && (initialPoint.getY() <= finalPoint.getY())) {
            side = Math.abs(finalPoint.getX() - initialPoint.getX());
            x2 = x1 + side;
            y2 = y1 + side;

            radius = Math.abs(x2 - x1) / 2;
            center.setX(x1 + radius);
            center.setY(y1 + radius);
            if (this.isFilled()) {
                if ((a >= x1 && a <= x2) && (b >= y1 && b <= y2) && (distance(center, point) <= radius)) {
                    return true;
                }
            } else {
                if (Math.abs(distance(center, point) - radius) < ERROR) {
                    return true;
                }
            }
        } else if ((initialPoint.getX() >= finalPoint.getX()) && (initialPoint.getY() <= finalPoint.getY())) {

            side = Math.abs(finalPoint.getX() - initialPoint.getX());
            x2 = x1 - side;
            y2 = y1 + side;
            radius = Math.abs(x2 - x1) / 2;
            center.setX(x1 - radius);
            center.setY(y1 + radius);
            if (this.isFilled()) {
                if ((a >= x2 && a <= x1) && (b >= y1 && b <= y2) && (distance(center, point) <= radius)) {
                    return true;
                }
            } else {
                if (Math.abs(distance(center, point) - radius) < ERROR) {
                    return true;
                }
            }
        } else if ((initialPoint.getX() <= finalPoint.getX()) && (initialPoint.getY() >= finalPoint.getY())) {
            side = Math.abs(finalPoint.getY() - initialPoint.getY());
            x2 = x1 + side;
            y2 = y1 - side;
            radius = Math.abs(x2 - x1) / 2;
            center.setX(x1 + radius);
            center.setY(y1 - radius);
            if (this.isFilled()) {
                if ((a >= x1 && a <= x2) && (b >= y2 && b <= y1) && (distance(center, point) <= radius)) {
                    return true;
                }
            } else {
                if (Math.abs(distance(center, point) - radius) < ERROR) {
                    return true;
                }
            }
        } else {
            int differenceInX = initialPoint.getY() - finalPoint.getY();
            int differenceInY = initialPoint.getX() - finalPoint.getX();
            int diagonal = (int) Math.sqrt(differenceInY * differenceInY + differenceInX * differenceInX);
            int startX = (int) (initialPoint.getX() - diagonal * Math.cos(Math.PI / 4));
            int startY = (int) (initialPoint.getY() - diagonal * Math.cos(Math.PI / 4));
            side = Math.abs(startX - initialPoint.getX());
            x2 = x1 - side;
            y2 = y1 - side;
            radius = Math.abs(x2 - x1) / 2;
            center.setX(x1 - radius);
            center.setY(y1 - radius);
            if (this.isFilled()) {
                if ((a >= x2 && a <= x1) && (b >= y2 && b <= y1) && (distance(center, point) <= radius)) {
                    return true;
                }
            } else {
                if (Math.abs(distance(center, point) - radius) < ERROR) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public Shape copyShape() {
        try {
            Circle newCircle = (Circle) this.clone();
            newCircle.setInitialPoint(new MyPoint(newCircle.getInitialPoint().getX() + SHIFT, newCircle.getInitialPoint().getY() + SHIFT));
            newCircle.setFinalPoint(new MyPoint(newCircle.getFinalPoint().getX() + SHIFT, newCircle.getFinalPoint().getY() + SHIFT));
            Color newColor = (Color) this.getColor();
            newCircle.setColor(newColor);
            return newCircle;
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
                        Circle s = (Circle) shape;
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
            Circle newCircle = (Circle) this.clone();
            newCircle.setInitialPoint(new MyPoint(newCircle.getInitialPoint().getX(), newCircle.getInitialPoint().getY()));
            newCircle.setFinalPoint(new MyPoint(newCircle.getFinalPoint().getX(), newCircle.getFinalPoint().getY()));
            Color newColor = (Color) this.getColor();
            newCircle.setColor(newColor);
            return newCircle;
        } catch (CloneNotSupportedException ex) {
            return null;
        }
    }

    @Override
    public Shape paste() {
            int diffX = this.getDraggedPoint().getX() - this.getPressedPoint().getX();
            int diffY = this.getDraggedPoint().getY() - this.getPressedPoint().getY();
            try {
            Circle s = (Circle) this.clone();
            s.setInitialPoint(new MyPoint(s.getInitialPoint().getX() + diffX, s.getInitialPoint().getY() + diffY));
            s.setFinalPoint(new MyPoint(s.getFinalPoint().getX() + diffX, s.getFinalPoint().getY() + diffY));
            Color newColor = (Color) this.getColor();
            s.setColor(newColor);
            return s;
        } catch (CloneNotSupportedException ex) {
            return null;
        }     }
}
