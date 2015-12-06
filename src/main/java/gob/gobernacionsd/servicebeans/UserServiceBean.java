/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.gobernacionsd.servicebeans;

import gob.gobernacionsd.dao.impl.UserDAOImpl;
import gob.gobernacionsd.entities.Department;
import gob.gobernacionsd.entities.UserInfo;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

/**
 *
 * @author juanf_000
 */
@Named
@ApplicationScoped
public class UserServiceBean implements Serializable{

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
        return udi.update(u);
    }
    
    public UserInfo delete(UserInfo u){
        
        return udi.delete(u);
    }
    
    
}
