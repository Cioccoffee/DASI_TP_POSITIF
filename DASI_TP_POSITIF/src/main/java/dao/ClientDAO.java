/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import ModeleDuDomaine.Client;
import javax.persistence.TypedQuery;


public class ClientDAO {
    
     public static void creerClient(Client c)
    {
        JpaUtil.obtenirEntityManager().persist(c);
    }
     
     public static void updateClient(Client c)
    {
        JpaUtil.obtenirEntityManager().merge(c);
    }
     
    public static Client findClientByName(String name, String fname)
    {
        TypedQuery<Client> query = JpaUtil.obtenirEntityManager().createQuery(
        "SELECT * FROM Client c WHERE c.name = :name AND c.firstname = :fname", 
                Client.class);
        query.setParameter("name", name);
        query.setParameter("fname", fname);
        return query.getSingleResult();
    }
    
    public static Client findClientById(int id)
    {
        return JpaUtil.obtenirEntityManager().find(Client.class ,id);
    }
    
    public static Client findClientByMail(String mail)
    {
        TypedQuery<Client> query = JpaUtil.obtenirEntityManager().createQuery(
        "SELECT * FROM Client c WHERE c.mail = :mail", 
                Client.class);
        query.setParameter("mail", mail);
        return query.getSingleResult();
    }
}
