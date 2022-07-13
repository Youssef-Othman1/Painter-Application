/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package module;

/**
 *
 */
public class ElipticalShapesFactory implements ShapeFactory {

    @Override
    public Shape getShape(String shapeName) {
        if (shapeName == null)
           return null;
       if(shapeName.equals("ELLIPSE"))  
           return new Ellipse();
        if(shapeName.equals("CIRCLE"))  
           return new Circle();
       return null; //To change body of generated methods, choose Tools | Templates.
    }
    
}
