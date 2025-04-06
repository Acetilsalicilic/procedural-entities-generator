/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package procentity.peg.entities;

import mathUtils.Vector2;

/**
 *
 * @author acetil
 */
public class Tree implements IEntity {
    private Vector2 coordinates;
    
    @Override
    public Vector2 getCoordinates() {
        return coordinates;
    }

    @Override
    public String getName() {
        return "Tree";
    }
    
}
