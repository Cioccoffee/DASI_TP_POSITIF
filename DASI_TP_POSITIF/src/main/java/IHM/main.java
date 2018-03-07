/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IHM;

import dao.JpaUtil;

/**
 *
 * @author vlezaud
 */
public class main {
    
    //création persistence unit
    JpaUtil.init();
    
    
    JpaUtil.destroy();
    
}
