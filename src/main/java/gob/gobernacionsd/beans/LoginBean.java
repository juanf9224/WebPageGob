/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.gobernacionsd.beans;

import gob.gobernacionsd.dao.impl.LoginDAOImpl;
import gob.gobernacionsd.entities.LoginInfo;
import gob.gobernacionsd.servicebeans.LoginServiceBean;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

/**
 *
 * @author juanf_000
 */
@Named
@SessionScoped
public class LoginBean implements Serializable {

    private String email;
    private String username;
    private String pwd;
    private String role;
    @Inject LoginServiceBean lsb;
    
    public static final String USER_SESSION_KEY = "user";

    
    /**
     * Creates a new instance of LoginBean
     */
    public LoginBean() {
    }
    
    
    public LoginServiceBean getLsb() {
        return lsb;
    }

    public void setLsb(LoginServiceBean lsb) {
        this.lsb = lsb;
    }

   
    public String getUsername() {
        return username;
    }
    
    @PostConstruct
    public void init(){
        this.lsb = new LoginServiceBean(new LoginDAOImpl());
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
     public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    
    public String validateUser(){
        try{
        LoginInfo li = new LoginInfo();
        li.setUsername(username);
        li.setPwd(pwd);
        return lsb.validateUser(li, username, pwd);
        }catch(Exception e){
            e.getMessage();
            return null;
        }
    }
     
     public String logout() {
        HttpSession session = (HttpSession)
             FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "non-responsive-login";
        
    }
    
    
}
