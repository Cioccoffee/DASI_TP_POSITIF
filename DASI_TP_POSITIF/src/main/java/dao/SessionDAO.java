package dao;

import ModeleDuDomaine.Client;
import ModeleDuDomaine.Employe;
import ModeleDuDomaine.Medium;
import ModeleDuDomaine.Session;
import java.util.Date;
import java.util.List;
import javax.persistence.TypedQuery;

public class SessionDAO {
    
    public static void creerSession(Session e)
    {
        JpaUtil.obtenirEntityManager().persist(e);
    }
     
     public static void updateSession(Session e)
    {
        JpaUtil.obtenirEntityManager().merge(e);
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
    
    public static long getNBSessionByMediumId(Medium m){
        TypedQuery<Long> query = JpaUtil.obtenirEntityManager().createQuery(
        "SELECT count(s) FROM Session s WHERE s.medium=:m", 
                Long.class);
        query.setParameter("m", m);
        return query.getSingleResult();
    }
    
    public static long getTotalSession(){
        TypedQuery<Long> query = JpaUtil.obtenirEntityManager().createQuery(
        "SELECT count(s) FROM Session s", 
                Long.class);
        return query.getSingleResult();
    }
    
    public static Session findLastSessionClient(Client client){
        TypedQuery<Session> query = JpaUtil.obtenirEntityManager().createQuery(
        "SELECT c FROM Session c WHERE c.client = :client AND c.debut = (SELECT max(c.debut) FROM Session c WHERE c.client = :client)", 
                Session.class);
        query.setParameter("client", client);
        Session s = query.getSingleResult();
        if(s==null){
             query = JpaUtil.obtenirEntityManager().createQuery(
        "SELECT c FROM Session c WHERE c.client = :client AND c.debut = (SELECT max(c.debut) FROM Session c)", 
                Session.class);
            query.setParameter("client", client);
            return query.getSingleResult();
        }
        else return s; 
    }
    
    public static List<Session> findAllSessionClient(Client client){
        TypedQuery<Session> query = JpaUtil.obtenirEntityManager().createQuery(
        "SELECT c FROM Session c WHERE c.client = :client", 
                Session.class);
        query.setParameter("client", client);
       return query.getResultList();

    }
}
