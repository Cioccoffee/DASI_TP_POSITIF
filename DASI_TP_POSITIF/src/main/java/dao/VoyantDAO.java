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
    
    public static List<Voyant> findAllVoyant()
    {
        TypedQuery<Voyant> query = JpaUtil.obtenirEntityManager().createQuery(
        "SELECT v FROM Voyant v", 
                Voyant.class);
        return query.getResultList();
    }
}
