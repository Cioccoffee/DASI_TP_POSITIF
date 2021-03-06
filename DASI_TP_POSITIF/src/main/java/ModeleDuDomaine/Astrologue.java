package ModeleDuDomaine;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;


@Entity
public class Astrologue extends Medium{
    
    private String ecole;
    private String promotion;

   public String getEcole() {
        return ecole;
    }

    public void setEcole(String ecole) {
        this.ecole = ecole;
    }

    public String getPromotion() {
        return promotion;
    }

    public void setPromotion(String promotion) {
        this.promotion = promotion;
    }

    public Astrologue(String nom, String bio, String ecole, String promotion) {
        this.nom = nom;
        this.bio = bio;
        this.ecole = ecole;
        this.promotion = promotion;
    }

    public Astrologue() {
    }
    
    public String toString(){
        String s = super.toString();
        s+=" - Ecole : "+this.ecole+" - Promotion : "+this.promotion;
        return s;
    }
}
