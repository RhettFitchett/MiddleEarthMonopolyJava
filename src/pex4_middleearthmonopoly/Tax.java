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
public class Tax extends Tiles{
    private Integer cost;

    public Tax(Integer cosst, String nam, String descriptin, Double psX, Double psY) {
        super(nam, descriptin, psX, psY);
        cost = cosst;
    }
    
    public Integer getCost(){
        return cost;
    }
}
