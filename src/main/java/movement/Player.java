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
    
    public Player() {
        this(Vector2.ZERO);
    }
    
    public Player(Vector2 initPos) {
        coordinates = initPos;
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
