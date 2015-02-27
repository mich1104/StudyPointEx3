/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

/**
 *
 * @author Michael
 */
public class Player {
    int id;
    String name;
    String country;
    public Player(int id, String name, String country){
        this.id = id;
        this.name = "\""+name+"\"";
        this.country = "\""+country+"\"";
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    
}
