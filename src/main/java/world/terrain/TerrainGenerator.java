/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package world.terrain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import mathUtils.Utils;
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

            float lowX = Math.abs(chunk.getCoordinates()[0].x());
            float highX = Math.abs(chunk.getCoordinates()[1].x());

            float lowY = Math.abs(chunk.getCoordinates()[0].y());
            float highY = Math.abs(chunk.getCoordinates()[1].y());
            
            float positionX = Math.abs(position.x());
            float positionY = Math.abs(position.y());

            if (lowX <= positionX && positionX < highX && lowY <= positionY && positionY < highY) {
                // Is in chunk!!
                ProceduralEntityGeneration.logger.log("Player in chunk " + chunk);
                HashMap<String, Chunk> retMap = new HashMap<>();
                retMap.put("true", chunk);
                return retMap;
            } else {
                Vector2 center = chunk.calcCenter();
                double currentDistance = Math.sqrt(Math.pow((center.x() - position.x()), 2) + Math.pow((center.y() - position.y()), 2));
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

    private Chunk createChunk(Vector2 position) {
        if (!chunkGeneration) {
            return null;
        }

        // Determine the coordinates of the new chunk
        int xSign = Utils.getSign(position.x());
        int ySign = Utils.getSign(position.y());
        
        Vector2[] chunkCoord = new Vector2[2];
        
        chunkCoord[0] = Vector2.ZERO;
        chunkCoord[0] = chunkCoord[0].x((float) Math.floor(Math.abs(position.x()) / chunkSize) * xSign * chunkSize);
        chunkCoord[0] = chunkCoord[0].y((float) Math.floor(Math.abs(position.y()) / chunkSize) * ySign * chunkSize);
        
        chunkCoord[1] = Vector2.ZERO;
        chunkCoord[1] = chunkCoord[1].x((float) Math.ceil(Math.abs(position.x()) / chunkSize) * xSign * chunkSize);
        chunkCoord[1] = chunkCoord[1].y((float) Math.ceil(Math.abs(position.y()) / chunkSize) * ySign * chunkSize);
        
        /**
         * Some nasty tricks to avoid 0 size chunks.
         * 
         * The idea is this: low coord <= player position < high coord,
         * but if the player position == high coord, we have that the math
         * above stops working. So, if we are in the top limit, as it is exclusive,
         * written in maths as [lowCoord, highCoord) in range notations, that means
         * we are in the next chunk, that has a range of [highCoord, evenHigher).
         * That's why I sum another chunk if equal.
         * 
         * The code looks kind of akward, but mainly I think because now Vector2 are
         * inmutable. I really created that class for fun, but with the now necessary
         * inmutability that it provides, it starts to look like a somewhat smart
         * idea.
         */
        if (chunkCoord[0].x() == chunkCoord[1].x()) {
            chunkCoord[1] = chunkCoord[1].x(chunkCoord[1].x() + chunkSize * xSign);
        }
        
        if (chunkCoord[0].y() == chunkCoord[1].y()) {
            chunkCoord[1] = chunkCoord[1].y(chunkCoord[1].y() + chunkSize * ySign);
        }
        
        // Create the chunk and return it
        Chunk newChunk = new Chunk(chunkSize, chunkCoord[0], chunkCoord[1]);
        return newChunk;
    }

    public void evaluatePlayer(Player player, World world) {
        // Initial chunk generation
        if (world.getAllChunks().isEmpty() && initialChunk) {
            ProceduralEntityGeneration.logger.logWarning("No chunks in world, generating starting one");
            // Create initial chunk
            Chunk initial = new Chunk(chunkSize, Vector2.ZERO, Vector2.ONE.multiplyBy(chunkSize));
            world.addChunk(initial);
        }
        if (!world.getAllChunks().isEmpty() && world.getAllChunks().get(0).getSize() != chunkSize) {
            ProceduralEntityGeneration.logger.logError("chunkSize of terrainGenerator isn't compatible with chunk size of world!");
            return;
        }

        Map<String, Chunk> result = isInChunk(player.getCoordinates(), world.getAllChunks());

        if (!result.containsKey("true")) {
            Chunk newChunk = createChunk(player.getCoordinates());
            world.addChunk(newChunk);
        }
    }
}
