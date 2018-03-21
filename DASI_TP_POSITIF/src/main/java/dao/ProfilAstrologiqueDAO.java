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
    
}
