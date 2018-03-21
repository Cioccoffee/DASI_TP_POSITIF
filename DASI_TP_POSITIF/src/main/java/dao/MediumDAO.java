package dao;

import ModeleDuDomaine.Medium;
import java.util.List;
import javax.persistence.TypedQuery;

public class MediumDAO {
    
    public static void creerMedium(Medium e)
    {
        JpaUtil.obtenirEntityManager().persist(e);
    }
     
     public static void updateMedium(Medium e)
    {
        JpaUtil.obtenirEntityManager().merge(e);
    }
     
    public static Medium findMediumByName(String nom)
    {
        TypedQuery<Medium> query = JpaUtil.obtenirEntityManager().createQuery(
        "SELECT m FROM Medium m WHERE m.nom = :nom", 
                Medium.class);
        query.setParameter("nom", nom);
        return query.getSingleResult();
    }
    
    public static List<Medium> findAllMedium(){
        TypedQuery<Medium> query = JpaUtil.obtenirEntityManager().createQuery(
        "SELECT m FROM Medium m", 
                Medium.class);
        return query.getResultList();
    }
    
    public static Medium findMediumById(int id){
        TypedQuery<Medium> query = JpaUtil.obtenirEntityManager().createQuery(
        "SELECT m FROM Medium m where m.id=:id", 
                Medium.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }
}
