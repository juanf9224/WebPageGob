/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.gobernacionsd.dao.impl;

import gob.gobernacionsd.dao.UserDAO;
import gob.gobernacionsd.entities.Department;
import gob.gobernacionsd.entities.UserInfo;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author juanf_000
 */
@ApplicationScoped
public class UserDAOImpl implements UserDAO, Serializable{

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("gobernacion_sd_unit");
    private EntityManager em = emf.createEntityManager();
    private EntityTransaction tx = em.getTransaction();

    @Override
    public UserInfo create(UserInfo t) {
        
        try {

            UserInfo ui = new UserInfo();
            long dept = findDept(t.getDepartmentID().getDepartment());
            Department department = em.find(Department.class, dept);
            ui.setName(t.getName());
            ui.setLastName(t.getLastName());
            ui.setAge(t.getAge());
            ui.setDepartmentID(department);
            ui.setDateCreated(new Date());
            ui.setLoginInfo1(t.getLoginInfo1());

            tx.begin();
            em.persist(ui);
            em.flush();
            tx.commit();
            return ui;
        } catch (Exception e) {
            e.toString();
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

    }

    public long findDept(String department) {
        try {

            long id = (long) em.createNamedQuery("Department.findByDepartment").setParameter("department", department).getSingleResult();

            return id;

        } catch (Exception e) {
            e.toString();
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }
    

    @Override
    public UserInfo retreive(UserInfo t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public UserInfo update(UserInfo p) {
        try {
            UserInfo ui = em.find(UserInfo.class, p.getUserInfoId());
            tx.begin();
            ui.setAge(p.getAge());
            ui.setName(p.getName());
            ui.setLoginInfo1(p.getLoginInfo1());
            ui.setDepartmentID(p.getDepartmentID());
            tx.commit();
            return ui;

        } catch (Exception e) {
            e.toString();
            return null;
        }
    }

    @Override
    public UserInfo delete(UserInfo t) {
        try {
            UserInfo u = em.find(UserInfo.class, t.getUserInfoId());
            tx.begin();
            em.remove(u);
            em.flush();
            tx.commit();

            return u;
        } catch (Exception e) {
            System.out.println(e.toString());
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }

    @Override
    public List<UserInfo> findAll() {
        try {
            List<UserInfo> all = null;
            TypedQuery<UserInfo> tq = em.createNamedQuery("UserInfo.findAll", UserInfo.class);
            all = tq.getResultList();
            return all;
        } catch (Exception e) {
            e.toString();
            return null;
        }
    }

    @Override
    public List<Department> findDepartment() {
        try {
            List<Department> findDept = null;

            TypedQuery<Department> tq = em.createNamedQuery("Department.findAll", Department.class);
            findDept = tq.getResultList();

            return findDept;

        } catch (Exception e) {
            e.toString();
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }

}
