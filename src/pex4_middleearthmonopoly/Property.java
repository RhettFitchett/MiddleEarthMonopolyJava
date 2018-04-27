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
public class Property extends Tiles {
    protected Integer buyValue;
    protected Integer rentValue;

    public Property() {
    }

    public Property(Integer buyVal, Integer rentVal, String nam, String descriptin, Double psX, Double psY) {
        super(nam, descriptin, psX, psY);
        buyValue = buyVal;
        rentValue = rentVal;
    }

    public Integer getBuyValue() {
        return buyValue;
    }

    public Integer getRentValue() {
        return rentValue;
    }
    
    
}
