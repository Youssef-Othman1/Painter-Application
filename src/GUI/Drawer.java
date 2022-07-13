/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import javax.swing.JPanel;
import module.*;

/**
 *
 */
public class Drawer extends JPanel implements MouseListener, MouseMotionListener {

    private String shapeName;
    private int selectedShape = -1;
    private Color color = Color.BLACK;
    private boolean filled = false;
    private boolean selectionMode = false;
    private String mode;
    private Shape oldShape, newShape;
    private ArrayList<Shape> drawings = new ArrayList<>();

    public Drawer() {
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    public int getSelectedShape() {
        return selectedShape;
    }

    public void setSelectedShape(int selectedShape) {
        this.selectedShape = selectedShape;
    }

    public boolean isSelectionMode() {
        return selectionMode;
    }

    public void setSelectionMode(boolean selectionMode) {
        this.selectionMode = selectionMode;
    }

    public boolean isFilled() {
        return filled;
    }

    public void setFilled(boolean filled) {
        this.filled = filled;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public String getShapeName() {
        return shapeName;
    }

    public void setShapeName(String shapeName) {
        this.shapeName = shapeName;
    }

    public ArrayList<Shape> getDrawings() {
        return drawings;
    }

    public void setDrawings(ArrayList<Shape> drawings) {
        this.drawings = drawings;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int i;
        for (i = 0; i < drawings.size(); i++) {
            if (drawings.get(i).isVisible()) {
                g.setColor(drawings.get(i).getColor());
                drawings.get(i).draw(g);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent me) {
    }

    @Override
    public void mousePressed(MouseEvent me) {
        if (selectionMode == false) {
            MyPoint current = new MyPoint(me.getX(), me.getY());
            Shape shape = null;
            if (getShapeName() != null) {
                if (getShapeName().equals("LINE")) {
                    ShapeFactory s = new LineFactory();
                    shape = s.getShape("LINE");
                    Line line = (Line) shape;
                    line.setShapeName("LINE");
                    line.setInitialPoint(current);
                    line.setFinalPoint(current);
                    drawings.add(line);
                } else if (getShapeName().equals("RECTANGLE")) {
                    ShapeFactory s = new PolygonFactory();
                    shape = s.getShape("RECTANGLE");
                    Rectangle rectangle = (Rectangle) shape;
                    rectangle.setShapeName("RECTANGLE");
                    rectangle.setInitialPoint(current);
                    rectangle.setFinalPoint(current);
                    drawings.add(rectangle);
                } else if (getShapeName().equals("SQUARE")) {
                    ShapeFactory s = new PolygonFactory();
                    shape = s.getShape("SQUARE");
                    Square square = (Square) shape;
                    square.setShapeName("SQUARE");
                    square.setInitialPoint(current);
                    square.setFinalPoint(current);
                    drawings.add(square);
                } else if (getShapeName().equals("RIGHTANGLETRIANGLE")) {
                    ShapeFactory s = new PolygonFactory();
                    shape = s.getShape("RIGHTANGLETRIANGLE");
                    RightAngleTriangle triangle = (RightAngleTriangle) shape;
                    triangle.setPointA(current);
                    triangle.setShapeName("RIGHTANGLETRIANGLE");
                    triangle.setPointB(current);
                    triangle.setPointC(current);
                    drawings.add(triangle);
                } else if (getShapeName().equals("ISOSCELESTRIANGLE")) {
                    ShapeFactory s = new PolygonFactory();
                    shape = s.getShape("ISOSCELESTRIANGLE");
                    IsoscelesTriangle triangle = (IsoscelesTriangle) shape;
                    triangle.setPointA(current);
                    triangle.setShapeName("ISOSCELESTRIANGLE");
                    triangle.setPointB(current);
                    triangle.setPointC(current);
                    drawings.add(triangle);
                } else if (getShapeName().equals("CIRCLE")) {
                    ShapeFactory s = new ElipticalShapesFactory();
                    shape = s.getShape("CIRCLE");
                    Circle circle = (Circle) shape;
                    circle.setInitialPoint(current);
                    circle.setShapeName("CIRCLE");
                    circle.setFinalPoint(current);
                    drawings.add(circle);
                } else if (getShapeName().equals("ELLIPSE")) {
                    ShapeFactory s = new ElipticalShapesFactory();
                    shape = s.getShape("ELLIPSE");
                    Ellipse ellipse = (Ellipse) shape;
                    ellipse.setShapeName("ELLIPSE");
                    ellipse.setInitialPoint(current);
                    ellipse.setFinalPoint(current);
                    drawings.add(ellipse);
                }
                shape.setColor(color);
                shape.setFilled(filled);
                repaint();
            }
        } else {
            if (selectionMode == true && !drawings.isEmpty()) {
                MyPoint current = new MyPoint(me.getX(), me.getY());
                for (int i = drawings.size() - 1; i >= 0 && !(this.getMode().equals("PASTE")); i--) {
                    if (drawings.get(i).contains(current)) {
                        if (this.getMode().equals("MOVE")) {
                            oldShape = drawings.get(i).copyWithoutShift();
                            this.setSelectedShape(i);
                            drawings.get(i).setPressedPoint(current);
                        } else if (this.getMode().equals("DELETE")) {
                            oldShape = drawings.get(i).copyWithoutShift();
                            drawings.get(i).setVisible(false);
                            newShape = drawings.get(i).copyWithoutShift();
                        } else if (this.getMode().equals("RESIZE")) {
                            oldShape = drawings.get(i).copyWithoutShift();
                            this.setSelectedShape(i);
                            drawings.get(i).resizeShape(current);
                        } else if (this.getMode().equals("COPY")) {
                            drawings.add(drawings.get(i).copyShape());
                        }
                         else if (this.getMode().equals("CHANGECOLOR")) {
                            oldShape = drawings.get(i).copyWithoutShift();
                            drawings.get(i).setColor(color);
                            newShape = drawings.get(i).copyWithoutShift();
                        }
                         else if(this.getMode().equals("COPYTOPASTE"))
                         { 
                            this.setSelectedShape(i);
                            drawings.get(i).setPressedPoint(current);
                         }
                        
                        repaint();
                        break;
                    }
                }
                if(this.getMode().equals("PASTE"))
                         {
                             if (selectedShape != -1){
                            drawings.get(selectedShape).setDraggedPoint(current);
                            drawings.add(drawings.get(selectedShape).paste());
                            repaint();
                             }
                         }
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        if (this.getMode() == null)
        {
            return;
        }
        if (this.getMode().equals("COPYTOPASTE"))
        {
            return;
        }
        if (selectedShape != -1) {
            this.selectedShape = -1;
        }
        LastAction l = new LastAction(this);
        if (this.getMode().equals("DRAW") && !drawings.isEmpty()) {
            oldShape = drawings.get(drawings.size() - 1).copyWithoutShift();
            oldShape.setVisible(false);
            newShape = drawings.get(drawings.size() - 1).copyWithoutShift();
            l.setOldShape(oldShape);
            l.setNewShape(newShape);
            LastAction.pushUndoStack(l);
        } else if (this.getMode().equals("COPY") && !drawings.isEmpty()) {
            oldShape = drawings.get(drawings.size() - 1).copyWithoutShift();
            oldShape.setVisible(false);
            newShape = drawings.get(drawings.size() - 1).copyWithoutShift();
            l.setOldShape(oldShape);
            l.setNewShape(newShape);
            LastAction.pushUndoStack(l);

        } else if (this.getMode().equals("RESIZE") && !drawings.isEmpty()) {
            l.setOldShape(oldShape);
            l.setNewShape(newShape);
            LastAction.pushUndoStack(l);

        } else if (this.getMode().equals("MOVE") && !drawings.isEmpty()) {
            l.setOldShape(oldShape);
            l.setNewShape(newShape);
            LastAction.pushUndoStack(l);

        }
        else if (this.getMode().equals("DELETE") && !drawings.isEmpty()) {
            l.setOldShape(oldShape);
            l.setNewShape(newShape);
            LastAction.pushUndoStack(l);

        }
        else if (this.getMode().equals("CHANGECOLOR") && !drawings.isEmpty()) {
            l.setOldShape(oldShape);
            l.setNewShape(newShape);
            LastAction.pushUndoStack(l);

        }
        else if(this.getMode().equals("PASTE") && !drawings.isEmpty())
        {
            oldShape = drawings.get(drawings.size() - 1).copyWithoutShift();
            oldShape.setVisible(false);
            newShape = drawings.get(drawings.size() - 1).copyWithoutShift();
            l.setOldShape(oldShape);
            l.setNewShape(newShape);
            LastAction.pushUndoStack(l);
        }
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }

    @Override
    public void mouseDragged(MouseEvent me) {
        MyPoint current = new MyPoint(me.getX(), me.getY());
        if (selectionMode == false) {
            if (getShapeName() != null) {
                if (getShapeName().equals("LINE")) {
                    Shape s = drawings.get(drawings.size() - 1);
                    Line l = (Line) s;
                    l.setFinalPoint(current);
                } else if (getShapeName().equals("RECTANGLE")) {
                    Shape s = drawings.get(drawings.size() - 1);
                    Rectangle rectangle = (Rectangle) s;
                    rectangle.setFinalPoint(current);
                } else if (getShapeName().equals("SQUARE")) {
                    Shape s = drawings.get(drawings.size() - 1);
                    Square square = (Square) s;
                    square.setFinalPoint(current);
                } else if (getShapeName().equals("RIGHTANGLETRIANGLE")) {
                    Shape s = drawings.get(drawings.size() - 1);
                    RightAngleTriangle triangle = (RightAngleTriangle) s;
                    triangle.setPointB(current);
                } else if (getShapeName().equals("ISOSCELESTRIANGLE")) {
                    Shape s = drawings.get(drawings.size() - 1);
                    IsoscelesTriangle triangle = (IsoscelesTriangle) s;
                    triangle.setPointB(current);
                } else if (getShapeName().equals("CIRCLE")) {
                    Shape s = drawings.get(drawings.size() - 1);
                    Circle circle = (Circle) s;
                    circle.setFinalPoint(current);
                } else if (getShapeName().equals("ELLIPSE")) {
                    Shape s = drawings.get(drawings.size() - 1);
                    Ellipse ellipse = (Ellipse) s;
                    ellipse.setFinalPoint(current);
                }
                repaint();
            }
        } else {
            if (selectionMode == true && !drawings.isEmpty()) {
                if (this.getMode().equals("MOVE") && selectedShape != -1) {
                    drawings.get(this.selectedShape).setDraggedPoint(current);
                    drawings.get(this.selectedShape).moveShape();
                    newShape = drawings.get(this.selectedShape).copyWithoutShift();
                    repaint();
                } else if (this.getMode().equals("RESIZE") && selectedShape != -1) {
                    drawings.get(this.selectedShape).resizeShape(current);
                    newShape = drawings.get(this.selectedShape).copyWithoutShift();
                    repaint();
                }
            }
        }
    }

    @Override
    public void mouseMoved(MouseEvent me) {
    }

}
