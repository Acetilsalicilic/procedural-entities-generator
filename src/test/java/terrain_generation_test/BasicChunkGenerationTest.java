/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package terrain_generation_test;

import logging.DefaultLogger;
import mathUtils.Vector2;
import movement.Player;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import world.World;
import world.terrain.TerrainGenerator;
import worldprinting.WorldPrinter;

/**
 *
 * @author acetil
 */
public class BasicChunkGenerationTest {
    
    private static World world;
    private static Player player;
    private static TerrainGenerator tr;
    private static DefaultLogger logger;
    private static WorldPrinter wp;
    private static int testCount;
    private static final float worldSize = 50f;
    
    public BasicChunkGenerationTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
        logger = new DefaultLogger(System.getProperty("user.home") + "/procedural", 5, true);
        testCount = 0;
        tr = new TerrainGenerator(5f, 2f, logger);
    }
    
    @AfterAll
    public static void tearDownClass() {
        logger.close();
    }
    
    @BeforeEach
    public void setUp() {
        testCount++;
        logger.log("Starting test number " + testCount + " +++++++++++++++++++++++++");
        world = new World(worldSize, logger);
        logger.log("Creating world with size " + worldSize);
        player = new Player();
        wp = new WorldPrinter(world, logger);
    }
    
    @AfterEach
    public void tearDown() {
    }

    @Test
    public void playerInOriginTest() {
        tr.evaluatePlayer(player, world);
        wp.printChunks();
        wp.printArea(20f, player);
    }
    
    @Test
    public void playerOutOfOriginTest(float[] playerPosition) {
        player.moveTo(new Vector2(12f, 12f));
        tr.evaluatePlayer(player, world);
        wp.printChunks();
        wp.printArea(20f, player);
    }
    
    @Test
    public void playerOutOfOriginBiggerTest() {
        player.moveTo(Vector2.ONE.multiplyBy(16f));
        tr.evaluatePlayer(player, world);
        wp.printChunks();
        wp.printArea(worldSize - 4, player);
    }
}
