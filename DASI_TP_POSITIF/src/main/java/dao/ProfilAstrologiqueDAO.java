/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import ModeleDuDomaine.ProfilAstrologique;


public class ProfilAstrologiqueDAO{
    
    public static void creerProfil(ProfilAstrologique p)
    {
        JpaUtil.obtenirEntityManager().persist(p);
    }
    
    public static void updateProfil(ProfilAstrologique p)
    {
        JpaUtil.obtenirEntityManager().merge(p);
    }
    
//    public static void obtenirProfil(Client c)
//    {
//        JpaUtil.obtenirEntityManager().merge(p);
//    }
    //normalement pas besoin, on fait juste un getProfil sur l'objet CLient récupéré
    
}
