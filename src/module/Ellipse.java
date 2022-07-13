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
public class Ellipse extends Shape {

    MyPoint initialPoint;
    MyPoint finalPoint;
    
    public Ellipse() {
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
        
        if ((initialPoint.getX() <= finalPoint.getX()) && (initialPoint.getY() <= finalPoint.getY()))
        {
            if (this.isFilled())
                 g.fillOval(initialPoint.getX(), initialPoint.getY(), Math.abs(finalPoint.getX()-initialPoint.getX()), Math.abs(finalPoint.getY()-initialPoint.getY()));
            else
                g.drawOval(initialPoint.getX(), initialPoint.getY(), Math.abs(finalPoint.getX()-initialPoint.getX()), Math.abs(finalPoint.getY()-initialPoint.getY()));
        }
        else if((initialPoint.getX() >= finalPoint.getX()) && (initialPoint.getY() <= finalPoint.getY()))
        {
            if (this.isFilled())
                g.fillOval(finalPoint.getX(), initialPoint.getY(),  Math.abs(finalPoint.getX()-initialPoint.getX()), Math.abs(finalPoint.getY()-initialPoint.getY()));
            else
                g.drawOval(finalPoint.getX(), initialPoint.getY(),  Math.abs(finalPoint.getX()-initialPoint.getX()), Math.abs(finalPoint.getY()-initialPoint.getY()));
        } 
        else if((initialPoint.getX() <= finalPoint.getX()) && (initialPoint.getY() >= finalPoint.getY())) 
        {
            if (this.isFilled())
                 g.fillOval(initialPoint.getX(), finalPoint.getY(),  Math.abs(finalPoint.getX()-initialPoint.getX()), Math.abs(finalPoint.getY()-initialPoint.getY()));
            else
                 g.drawOval(initialPoint.getX(), finalPoint.getY(),  Math.abs(finalPoint.getX()-initialPoint.getX()), Math.abs(finalPoint.getY()-initialPoint.getY()));
        }     
        else 
        {
            if(this.isFilled())
                g.fillOval(finalPoint.getX(), finalPoint.getY(),  Math.abs(finalPoint.getX()-initialPoint.getX()), Math.abs(finalPoint.getY()-initialPoint.getY()));
            else
                g.drawOval(finalPoint.getX(), finalPoint.getY(),  Math.abs(finalPoint.getX()-initialPoint.getX()), Math.abs(finalPoint.getY()-initialPoint.getY()));
        }   
    }

    @Override
    public boolean contains(MyPoint point) {
        int x1 = initialPoint.getX();
        int y1 = initialPoint.getY();
        int x2 = finalPoint.getX();
        int y2 = finalPoint.getY();
        int a = point.getX();
        int b = point.getY();
        int width = Math.abs(x2-x1);
        int height = Math.abs(y2-y1);
        if ((initialPoint.getX() <= finalPoint.getX()) && (initialPoint.getY() <= finalPoint.getY()))
        {
            int h = x1 + (width / 2);
            int k = y1 + (height / 2);
            double p = Math.pow(a - h, 2) / ((width/2) * (width/2)) + Math.pow(b - k, 2) / ((height/2) * (height/2));                        
            if (this.isFilled())
            {
                if((a>=x1 && a<=x2) && (b>=y1&& b<=y2) && p < 1)
                     return true;
            }      
            else
            {
                if (p < 1.1 && p > 0.9)
                    return true;
            }
        }
        else if((initialPoint.getX() >= finalPoint.getX()) && (initialPoint.getY() <= finalPoint.getY()))
        {
            
            int h = x1 - (width / 2);
            int k = y1 + (height / 2);
            double p = Math.pow(a - h, 2) / ((width/2) * (width/2)) + Math.pow(b - k, 2) / ((height/2) * (height/2)); 
            if (this.isFilled())
            {
                if((a>=x2 && a<=x1) && (b>=y1&& b<=y2) && p < 1)
                     return true;    
            }
            else
            {
                if (p < 1.1 && p > 0.9)
                    return true;            
            }
                 
        } 
        else if((initialPoint.getX() <= finalPoint.getX()) && (initialPoint.getY() >= finalPoint.getY())) 
        {
            int h = x1 + (width / 2);
            int k = y1 - (height / 2);
            double p = Math.pow(a - h, 2) / ((width/2) * (width/2)) + Math.pow(b - k, 2) / ((height/2) * (height/2)); 
            if (this.isFilled())
            { 
                if((a>=x1 && a<=x2) && (b>=y2&& b<=y1) && p < 1)
                     return true;
            }
            else
           {
             if (p < 1.1 && p > 0.9)
                    return true;  
           }  
        }     
        else
        {
            int h = x1 - (width / 2);
            int k = y1 - (height / 2);
            double p = Math.pow(a - h, 2) / ((width/2) * (width/2)) + Math.pow(b - k, 2) / ((height/2) * (height/2)); 
           if (this.isFilled())
           { 
               if((a>=x2 && a<=x1) && (b>=y2&& b<=y1) && p < 1)
                     return true; 
           }
           else
           {
             if (p < 1.1 && p > 0.9)
                    return true;  
           }               
      }    
       return false;
    }

