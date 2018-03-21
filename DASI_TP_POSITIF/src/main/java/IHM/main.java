package IHM;

import ModeleDuDomaine.*;
import dao.EmployeDAO;
import dao.JpaUtil;
import dao.MediumDAO;
import dao.SessionDAO;
import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.TypedQuery;
import service.Service;
import util.Saisie;

public class main extends JpaUtil{
    
    public static void main(String[] args)
    {
        //création persistence unit
        JpaUtil.init();
        
        Service.initDB();
        Service.reinitDispoEmploye();
        TestUI();
        
        JpaUtil.destroy();
    }
    
    public static void TestInscription(){
        System.out.println("Inscription");
        String civilite = Saisie.lireChaine("Civilité : ");
        String nom = Saisie.lireChaine("Nom : ");
        String prenom = Saisie.lireChaine("Prénom : ");
        String jour = Saisie.lireChaine("Date de naissance (Jour) : ");
        String mois = Saisie.lireChaine("Date de naissance (Mois) : ");
        String annee = Saisie.lireChaine("Date de naissance (Année) : ");
        String adresse = Saisie.lireChaine("Adresse : ");
        String telephone = Saisie.lireChaine("Telephone : ");
        String mail = Saisie.lireChaine("Adresse mail : ");
        String mdp = Saisie.lireChaine("Mot de passe : ");
        
        Service.Inscription(prenom, nom, civilite, jour, mois, annee, mail, telephone, mdp, adresse);
    }
    
    public static void TestConnexion(){
        System.out.println("Connexion");
        
        String mail = Saisie.lireChaine("Adresse mail : ");
        String mdp = Saisie.lireChaine("Mot de passe : ");
        
        System.out.println(Service.Connexion(mail, mdp));
    }
    
    public static void TestHistorique(){
        JpaUtil.creerEntityManager();
        System.out.println("Voici les clients inscrits :");
        List<Client> lc = new LinkedList<Client>();
        TypedQuery<Client> query = JpaUtil.obtenirEntityManager().createQuery(
        "SELECT c FROM Client c", 
                Client.class);
        lc = query.getResultList();
        JpaUtil.fermerEntityManager();
        for(int i = 0; i < lc.size(); i++){
            Client c2 = lc.get(i);
            System.out.println(c2.getId()+" "+c2.getNom()+" "+c2.getPrenom());
        }
        int id = Saisie.lireInteger(" Veuillez saisir un identifiant appartenant à cette liste : ");
        
        List<Session> ls = Service.getHistorique(Service.findClientById(id));
        for(int i = 0; i < ls.size(); i++){
            Session s = ls.get(i);
            System.out.println(s.getDebut()+" "+s.getFin()+" "
                    +s.getEmploye().getNom()+" "+s.getMedium().getNom()+" "
                    +s.getClient().getNom()+" "+s.getClient().getPrenom());
        }
        
    }
    
    public static Medium TestDemandeDeVoyance(Client c){
        System.out.println("Type de medium :");
        System.out.println("1. Astrologue");
        System.out.println("2. Tarologue");
        System.out.println("3. Voyant");
        int mediumType = Saisie.lireInteger("Veuillez choisir un type de Medium : ");
        List<Medium> lm = new LinkedList<Medium>();
        switch(mediumType){
            case 1 : 
                lm = Service.findAllAstroAsMedium();
                System.out.println("Liste des Astrologues :");
                break;
            case 2 :
                lm = Service.findAllTaroAsMedium();
                System.out.println("Liste des Tarologues :");
                break;
            case 3 :
                lm = Service.findAllVoyantAsMedium();
                System.out.println("Liste des Voyants :");
                break;
                
            default :
                System.out.println("ceci n'est pas un type de medium");
                break;
        }
        
        for(int i = 0; i < lm.size(); i++){
            System.out.println(lm.get(i).toString());
        }
        int idmedium = Saisie.lireInteger("Veuillez saisir l'id du Medium voulu : ");
        return Service.findMediumById(idmedium);
        
    }
    
    public static void TestRealisationConsultation(Client c,Medium m){
        Employe e = Service.traiterDemandeDeVoyance(c, m);
        System.out.println(" Début des actions côté employé : ");
        //générer prédiction
        int a = Saisie.lireInteger("Qualité de la prédiction en amour (de 1 à 4): ");
        int s = Saisie.lireInteger("Qualité de la prédiction en santé (de 1 à 4): ");
        int t = Saisie.lireInteger("Qualité de la prédiction en travail (de 1 à 4): ");
        try {
            List<String> prediction = new LinkedList<String>();
            prediction = Service.genererPredictions(c, a, s, t);
            for(int i = 0; i < prediction.size(); ++i){
                System.out.println(prediction.get(i));
            }
        } catch (IOException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
        Service.demarrerSession(c, e, m);
        System.out.println(" Session créée ");
        System.out.println(" Fin de session ");
        String comment = Saisie.lireChaine("Votre commentaire : ");
        e = Service.findEmployeById(e.getId());
        Service.cloturerSession(c, e, m, comment);
    }
    
    public static void TestStatistiques(){
        System.out.println("Statistiques");
        System.out.println("Statistiques des employés");
        List<Employe> le = Service.findAllEmploye();
        for(int i = 0; i < le.size(); ++i){
            Employe e2 = le.get(i);
            System.out.println(e2.getNom()+" "+e2.getPrenom());
            System.out.println("Nb de sessions réalisées : "+Service.getAffectations(e2));
            System.out.println("Pourcentage des sessions : "+Service.getPourcentageSessionEmploye(e2));
        }
        System.out.println("Statistiques des mediums");
        List<Medium> lm = Service.findAllMedium();
        for(int i = 0; i < lm.size(); ++i){
            Medium m2 = lm.get(i);
            System.out.println(m2.getNom());
            System.out.println("Nb de sessions réalisées : "+ Service.getNBSessionByMediumId(m2) );
        }
    }
    
    public static void TestUI(){
        String action ="";
        
        while(!action.equals("exit")){
            
            switch(action){
                case "1" :
                    TestInscription();
                    break;
                case "2" :
                    TestConnexion();
                    break;
                case "3" :
                    TestHistorique();
                    break;
                case "4" :
                    int idclient = Saisie.lireInteger("Veuillez saisir l'id du client voulu : ");
                    Client c = Service.findClientById(idclient);
                    Medium m = TestDemandeDeVoyance(c);
                    System.out.println(" Fin des actions côté client : ");
                    TestRealisationConsultation(c,m);
                    break;
                case "5" :
                    TestStatistiques();
                    break;
                default :
                    break;
            }
            
            System.out.println("Veuillez choisir l'action à réaliser : ");
            System.out.println("1.Inscrire un client"); 
            System.out.println("2.Se connecter");
            System.out.println("3.Consulter l'historique d'un client");
            System.out.println("4.Faire et demander une consultation");
            System.out.println("5.Afficher les statistiques");
            System.out.println("exit : arrêter le programme");
            System.out.println("Afficher des éléments");
            
            action = Saisie.lireChaine("Votre action : ");
        }
    }
}
