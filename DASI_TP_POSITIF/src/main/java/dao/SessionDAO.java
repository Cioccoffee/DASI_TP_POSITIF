/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import ModeleDuDomaine.Employe;
import ModeleDuDomaine.Medium;
import ModeleDuDomaine.Session;
import java.util.Date;
import java.util.List;
import javax.persistence.TypedQuery;

/**
 *
 * @author vlezaud
 */
public class SessionDAO {
    
    public static void creerSession(Session e)
    {
        JpaUtil.obtenirEntityManager().persist(e);
    }
     
     public static void updateSession(Session e)
    {
        JpaUtil.obtenirEntityManager().merge(e);
    }
     
    public static List<Session> findSessionByClientId(int id)
    {
        TypedQuery<Session> query = JpaUtil.obtenirEntityManager().createQuery(
        "SELECT c FROM Session c WHERE c.client = :id", 
                Session.class);
        query.setParameter("id", id);
        return query.getResultList();
    }
    
    public static List<Session> findSessionByDate(Date d)
    {
        TypedQuery<Session> query = JpaUtil.obtenirEntityManager().createQuery(
        "SELECT c FROM Session c WHERE c.debut = :deb", 
                Session.class);
        query.setParameter("deb", d);
        // un peu plus complexe = ici date sans heure
        // ATTENTION !!!!!!!!!!!
        return query.getResultList();
    }
    
    public static List<Session> findSessionByMedium(int id)
    {
        TypedQuery<Session> query = JpaUtil.obtenirEntityManager().createQuery(
        "SELECT c FROM Session c WHERE c.medium = :id", 
                Session.class);
        query.setParameter("id", id);
        return query.getResultList();
    }
    
    public static List<Session> findSessionByEmploye(Employe e)
    {
        TypedQuery<Session> query = JpaUtil.obtenirEntityManager().createQuery(
        "SELECT c FROM Session c WHERE c.employe = :e", 
                Session.class);
        query.setParameter("e", e);
        return query.getResultList();
    }
    
//    public static Session findSessionByClientId(int id)
//    {
//        TypedQuery<Session> query = JpaUtil.obtenirEntityManager().createQuery(
//        "SELECT * FROM Session c WHERE c.client = :id AND c.firstname = :fname", 
//                Session.class);
//        query.setParameter("id", id);
//        query.setParameter("fname", fname);
//        return query.getSingleResult();
//    }
    
    public static List<Session> findAllSession()
    {
        TypedQuery<Session> query = JpaUtil.obtenirEntityManager().createQuery(
        "SELECT s FROM Session s", 
                Session.class);
        return query.getResultList();
    }
    
    public static List<Integer> getNBSessionByEmploye(){
        TypedQuery<Integer> query = JpaUtil.obtenirEntityManager().createQuery(
        "SELECT count(s) FROM Session s GROUP BY Employe", 
                Integer.class);
        return query.getResultList();
    }
    
    public static List<Integer> getNBSessionByMedium(){
        TypedQuery<Integer> query = JpaUtil.obtenirEntityManager().createQuery(
        "SELECT count(s) FROM Session s GROUP BY Medium", 
                Integer.class);
        return query.getResultList();
    }
    
    public static int getNBSessionByMediumId(int id){
        TypedQuery<Integer> query = JpaUtil.obtenirEntityManager().createQuery(
        "SELECT count(s) FROM Session s WHERE s.medium=:id", 
                Integer.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }
}
