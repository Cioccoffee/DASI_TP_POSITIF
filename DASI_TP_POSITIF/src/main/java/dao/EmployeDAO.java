/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import ModeleDuDomaine.Employe;
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
     
    public static Employe findEmployeByName(String name, String fname)
    {
        TypedQuery<Employe> query = JpaUtil.obtenirEntityManager().createQuery(
        "SELECT * FROM Employe c WHERE c.name = :name AND c.firstname = :fname", 
                Employe.class);
        query.setParameter("name", name);
        query.setParameter("fname", fname);
        return query.getSingleResult();
    }
    
    public static int getMaxAffectations(){
        TypedQuery<Integer> query = JpaUtil.obtenirEntityManager().createQuery("SELECT MAX(e.affectations) FROM Employe e",Integer.class);
        return query.getSingleResult();
    }
}
