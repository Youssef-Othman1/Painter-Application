/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package module;


/**
 *
 */
public abstract class Triangle extends Shape{
    MyPoint pointA,pointB,pointC;

    public Triangle() {
    }

    public MyPoint getPointA() {
        return pointA;
    }

    public void setPointA(MyPoint pointA) {
        this.pointA = pointA;
    }

    public MyPoint getPointB() {
        return pointB;
    }

    public void setPointB(MyPoint pointB) {
        this.pointB = pointB;
    }

    public MyPoint getPointC() {
        return pointC;
    }

    public void setPointC(MyPoint pointC) {
        this.pointC = pointC;
    }
}
