/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package module;

/**
 *
 */
public class TriangleFactory extends PolygonFactory {

    @Override
    public Shape getShape(String shapeName) {
        if(shapeName.equals("ISOSCELESTRIANGLE"))
            return new IsoscelesTriangle();
         if(shapeName.equals("RIGHTANGLETRIANGLE"))
            return new RightAngleTriangle();  
         return null;
    }
    
    
}
