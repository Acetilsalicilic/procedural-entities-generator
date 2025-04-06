/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mathUtils;

/**
 *
 * @author acetil
 */
public class Vector2 {
    public float x;
    public float y;
    
    public Vector2(float x, float y) {
        this.x = x;
        this.y = y;
    }
    
    public void multiplyBy(float factor) {
        x = x * factor;
        y = y* factor;
    }
    
    public float[] toArray() {
        return new float[] {x, y};
    }
    
    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
    
    // Some constants
    public static final Vector2 ZERO = new Vector2(0, 0);
    public static final Vector2 ONE = new Vector2(1, 1);
}
