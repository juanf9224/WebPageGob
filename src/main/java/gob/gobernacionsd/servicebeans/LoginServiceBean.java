/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.gobernacionsd.servicebeans;

import gob.gobernacionsd.dao.impl.LoginDAOImpl;
import gob.gobernacionsd.entities.LoginInfo;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author juanf_000
 */
@Named
@ApplicationScoped
public class LoginServiceBean {
    
    @Inject LoginDAOImpl ldi;
    /**
     * Creates a new instance of LoginServiceBean
     */
    
    public LoginServiceBean() {
    }
    
    public LoginServiceBean(LoginDAOImpl ldi){
        this.ldi = ldi;
    }
    
    public String validateUser(LoginInfo li, String username, String pwd){
        
       return ldi.validateUser(li, username, pwd);
    }
    
}
