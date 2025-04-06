/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package movement;

/**
 *
 * @author acetil
 */
public class Player {
    private float[] coordinates;
    
    private static Player instance;
    
    {
        instance = new Player();
        instance.coordinates = new float[] {0, 0};
    }
    
    public static Player getInstance() {
        return instance;
    }
}
