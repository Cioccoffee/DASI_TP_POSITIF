/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModeleDuDomaine;

import java.util.Date;
import javax.persistence.*;

class SessionId{
     private Date debut;
     //@OneToOne
     private int client;
}

@Entity @IdClass(SessionId.class)
public class Session {
    
    @Id
    @Temporal(TemporalType.DATE)
    private Date debut;
    // il faut une date qui inclut l'heure
    @Temporal(TemporalType.DATE)
    private Date fin;
    private String comment;
    
    @Id
    @OneToOne
    private Client client;
    @OneToOne
    private Employe employe;
    @OneToOne
    private Medium medium;

    public Date getDebut() {
        return debut;
    }

    public void setDebut(Date debut) {
        this.debut = debut;
    }

    public Date getFin() {
        return fin;
    }

    public void setFin(Date fin) {
        this.fin = fin;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Employe getEmploye() {
        return employe;
    }

    public void setEmploye(Employe employe) {
        this.employe = employe;
    }

    public Medium getMedium() {
        return medium;
    }

    public void setMedium(Medium medium) {
        this.medium = medium;
    }

    public Session() {
    }

    public Session(Date debut, Date fin, String comment, Client client, Employe employe, Medium medium) {
        this.debut = debut;
        this.fin = fin;
        this.comment = comment;
        this.client = client;
        this.employe = employe;
        this.medium = medium;
    }
    
    
    
}
