/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.gobernacionsd.dao.impl;

import gob.gobernacionsd.dao.UserDAO;
import gob.gobernacionsd.entities.Department;
import gob.gobernacionsd.entities.LoginInfo;
import gob.gobernacionsd.entities.UserInfo;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

/**
 *
 * @author juanf_000
 */
public class UserDAOImpl implements UserDAO{

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("gob_GobernacionStoDgo_war_1.0-SNAPSHOTPU");
    //@PersistenceContext(unitName = "gob_GobernacionStoDgo_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Transactional
    @Override
    public UserInfo create(UserInfo t) {
        em = emf.createEntityManager();
        try {

            UserInfo ui = new UserInfo();
            long dept = findDept(t.getDepartmentID().getDepartment());
            Department department = em.find(Department.class, dept);
            ui.setName(t.getName());
            ui.setAge(t.getAge());
            ui.setDepartmentID(department);
            ui.setDateCreated(new Date());
            ui.setLoginInfo1(t.getLoginInfo1());

            em.persist(ui);

            return ui;
        } catch (Exception e) {
            e.toString();
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

    }

    public long findDept(String department) {
        em = emf.createEntityManager();
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

    @Transactional
    @Override
    public UserInfo update(UserInfo t) {
        em = emf.createEntityManager();
        try {

            LoginInfo li = em.find(LoginInfo.class, t.getLoginInfo1().getLoginInfoId());
            li.setUsername(t.getLoginInfo1().getUsername());
            li.setPwd(t.getLoginInfo1().getPwd());
            Department d = em.find(Department.class, t.getDepartmentID().getDepartmentId());
            d.setDepartment(t.getDepartmentID().getDepartment());
            UserInfo ui = em.find(UserInfo.class, li);

            ui.setAge(t.getAge());
            ui.setName(t.getName());
            ui.setLoginInfo1(li);
            ui.setDepartmentID(d);
            
            return ui;

        } catch (Exception e) {
            e.toString();
            return null;
        }
    }

    @Transactional
    @Override
    public UserInfo delete(UserInfo t) {
        em = emf.createEntityManager();
        try {
            UserInfo u = em.find(UserInfo.class, t.getUserInfoId());

            em.remove(u);

            return u;
        } catch (Exception e) {
            System.out.println(e.toString());
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }

    @Override
    public List<UserInfo> findAll() {
        em = emf.createEntityManager();
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
        em = emf.createEntityManager();
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
