/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModeleDuDomaine;

import java.util.List;
import javax.persistence.*;


// @MappedSuperclass = uniquement si ce n'est pas une entité
@Entity
@Inheritance (strategy = InheritanceType.TABLE_PER_CLASS)
public class Medium {
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
}
