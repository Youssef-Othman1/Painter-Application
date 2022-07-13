/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package module;

import GUI.Drawer;
import java.util.Stack;

/**
 *
 */
public class LastAction {

    private Shape oldShape, newShape;
    private Drawer drawer;
    private static Stack<LastAction> undoStack = new Stack<>();
    private static Stack<LastAction> redoStack = new Stack<>();

    public LastAction(Shape oldShape, Shape newShape) {
        this.oldShape = oldShape;
        this.newShape = newShape;
    }

    public LastAction(Drawer drawer) {
        this.drawer = drawer;
    }

    public Shape getOldShape() {
        return oldShape;
    }

    public void setOldShape(Shape oldShape) {
        this.oldShape = oldShape;
    }

    public Shape getNewShape() {
        return newShape;
    }

    public void setNewShape(Shape newShape) {
        this.newShape = newShape;
    }

    public static void pushUndoStack(LastAction l) {
        undoStack.push(l);
    }

    public void undo() {
        if (!undoStack.isEmpty()) {
            int i;
            LastAction poppedAction = undoStack.pop();
            for (i = 0; i < drawer.getDrawings().size(); i++) {
                if (poppedAction.getNewShape().equals(drawer.getDrawings().get(i))) {
                    drawer.getDrawings().set(i, poppedAction.getOldShape());
                    redoStack.push(new LastAction(poppedAction.newShape, poppedAction.oldShape));                
                    break;
                }
            }
           drawer.repaint();
        }
    }

    public void redo() {
        if (!redoStack.isEmpty()) {
            int i;
            LastAction poppedAction = redoStack.pop();
            for (i = 0; i < drawer.getDrawings().size(); i++) {
                if (poppedAction.getNewShape().equals(drawer.getDrawings().get(i))) {
                    drawer.getDrawings().set(i, poppedAction.getOldShape());
                    undoStack.push(new LastAction(poppedAction.newShape, poppedAction.oldShape));
                }
            }
            drawer.repaint();
        }
    }

}