/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package movement;

import mathUtils.Vector2;

/**
 *
 * @author acetil
 */
public class Player {
    private Vector2 coordinates;
    
    private static Player instance;
    
    public static void initialize() {
        if (instance != null)
            return;
        instance = new Player();
        instance.coordinates = Vector2.ZERO;
    }
    
    public static void initialize(Vector2 initPos) {
        if (instance != null)
            return;
        instance = new Player();
        instance.coordinates = initPos;
    }
    
    public static Player getInstance() {
        return instance;
    }
    
    public Vector2 getCoordinates() {
        return coordinates;
    }
    
    public void moveTo(Vector2 position) {
        coordinates = position;
    }
    
    @Override
    public String toString() {
        return "coords: " + coordinates;
    }
}
