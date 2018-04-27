/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pex4_middleearthmonopoly;

import java.util.ArrayList;

/**
 *
 * @author Boba Rhett
 */
public class Human extends Player {

    private int imageChoice;

    public Human() {
        imageChoice = 1;
    }

    @Override
    public void buyProperty() {

    }

    @Override
    public int getMoney() {
        return money;
    }

    public void setMoney(Integer muns){
        money = muns;
    }
    @Override
    public String printMoney() {
        String temp = Integer.toString(money);
        return temp;
    }

    @Override
    public ArrayList<Property> getProperties() {
        return properties;
    }

    @Override
    public String getIcon() {
        return ImageURL;
    }

    @Override
    public void addProperty(Property prop) {
        properties.add(prop);
    }

    @Override
    public boolean getInJail() {
        return inJail;
    }

    @Override
    public void setInJail(boolean x) {
        inJail = x;
}
    
    
    

}
