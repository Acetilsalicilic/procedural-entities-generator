/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package world;

import java.util.ArrayList;
import java.util.List;
import procentity.peg.ProceduralEntityGeneration;
import world.terrain.Chunk;

/**
 *
 * @author acetil
 */
public class World {
    private float size;
    private List<Chunk> chunks;
    
    public World(float size) {
        this.size = size;
        chunks = new ArrayList<Chunk>();
    }
    
    public void addChunk(Chunk newChunk) {
        newChunk.setId(chunks.size());
        ProceduralEntityGeneration.logger.log("Adding chunk to world " + newChunk);
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
