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
public class Tarologue extends Medium{
    
    private String ecole;
    private int promotion;

    public String getEcole() {
        return ecole;
    }

    public void setEcole(String ecole) {
        this.ecole = ecole;
    }

    public int getPromotion() {
        return promotion;
    }

    public void setPromotion(int promotion) {
        this.promotion = promotion;
    }
    
    
}
