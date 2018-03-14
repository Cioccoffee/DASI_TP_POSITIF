/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IHM;

import dao.JpaUtil;
import service.Service;

/**
 
 * @author vlezaud
 */
public class main {
    
    //création persistence unit
    
    public static void main(String[] args)
    {
        JpaUtil.init();
    
        Service.Inscription("Roger", "Lamy", "Monsieur", 
            2, 2, 2, "vic@fjzk.com", "0446432121", 
            "mdp", "ugviueg");
        
        //Service.Connexion(mail, mdp)
        
        
        
        
        
        
        
        JpaUtil.destroy();
    }
    
    
}
