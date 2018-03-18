/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModeleDuDomaine;

import javax.persistence.*;

/*class EmployeId{
    private String nom;
    private String prenom;
}*/

@Entity //@IdClass(EmployeId.class)
public class Employe {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO) int id;
    //@Id
    private String nom;
    //@Id
    private String prenom;
    private String motdepasse;
    
    private int affectations;
    
    private boolean disponibilite;
    
    @Version
    private int version;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getMotdepasse() {
        return motdepasse;
    }

    public void setMotdepasse(String motdepasse) {
        this.motdepasse = motdepasse;
    }

    public int getAffectations() {
        return affectations;
    }

    public void setAffectations(int affectations) {
        this.affectations = affectations;
    }

    public boolean isDisponibilite() {
        return disponibilite;
    }

    public void setDisponibilite(boolean disponibilite) {
        this.disponibilite = disponibilite;
    }

    public int getId() {
        return id;
    }

    
    public Employe(String nom, String prenom, String motdepasse) {
        this.nom = nom;
        this.prenom = prenom;
        this.motdepasse = motdepasse;
        this.affectations = 0;
        this.disponibilite = true;
    }

    public Employe() {
    }
    
    
}
