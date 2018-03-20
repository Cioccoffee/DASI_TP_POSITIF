/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import ModeleDuDomaine.Client;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;


public class ClientDAO {
    
     public static void creerClient(Client c)
    {
        try {
               JpaUtil.obtenirEntityManager().persist(c);
            } catch (Exception ex) {
                Logger.getLogger(ClientDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        
    }
     
     public static void updateClient(Client c)
    {
        JpaUtil.obtenirEntityManager().merge(c);
    }
     
    public static Client findClientByName(String name, String fname)
    {
        TypedQuery<Client> query = JpaUtil.obtenirEntityManager().createQuery(
        "SELECT c FROM Client c WHERE c.name = :name AND c.firstname = :fname", 
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
        "SELECT c FROM Client c WHERE c.mail = :mail", 
                Client.class);
        query.setParameter("mail", mail);
        try{
            return query.getSingleResult();
        }
        catch(NoResultException e){
           return null; 
        }
        
    }
    
    public static List<Client> findAllClient(){
        TypedQuery<Client> query = JpaUtil.obtenirEntityManager().createQuery(
        "SELECT c FROM Client c", 
                Client.class);
        return query.getResultList();
    }
    
//    public static int getHighestID(){
//        Query query = JpaUtil.obtenirEntityManager().createQuery(
//        "SELECT max(d) FROM Client");
//        return (int) query.getSingleResult();
//    }
}
