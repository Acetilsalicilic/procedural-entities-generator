/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package worldprinting;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import logging.DefaultLogger;
import mathUtils.Vector2;
import movement.Player;
import procentity.peg.ProceduralEntityGeneration;
import world.World;
import world.terrain.Chunk;

/**
 *
 * @author acetil
 */
public class WorldPrinter {
    private World world;
    private DefaultLogger logger;
    private final char[] symbols = new char[]{' ', '-', '|', '+', 'P', '#'};
    
    public WorldPrinter(World world, DefaultLogger logger) {
        this.world = world;
        this.logger = logger;
    }
    
    public void printChunks() {
        logger.log("World chunks:");
        for (var chunk : world.getAllChunks()) {
            logger.log("- " + chunk.toString());
        }
    }
    
    public void printArea(float size, Player player) {
        Map<Vector2, Chunk> coordMap = new HashMap<>();
        float chunkSize = 0;
        
        float low = 0 - (size / 2);
        float high = size / 2;
        
        for (var chunk : world.getAllChunks()) {
            Vector2 center = chunk.calcCenter();
            if (low < center.x() && center.x() < high && low < center.y() && center.y() < high ) {
                coordMap.put(chunk.getCoordinates()[0], chunk);
                chunkSize = chunk.getSize();
            }
        }
        
        // Print them
        StringBuilder sb = new StringBuilder();
        
        for (float x_index = size / 2; x_index > -(size / 2); x_index--) { // Line iteration
            for (float y_index = size / 2; y_index > -(size / 2); y_index--) { // Column iteration
                // Determine the chunk in here
                Chunk current = null;
                Vector2 currentPos = new Vector2(x_index, y_index);
                for (var vector : coordMap.keySet()) {
                    if (currentPos.isInVectorRange(vector, chunkSize)) {
                        current = coordMap.get(vector);
                    }
                }

                // determine the symbol to append
                if (x_index == 0 && y_index == 0) {
                    sb.append('0');
                    continue;
                }
                
                Vector2 playerOffsetPos = new Vector2(Math.round(player.getCoordinates().x()), Math.round(player.getCoordinates().y()));
                if (playerOffsetPos.equals(currentPos)) {
                    sb.append('P');
                    continue;
                }
                
                if (current == null) {
                    sb.append('#');
                    continue;
                }
                
                if (
                        (current.getCoordinates()[0].x() == x_index && current.getCoordinates()[0].y() == y_index) 
                        || 
                        (current.getCoordinates()[1].x() == x_index && current.getCoordinates()[1].y() == y_index)
                        ) {
                    sb.append('+');
                    continue;
                }
                
                if (current.getCoordinates()[0].x() == x_index || current.getCoordinates()[1].x() == x_index) {
                    sb.append('|');
                    continue;
                }
                
                if (current.getCoordinates()[0].y() == y_index || current.getCoordinates()[1].y() == y_index) {
                    sb.append('-');
                    continue;
                }
                
                sb.append(' ');
            }
            
            sb.append('\n');
        }
        
        // Print it!
        logger.log("Printing world with size " + size);
        logger.log("Coordinates " + (Vector2.ONE.multiplyBy(low)) + " to " + Vector2.ONE.multiplyBy(high));
        logger.logWorld(sb);
    }
}
