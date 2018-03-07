/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import ModeleDuDomaine.Client;

/**
 *
 * @author vlezaud
 */
public class ClientDAO {
    
     public static void creerClient(Client c)
    {
        JpaUtil.obtenirEntityManager().persist(c);
    }
     
     public static void updateClient(Client c)
    {
        JpaUtil.obtenirEntityManager().merge(c);
    }
}
