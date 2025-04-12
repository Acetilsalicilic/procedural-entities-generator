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
    private final Vector2 fpCoord;
    private final Vector2 spCoord;
    private final List<IEntity> entityList;
    private final float size;
    private Integer id;
    
    public Chunk(float size, Vector2 firstPointCoord, Vector2 secondPointCoord) {
        this.size = size;
        fpCoord = firstPointCoord;
        spCoord = secondPointCoord;
        
        entityList = new ArrayList<>();
    }
    
    public Chunk(float size, Vector2[] coords) {
        this.size = size;
        fpCoord = coords[0];
        spCoord = coords[1];
        
        entityList = new ArrayList<>();
    }
    
    public void setId(int id) {
        if (this.id == null) {
            this.id = id;
        }
    }
    
    public int getId() {
        return id;
    }
    
    public float getSize() {
        return size;
    }
    
    public Vector2 calcCenter() {
        float centerX = (fpCoord.x() + spCoord.x()) / 2;
        float centerY = (fpCoord.y() + spCoord.y()) / 2;
        
        return new Vector2(centerX, centerY);
    }
    
    public List<IEntity> getAllEntities() {
        return entityList;
    }
    
    public Vector2[] getCoordinates() {
        return new Vector2[]{fpCoord, spCoord};
    }
    
    @Override
    public String toString() {
        return "Chunk [" + id + "], " + fpCoord + " to " + spCoord;
    }
}
