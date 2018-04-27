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
public abstract class Player {

    protected String ImageURL;
    protected int money; //each player starts with $1500 in monopoly
    //protected ArrayList<String> properties;
    protected ArrayList<Property> properties;
    protected boolean inJail;

    public Player() {
        money = 1500;
        properties = new ArrayList<>();
    }

    public abstract void buyProperty();

    public abstract int getMoney();

    public abstract String printMoney();

    public abstract String getIcon();

    public abstract ArrayList<Property> getProperties();
    
    public abstract void addProperty(Property prop);
    
    public abstract void setInJail(boolean x);
    
    public abstract boolean getInJail();

}
