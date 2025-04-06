/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package world.terrain;

import movement.Player;

/**
 *
 * @author acetil
 */
public class TerrainGenerator {
    private float chunkSize;
    private float entityDistance;
    
    public TerrainGenerator(float chunkSize, float entityDistance) {
        this.chunkSize = chunkSize;
        this.entityDistance = entityDistance;
    }
    
    public void evaluatePlayer(Player player) {
        
    }
}
