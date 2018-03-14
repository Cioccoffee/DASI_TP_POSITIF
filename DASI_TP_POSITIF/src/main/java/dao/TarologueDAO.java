/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import ModeleDuDomaine.Tarologue;
import ModeleDuDomaine.Medium;
import java.util.List;
import javax.persistence.TypedQuery;


public class TarologueDAO {
    
    public static void creerTarologue(Tarologue e)
    {
        JpaUtil.obtenirEntityManager().persist(e);
    }
     
     public static void updateTarologue(Tarologue e)
    {
        JpaUtil.obtenirEntityManager().merge(e);
    }
     
    public static Tarologue findTaroByName(String name, String fname)
    {
        TypedQuery<Tarologue> query = JpaUtil.obtenirEntityManager().createQuery(
        "SELECT c FROM Tarologue c WHERE c.name = :name AND c.firstname = :fname", 
                Tarologue.class);
        query.setParameter("name", name);
        query.setParameter("fname", fname);
        return query.getSingleResult();
    }
    
    public static List<Tarologue> findAllTaro()
    {
        TypedQuery<Tarologue> query = JpaUtil.obtenirEntityManager().createQuery(
        "SELECT t FROM Tarologue t", 
                Tarologue.class);
        return query.getResultList();
    }
}
