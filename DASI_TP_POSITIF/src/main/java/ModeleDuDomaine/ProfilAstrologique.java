package ModeleDuDomaine;

import java.util.List;
import javax.persistence.*;

@Entity
public class ProfilAstrologique {
    
    @Id @GeneratedValue(strategy=GenerationType.AUTO) int id;
    private String signeAstro;
    private String signeChinois;
    private String couleur;
    private String animal;

    public String getSigneAstro() {
        return signeAstro;
    }

    public void setSigneAstro(String signeAstro) {
        this.signeAstro = signeAstro;
    }

    public String getSigneChinois() {
        return signeChinois;
    }

    public void setSigneChinois(String signeChinois) {
        this.signeChinois = signeChinois;
    }

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public String getAnimal() {
        return animal;
    }

    public void setAnimal(String animal) {
        this.animal = animal;
    }

    public ProfilAstrologique(String signeAstro, String signeChinois, String couleur, String animal) {
        this.signeAstro = signeAstro;
        this.signeChinois = signeChinois;
        this.couleur = couleur;
        this.animal = animal;
    }

    public ProfilAstrologique() {
        
    }
    
    public ProfilAstrologique(List<String> infos) {
        this.signeAstro = infos.get(0);
        this.signeChinois = infos.get(1);
        this.couleur = infos.get(2);
        this.animal = infos.get(3);
    }
    
    
    
}
