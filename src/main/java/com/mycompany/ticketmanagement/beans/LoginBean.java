/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ticketmanagement.beans;

import com.mycompany.ticketmanagement.dao.impl.LoginDAOImpl;
import com.mycompany.ticketmanagement.entities.LoginInfo;
import com.mycompany.ticketmanagement.servicebeans.LoginServiceBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.security.MessageDigest;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author juanf_000
 */
@ManagedBean
@SessionScoped
public class LoginBean implements Serializable {

    private String username;
    private String pwd;
    private String role;
    private String name;
    @ManagedProperty("#{LoginServiceBean}")
    private LoginServiceBean lsb;
    
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
    
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String validateUser(){
        try{
        LoginInfo li = new LoginInfo();
        li.setUsername(username);
        li.setPwd(pwd);
        return lsb.validateUser(li, username, pwd);
        }catch(Exception e){
            e.toString();
            return null;
        }
    }
     
     public String logout() {
        HttpSession session = (HttpSession)
             FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "login";
        
    }
    
    
}
