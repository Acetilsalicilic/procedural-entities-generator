/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package procentity.peg;

import logging.DefaultLogger;
import mathUtils.Vector2;
import movement.Player;
import world.World;
import world.terrain.TerrainGenerator;
import worldprinting.WorldPrinter;

/**
 *
 * @author acetil
 */
public class ProceduralEntityGeneration {
    public static DefaultLogger logger;

    public static void main(String[] args) {
        // Initializating logger
        logger = new DefaultLogger(System.getProperty("user.home") + "/procedural.txt", 2, true);
        
        logger.logWarning("Instantiating world");
        World world = new World(500f, logger);
        logger.log("World: " + world);
        
        // confirming player
        Player player = new Player(new Vector2(10f, 10f));
        logger.log("Player: " + player);
        logger.log("Creating terrain generator");
        TerrainGenerator tr = new TerrainGenerator(7f, 3, true, true, false, true, logger);
        
        logger.log("First analyzing");
        tr.evaluatePlayer(player, world);
        
        logger.log("Second analysis");
        tr.evaluatePlayer(player, world);
        
        WorldPrinter wp = new WorldPrinter(world, logger);
        wp.printChunks();
        // Game loop
        /*while (true) {
            
        }*/
        
        wp.printArea(25, player);
        logger.close();
    }
}
