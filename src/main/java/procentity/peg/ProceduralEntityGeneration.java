/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package procentity.peg;

import logging.DefaultLogger;
import world.World;

/**
 *
 * @author acetil
 */
public class ProceduralEntityGeneration {

    public static void main(String[] args) {
        DefaultLogger logger = new DefaultLogger(System.getProperty("user.home") + "/procedural.txt", 2, true);
        logger.log("Hola mundo!");
        logger.log("Hola mundo!");
        logger.logWarning("Esta es una advertencia!");
        
        logger.logWarning("Instantiating world");
        World world = new World(500f);
        logger.log("World: " + world);
        // Game loop
        /*while (true) {
            
        }*/
        logger.close();
    }
}
