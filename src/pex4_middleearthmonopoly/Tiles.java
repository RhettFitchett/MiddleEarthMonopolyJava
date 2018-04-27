/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pex4_middleearthmonopoly;

/**
 *
 * @author Boba Rhett
 */
public class Tiles {
    protected String name;
    protected String description;
    protected Double posX;
    protected Double posY;
    
    public Tiles(){
        
    }

    public Tiles(String nam, String descriptin, Double psX, Double psY) {
        name = nam;
        description = descriptin;
        posX = psX;
        posY = psY;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Double getPosX() {
        return posX;
    }

    public Double getPosY() {
        return posY;
    }
    
    
    
}
