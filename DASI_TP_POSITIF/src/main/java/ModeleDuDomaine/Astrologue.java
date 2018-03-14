/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModeleDuDomaine;

import javax.persistence.Entity;

/**
 *
 * @author vlezaud
 */
//@Entity = ne pas mettre car id hérité
public class Astrologue extends Medium{
    
    
    private String cartes;

    public String getCartes() {
        return cartes;
    }

    public void setCartes(String cartes) {
        this.cartes = cartes;
    }
    
    
}
