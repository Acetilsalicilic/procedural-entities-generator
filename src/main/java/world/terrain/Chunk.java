/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package world.terrain;

import java.util.ArrayList;
import java.util.List;
import mathUtils.Vector2;
import procentity.peg.entities.IEntity;

/**
 *
 * @author acetil
 */
public class Chunk {
    private Vector2 coordinates;
    private List<IEntity> entityList;
    private float size;
    private int id;
    
    public Chunk(float size, Vector2 coordinates, int id) {
        this.size = size;
        this.coordinates = coordinates;
        
        entityList = new ArrayList<>();
    }
    
    public int getId() {
        return id;
    }
    
    public float getSize() {
        return size;
    }
    
    public Vector2 calcCenter() {
        float centerX = coordinates.x  + size / 2;
        float centerY = coordinates.y  + size / 2;
        
        return new Vector2(centerX, centerY);
    }
    
    public List<IEntity> getAllEntities() {
        return entityList;
    }
    
    public Vector2 getCoordinates() {
        return coordinates;
    }
    
    @Override
    public String toString() {
        return "Chunk [" + id + "], " + coordinates;
    }
}
