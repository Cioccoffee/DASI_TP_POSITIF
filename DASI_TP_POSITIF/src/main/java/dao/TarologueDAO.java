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
    
    public static List<Tarologue> findAllTaro()
    {
        TypedQuery<Tarologue> query = JpaUtil.obtenirEntityManager().createQuery(
        "SELECT t FROM Tarologue t", 
                Tarologue.class);
        return query.getResultList();
    }
}
