/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pex4_middleearthmonopoly;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Boba Rhett
 */
public class Card extends Tiles{
    private ArrayList cards = new ArrayList();
    private Integer randChoice;
    

    public Card(String nam, String descriptin, Double psX, Double psY) {
        super(nam, descriptin, psX, psY);
    }

    public Integer getRandChoice() {
        Random randInt = new Random();
        randChoice = randInt.nextInt();
        return randChoice;
    }
    
    
   
    
    
    
}
