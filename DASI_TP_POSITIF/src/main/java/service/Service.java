/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import ModeleDuDomaine.*;
import dao.*;
import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Service {
    
    /*
    @return : long = id client si ok ou -1 si problème
    @params : mail et mot de passe en String
    */
    public static long Connexion(String mail, String mdp)
    {
        JpaUtil.creerEntityManager();
        //chercher client
        Client c = ClientDAO.findClientByMail(mail);
        JpaUtil.fermerEntityManager();
        // check mdp = right one
        if(c!=null && c.getMotdepasse().equals(mdp))
        
            return c.getId();
        
        else
            return -1; 
    }
    
    /*
    Tester le caractère NULL des champs avant d'appeler Inscription
    dans l'UI pour printer un "veuillez remplir..." ou mettre le.s champ.s
    problématique.s en rouge
    
    */
    public static long Inscription(String prenom, String nom, String civilite, 
            String jour, String mois, String annee, String mail, String telephone, 
            String mdp, String adresse)
    {
        JpaUtil.creerEntityManager();
        //check unique mail
        if(VerifierUniciteMail(mail)){
            //int id = ClientDAO.getHighestID()+1;
            Date naissance =new Date(Integer.parseInt(annee),Integer.parseInt(mois),Integer.parseInt(jour)); //init correctement
            
            JpaUtil.ouvrirTransaction();
            
            Client c = new Client(civilite, nom, prenom, adresse, mail, mdp, telephone, naissance);
            
            
            //créer profil astro correspondant
            
            AstroTest astroTest = new AstroTest("ASTRO-02-M0lGLURBU0ktQVNUUk8tQjAy");
            try {
                c.setProfil(new ProfilAstrologique(astroTest.getProfil(prenom, naissance)));
            } catch (IOException ex) {
                Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            ClientDAO.creerClient(c);
            
            JpaUtil.validerTransaction();
            
            JpaUtil.fermerEntityManager();
            
            System.out.println("Expediteur : contact@posit.if");
            System.out.println("Pour : " + mail);
            System.out.println("Sujet : Bienvenue chez POSIT'IF");
            System.out.println("Bonjour " + prenom + ",");
            System.out.println("Nous vous confirmons votre inscription au service POSIT'IF. Votre numéro de client est : " + c.getId());
            return c.getId();
        }
        else
        {
            System.out.println("Expediteur : contact@posit.if");
            System.out.println("Pour : " + mail);
            System.out.println("Sujet : Bienvenue chez POSIT'IF");
            System.out.println("Bonjour " + prenom + ",");
            System.out.println("Votre inscription au service POSIT'IF a malencontreusement échoué... Merci de recommencer ultérieurement.");
            JpaUtil.fermerEntityManager();
            return -1;
        }
            
            
        
    }
    
    public static boolean VerifierUniciteMail(String mail){
        Client c = ClientDAO.findClientByMail(mail);
        if(c!=null)
            return false;
        else
            return true; 
    }
    
    
    // QUID DU MDP ???????
    // compléter pour les Dates et tel
    public static void ModifyClient(int id, String prenom, String nom, String civilite, 
            String jour, String mois, String annee, String mail, String telephone, 
            String mdp, String adresse){
        
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
        
        Client c = ClientDAO.findClientById(id);
        
        if(!prenom.equalsIgnoreCase("")){
            c.setPrenom(prenom);
        }
        if(!nom.equalsIgnoreCase("")){
            c.setNom(nom);
        }
        if(!civilite.equalsIgnoreCase("")){
            c.setCivilite(civilite);
        }
        if(!mail.equalsIgnoreCase("")){
            c.setMail(mail);
        }
        if(!jour.equalsIgnoreCase("") && !mois.equalsIgnoreCase("") && !annee.equalsIgnoreCase("")){
            c.setNaissance( new Date(Integer.parseInt(annee),Integer.parseInt(mois),Integer.parseInt(jour)) );
        }
        if(!telephone.equalsIgnoreCase("")){
            c.setTel(telephone);
        }
        if(!adresse.equalsIgnoreCase("")){
            c.setAdresse(adresse);
        }
        ClientDAO.updateClient(c);
        
        JpaUtil.validerTransaction();
        JpaUtil.fermerEntityManager();
    }
    
    public static Employe trouverEmployeDispo(Medium m){
        //MediumDAO.findMediumByName(name);
        
        int min_affectations = EmployeDAO.getMaxAffectations();
        Employe selected = new Employe();
        
        boolean init = false;
        
        for(int i = 1; i < m.getListEmploye().size(); i++){
            
            if(m.getListEmploye().get(i).isDisponibilite()){
                if(!init){
                    min_affectations = m.getListEmploye().get(i).getAffectations();
                    selected = m.getListEmploye().get(i);
                    init = true;
                }
                else{
                    if(m.getListEmploye().get(i).getAffectations() < min_affectations)
                        selected = m.getListEmploye().get(i);
                        min_affectations = m.getListEmploye().get(i).getAffectations();
                }
            }
        }
        if(init) return selected;
        else return null;
    }
    
    public static String notifierEmploye(Client c, Medium m, Employe e){
        String s ="";
        s = "Vous avez une demande de voyance \r\n Client : "+c.getCivilite()+" "+c.getPrenom()+" "+c.getNom()+
                "\r\n Medium : "+m.getNom();
        return s;
    }
    
    
    public static void creerMedium()
    {
        
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
        
        //Voyant v = new Voyant("J. Dalmarre", "Votre avenir est devant vous : regardons-le ensemble !", "Marc de Café");
        //VoyantDAO.creerVoyant(v);
        
        Tarologue t = new Tarologue("Mme Irma", "Comprenez votre entourage grâce à mes cartes ! Résultats rapides.", "Tarot de Marseille");
        TarologueDAO.creerTarologue(t);
        Astrologue a = new Astrologue("Mme Mounia Mounia", "Avenir, avenir, que nous réserves-tu ? N'attendez plus, demandez à me consulter !", "Institut des Nouveaux Savoirs Astrologiques", "2010");
        AstrologueDAO.creerAstrologue(a);
        
        JpaUtil.validerTransaction();
        JpaUtil.fermerEntityManager();
        
    }
    
    public static void creerTarologue(String nom, String commentaire, String cartes)
    {
        Tarologue t = new Tarologue(nom,commentaire,cartes);
        
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
        TarologueDAO.creerTarologue(t);
        JpaUtil.validerTransaction();
        JpaUtil.fermerEntityManager();
    }
    
    public static void creerAstrologue(String nom, String bio, String ecole, String promo)
    {
        Astrologue t = new Astrologue(nom,bio,ecole,promo);
        
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
        AstrologueDAO.creerAstrologue(t);
        JpaUtil.validerTransaction();
        JpaUtil.fermerEntityManager();
    }
    
    public static void creerVoyant(String nom, String commentaire, String support)
    {
        Voyant t = new Voyant(nom,commentaire,support);
        
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
        VoyantDAO.creerVoyant(t);
        JpaUtil.validerTransaction();
        JpaUtil.fermerEntityManager();
    }
    
   public static void initDB()
   {
       Service.creerVoyant("Gwenaël", "Spécialistes au-delà de TOUTES les frontières.","Boule de Cristal");
       Service.creerVoyant("J. Dalmarre", "Votre avenir est devant vous : regardons-le ensemble !","Marc de Café");
       
       Service.creerTarologue("Mme Irma", "Comprenez votre entourage grâce à mes cartes ! Résultats rapides", "Tarot de Marseille");
       Service.creerTarologue("Mme Lisa Maria NGUYINIA", "Mes cartes spécialisées pour la région Bretagne répondront à toutes vos questions personnelles", "Tarot de Brocéliande");
       
       Service.creerAstrologue("Mme Sara", "Basée à Champigny-sur-Marne, Mme Sara vous révèlera votre avenir pour éclairer votre passé", "Ecole Normale Supérieure d'Astrologie (ENS-Astro)", "2006");
       Service.creerAstrologue("Mme Mounia Mounia", "Avenir, avenir, que nous réserves-tu ? N'attendez plus, demandez à me consulter !", "Institut des Nouveaux Savoirs Astrologiques", "2010");
   }
    //methodes find
    
   public static void creerEmploye(String nom, String prenom, String mdp){
       List<Medium> lm = new LinkedList<Medium>();
       lm.add(new Medium());
       lm.clear();
       creerEmploye(nom,prenom,mdp, lm);
   }
   
   public static void creerEmploye(String nom, String prenom, String mdp, List<Medium> lm){
       Employe e = new Employe(nom, prenom, mdp);
       
       JpaUtil.creerEntityManager();
       JpaUtil.ouvrirTransaction();
        
       EmployeDAO.creerEmploye(e);
        
       JpaUtil.validerTransaction();
       JpaUtil.fermerEntityManager();
        
       for(int i = 0; i < lm.size(); i++)
            
           ajouterEmployeToMedium(e,lm.get(i));
        
   }
//   public static void ajouterEmploye(Employe e, List<Medium> lm){
//        
//        
//        //Medium current = new Medium();
//        
//   }
   
   public static void ajouterEmployeToMedium(Employe e, Medium m){
       JpaUtil.creerEntityManager();
       JpaUtil.ouvrirTransaction();
       m.addEmploye(e);
       MediumDAO.updateMedium(m);
       JpaUtil.validerTransaction();
       JpaUtil.fermerEntityManager();
   }
   
    public static Client findClientById(int id){
        JpaUtil.creerEntityManager();
        Client c = ClientDAO.findClientById(id);
        JpaUtil.fermerEntityManager();
        return c;
    }
    
    public static List<Tarologue> findAllTaro(){
        JpaUtil.creerEntityManager();
        List<Tarologue> lt = TarologueDAO.findAllTaro();
        JpaUtil.fermerEntityManager();
        return lt;
    }
    
    public static List<Astrologue> findAllAstro(){
        JpaUtil.creerEntityManager();
        List<Astrologue> la = AstrologueDAO.findAllAstro();
        JpaUtil.fermerEntityManager();
        return la;
    }
    
    public static List<Voyant> findAllVoyant(){
        JpaUtil.creerEntityManager();
        List<Voyant> lv = VoyantDAO.findAllVoyant();
        JpaUtil.fermerEntityManager();
        return lv;
    }
    
    public static List<Medium> findAllMedium(){
        JpaUtil.creerEntityManager();
        List<Medium> lm = MediumDAO.findAllMedium();
        JpaUtil.fermerEntityManager();
        return lm;
    }
    
}
