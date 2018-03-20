/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import ModeleDuDomaine.*;
import dao.*;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
            //Calendar calendar = Calendar.getInstance();
            GregorianCalendar g = new GregorianCalendar(Integer.parseInt(annee)/*+1900*/,Integer.parseInt(mois),Integer.parseInt(jour));
            //g.getTime(); //init correctement
            SimpleDateFormat sdf = new SimpleDateFormat("ddMMyy");
            String birth =jour+mois+annee;
            Date naissance = new Date();
            try {
                naissance = sdf.parse(birth);
            } catch (ParseException ex) {
                Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
            }
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
    
    public static void ModifierClient(int id, String prenom, String nom, String civilite, 
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
    
    /* @param Client c, Medium m
    @return String = notification Employe si employe dispo
            else = ""
    */
    public static Employe traiterDemandeDeVoyance(Client c, Medium m){
        
        Employe e = trouverEmployeDispo(m);
        System.out.println("EMPLOYE CHOISI : "+e.getNom()+" "+e.getPrenom());
        if(e!=null){
            System.out.println(notifierEmploye(c,m,e));
            return e;
        }
        else return null;
    }
    
    public static Employe trouverEmployeDispo(Medium m){
        //MediumDAO.findMediumByName(name);
        JpaUtil.creerEntityManager();
        int min_affectations = EmployeDAO.getMaxAffectations();
        JpaUtil.fermerEntityManager();
        Employe selected = new Employe();
        
        boolean init = false;
        
        for(int i = 0; i < m.getListEmploye().size(); i++){
            System.out.println("init = " + init);
            System.out.println("list = " + m.getListEmploye().get(i));
            if(m.getListEmploye().get(i).isDisponibilite()){
                if(!init){
                    min_affectations = m.getListEmploye().get(i).getAffectations();
                    selected = m.getListEmploye().get(i);
                    init = true;
                    
                }
                else if(m.getListEmploye().get(i).getAffectations() < min_affectations){
                        selected = m.getListEmploye().get(i);
                        min_affectations = m.getListEmploye().get(i).getAffectations();
                    }
                }
            }
        
        if(init){
            JpaUtil.creerEntityManager();
            JpaUtil.ouvrirTransaction();
            selected.setDisponibilite(false);
            selected.addAffectation();
            EmployeDAO.updateEmploye(selected);
            JpaUtil.validerTransaction();
            JpaUtil.fermerEntityManager();
            return selected;
        }
        else return null;
    }
    
    public static String notifierEmploye(Client c, Medium m, Employe e){
        String s ="";
        s = "Vous avez une demande de voyance \r\n Client : "+c.getCivilite()+" "+c.getPrenom()+" "+c.getNom()+
                "\r\n Medium : "+m.getNom();
        return s;
    }
    
    public static void demarrerSession(Client c, Employe e, Medium m){
        Date debut = new Date();
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
        Session s = new Session(debut, c, e, m);
        SessionDAO.creerSession(s);
        JpaUtil.validerTransaction();
        JpaUtil.fermerEntityManager();
    }
    
    public static void cloturerSession(Client c, Employe e, Medium m, String commentaire){
        Date fin = new Date();
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
        Session s = SessionDAO.findLastSessionClient(c/*.getId()*/);
        s.setFin(fin);
        s.setComment(commentaire);
        SessionDAO.updateSession(s);
        JpaUtil.validerTransaction();
        JpaUtil.ouvrirTransaction();
        e.setDisponibilite(true);
        EmployeDAO.updateEmploye(e);
        JpaUtil.validerTransaction();
        JpaUtil.fermerEntityManager();
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
    
    public static Medium creerTarologue(String nom, String commentaire, String cartes)
    {
        Tarologue t = new Tarologue(nom,commentaire,cartes);
        
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
        TarologueDAO.creerTarologue(t);
        JpaUtil.validerTransaction();
        JpaUtil.fermerEntityManager();
        return t;
        
    }
    
    public static Medium creerAstrologue(String nom, String bio, String ecole, String promo)
    {
        Astrologue t = new Astrologue(nom,bio,ecole,promo);
        
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
        AstrologueDAO.creerAstrologue(t);
        JpaUtil.validerTransaction();
        JpaUtil.fermerEntityManager();
        return t;
    }
    
    public static Medium creerVoyant(String nom, String commentaire, String support)
    {
        Voyant t = new Voyant(nom,commentaire,support);
        
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
        VoyantDAO.creerVoyant(t);
        JpaUtil.validerTransaction();
        JpaUtil.fermerEntityManager();
        
        return t;
    }
    
   public static void initDB()
   {
       Medium v1 = Service.creerVoyant("Gwenaël", "Spécialistes au-delà de TOUTES les frontières.","Boule de Cristal");
       Medium v2 = Service.creerVoyant("J. Dalmarre", "Votre avenir est devant vous : regardons-le ensemble !","Marc de Café");
       
       Medium t1 = Service.creerTarologue("Mme Irma", "Comprenez votre entourage grâce à mes cartes ! Résultats rapides", "Tarot de Marseille");
       Medium t2 = Service.creerTarologue("Mme Lisa Maria NGUYINIA", "Mes cartes spécialisées pour la région Bretagne répondront à toutes vos questions personnelles", "Tarot de Brocéliande");
       
       Medium a1 = Service.creerAstrologue("Mme Sara", "Basée à Champigny-sur-Marne, Mme Sara vous révèlera votre avenir pour éclairer votre passé", "Ecole Normale Supérieure d'Astrologie (ENS-Astro)", "2006");
       Medium a2 = Service.creerAstrologue("Mme Mounia Mounia", "Avenir, avenir, que nous réserves-tu ? N'attendez plus, demandez à me consulter !", "Institut des Nouveaux Savoirs Astrologiques", "2010");
       
//       //List<Astrologue> la = findAllAstro();
//       List<Medium> lam = new LinkedList<Medium>();
//       lam = findAllAstroAsMedium();
//       /*for(int i = 0; i < la.size(); i++){
//           lam.add((Medium)la.get(i));
//       }*/
//       //List<Voyant> lv = findAllVoyant();
//       List<Medium> lvm = new LinkedList<Medium>();
//       lvm = findAllVoyantAsMedium();
//       /*for(int i = 0; i < lv.size(); i++){
//           lvm.add((Medium)lv.get(i));
//       }*/
//       //List<Tarologue> lt = findAllTaro();
//       List<Medium> ltm = new LinkedList<Medium>();
//       ltm = findAllTaroAsMedium();
//       /*for(int i = 0; i < lt.size(); i++){
//           ltm.add((Medium)lt.get(i));
//       }*/
//       List<Medium> lm = findAllMedium();
       
       Employe e1 = Service.creerEmploye("GIREUX", "Zouhair", "mdp"/*,lam*/);
       Employe e2 = Service.creerEmploye("TCHIUMAKOVA", "Nicolas", "mdp"/*,lvm*/);
       Employe e3 = Service.creerEmploye("KEMARO", "Cédric", "mdp"/*,ltm*/);
       Employe e4 = Service.creerEmploye("PAMITESCU", "Olena", "mdp"/*,lm*/);
       
       ajouterEmployeToMedium(e1,a1);
       ajouterEmployeToMedium(e1,a2);
       
       ajouterEmployeToMedium(e2,v1);
       ajouterEmployeToMedium(e2,v2);
       
       ajouterEmployeToMedium(e3,t1);
       ajouterEmployeToMedium(e3,t2);
       
       ajouterEmployeToMedium(e4,v1);
       ajouterEmployeToMedium(e4,v2);
       ajouterEmployeToMedium(e4,t1);
       ajouterEmployeToMedium(e4,t2);
       ajouterEmployeToMedium(e4,a1);
       ajouterEmployeToMedium(e4,a2);
       
       //des clients
       Inscription("Jean", "Moulin", "Monsieur", "31", "03", "1991", "jm@mail.com", "0782635917", "mdp", "10 rue des peupliers");
       Inscription("Syvain","Durieux","Monsieur","15","04","1972","sd@monmail.com","0392731965","mdp","35 rue des acacias");
       Inscription("Ghislaine","Bernard","Madame","24","02","1982","bg@monmail.com","0583691256","mdp","27 rue des Mimosas");
   
       //créer des sessions
       List<Client> lc = new LinkedList<Client>();
       JpaUtil.creerEntityManager();
       lc = ClientDAO.findAllClient();
       JpaUtil.fermerEntityManager();
       for(int n = 0; n < 4; n++){
           traiterDemandeDeVoyance(lc.get(n),a1);
           demarrerSession(lc.get(n),e1,a1);
           cloturerSession(lc.get(n),e1,a1,"ras");
           
           traiterDemandeDeVoyance(lc.get(n),v2);
           demarrerSession(lc.get(n),e2,v2);
           cloturerSession(lc.get(n),e2,v2,"ras");
           
           traiterDemandeDeVoyance(lc.get(n),t2);
           demarrerSession(lc.get(n),e3,t2);
           cloturerSession(lc.get(n),e3,t2,"ras");
       }
       //findMediumById();
       
   }
    //methodes find
    
   public static Employe creerEmploye(String nom, String prenom, String mdp){
       /*List<Medium> lm = new LinkedList<Medium>();
       lm.add(new Medium());
       lm.clear();
       creerEmploye(nom,prenom,mdp, lm);*/
       Employe e = new Employe(nom, prenom, mdp);
       
       JpaUtil.creerEntityManager();
       JpaUtil.ouvrirTransaction();
        
       EmployeDAO.creerEmploye(e);
        
       JpaUtil.validerTransaction();
       JpaUtil.fermerEntityManager();
       
       return e;
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
   
   public static void ajouterEmployeToMedium(Employe e, Medium m){
       JpaUtil.creerEntityManager();
       JpaUtil.ouvrirTransaction();
       m.addEmploye(e);
       MediumDAO.updateMedium(m);
       JpaUtil.validerTransaction();
       JpaUtil.fermerEntityManager();
   }
   
   //a retirer
   public static void creerSession(Client c, Employe e, Medium m, Date debut, Date fin, String commentaire){
       Session s  = new Session(debut,fin,commentaire,c,e,m);
       JpaUtil.creerEntityManager();
       JpaUtil.ouvrirTransaction();
       SessionDAO.creerSession(s);
       JpaUtil.validerTransaction();
       JpaUtil.fermerEntityManager();
   }
   
   public static Client findClientById(int id){
        JpaUtil.creerEntityManager();
        Client c = ClientDAO.findClientById(id);
        JpaUtil.fermerEntityManager();
        return c;
    }
   
   //a retirer
   public static List<Session> findAllClientSession(int id){
       JpaUtil.creerEntityManager();
       List<Session> ls = SessionDAO.findSessionByClientId(id);
       JpaUtil.fermerEntityManager();
       return ls;
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
    
    public static Medium findMediumById(int id){
        JpaUtil.creerEntityManager();
        Medium m = MediumDAO.findMediumById(id);
        JpaUtil.fermerEntityManager();
        return m;
    }
    
    public static List<Employe> findAllEmploye(){
        JpaUtil.creerEntityManager();
        List<Employe> le = EmployeDAO.findAllEmploye();
        JpaUtil.fermerEntityManager();
        return le;
    }
    
    public static Employe findEmployeById(int id){
        JpaUtil.creerEntityManager();
        Employe e = EmployeDAO.findEmployeById(id);
        JpaUtil.fermerEntityManager();
        return e;
    }
    
    public static List<Medium> findAllVoyantAsMedium(){
        /*JpaUtil.creerEntityManager();
        List<Medium> lvm = MediumDAO.findAllVoyantAsMedium();
        JpaUtil.fermerEntityManager();*/
        List<Voyant> lv = findAllVoyant();
        List<Medium> lvm = new LinkedList<Medium>();
        for(int i = 0; i < lv.size(); ++i){
            lvm.add((Medium)lv.get(i));
        }
        return lvm;
    }
    
    public static List<Medium> findAllTaroAsMedium(){
        /*JpaUtil.creerEntityManager();
        List<Medium> ltm = MediumDAO.findAllTaroAsMedium();
        JpaUtil.fermerEntityManager();*/
        List<Tarologue> lt = findAllTaro();
        List<Medium> ltm = new LinkedList<Medium>();
        for(int i = 0; i < lt.size(); ++i){
            ltm.add((Medium)lt.get(i));
        }
        return ltm;
    }
    
    public static List<Medium> findAllAstroAsMedium(){
        /*JpaUtil.creerEntityManager();
        List<Medium> lam = MediumDAO.findAllAstroAsMedium();
        JpaUtil.fermerEntityManager();*/
        List<Astrologue> la = findAllAstro();
        List<Medium> lam = new LinkedList<Medium>();
        for(int i = 0; i < la.size(); ++i){
            lam.add((Medium)la.get(i));
        }
        return lam;
    }
    
    public static void reinitDispoEmploye(){
        List<Employe> le = new LinkedList<Employe>();
        le = Service.findAllEmploye();
        for(int i = 0; i < le.size(); i++){
            //System.out.println(le.get(i).getNom()+" "+i);
            JpaUtil.creerEntityManager();
            JpaUtil.ouvrirTransaction();
            le.get(i).setDisponibilite(true);
            EmployeDAO.updateEmploye(le.get(i));
            JpaUtil.validerTransaction();
            JpaUtil.fermerEntityManager();
        }
    }
    
    //statistiques
    //nb de voyances/med et /emp
    // % / emp des sessions
    
    public static int getAffectations(Employe e){
        return e.getAffectations();
    }
    
    public static long getNBSessionByMediumId(Medium m){
        JpaUtil.creerEntityManager();
        long count = SessionDAO.getNBSessionByMediumId(m);
        JpaUtil.fermerEntityManager();
        return count;
    }
    
    public static long getPourcentageSessionEmploye(Employe e){
        JpaUtil.creerEntityManager();
        long total = SessionDAO.getTotalSession();
        JpaUtil.fermerEntityManager();
        if(total == 0) return 0;
        long percentage = e.getAffectations()/total;
        return percentage;
    }
    
    public static List<String> genererPredictions(Client c, int a, int s, int t) throws IOException{
        JpaUtil.creerEntityManager();
        ProfilAstrologique pa = c.getProfil();
        JpaUtil.fermerEntityManager();
        AstroTest at = new AstroTest("ASTRO-02-M0lGLURBU0ktQVNUUk8tQjAy");
        return at.getPredictions(pa.getCouleur(), pa.getAnimal(), a, s ,t);
    }
    
    public static List<Session> getHistorique(Client c){
        JpaUtil.creerEntityManager();
        List<Session> ls = SessionDAO.findAllSessionClient(c);
        JpaUtil.fermerEntityManager();
        return ls;
    }
}
