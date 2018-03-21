package ModeleDuDomaine;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import static javax.persistence.DiscriminatorType.STRING;

@Entity
@Inheritance (strategy = InheritanceType.JOINED)
public class Medium {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO) int id;
    protected String nom;
    protected String bio;

    @OneToMany
    protected List<Employe> listEmploye;
    
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
        return "ID : "+this.id+" - Nom : "+this.nom+" - Bio : "+this.bio;
    }
    
}
