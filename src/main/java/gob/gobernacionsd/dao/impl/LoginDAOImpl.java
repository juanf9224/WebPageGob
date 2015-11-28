/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.gobernacionsd.dao.impl;

import static gob.gobernacionsd.beans.LoginBean.USER_SESSION_KEY;
import gob.gobernacionsd.dao.LoginDAO;
import gob.gobernacionsd.entities.LoginInfo;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

/**
 *
 * @author juanf_000
 */
public class LoginDAOImpl implements LoginDAO{

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("gob_GobernacionStoDgo_war_1.0-SNAPSHOTPU");
    //@PersistenceContext(unitName = "gob_GobernacionStoDgo_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    public LoginInfo create(LoginInfo t) {

        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    @Override
    public LoginInfo retreive(LoginInfo t) {
        try {
            em = emf.createEntityManager();
            
            LoginInfo login = em.createNamedQuery("LoginInfo.findByUsernamePwdEmail", LoginInfo.class).setParameter("username", t.getUsername()).setParameter("pwd", t.getPwd()).getSingleResult();

            return login;

        } catch (NoResultException nre) {
            nre.getMessage();
            return null;
        }
    }

    public String validateUser(LoginInfo li, String username, String pwd) {
        FacesContext context = FacesContext.getCurrentInstance();
        LoginInfo login = retreive(li);

        if (login != null) {
            if (!login.getUsername().equals(username) && !login.getPwd().equals(pwd)) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Login Failed!",
                        "Username or  password specified is not correct.");
                context.addMessage(null, message);
                return null;
            }

            if (login.getRole().equals("admin")) {
                context.getExternalContext().getSessionMap().put(USER_SESSION_KEY, login);
                //String outcome = "admin-app-main";
                return "admin-app-main";
            } else {
                context.getExternalContext().getSessionMap().put(USER_SESSION_KEY, login);
                //String outcome = "app-main";
                return "app-main";
            }
        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Login Failed!",
                    "Username or password specified is not correct.");
            context.addMessage(null, message);
            return null;
        }
    }

    /* public String outcome(String outcome){
        
        if(outcome.equals("admin-app-main")){
            return lsb.outcomeAdmin();
        }else{
            return lsb.outcomeUser();
                }
    }*/
    @Override
    public LoginInfo update(LoginInfo t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LoginInfo delete(LoginInfo t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<LoginInfo> findAll() {

        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
