/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package world.terrain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import mathUtils.Vector2;
import movement.Player;
import procentity.peg.ProceduralEntityGeneration;
import world.World;

/**
 *
 * @author acetil
 */
public class TerrainGenerator {

    private float chunkSize;
    private float entityDistance;

    private boolean chunkSearch;
    private boolean chunkGeneration;
    private boolean entityCreation;
    private boolean initialChunk;

    public TerrainGenerator(float chunkSize, float entityDistance) {
        this(chunkSize, entityDistance, true, true, true, false);
    }

    public TerrainGenerator(Chunk sampleChunk, float entityDistance) {
        this(sampleChunk.getSize(), entityDistance, true, true, true, false);
    }

    public TerrainGenerator(float chunkSize, float entityDistance, boolean chunkSearch, boolean chunkGeneration, boolean entityCreation, boolean initialChunk) {
        this.chunkSize = chunkSize;
        this.entityDistance = entityDistance;
        this.chunkSearch = chunkSearch;
        this.chunkGeneration = chunkGeneration;
        this.entityCreation = entityCreation;
        this.initialChunk = initialChunk;

        ProceduralEntityGeneration.logger.log("Creating TerrainGenerator with config {chunkSearch: " + chunkSearch + ", chunkGeneration: " + chunkGeneration + ", entityGeneration: " + entityCreation + ", initialChunk: " + initialChunk + "}");
        ProceduralEntityGeneration.logger.log("Values: {chunkSize: " + this.chunkSize + ", entityDistance: " + this.entityDistance + "}");
        ProceduralEntityGeneration.logger.log("A starting chunk will be generated when a world is first analyzed");
    }

    private Map<String, Chunk> isInChunk(Vector2 position, List<Chunk> chunks) {

        if (!chunkSearch) {
            HashMap<String, Chunk> retMap = new HashMap<>();
            retMap.put("true", null);
            return retMap;
        }

        Chunk closest = null;
        double distance = -1;

        for (var chunk : chunks) {

            float lowX = Math.abs(chunk.getCoordinates().x - chunkSize);
            float highX = Math.abs(chunk.getCoordinates().x);

            float lowY = Math.abs(chunk.getCoordinates().y - chunkSize);
            float highY = Math.abs(chunk.getCoordinates().y);

            if (lowX < position.x && position.x < highX && lowY < position.y && position.y < highY) {
                // Is in chunk!!
                ProceduralEntityGeneration.logger.log("Player in chunk " + chunk);
                HashMap<String, Chunk> retMap = new HashMap<>();
                retMap.put("true", chunk);
                return retMap;
            } else {
                Vector2 center = chunk.calcCenter();
                double currentDistance = Math.sqrt(Math.pow((center.x - position.x), 2) + Math.pow((center.y - position.y), 2));
                if (currentDistance < distance) {
                    closest = chunk;
                    distance = currentDistance;
                }
            }
        }

        ProceduralEntityGeneration.logger.logWarning("Player not in chunk");
        HashMap<String, Chunk> retMap = new HashMap<>();
        retMap.put("false", closest);
        return retMap;

    }

    private Chunk createChunk() {
        if (!chunkGeneration) {
            return null;
        }

        throw new UnsupportedOperationException("No chunk generation implemented");
    }

    public void evaluatePlayer(Player player, World world) {
        // Initial chunk generation
        if (world.getAllChunks().isEmpty() && initialChunk) {
            ProceduralEntityGeneration.logger.logWarning("No chunks in world, generating starting one");
            // Create initial chunk
            Chunk initial = new Chunk(chunkSize, new Vector2(chunkSize, chunkSize), world.getNextId());
            world.addChunk(initial);
        }
        if (!world.getAllChunks().isEmpty() && world.getAllChunks().get(0).getSize() != chunkSize) {
            ProceduralEntityGeneration.logger.logError("chunkSize of terrainGenerator isn't compatible with chunk size of world!");
            return;
        }

        Map<String, Chunk> result = isInChunk(player.getCoordinates(), world.getAllChunks());

        if (!result.containsKey("true")) {
            Chunk newChunk = createChunk();
            world.addChunk(newChunk);
        }
    }
}
