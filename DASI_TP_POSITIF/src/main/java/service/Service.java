/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import ModeleDuDomaine.Client;
import dao.ClientDAO;
import java.util.Date;

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
    
    public long Inscription(String prenom, String nom, String civilite, 
            int jour, int mois, int annee, String mail, String telephone, 
            String mdp, String adresse)
    {
        
        //check unique mail
        if(VerifierUniciteMail(mail)){
            int id = ClientDAO.getHighestID()+1;
            Date naissance = new Date(); //init correctement
            int tel = Integer.parseInt(telephone);
            Client c = new Client(civilite, nom, prenom, adresse, mail, mdp, id, tel, naissance);
            return 0;
        }
        else
            return -1;
            
        
    }
    
    public boolean VerifierUniciteMail(String mail){
        Client c = ClientDAO.findClientByMail(mail);
        if(c!=null)
        
            return false;
        
        else
            return true; 
    }
}
