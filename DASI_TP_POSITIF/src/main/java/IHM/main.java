/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IHM;


import ModeleDuDomaine.*;
import dao.JpaUtil;
import dao.MediumDAO;
import java.util.Date;
import java.util.List;
import service.Service;

public class main {
    
    //création persistence unit
    
    public static void main(String[] args)
    {
        JpaUtil.init();
        
        //Service.initDB();
//        Service.Inscription("Prenom", "nom"," civilite", "1", "1", "1", "mail", "telephone", "mdp", "adresse");
//       
//        
//        System.out.println(Service.Connexion("vic@fjzg.com", "md2"));
//        
//        Service.ModifyClient(1, "Gérard", "", "Mme", "", "", "", "", "", "", "");
//        
//        Service.creerMedium();
//        
//        List<Medium> lm = Service.findAllMedium();
//        for(int i = 0; i < lm.size(); i++) System.out.println(lm.get(i).getNom());
//        
//        List<Voyant> lv = Service.findAllVoyant();
//        for(int i = 0; i < lv.size(); i++) System.out.println("v : "+lv.get(i).getNom());
//        
//        List<Astrologue> la = Service.findAllAstro();
//        for(int i = 0; i < la.size(); i++) System.out.println("a : "+la.get(i).getNom());
//        
//        List<Tarologue> lt = Service.findAllTaro();
//        for(int i = 0; i < lt.size(); i++) System.out.println("t : "+lt.get(i).getNom());
;
        //Service.ajouterEmploye(new Employe("Nom","PNom","mdp"), Service.findAllMedium());

        //Service.Inscription("Jean", "Moulin", "Monsieur", "31", "03", "1991", "jm@mail.com", "0782635917", "mdp", "adresse de JM");
        Client c = Service.findClientById(101);
        JpaUtil.creerEntityManager();
        Medium m = MediumDAO.findMediumByName("Mme Irma");
        JpaUtil.fermerEntityManager();
        Service.findAllMedium();
        Employe e = Service.trouverEmployeDispo(m);
        //if(e!=null){
            //System.out.println("employe ID = " + e.getNom()+ " "+e.getId());
           //System.out.println(Service.traiterDemandeDeVoyance(c, m));
//            Date debut = new Date();
//            System.out.println("minutes : "+debut.getMinutes());
//            //Date fin = debut + 10000;
//            Service.creerSession(c, e, m, debut, new Date(), "commentaire");
//            
//        }else{
//            System.out.println("e est NULL :( ");
//        }
        
        Service.demarrerSession(c, e, m);
        Service.cloturerSession(c, e, m, "commentaire");
        

        List<Employe> le = Service.findAllEmploye();
        for(int i = 0; i < le.size(); ++i){
            Employe e2 = le.get(i);
            System.out.println(e2.getNom()+" "+e2.getPrenom());
            System.out.println("Nb de sessions réalisées : "+Service.getAffectations(e2));
            System.out.println("Pourcentage des sessions : "+Service.getPourcentageSessionEmploye(e2));
        }
        
        JpaUtil.destroy();
    }
    
    
}
