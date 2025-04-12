/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package world;

import java.util.ArrayList;
import java.util.List;
import logging.DefaultLogger;
import procentity.peg.ProceduralEntityGeneration;
import world.terrain.Chunk;

/**
 *
 * @author acetil
 */
public class World {
    private float size;
    private List<Chunk> chunks;
    private DefaultLogger logger;
    
    public World(float size, DefaultLogger logger) {
        this.size = size;
        this.logger = logger;
        chunks = new ArrayList<Chunk>();
    }
    
    public void addChunk(Chunk newChunk) {
        newChunk.setId(chunks.size());
        logger.log("Adding chunk to world " + newChunk);
        chunks.add(newChunk);
    }
    
    public List<Chunk> getAllChunks() {
        return chunks;
    }
    
    @Override
    public String toString() {
        return "size: " + size + ", chunks: " + chunks.size();
    }
}
