/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ticketmanagement.servicebeans;

import com.mycompany.ticketmanagement.dao.impl.UserDAOImpl;
import com.mycompany.ticketmanagement.entities.Department;
import com.mycompany.ticketmanagement.entities.UserInfo;
import java.util.List;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author juanf_000
 */
@ManagedBean
@ApplicationScoped
public class UserServiceBean {

    UserDAOImpl udi;
    /**
     * Creates a new instance of UserServiceBean
     */
    
    
    public UserServiceBean(){}
    
    public UserServiceBean(UserDAOImpl udi) {
        this.udi = udi;
    }
    
    
    public UserInfo create(UserInfo ui){
        return udi.create(ui);
    }
    
    public List<Department> findDept(){
        return udi.findDepartment();
    }
    
    public List<UserInfo> findAll(){
        return udi.findAll();
    }
    
    public UserInfo update(UserInfo u){
        return udi.update(null);
    }
    
    public UserInfo delete(UserInfo u){
        
        return udi.delete(u);
    }
    
    
}
