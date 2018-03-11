/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import ModeleDuDomaine.Medium;
import java.util.List;
import javax.persistence.TypedQuery;


// pas sûr que cette classe soit utile, on le créera directment en fct de leur 
// spécialité

public class MediumDAO {
    
    public static void creerMedium(Medium e)
    {
        JpaUtil.obtenirEntityManager().persist(e);
    }
     
     public static void updateMedium(Medium e)
    {
        JpaUtil.obtenirEntityManager().merge(e);
    }
     
    public static Medium findMediumByName(String name, String fname)
    {
        TypedQuery<Medium> query = JpaUtil.obtenirEntityManager().createQuery(
        "SELECT * FROM Medium c WHERE c.name = :name AND c.firstname = :fname", 
                Medium.class);
        query.setParameter("name", name);
        query.setParameter("fname", fname);
        return query.getSingleResult();
    }
    
    public static List<Medium> findAllMedium(){
        TypedQuery<Medium> query = JpaUtil.obtenirEntityManager().createQuery(
        "SELECT * FROM Astrologue UNION SELECT * FROM Tarologue UNION SELECT * FROM Voyant", 
                Medium.class);
        return query.getResultList();
    }
    
    public static List<Medium> findAllMediumBySpeciality(String s)
    {
        
        if(s.equals("astrologue")){
            return AstrologueDAO.findAllAstro();
        } else if(s.equals("tarologue")){
            
        }else{
            
        }
        
    }
    
}
