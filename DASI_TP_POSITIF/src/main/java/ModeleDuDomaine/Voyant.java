package ModeleDuDomaine;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;


@Entity
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
    
    public String toString(){
        String s = super.toString();
        s+=" - Support : "+this.support;
        return s;
    }
}
