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
    private float x;
    private float y;
    /**
    * I had to make this structure inmutable, because I was having some
    * nasty effects that I just couldn't pinpoint, and I was sure it had something
    * to do with accidentally modifying a Vector2 somewhere, so I made it
    * Inmutable and like that! problem solved. I still don't know where I was
    * changing it, but I was it seems.
    */
    public float x() {
        return x;
    }
    
    public float y() {
        return y;
    }
    
    public Vector2 x(float newX) {
        return new Vector2(newX, y);
    }
    
    public Vector2 y(float newY) {
        return new Vector2(x, newY);
    }
    
    public Vector2(float x, float y) {
        this.x = x;
        this.y = y;
    }
    
    public Vector2 multiplyBy(float factor) {
        return new Vector2(x * factor, y * factor);
    }
    
    public Vector2 sum(Vector2 value) {
        return new Vector2(x + value.x, y + value.y);
    }
    
    public Vector2 sum(float value) {
        return new Vector2(x + value, y + value);
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
