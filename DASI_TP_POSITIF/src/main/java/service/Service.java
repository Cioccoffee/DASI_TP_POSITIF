/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import ModeleDuDomaine.*;
import dao.*;
import java.util.Date;
import java.util.List;

/**
 *
 * @author vlezaud
 */
public class Service {
    
    /*
    @return : long = id client si ok ou -1 si problème
    @params : mail et mot de passe en String
    */
    public static long Connexion(String mail, String mdp)
    {
        //chercher client
        Client c = ClientDAO.findClientByMail(mail);
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
    
    // quitte à part faire de traitement préli, ils vont pas nous filer 
    // des int pour la Date hein
    public static long Inscription(String prenom, String nom, String civilite, 
            int jour, int mois, int annee, String mail, String telephone, 
            String mdp, String adresse)
    {
        
        //check unique mail
        if(VerifierUniciteMail(mail)){
            int id = ClientDAO.getHighestID()+1;
            Date naissance = new Date(); //init correctement
            int tel = Integer.parseInt(telephone);
            Client c = new Client(civilite, nom, prenom, adresse, mail, mdp, id, tel, naissance);
            return id;
        }
        else
            return -1;
            
        
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
            
        }
        if(!telephone.equalsIgnoreCase("")){
            
        }
        if(!adresse.equalsIgnoreCase("")){
            c.setAdresse(adresse);
        }
        ClientDAO.updateClient(c);
    }
    
    
    
    
    
    
    
    
    
    
    
    
    //methodes find
    
    public static Client findClientById(int id){
        return ClientDAO.findClientById(id);
    }
    
    public static List<Tarologue> findAllTaro(){
        return TarologueDAO.findAllTaro();
    }
    
    public static List<Astrologue> findAllAstro(){
        return AstrologueDAO.findAllAstro();
    }
    
    public static List<Voyant> findAllVoyanto(){
        return VoyantDAO.findAllVoyant();
    }
    
    
}
