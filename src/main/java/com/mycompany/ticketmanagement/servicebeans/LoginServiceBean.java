/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ticketmanagement.servicebeans;

import com.mycompany.ticketmanagement.beans.LoginBean;
import com.mycompany.ticketmanagement.dao.impl.LoginDAOImpl;
import com.mycompany.ticketmanagement.entities.LoginInfo;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

/**
 *
 * @author juanf_000
 */
@ManagedBean
@ApplicationScoped
public class LoginServiceBean {

    private LoginDAOImpl ldi;
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
    
   /* public String outcomeAdmin(){
        
        return "admin-app-main";
    }
    
    public String outcomeUser(){
        
        return "app-main";
    }*/
    
}
