/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package procentity.peg.entities;

/**
 *
 * @author acetil
 */
public class Tree implements IEntity {
    private float coordinates[];
    
    @Override
    public float[] getCoordinates() {
        return coordinates;
    }

    @Override
    public String getName() {
        return "Tree";
    }
    
}
