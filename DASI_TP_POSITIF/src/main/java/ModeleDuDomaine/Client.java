package ModeleDuDomaine;

import java.util.Date;
import javax.persistence.*;

@Entity
public class Client {
    
    @Id @GeneratedValue(strategy=GenerationType.AUTO) 
    private int id;
    
    private String civilite;
    private String nom;
    private String prenom;
    private String adresse;
    private String mail;
    private String motdepasse;
    private String tel;
    
    @Temporal(TemporalType.DATE)
    private Date naissance;

    @OneToOne(cascade=CascadeType.PERSIST)
    private ProfilAstrologique profil;
            
    public String getCivilite() {
        return civilite;
    }

    public void setCivilite(String civilite) {
        this.civilite = civilite;
    }

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

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getMotdepasse() {
        return motdepasse;
    }

    public void setMotdepasse(String motdepasse) {
        this.motdepasse = motdepasse;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Date getNaissance() {
        return naissance;
    }

    public void setNaissance(Date naissance) {
        this.naissance = naissance;
    }

    public ProfilAstrologique getProfil() {
        return profil;
    }

    public void setProfil(ProfilAstrologique profil) {
        this.profil = profil;
    }

    public Client(String civilite, String nom, String prenom, String adresse, String mail, String motdepasse, String tel, Date naissance) {
        this.civilite = civilite;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.mail = mail;
        this.motdepasse = motdepasse;
        
        this.tel = tel;
        this.naissance = naissance;
    }

    public Client() {
    }
    
    
    
}
