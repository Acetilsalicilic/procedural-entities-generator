/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package procentity.peg;

import logging.DefaultLogger;
import mathUtils.Vector2;
import movement.Player;
import world.World;
import world.terrain.TerrainGenerator;

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
        World world = new World(500f);
        logger.log("World: " + world);
        
        // confirming player
        Player.initialize(new Vector2(0.5f, 0.5f));
        logger.log("Player: " + Player.getInstance());
        logger.log("Creating terrain generator");
        TerrainGenerator tr = new TerrainGenerator(5f, 3, true, false, false, true);
        
        logger.log("First analyzing");
        tr.evaluatePlayer(Player.getInstance(), world);
        // Game loop
        /*while (true) {
            
        }*/
        logger.close();
    }
}
