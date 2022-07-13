/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package module;

/**
 *
 */
public class PolygonFactory implements ShapeFactory {

    public PolygonFactory() {
    }

    
    @Override
    public Shape getShape(String shapeName) {
        if(shapeName==null)
            return null;
        if(shapeName.equals("SQUARE"))
            return new Square();
        if(shapeName.equals("RECTANGLE"))
            return new Rectangle();
         if(shapeName.equals("ISOSCELESTRIANGLE") || shapeName.equals("RIGHTANGLETRIANGLE"))
         {
             TriangleFactory factory= new TriangleFactory(); 
             return factory.getShape(shapeName);
         }
        return null;
    }
    
}
