/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import ModeleDuDomaine.Medium;
import ModeleDuDomaine.Astrologue;
import javax.persistence.TypedQuery;
import java.util.List;

public class AstrologueDAO {
    
    public static void creerAstrologue(Astrologue e)
    {
        JpaUtil.obtenirEntityManager().persist(e);
    }
     
     public static void updateAstrologue(Astrologue e)
    {
        JpaUtil.obtenirEntityManager().merge(e);
    }
     
    public static Astrologue findAstroByName(String name, String fname)
    {
        TypedQuery<Astrologue> query = JpaUtil.obtenirEntityManager().createQuery(
        "SELECT * FROM Astrologue c WHERE c.name = :name AND c.firstname = :fname", 
                Astrologue.class);
        query.setParameter("name", name);
        query.setParameter("fname", fname);
        return query.getSingleResult();
    }
    
    public static List<Astrologue> findAllAstro()
    {
        TypedQuery<Astrologue> query = JpaUtil.obtenirEntityManager().createQuery(
        "SELECT * FROM Astrologue", 
                Astrologue.class);
        return query.getResultList();
    }
}
