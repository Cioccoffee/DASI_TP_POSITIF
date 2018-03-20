/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import ModeleDuDomaine.Employe;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public class EmployeDAO {
    public static void creerEmploye(Employe e)
    {
        JpaUtil.obtenirEntityManager().persist(e);
    }
     
     public static void updateEmploye(Employe e)
    {
        JpaUtil.obtenirEntityManager().merge(e);
    }
     
    public static Employe findEmployeById(int id){
        return JpaUtil.obtenirEntityManager().find(Employe.class ,id);
    }
    public static Employe findEmployeByName(String nom, String prenom)
    {
        TypedQuery<Employe> query = JpaUtil.obtenirEntityManager().createQuery(
        "SELECT e FROM Employe e WHERE e.nom = :nom AND e.prenom = :prenom", 
                Employe.class);
        query.setParameter("nom", nom);
        query.setParameter("prenom", prenom);
        return query.getSingleResult();
    }
    
    public static List<Employe> findAllEmploye()
    {
        TypedQuery<Employe> query = JpaUtil.obtenirEntityManager().createQuery(
        "SELECT e FROM Employe e", 
                Employe.class);
        return query.getResultList();
    }
    
    public static int getMaxAffectations(){
        TypedQuery<Integer> query = JpaUtil.obtenirEntityManager().createQuery("SELECT MAX(e.affectations) FROM Employe e",Integer.class);
        return query.getSingleResult();
    }
}
