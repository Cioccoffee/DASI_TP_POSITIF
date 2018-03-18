/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IHM;


import ModeleDuDomaine.Astrologue;
import ModeleDuDomaine.Employe;
import ModeleDuDomaine.Medium;
import ModeleDuDomaine.Tarologue;
import ModeleDuDomaine.Voyant;
import dao.JpaUtil;
import java.util.List;
import service.Service;

/**
 
 * @author vlezaud
 */
public class main {
    
    //création persistence unit
    
    public static void main(String[] args)
    {
        JpaUtil.init();
        
        Service.initDB();
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

        //Service.ajouterEmploye(new Employe("Nom","PNom","mdp"), Service.findAllMedium());

        JpaUtil.destroy();
    }
    
    
}
