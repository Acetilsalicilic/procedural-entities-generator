/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package worldprinting;

import procentity.peg.ProceduralEntityGeneration;
import world.World;

/**
 *
 * @author acetil
 */
public class WorldPrinter {
    private World world;
    
    public WorldPrinter(World world) {
        this.world = world;
    }
    
    public void printChunks() {
        ProceduralEntityGeneration.logger.log("World chunks:");
        for (var chunk : world.getAllChunks()) {
            ProceduralEntityGeneration.logger.log("- " + chunk.toString());
        }
    }
}
