/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModeleDuDomaine;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;


@Entity
@DiscriminatorValue("Voyant")
public class Voyant extends Medium{
    
    private String support;

    public String getSupport() {
        return support;
    }

    public void setSupport(String support) {
        this.support = support;
    }

    public Voyant(String nom, String bio, String support) {
        this.nom = nom;
        this.bio = bio;
        this.support = support;
    }

    public Voyant() {
    }
    
    
}
