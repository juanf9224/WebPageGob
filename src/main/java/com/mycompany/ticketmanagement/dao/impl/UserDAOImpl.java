/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ticketmanagement.dao.impl;

import com.mycompany.ticketmanagement.dao.UserDAO;
import com.mycompany.ticketmanagement.entities.Department;
import com.mycompany.ticketmanagement.entities.LoginInfo;
import com.mycompany.ticketmanagement.entities.UserInfo;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author juanf_000
 */
public class UserDAOImpl implements UserDAO{
    
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.mycompany_TicketManagement_war_1.0-SNAPSHOTPU");
    EntityManager em = emf.createEntityManager();
    EntityTransaction tx = em.getTransaction();

    @Override
    public UserInfo create(UserInfo t) {
        
        try{
            
            UserInfo ui = new UserInfo();
            long dept = findDept(t.getDepartmentID().getDepartment());
            Department department = em.find(Department.class, dept);
            ui.setName(t.getName());
            ui.setEmail(t.getEmail());
            ui.setDepartmentID(department);
            ui.setDateCreated(new Date());
            ui.setLoginInfo1(t.getLoginInfo1());
            
            tx.begin();            
            em.persist(ui);
            tx.commit();
            
            return ui;
        }catch(Exception e){
            e.toString();
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        
        
    }
    
    public long findDept(String department){
        
        try{
            
            long id =(long) em.createNamedQuery("Department.findByDepartment").setParameter("department", department).getSingleResult();
            
            return id;
            
        }catch(Exception e){
            e.toString();
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public UserInfo retreive(UserInfo t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public UserInfo update(UserInfo t) {
        try{
            
            LoginInfo li = em.find(LoginInfo.class, t.getLoginInfo1().getLoginInfoId());
            li.setUsername(t.getLoginInfo1().getUsername());
            li.setPwd(t.getLoginInfo1().getPwd());
            Department d = em.find(Department.class, t.getDepartmentID().getDepartmentId());
            d.setDepartment(t.getDepartmentID().getDepartment());
            UserInfo ui = em.find(UserInfo.class, li);
            
            tx.begin();
            ui.setEmail(t.getEmail());
            ui.setName(t.getName());
            ui.setLoginInfo1(li);
            ui.setDepartmentID(d);
            tx.commit();
            
            return ui;
            
        }catch(Exception e){
            e.toString();
            return null;
        }
    }

    @Override
    public UserInfo delete(UserInfo t) {
        try{
            UserInfo u = em.find(UserInfo.class, t.getUserInfoId());
            
            tx.begin();
            em.remove(u);
            tx.commit();
            
            return u;
        }catch(Exception e){
            System.out.println(e.toString());
            throw new UnsupportedOperationException("Not supported yet.");
        } 
    }

    @Override
    public List<UserInfo> findAll() {
        try{
            List<UserInfo> all = null;
            TypedQuery<UserInfo> tq = em.createNamedQuery("UserInfo.findAll", UserInfo.class);
            all = tq.getResultList();
            return all;
        }catch(Exception e){
            e.toString();
            return null;
        }
    }
    
    @Override
    public List<Department> findDepartment(){
        
        try{
                List<Department> findDept = null;
            
                TypedQuery<Department> tq = em.createNamedQuery("Department.findAll", Department.class);
                findDept = tq.getResultList();
                
                return findDept;
    
            }catch(Exception e){
                e.toString();
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
    }
    
}
