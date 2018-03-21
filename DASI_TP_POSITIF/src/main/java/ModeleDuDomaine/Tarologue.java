package ModeleDuDomaine;

import javax.persistence.DiscriminatorValue;
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
    
    public String toString(){
        String s = super.toString();
        s+=" - Cartes : "+this.cartes;
        return s;
    }
}
