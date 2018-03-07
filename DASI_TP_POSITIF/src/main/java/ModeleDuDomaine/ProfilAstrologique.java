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
@Entity
public class ProfilAstrologique {
    
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
    
    
    
}
