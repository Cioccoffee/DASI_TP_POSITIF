/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModeleDuDomaine;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import static javax.persistence.DiscriminatorType.STRING;


// @MappedSuperclass = uniquement si ce n'est pas une entité
@Entity
@Inheritance (strategy = InheritanceType.JOINED)
/*@Inheritance (strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="medium_type", discriminatorType = STRING)*/
public class Medium /*implements Serializable*/ {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO) int id;
    protected String nom;
    protected String bio;

    protected List<Employe> listEmploye /*= new List<Employe>()*/;
    
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

    public List<Employe> getListEmploye() {
        return listEmploye;
    }
    
    public void addEmploye(Employe e) {
        this.listEmploye.add(e);
    }

    public int getId() {
        return id;
    }
    
    public String toString(){
        return "ID : "+this.id+" Nom : "+this.nom+" Bio : "+this.bio";
    }
}
