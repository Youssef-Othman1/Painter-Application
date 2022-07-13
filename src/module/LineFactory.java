/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package module;

/**
 *
 */
public class LineFactory implements ShapeFactory {

    @Override
    public Shape getShape(String shapeName) {
       if (shapeName == null)
           return null;
       if(shapeName.equals("LINE"))  
           return new Line();
       return null;
           
   }
    
}
