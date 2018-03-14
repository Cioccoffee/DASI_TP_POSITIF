/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import ModeleDuDomaine.Client;
import dao.ClientDAO;

/**
 *
 * @author vlezaud
 */
public class Service {
    
    /*
    @return : long = id client si ok ou -1 si problème
    @params : mail et mot de passe en String
    */
    public static long login(String mail, String mdp)
    {
        //chercher client
        Client c = ClientDAO.findClientByMail(mail);
        // check mdp = right one
        if(c!=null && c.getMotdepasse().equals(mdp))
        {
            return c.getId();
        }
        else
            return -1; 
    }
    
    
}
