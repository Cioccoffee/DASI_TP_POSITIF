/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import ModeleDuDomaine.Voyant;
import ModeleDuDomaine.Medium;
import java.util.List;
import javax.persistence.TypedQuery;


public class VoyantDAO {
    
    public static void creerVoyant(Voyant e)
    {
        JpaUtil.obtenirEntityManager().persist(e);
    }
     
     public static void updateVoyant(Voyant e)
    {
        JpaUtil.obtenirEntityManager().merge(e);
    }
     
    public static Voyant findVoyantByName(String name, String fname)
    {
        TypedQuery<Voyant> query = JpaUtil.obtenirEntityManager().createQuery(
        "SELECT c FROM Voyant c WHERE c.name = :name AND c.firstname = :fname", 
                Voyant.class);
        query.setParameter("name", name);
        query.setParameter("fname", fname);
        return query.getSingleResult();
    }
    
    public static List<Voyant> findAllVoyant()
    {
        TypedQuery<Voyant> query = JpaUtil.obtenirEntityManager().createQuery(
        "SELECT v FROM Voyant v", 
                Voyant.class);
        return query.getResultList();
    }
}
