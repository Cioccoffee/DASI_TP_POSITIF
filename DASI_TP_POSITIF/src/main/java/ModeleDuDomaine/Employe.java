package ModeleDuDomaine;

import javax.persistence.*;

@Entity 
public class Employe {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO) int id;
    private String nom;
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

    public void addAffectation(){
        ++affectations;
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
