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
    
//    public static List<Medium> findAllMediumBySpeciality(String s)
//    {
//        
//        if(s.equals("astrologue")){
//            return AstrologueDAO.findAllAstro();
//        } else if(s.equals("tarologue")){
//            return TarologueDAO.findAllTaro();
//        }else{
//            return VoyantDAO.findAllVoyant();
//        }
//        
//    }
    
    public static List<Medium> findAllAstroAsMedium(){
         TypedQuery<Medium> query = JpaUtil.obtenirEntityManager().createQuery(
        "SELECT m FROM Medium m where m.medium_type =:type", 
                Medium.class);
         query.setParameter("type", "Astrologue");
        return query.getResultList();
    }
    public static List<Medium> findAllTaroAsMedium(){
        TypedQuery<Medium> query = JpaUtil.obtenirEntityManager().createQuery(
        "SELECT m FROM Medium m where m.medium_type =:type", 
                Medium.class);
        query.setParameter("type", "Tarologue");
        return query.getResultList();
    }
    public static List<Medium> findAllVoyantAsMedium(){
        TypedQuery<Medium> query = JpaUtil.obtenirEntityManager().createQuery(
        "SELECT m FROM Medium m where m.medium_type =:type", 
                Medium.class);
        query.setParameter("type", "Voyant");
        return query.getResultList();
    }
}
