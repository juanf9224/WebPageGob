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
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author juanf_000
 */
public class UserDAOImpl implements UserDAO {

    @PersistenceContext(unitName = "gob_GobernacionStoDgo_war_1.0-SNAPSHOTPU")
    private EntityManagerFactory emf;
    private EntityManager em;
    private EntityTransaction tx;

    @Override
    public UserInfo create(UserInfo t) {
        em = emf.createEntityManager();
        tx = em.getTransaction();
        try {

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

    @Override
    public UserInfo update(UserInfo t) {
        em = emf.createEntityManager();
        tx = em.getTransaction();
        try {

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

        } catch (Exception e) {
            e.toString();
            return null;
        }
    }

    @Override
    public UserInfo delete(UserInfo t) {
        em = emf.createEntityManager();
        tx = em.getTransaction();
        try {
            UserInfo u = em.find(UserInfo.class, t.getUserInfoId());

            tx.begin();
            em.remove(u);
            tx.commit();

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
