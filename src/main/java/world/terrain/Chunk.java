/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package world.terrain;

import java.util.ArrayList;
import java.util.List;
import procentity.peg.entities.IEntity;

/**
 *
 * @author acetil
 */
public class Chunk {
    private float[] coordinates;
    private List<IEntity> entityList;
    private float size;
    
    public Chunk(float size, float[] coordinates) {
        this.size = size;
        this.coordinates = coordinates;
        
        entityList = new ArrayList<>();
    }
    
    public List<IEntity> getAllEntities() {
        return entityList;
    }
    
    public float[] getCoordinates() {
        return coordinates;
    }
}