    @Override
    public Shape copyShape() {
         try {
           Ellipse newEllipse = (Ellipse) this.clone();
           newEllipse.setInitialPoint(new MyPoint(newEllipse.getInitialPoint().getX()+SHIFT, newEllipse.getInitialPoint().getY()+SHIFT));
           newEllipse.setFinalPoint(new MyPoint(newEllipse.getFinalPoint().getX()+SHIFT, newEllipse.getFinalPoint().getY()+SHIFT));
           Color newColor = (Color) this.getColor();
           newEllipse.setColor(newColor);
           return newEllipse;
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
        this.setFinalPoint(new MyPoint(x2+diffX, y2+diffY));
        this.setPressedPoint(new MyPoint(this.getPressedPoint().getX()+diffX, this.getPressedPoint().getY()+diffY));    }

    @Override
    public boolean equals(Shape shape) {
        if(this.getShapeName().equals(shape.getShapeName()))
        {
            if (shape.getColor()!=null && this.getColor() !=null)
            {
                if (this.getColor().equals(shape.getColor()))
                {
                    if ((this.isFilled()==shape.isFilled()) && (this.isSelected() == shape.isSelected()) && (this.isVisible() == shape.isVisible()))  
                    {
                        Ellipse  s= (Ellipse)shape;
                        if (this.getInitialPoint().equals(s.getInitialPoint()) && this.getFinalPoint().equals(s.getFinalPoint()))
                        {
             
                            return true;
                        }
                       
                    }
                }
            }  
        }
        return false;    }

    @Override
    public Shape copyWithoutShift() {
   try {
           Ellipse newEllipse = (Ellipse) this.clone();
           newEllipse.setInitialPoint(new MyPoint(newEllipse.getInitialPoint().getX(), newEllipse.getInitialPoint().getY()));
           newEllipse.setFinalPoint(new MyPoint(newEllipse.getFinalPoint().getX(), newEllipse.getFinalPoint().getY()));
           Color newColor = (Color) this.getColor();
           newEllipse.setColor(newColor);
           return newEllipse;
        } catch (CloneNotSupportedException ex) {
            return null;
        }    }

    @Override
    public Shape paste() {
            int diffX = this.getDraggedPoint().getX() - this.getPressedPoint().getX();
            int diffY = this.getDraggedPoint().getY() - this.getPressedPoint().getY();
            try {
            Ellipse s = (Ellipse) this.clone();
            s.setInitialPoint(new MyPoint(s.getInitialPoint().getX() + diffX, s.getInitialPoint().getY() + diffY));
            s.setFinalPoint(new MyPoint(s.getFinalPoint().getX() + diffX, s.getFinalPoint().getY() + diffY));
            Color newColor = (Color) this.getColor();
            s.setColor(newColor);
            return s;
        } catch (CloneNotSupportedException ex) {
            return null;
        }     }
    
}
