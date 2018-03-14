/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModeleDuDomaine;

import javax.persistence.*;


// @MappedSuperclass = uniquement si ce n'est pas une entité
@Entity
@Inheritance (strategy = InheritanceType.TABLE_PER_CLASS)
public class Medium {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO) int id;
    protected String nom;
    protected String bio;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
    
    
}
