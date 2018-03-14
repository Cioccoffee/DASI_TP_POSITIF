/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModeleDuDomaine;

import javax.persistence.Entity;


@Entity
public class Tarologue extends Medium{
    
    
    private String cartes;

    public Tarologue(String nom, String bio, String cartes) {
        this.nom = nom;
        this.bio = bio;
        this.cartes = cartes;
    }

    public Tarologue() {
    }

    
     public String getCartes() {
        return cartes;
    }

    public void setCartes(String cartes) {
        this.cartes = cartes;
    }
    
}
